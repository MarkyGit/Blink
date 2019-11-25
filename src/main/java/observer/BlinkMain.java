package observer;

import java.time.Instant;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observer.app.BlinkApp;

public class BlinkMain extends Application {

	static Circle circle;

	@Override
	public void start(Stage primaryStage) {

		initFxDialog(primaryStage);
	

	}

	public static void main(String[] args) {
		System.out.println("Start main: " + Instant.now());
		startTimerActor();
		launch(args);
	}
	
	
	

	private static void startTimerActor() {

		Runnable blinkRunner = () -> {
			while (true) {
				BlinkApp.getBlinkController().toggle();
				System.out.println("led " + BlinkApp.getLed().isLedOn());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

				};
			}
		};
		Thread thread = new Thread(blinkRunner);
		thread.setDaemon(true);
		thread.start();
	}
	
	private static void initFxDialog(Stage stage) {
		circle = new Circle(220, 135, 25, Color.RED);
		Text text = new Text(150, 50, "Blink with Observer");
		text.setFont(Font.font(null, FontWeight.BOLD, 15));
		text.setFill(Color.CRIMSON);
		Group root = new Group(circle, text);
		Scene scene = new Scene(root, 500, 300);
		// Setting the fill color to the scene and the title
		scene.setFill(Color.LAVENDER);
		stage.setTitle("Event Filters Example");
		stage.setScene(scene);
		// Displaying the contents of the stage
		stage.show();		
	}
}
