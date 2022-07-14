package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// la FP de l'applicat°, la fenetre d'accueil
public class Main_controller implements Initializable {

	// ================================================
	// variables
	
	// obligatoire pr ouvrir F01
	private Main main;
	
	//
	private Stage FP;
	
	// ================================================
	
	@FXML
	private AnchorPane ac;
	
	@FXML
	private Pane p;
	
	@FXML
	private Button btn_quitter;
	
	@FXML
	private Button btn_ouvrir_section_livre;
	
	@FXML
	private Button btn_ouvrir_section_emprunt;
	
	@FXML
	private Button btn_ouvrir_section_client;
	
	// ================================================
	
	// CONSTRUCTEURS
	
	// 01
	public Main_controller() { }
	
	// ================================================
	
	// GETTERS - SETTERS
	
	// btn ouvrir section client
	public Button getBtn_ouvrir_section_client() {
		return btn_ouvrir_section_client;
	}

	public void setBtn_ouvrir_section_client(Button btn_ouvrir_section_client) {
		this.btn_ouvrir_section_client = btn_ouvrir_section_client;
	}

	// ================================================
	
	@Override
	public void initialize(URL p_url, ResourceBundle p_r_bdle) { }
	
	// ================================================
	
	// invoquée depuis
	// fichier: Main.java
	// methode: initLFP()
	public void setMain(Main p_main) {
		
		//
		System.out.println("fichier: Main_controller.java");
		System.out.println("methode: setMain()");
		
		//
		this.main = p_main;
		
	}
	
	// ================================================
	
	//
	public void set_FP(Stage p_fenetre) {
		
		//
		System.out.println("fichier: Main_controller.java");
		System.out.println("methode: set_FP()");
		
		//
		this.FP = p_fenetre;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.FP.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override 
		    public void handle(WindowEvent event) {
		    	System.out.println("fermeture programme");
		        // event.consume();
		    	Platform.exit();
		        System.exit(0);
		    } 
		});
	}
	
	// ================================================
	
	@FXML
	private void quitter_appli() {
		
		//
		System.out.println("fichier: Main_controller.java");
		System.out.println("methode: quitter_appli()");
		
		//
		this.FP.close();
		
		//
		Platform.exit();
		System.exit(0);
		
	}
	
	// ================================================
	
	// ouvre la fenetre F01, la sect° livre
	@FXML
	private void ouvrirF01() {
		
		//
		System.out.println("fichier: Main_controller.java");
		System.out.println("methode: ouvrirF01()");
		
		//
		main.init_F01();
		
	}
	
	// ================================================
	
	// ouvre la fenetre F02, la sect° client
	@FXML
	private void ouvrir_section_client() {
		
		//
		System.out.println("fichier: Main_controller.java");
		System.out.println("methode: ouvrir_section_client()");
		
		//
		main.init_F02();
		
		// methode testIMPRESSION()
		// implémentée ds
		// fichier: Main.java
		// invoquée depuis ici,
		// la méthode est executée
		// main.testIMPRESSION();
		
	}
	
	// ================================================
	
	// ouvre la F03, la sect° emprunt
	@FXML
	private void ouvrir_section_emprunt() {
		
		//
		System.out.println("fichier: Main_controller.java");
		System.out.println("méthode: ouvrir_section_emprunt()");
		
		//
		main.init_F03();
	}
	
}
