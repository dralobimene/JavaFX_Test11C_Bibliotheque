package application.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.model.Livre;
import application.util.Connexion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// class qui permet la MAJ d'1 livre
public class F01B_controller implements Initializable {

	// ================================================
	// variables
	
	// TRIPLET 02A: la variable
	// triplet 02B: la methode, fichier F01B_controller
	// triplet 02C: invocat° de la methode
	//				ds fichier: Main.java
	//				ds methode: init_F01B_MAJ_livre()
	private Main main;
	
	//
	private Stage F01B_MAJ_livre;
	
	//
	private Livre livre;
	
	// ================================================
	
	@FXML
	private AnchorPane AC;
	
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
	private Button btn_MAJ;
	
	@FXML
	private Button btn_annuler;
	
	@FXML
	private Label lbl_presente_AUTEUR_actuel;
	
	@FXML
	private Label lbl_presente_TITRE_actuel;
	
	@FXML
	private Label lbl_contient_AUTEUR_actuel;
	
	@FXML
	private Label lbl_contient_TITRE_actuel;
	
	// ================================================
	
	// CONSTRUCTEURS
	
	// 01
	public F01B_controller() { }
	
	// ================================================
	
	@Override
	public void initialize(URL url, ResourceBundle r_bdle) { }
	
	// ================================================
	
	// SETTER DE LA FENETRE PRINCIPALE
	// TRIPLET 02B: la méthode
	// triplet 02A: la variable: ds fichier F01B_controller
	// triplet 02C: invocat° de la methode:
	//				ds fichier Main.java
	//				methode: init_F01B_MAJ_livre()
	
	// invoquée depuis
	// fichier: Main.java
	// methode: init_F01B_MAJ_livre()
	public void setMain(Main p_main) {
		
		//
		this.main = p_main;
		
	}
	
	// ================================================

