package application.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.model.Client;
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

public class F02B_controller implements Initializable {

	// ================================================
	// variables
	
	//
	private Main main;
	
	//
	private Stage F02B;
	
	//
	private Client client;
	
	// ================================================
	
	@FXML
	private AnchorPane AP;
	
	@FXML
	private Pane P;
	
	@FXML
	private Label lbl_presente_id;
	
	@FXML
	private Label lbl_presente_PRENOM;
	
	@FXML
	private Label lbl_presente_NOM;
	
	@FXML
	private Label lbl_presente_TELEPHONE;
	
	@FXML
	private Label lbl_presente_MAIL;
	
	@FXML
	private TextField txtF_contient_ID;
	
	@FXML
	private TextField txtF_contient_PRENOM;
	
	@FXML
	private TextField txtF_contient_NOM;
	
	@FXML
	private TextField txtF_contient_TELEPHONE;
	
	@FXML
	private TextField txtF_contient_MAIL;
	
	@FXML
	private Button btn_MAJ;
	
	@FXML
	private Button btn_annuler;
	
	@FXML
	private Label lbl_presente_actuel_prenom;
	
	@FXML
	private Label lbl_contient_actuel_prenom;
	
	@FXML
	private Label lbl_presente_actuel_nom;
	
	@FXML
	private Label lbl_contient_actuel_nom;
	
	@FXML
	private Label lbl_presente_actuel_telephone;
	
	@FXML
	private Label lbl_contient_actuel_telephone;
	
	@FXML
	private Label lbl_presente_actuel_mail;
	
	@FXML
	private Label lbl_contient_actuel_mail;
	
	// ================================================
	
	// CONSTRUCTEUR(S)
	
	// 01
	public F02B_controller() { }
	
	// ================================================
	
	//
	@Override
	public void initialize(URL p_url, ResourceBundle p_r_bdle) { }
	
	// ================================================
	
	public void setMain(Main p_main) {
		
		System.out.println("fichier: F02B_controller.java");
		System.out.println("methode: setMain()");
		
		//
		this.main = p_main;
		
	}
	
	// ================================================
	
