package application;
import dao.IngredientDAO;
import dao.NoteDAO;
import dao.ProfileDAO;
import dao.RecetteDAO;
import dao.UstensileDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		

		Modele model = new Modele(primaryStage);
		
		try {
			
			model.switchScene(CreationScenes.creerChoixProfileScene(model));
			
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
