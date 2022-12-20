module Projet_Recettes_POO {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
    opens application.Controllers to javafx.fxml, javafx.graphics;
	opens application.Datas to javafx.fxml, javafx.graphics;
}
