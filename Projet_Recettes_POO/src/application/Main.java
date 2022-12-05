package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import dao.Dao;
import dao.IngredientDAO;
import dao.RecetteDAO;
import dao.UstensileDAO;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		RecetteDAO recetteDao = new RecetteDAO();
		IngredientDAO ingrDao = new IngredientDAO();
		UstensileDAO ustDao = new UstensileDAO();
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			Ustensile u = new Ustensile("Casserole");
			ustDao.create(u);
			
			for(Ustensile r : ustDao.getAll()) {
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
