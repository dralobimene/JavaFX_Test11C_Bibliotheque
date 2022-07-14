package application.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.util.Connexion;
import application.model.Livre;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// class qui permet la creat° d'1 livre
public class F01A_controller implements Initializable {
	
	// ================================================
	// variables
	
	// declarat° facultative car
	// pas de methode setMain(). En effet
	// la fenetre qui permet la creat° de
	// livre n'a aucun rapport avec la FP
	// Main main;
	
	// TRIPLET 01A: la variable
	// triplet 01B: la methode, fichier F01A_controller
	// triplet 01C: invocat° de la methode
	//				ds fichier: Main.java
	//				ds methode: init_F01A_creer_livre()
	// private Main main;
	
	//
	private Stage F01A_creer_livre;
	
	// ================================================

	@FXML
	private AnchorPane AP;
	
	@FXML
	private Pane P;
	
	@FXML
	private Label lbl_presente_ID;
	
	@FXML
	private Label lbl_presente_AUTEUR;
	
	@FXML
	private Label lbl_presente_TITRE;
	
	@FXML
	private TextField txtF_contient_ID;
	
	@FXML
	private TextField txtF_contient_AUTEUR;
	
	@FXML
	private TextField txtF_contient_TITRE;
	
	@FXML
	private Button btn_inserer;
	
	@FXML
	private Button btn_annuler;
	
	// ================================================
	
	// CONSTRUCTEURS
	
	// 01
	public F01A_controller() { }
	
	// ================================================
	
	@Override
    public void initialize(URL url, ResourceBundle r_bdle) { }
	
	// ================================================
	
	// TRIPLET 01B: la méthode
	// triplet 01A: la variable: ds fichier F01A_controller
	// triplet 01C: invocat° de la methode:
	//				ds fichier Main.java
	//				methode: init_F01A_creer_livre()
	
	/*
	// PAS NECESSAIRE, la fenetre qui permet
	// la creat° de livre n'a aucun rapport avec
	// la FP
	// la déclarat° de la variable main est facultative
	
	// invoquée depuis
	// fichier: Main.java
	// methode: init_F01A_creer_livre()
	public void setMain(Main p_main) {
		
		//
		this.main = p_main;
		
	}
	*/
	
	// ================================================
	
	// methode qui set cette fenetre
	// NOTE:
	// methode qui prend en parametre cette Stage
	// (variable declarée plus haut)
	// invoquée depuis
	// fichier: Main.java
	// methode: init_F01A_creer_livre()
	// obligat° de declarer cette methode si l'on veut
	// fermer la fenetre au clic btn
	public void set_F01A_creer_livre(Stage p_fenetre) {
		
		//
		this.F01A_creer_livre = p_fenetre;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.F01A_creer_livre.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    
			@Override 
		    public void handle(WindowEvent event) {
		    	
		    	System.out.println("Possibilité de programmer");
		    	System.out.println("des actions qd on ferme la");
		    	System.out.println("fenetre depuis le btn heut - droite");
		    	
		    } 
		});
		
	}
	
	
	// ================================================
	
	// methode qd on appuie sur le bouton inserer
	// insere ds la db
	@FXML
	private void insererLivre() {
		
		//
		System.out.println("fichier: F01A_controller.java");
		System.out.println("methode: insererLivre()");
		
		// les variables
		Livre livre;
		String insert_query;
		Connection con;
		PreparedStatement p_st;
		
		// initialisat° de l'instance de type Livre
		// L'instance livre recupere cô attributs auteur et titre
		// ce que l'utilisateur a renseigné ds les TextFields
		// correspondants
		livre = new Livre(txtF_contient_AUTEUR.getText(),
							txtF_contient_TITRE.getText());
		
		// presentation ds la console pr debug
		/*
		System.out.println("Le livre pret a etre inséré ds la db");
		System.out.println("a les caracteristiques suivantes: ");
		System.out.println("ID: " + livre.getId_livre());
		System.out.println("Auteur: " + livre.getAuteur_livre());
		System.out.println("Titre: " + livre.getTitre_livre());
		*/
		
		// redact° de la requete d'insert°
		// on indique a la requete d'inserer id_livre, ms
		// on insere 1 valeur "null", l'autoincrement de
		// la tbl va se charger d'inserer la valeur correcte
		insert_query = "INSERT INTO livre ("
			    + " id_livre,"
			    + " auteur,"
			    + " titre ) VALUES ("
			    + "null, '"
			    + livre.getAuteur_livre()
			    + "', '"
			    + livre.getTitre_livre()
			    + "')";
		
		// déclarat°, initialisat° d'1 variable de type Connection
		// obtenir la connexion grace a la class Connexion
		con = Connexion.getCon();
		
		// imprimer la requete pr debug
		// System.out.println(insert_query);
		
		try {
			
			// initialisat° de la variable de type PreparedStatement
			// prepare la requete
			p_st = con.prepareStatement(insert_query);
			
			// execute la requete
			p_st.executeUpdate();
			
			// ferme la connexion a la db
			con.close();
			
			// ferme la fenetre
			this.F01A_creer_livre.close();
			
			
		} catch(SQLException except) {
			
			System.out.println("Exception:");
			System.out.println("fichier: F01A_controller.java");
			System.out.println("methode: insererLivre()");
			Logger.getLogger(F01A_controller.class.getName()).log(Level.SEVERE, null, except);
			
		}
	}
	
	// ================================================
	
	//
	@FXML
	private void annuler() {
		
		//
		this.F01A_creer_livre.close();
		
	}
	
	// ================================================
	
}
