package visitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.stream.Stream;

// from https://docs.oracle.com/javase/tutorial/essential/io/walk.html

public class PrintFilesDemo {

	public static void main(String[] args) {

		String startDir = "src";

		try {			
			System.out.println(" walkRecursive");
			walkRecursive(startDir);

			System.out.println("\n simpleJava8Walk");
			simpleJava8Walk(startDir);

			System.out.println("\n simpleJava8Find: *.txt");
			simpleJava8Find(startDir, "txt");

			System.out.println("\n simpleVisit");
			simpleVisit(startDir);

			System.out.println("\n simpleFindVisit:  *.java");
			simpleFindVisit(startDir, "*.java");

			System.out.println("\n simpleFindVisit:  *.txt");
			simpleFindVisit(startDir, "*.txt");


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void walkRecursive(String startingDir) {

		File root = new File( startingDir );
		File[] list = root.listFiles();

		if (list == null) return;

		for ( File f : list ) {
			if ( f.isDirectory() ) {
				walkRecursive( f.getAbsolutePath() );
				System.out.println( "Dir:" + f.getAbsoluteFile() );
			}
			else {
				System.out.println( "File:" + f.getAbsoluteFile() );
			}
		}
	}

	private static void simpleJava8Walk (String startingDir) throws IOException {

		Files.walk(Paths.get(startingDir))
		.filter(Files::isRegularFile)
		.forEach(System.out::println);
	}

	private static void simpleJava8Find (String startingDir, String findPattern) throws IOException {

		try (Stream<Path> stream = Files.find(Paths.get(startingDir), 
				100,
				(path, basicFileAttributes) -> {
					File file = path.toFile();
					return !file.isDirectory() &&
							file.getName().contains(findPattern);
		})){
			stream.forEach(System.out::println);
		};

	}

	private static void simpleVisit(String startingDir) throws IOException {

		Path startingPath = Paths.get(startingDir);
		PrintFiles printFiles = new PrintFiles();

		Files.walkFileTree(startingPath, printFiles);

	}

	private static void simpleFindVisit(String startingDir, String findPattern) throws IOException {

		Path startingPath = Paths.get(startingDir);

		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		Finder finder = new Finder(findPattern);

		Files.walkFileTree(startingPath, opts, Integer.MAX_VALUE, finder);

	}


}


