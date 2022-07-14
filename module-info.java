module JavaFX_Test11A_Bibliotheque {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	requires java.sql;
	
	exports application;
	opens application.view to javafx.graphics, javafx.fxml;
	opens application.model to javafx.graphics, javafx.fxml, javafx.base;
}
