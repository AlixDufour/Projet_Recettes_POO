package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.ScrollPane;

import dao.Dao;
import dao.RecetteDAO;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		RecetteDAO test = new RecetteDAO();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("./../scenes/MainScene.fxml"));
			Scene scene = new Scene(root,800,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
