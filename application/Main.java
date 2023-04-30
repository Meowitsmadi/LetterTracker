package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import sql.sqliteDemo;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Image icon = new Image("/images/logo.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Letter Tracker");
			sqliteDemo.PopulateInitialData();
			Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
