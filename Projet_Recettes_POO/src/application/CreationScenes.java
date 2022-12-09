package application;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CreationScenes {

	public static Scene creerMainScene(Modele model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/scenes/MainScene.fxml"));
		
		Parent root = loader.load();
		MainController mainController = loader.getController();
		mainController.setModele(model);
				
		Scene scene = new Scene(root,800,450);
		scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("bootstrap.css")).toString());
		
		return scene;
	}
	
	public static Scene creerChoixProfileScene(Modele model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/scenes/ChoisirProfil.fxml"));
		
		Parent root = loader.load();
		
		// A faire : Controller à récup pour appeler setModele comme au dessus
		ChoixProfileController controller = loader.getController();
		controller.setModele(model);

		Scene scene = new Scene(root,800,450);
		scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("bootstrap.css")).toString());
		
		return scene;
	}
	
	public static Scene creerProfileScene(Modele model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/scenes/Profil.fxml"));
		
		Parent root = loader.load();
		
		// A faire : Controller à récup pour appeler setModele comme au dessus
		ProfileController controller = loader.getController();
		controller.setModele(model);

		Scene scene = new Scene(root,800,450);
		scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("bootstrap.css")).toString());
		
		return scene;
	}
	
	public static Scene creerRecetteFormScene(Modele model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/scenes/RecetteForm.fxml"));
		
		Parent root = loader.load();
		
		// A faire : Controller à récup pour appeler setModele comme au dessus
		RecetteFormController controller = loader.getController();
		controller.setModele(model);

		Scene scene = new Scene(root,800,450);
		scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("bootstrap.css")).toString());
		
		return scene;
	}
	
	public static Scene creerRecetteScene(Modele model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/scenes/Recette.fxml"));
		
		Parent root = loader.load();
		
		// A faire : Controller à récup pour appeler setModele comme au dessus
		RecetteController controller = loader.getController();
		controller.setModele(model);

		Scene scene = new Scene(root,800,450);
		scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("bootstrap.css")).toString());
		
		return scene;
	}
	
	public static Scene creerFormulaireRecetteScene(Modele model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/scenes/RecetteForm.fxml"));
		
		Parent root = loader.load();
		
		// A faire : Controller à récup pour appeler setModele comme au dessus
		RecetteFormController controller = loader.getController();
		controller.setModele(model);

		Scene scene = new Scene(root,800,450);
		scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("bootstrap.css")).toString());
		
		return scene;
	}
	
}
