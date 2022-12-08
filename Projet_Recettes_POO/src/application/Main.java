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
		
		RecetteDAO recetteDao = new RecetteDAO();
		IngredientDAO ingrDao = new IngredientDAO();
		UstensileDAO ustDao = new UstensileDAO();
		ProfileDAO pDao = new ProfileDAO();
		Modele model = new Modele();
		NoteDAO notesDao = new NoteDAO();
		
		try {
			FXMLLoader test = new FXMLLoader();
			test.setLocation(Main.class.getResource("/scenes/MainScene.fxml"));
			
			Parent root = test.load();
			MainController mainController = test.getController();
			mainController.setModele(model);
					
			Scene scene = new Scene(root,800,450);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			for (Note n : notesDao.getAll()) {
				System.out.println(n);
			}
			
			System.out.println(notesDao.getNoteByIDs(2, 0));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
