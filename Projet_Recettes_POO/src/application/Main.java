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
import dao.IngredientDAO;
import dao.ProfileDAO;
import dao.RecetteDAO;
import dao.UstensileDAO;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		RecetteDAO recetteDao = new RecetteDAO();
		IngredientDAO ingrDao = new IngredientDAO();
		UstensileDAO ustDao = new UstensileDAO();
		ProfileDAO pDao = new ProfileDAO();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("./../scenes/MainScene.fxml"));
			Scene scene = new Scene(root,800,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			for(Recette r : recetteDao.getAll()) {
				System.out.println(r);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