	public void setF02B(Stage p_stage) {
		
		System.out.println("fichier: F02B_controller.java");
		System.out.println("methode: setF02B()");
		
		//
		this.F02B = p_stage;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.F02B.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    
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
	// 1] - check si 1 instance (ici client)
	// a bien été sélectionnée
	// appelée depuis
	// fichier: Main.java
	// methode: init_F02B()
	// 2] - remplit les TxtF avec les infos du
	// client sélectionné
	// ds le cas contraire, ne fait rien, aucune instruct°
	
	// INTERACTIONS ENTRE LES DIFFERENTS ELTS
	// 1]- déclarer 1 variable de type Client
	// en haut de ce fichier
	// 2] - implémenter cette méthode (setClient()
	// 3] - ds le fichier Main.java
	//		-> rajouter à la methode d'initialisat° de la
	//			fenetre 1 parametre de type Client
	//		-> le controleur appele cette méthode (setClient())
	//			avec le parametre "client" (voir MARQUE 20220705175700, 01)
	// 4] - ds le fichier F02_client_controller.java: (voir MARQUE 02)
	//		-> il y a la methode qd on clic sur le bouton
	//		-> "clients". Ds cette méthode nommée "editerClient()"
	//		-> il y a 1 variable de type Client qui prend cô
	//		-> valeur la Ligne sélectionée ds la TableView.
	//		-> cette valeur est ensuite transmise cô parametre
	//		-> a la methode qui initialise la fenetre d'edition
	// 5] - Ds ce fichier (F02B_controller.java), il faut
	//		-> implementer la methode qui:
	//			- verifie qu'il n'y a pas d'erreur ds les txtF
	//			- se connecte a la db
	//			- ecrit la requete
	//			- execute la requete
	//			- Affiche une boite de dialogue si erreur(s) ds les txtF
	//		La methode est implémentée juste au-dessous de celle-ci
	public Client setClient(Client p_client) {
		
		//
		this.client = p_client;
		
		if(this.client == null) {
			
			// System.out.println("Aucun client n'a été sélectionné");
			
		} else {
			
			// System.out.println("1 client a bien été sélectionné");
			
			// pr debug
			/*
			System.out.println("Le client sélectionné pr edition");
			System.out.println("a les caracteristiques suivantes");
			System.out.println("ID: " + this.livre.getClientId());
			System.out.println("Prenom: " + this.livre.getClientPrenom());
			System.out.println("Nom: " + this.livre.getClientNom());
			System.out.println("Telephone: " + this.livre.getClientTelephone());
			System.out.println("Mail: " + this.livre.getClientMail());
			*/
			
			// MAJ des Lbl puisque l'utilisateur
			// a séléctionné 1 client pr en modifier
			// les infos
			lbl_contient_actuel_prenom.setText(this.client.getClientPrenom());
			lbl_contient_actuel_nom.setText(this.client.getClientNom());
			lbl_contient_actuel_telephone.setText(this.client.getClientTelephone());
			lbl_contient_actuel_mail.setText(this.client.getClientMail());
		}
		
		return this.client;
	}
	
	
	// ================================================
	
	// MAJ le client que l'on a selectionné ds la db
	@FXML
	private void MAJClient() {
		
		//
		System.out.println("fichier: F02B_controller.java");
		System.out.println("methode: MAJClient()");
		
		// les variables
		client = setClient(client);
		String erreur_msg = "";
		String update_query;
		Connection con;
		PreparedStatement p_stmt;
		
		// affichage console pr debug
		/*
		System.out.println("Le client qui doit etre MAJ a les caracteristiques");
		System.out.println("suivantes:");		
		System.out.println("ID: " + client.getClientId());
		System.out.println("Prenom: " + client.getClientPrenom());
		System.out.println("Nom: " + client.getClientNom());
		System.out.println("Telephone: " + client.getClientTelephone());
		System.out.println("Mail: " + client.getClientMail());
		System.out.println("Ses caracteristiques deviennent:");
		System.out.println("ID (inchangé): " + client.getClientId());
		System.out.println("Prenom (changé): " + txtF_contient_PRENOM.getText());
		System.out.println("Nom (changé): " + txtF_contient_NOM.getText());
		System.out.println("Telephone (changé): " + txtF_contient_TELEPHONE.getText());
		System.out.println("Mail (changé): " + txtF_contient_MAIL.getText());
		*/
		
		// verifie que l'utilisateur a renseigné le prenom
		if(txtF_contient_PRENOM.getText() == null
				|| txtF_contient_PRENOM.getText().length() == 0) {
			
			//
			erreur_msg += "Vs n'avez pas renseigné de prénom pr ce client\n";
			
		}
		
		// verifie que l'utilisateur a renseigné le nom
		if(txtF_contient_NOM.getText() == null
				|| txtF_contient_NOM.getText().length() == 0) {
			
			//
			erreur_msg += "Vs n'avez pas renseigné de nom pr ce client\n";
			
		}
		
		// verifie que l'utilisateur a renseigné le telephone
		if(txtF_contient_TELEPHONE.getText() == null
				|| txtF_contient_TELEPHONE.getText().length() == 0) {
			
			//
			erreur_msg += "Vs n'avez pas renseigné de téléphone pr ce client\n";
			
		}
		
		// verifie que l'utilisateur a renseigné le mail
		if(txtF_contient_MAIL.getText() == null
				|| txtF_contient_MAIL.getText().length() == 0) {
			
			//
			erreur_msg += "Vs n'avez pas renseigné d'email pr ce client\n";
			
		}
		
		// si pas d'erreur, MAJ de l'ergt
		if(erreur_msg.length() == 0) {
			
			//
			// System.out.println("Aucune erreur détectée: MAJ de l'ergt");
			
			// affichage console pr debug
			/*
			System.out.println("Le client qui doit etre MAJ a les caracteristiques");
			System.out.println("suivantes:");		
			System.out.println("ID: " + client.getClientId());
			System.out.println("Prenom: " + client.getClientPrenom());
			System.out.println("Nom: " + client.getClientNom());
			System.out.println("Telephone: " + client.getClientTelephone());
			System.out.println("Mail: " + client.getClientMail());
			System.out.println("Ses caracteristiques deviennent:");
			System.out.println("ID (inchangé): " + client.getClientId());
			System.out.println("Prenom (changé): " + txtF_contient_PRENOM.getText());
			System.out.println("Nom (changé): " + txtF_contient_NOM.getText());
			System.out.println("Telephone (changé): " + txtF_contient_TELEPHONE.getText());
			System.out.println("Mail (changé): " + txtF_contient_MAIL.getText());
			*/
			
			// redact° de la requete de MAJ
			update_query = "UPDATE client \n"
					+ "SET prenom = '"
					+ txtF_contient_PRENOM.getText()
					+ "', nom = '"
					+ txtF_contient_NOM.getText()
					+ "', telephone = '"
					+ txtF_contient_TELEPHONE.getText()
					+ "', mail = '"
					+ txtF_contient_MAIL.getText()
					+ "' WHERE id_abonne = "
					+ client.getClientId()
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
				this.F02B.close();
				
			} catch(SQLException except) {
				
				//
				System.out.println("Exception");
				System.out.println("fichier: F02B_controller.java");
				System.out.println("methode: MAJClient()");
				//Logger.getLogger(update_query);
				Logger.getLogger(F02B_controller.class.getName()).log(Level.SEVERE, null, except);
				
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
            alert.initOwner(F02B);
            alert.setTitle("Erreur(s) détectée(s)");
            alert.setHeaderText("Veuillez renseigner les champs.");
            alert.setContentText(erreur_msg);

            alert.showAndWait();
		}
		
	}
	
	// ================================================
	
	//
	@FXML
	private void annuler() {
		
		//
		System.out.println("fichier: F02B_controller.java");
		System.out.println("methode: annuler()");
		
		//
		this.F02B.close();
		
	}
	// ================================================
}