	// SETTER DE CETTE FENETRE
	// methode qui set cette fenetre
	// NOTE:
	// methode qui prend en parametre cette Stage
	// (variable declarée plus haut)
	// invoquée depuis
	// fichier: Main.java
	// methode: init_F01B_MAJ_livre()
	// obligat° de declarer cette methode si l'on veut
	// fermer la fenetre au clic btn
	public void set_F01B_MAJ_livre(Stage p_fenetre) {
		
		//
		this.F01B_MAJ_livre = p_fenetre;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.F01B_MAJ_livre.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    
			@Override 
		    public void handle(WindowEvent event) {
		    	
		    	System.out.println("Possibilité de programmer");
		    	System.out.println("des actions qd on ferme la");
		    	System.out.println("fenetre depuis le btn haut - droite");
		    	
		    } 
		});
		
	}
	
	// ================================================
	
	// methode qui:
	// 1] - check si 1 instance (ici livre)
	// a bien été sélectionnée
	// appelée depuis
	// fichier: Main.java
	// methode: init_F01B_MAJ_livre()
	// 2] - remplit les TxtF avec les infos du
	// livre sélectionné
	// ds le cas contraire, ne fait rien, aucune instruct°
	
	// INTERACTIONS ENTRE LES DIFFERENTS ELTS
	// 1]- déclarer 1 variable de type Livre
	// en haut de ce fichier
	// 2] - implémenter cette méthode (setLivre()
	// 3] - ds le fichier Main.java
	//		-> rajouter à la methode d'initialisat° de la
	//			fenetre 1 parametre de type Livre
	//		-> le controleur appele cette méthode (setLivre())
	//			avec le parametre "livre" (voir MARQUE 01)
	// 4] - ds le fichier F01_controller.java: (voir MARQUE 02)
	//		-> il y a la methode qd on clic sur le bouton
	//		-> "livres". Ds cette méthode nommée "editerLivres()"
	//		-> il y a 1 variable de type Livre qui prend cô
	//		-> valeur la Ligne sélectionée ds la TableView.
	//		-> cette valeur est ensuite transmise cô parametre
	//		-> a la methode qui initialise la fenetre d'edition
	public Livre setLivre(Livre p_livre) {
		
		//
		this.livre = p_livre;
		
		if(this.livre == null) {
			
			// System.out.println("Aucun livre n'a été sélectionné");
			
		} else {
			
			// System.out.println("1 livre a bien été sélectionné");
			
			// pr debug
			/*
			System.out.println("Le livre sélectionné pr edition");
			System.out.println("a les caracteristiques suivantes");
			System.out.println("ID: " + this.livre.getId_livre());
			System.out.println("Auteur: " + this.livre.getAuteur_livre());
			System.out.println("Titre: " + this.livre.getTitre_livre());
			*/
			
			// MAJ des Lbl puisque l'utilisateur
			// a séléctionné 1 livre pr en modifier
			// les infos
			lbl_contient_AUTEUR_actuel.setText(this.livre.getAuteur_livre());
			lbl_contient_TITRE_actuel.setText(this.livre.getTitre_livre());
		}
		
		return this.livre;
	}
	
	// ================================================
	
	// MAJ l'ergt du livre sélectionné ds la db
	@FXML
	private void MAJ() {
		
		System.out.println("fichier: F01B_controller.java");
		System.out.println("methode: MAJ()");
		
		// les variables
		livre = setLivre(livre);
		String erreur_msg = "";
		String update_query;
		Connection con;
		PreparedStatement p_stmt;
		
		// affichage console pr debug
		/*
		System.out.println("Le livre qui doit etre MAJ a les caracteristiques");
		System.out.println("suivantes:");		
		System.out.println("ID: " + livre.getId_livre());
		System.out.println("Auteur: " + livre.getAuteur_livre());
		System.out.println("Titre: " + livre.getTitre_livre());
		System.out.println("Ses caracteristiques deviennent:");
		System.out.println("ID (inchangé): " + livre.getId_livre());
		System.out.println("Auteur (changé): " + txtF_contient_AUTEUR.getText());
		System.out.println("Titre (changé): " + txtF_contient_TITRE.getText());
		*/
		
		// verifie que l'utilisateur a renseigné l'auteur
		if(txtF_contient_AUTEUR.getText() == null
				|| txtF_contient_AUTEUR.getText().length() == 0) {
			
			//
			erreur_msg += "Vs n'avez pas renseigné d'auteur pr ce livre\n";
			
		}
		
		// verifie que l'utilisateur a renseigné le titre
		if(txtF_contient_TITRE.getText() == null
				|| txtF_contient_TITRE.getText().length() == 0) {
			
			//
			erreur_msg += "Vs n'avez pas renseigné de titre pr ce livre\n";
			
		}
		
		// si pas d'erreur, MAJ de l'ergt
		if(erreur_msg.length() == 0) {
			
			//
			// System.out.println("Aucune erreur détectée: MAJ de l'ergt");
			
			// affichage console pr debug
			/*
			System.out.println("Le livre qui doit etre MAJ a les caracteristiques");
			System.out.println("suivantes:");		
			System.out.println("ID: " + livre.getId_livre());
			System.out.println("Auteur: " + livre.getAuteur_livre());
			System.out.println("Titre: " + livre.getTitre_livre());
			System.out.println("Ses caracteristiques deviennent:");
			System.out.println("ID (inchangé): " + livre.getId_livre());
			System.out.println("Auteur (changé): " + txtF_contient_AUTEUR.getText());
			System.out.println("Titre (changé): " + txtF_contient_TITRE.getText());
			*/
			
			// redact° de la requete de MAJ
			update_query = "UPDATE livre \n"
					+ "SET auteur = '"
					+ txtF_contient_AUTEUR.getText()
					+ "', titre = '"
					+ txtF_contient_TITRE.getText()
					+ "' WHERE id_livre = "
					+ livre.getId_livre()
					+ ";";
			
			// imprime la requete pr debug
			// System.out.println(update_query);
			
			// initialisat° de la variable de type Connection
			// obtenir la connexion grace a la class Connexion
			con = Connexion.getCon();
			
			try {
				
				// initialisat° de la variable de type PreparedStatement
				p_stmt = con.prepareStatement(update_query);
				
				// execute la requete
				p_stmt.executeUpdate();
				
				// ferme la connex° a la db
				con.close();
				
				// fermer la fenetre
				this.F01B_MAJ_livre.close();
				
			} catch(SQLException except) {
				
				//
				System.out.println("Exception");
				System.out.println("fichier: F01B_controller.java");
				System.out.println("methode: MAJ()");
				//Logger.getLogger(update_query);
				Logger.getLogger(F01B_controller.class.getName()).log(Level.SEVERE, null, except);
				
			}
		}
		// sinon affiche 1 boite de dialogue de type ALERT
		else {
			
			/*
			System.out.println("erreur détectée, Alert window");
			System.out.println(erreur_msg);
			*/
			
            Alert alert = new Alert(AlertType.ERROR);
            // n'a l'air d'avoir aucune difference sur le
            // comportement des fenetres
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(F01B_MAJ_livre);
            alert.setTitle("Erreur(s) détectée(s)");
            alert.setHeaderText("Veuillez renseigner les champs.");
            alert.setContentText(erreur_msg);

            alert.showAndWait();
			
		}
	}
	
	// ================================================
	
	@FXML
	private void annuler() {
		
		//
		this.F01B_MAJ_livre.close();
		
	}
	
	// ================================================
	
}
