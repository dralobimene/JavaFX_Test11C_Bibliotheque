package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class F03_emprunt_controller_creer_emprunt implements Initializable {
	
	// ======================================================
	
	// les variables
	
	//
	private Main main;
	
	//
	private Stage F03_creer_emprunt;
	
	// ======================================================
	
	@FXML
	private AnchorPane AP;
	
	@FXML
	private Pane P;
	
	// ======================================================
	
	// constructeur(s)
	
	public F03_emprunt_controller_creer_emprunt() { }
	
	// ======================================================
	
	@Override
	public void initialize(URL p_url, ResourceBundle p_R_bdle) { }
	
	// ======================================================
	
	public void set_main(Main p_main) {
		
		//
		this.main = p_main;
		
	}
	
	// ======================================================
	
	public void set_F03_creer_emprunt(Stage p_stage) {
		
		//
		this.F03_creer_emprunt = p_stage;
		
	}

}
