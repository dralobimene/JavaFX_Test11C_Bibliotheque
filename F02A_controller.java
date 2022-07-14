package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.model.Client;

import application.util.Connexion;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// fenetre qui permet la creat° d'1 client
public class F02A_controller implements Initializable {

	// variables
	
	//
	private Main main;
	
	//
	private Stage F02A;
	
	// test 20220705121600, 01
	private Stage F02;
	
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
	private Button btn_inserer;
	
	@FXML
	private Button btn_annuler;
	
	// ================================================
	
	// Constructeur(s)
	
	// 01
	public F02A_controller() { }
	
	// ================================================
	
	@Override
	public void initialize(URL p_url, ResourceBundle p_r_bdle) {
		
		// renvoie erreur: this.main is null
		// main.getClient_test();
	}
	
	// ================================================
	
	//SETTER du Main
	public void setMain(Main p_main) {
		
		System.out.println("fichier: F02A_controller.java");
		System.out.println("methode setMain()");
		//
		this.main = p_main;
		
		// ajout test 20220705115600, etape 04
		// l'appel a la methode fonctionne
		// ms la TV de F02 ne se met pas a jour
		// main.getClient_test();
		
	}
	
	// ================================================
	
	// SETTER de F02A, permet de creer client
	public void setF02A(Stage p_stage) {
		
		//
		System.out.println("fichier: F02A_controller.java");
		System.out.println("methode: setF02A()");
		
		//
		this.F02A = p_stage;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.F02A.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    
			@Override 
		    public void handle(WindowEvent event) {
		    	
		    	System.out.println("Possibilité de programmer");
		    	System.out.println("des actions qd on ferme la");
		    	System.out.println("fenetre depuis le btn haut - droite");
		    	
		    } 
		});
		
	}
	
	// test 20220705121600, 02
	public void setF02(Stage p_stage) {
		
		System.out.println("fichier F02A_controller.java");
		System.out.println("methode setF02()");
		//
		this.F02 = p_stage;
		
		// l'appel a la methode fonctionne
		// ms la TV de F02 ne se met pas a jour
		// main.getClient_test();
	}
	
	// ================================================
	
	@FXML
	private void insererClient() {
		
		//
		System.out.println("fichier: F02A_controller.java");
		System.out.println("methode: insererClient()");
		
		// les variables
		Client client;
		String insert_query;
		Connection con;
		PreparedStatement p_stmt;
		
		// initialisat° de l'instance de type Client
		// L'instance client recupere cô attributs
		// prenom, nom, telephone, mail
		// ce que l'utilisateur a renseigné ds les TextFields
		// correspondants
		client = new Client(txtF_contient_PRENOM.getText(),
							txtF_contient_NOM.getText(),
							txtF_contient_TELEPHONE.getText(),
							txtF_contient_MAIL.getText());
		
		// presentation ds la console pr debug
		/*
		System.out.println("Le client pret a etre inséré ds la db");
		System.out.println("a les caracteristiques suivantes: ");
		System.out.println("ID: " + client.getClientId());
		System.out.println("Prenom: " + client.getClientPrenom());
		System.out.println("Nom: " + client.getClientNom());
		System.out.println("Telephone: " + client.getClientTelephone());
		System.out.println("Nom: " + client.getClientMail());
		*/
		
		// redact° de la requete d'insert°
		// on indique a la requete d'inserer id_client, ms
		// on insere 1 valeur "null", l'autoincrement de
		// la tbl va se charger d'inserer la valeur correcte
		insert_query = "INSERT INTO client ("
			    + " id_abonne,"
			    + " prenom,"
			    + " nom,"
			    + " telephone,"
			    + " mail) VALUES ("
			    + "null, '"
			    + client.getClientPrenom()
			    + "', '"
			    + client.getClientNom()
			    + "', '"
			    + client.getClientTelephone()
			    + "', '"
			    + client.getClientMail()
			    + "')";
		
		// déclarat°, initialisat° d'1 variable de type Connection
		// obtenir la connexion grace a la class Connexion
		con = Connexion.getCon();
		
		// imprimer la requete pr debug
		// System.out.println(insert_query);
		
		try {
			
			// initialisat° de la variable de type PreparedStatement
			// prepare la requete
			p_stmt = con.prepareStatement(insert_query);
			
			// execute la requete
			p_stmt.executeUpdate();
			
			// ferme la connexion a la db
			p_stmt.close();
			con.close();
			
			// test 20220705131700, 01
			// recupere bien ts les ergts de la table client
			// (y compris celui que l'on vient de creer)
			// TV n'est pas MAJ
			// main.getClient_test();
						
			// n'affiche que les buit-in de F02
			// F02.
			// this.F02.
			
			// --------------------------------------------
			// test 20220705133800, 01
			FXMLLoader loaderT = new FXMLLoader();
			loaderT.setLocation(Main.class.getResource("view/F02_client_fxml.fxml"));
			try {
				AP = (AnchorPane) loaderT.load();
			} catch (IOException except ) {
				except.printStackTrace();
			}
			F02_client_controller ctrlT = new F02_client_controller();
			ctrlT = loaderT.getController();
			
			ctrlT.liste_client.removeAll();
			
			// pas de MAJ de la TV
			// ctrlT.getTV().setItems(ctrlT.getClient());
			
			// pas de MAJ de la TV
			// ctrlT.getTV().setItems(main.getClient_test());
			
			// pas de MAJ de la TV
			// ctrlT.afficherTblClient();
			
			// --------------------------------------------
			
			// ferme la fenetre
			this.F02A.close();
			
		} catch(SQLException except) {
			
			//
			System.out.println("Exception:");
			System.out.println("fichier: F02A_controller.java");
			System.out.println("methode: insererClient()");
			Logger.getLogger(F02A_controller.class.getName()).log(Level.SEVERE, null, except);
		}
	}
	
	// ================================================
	
	@FXML
	private void annuler() {
		
		//
		System.out.println("fichier: F02A_controller.java");
		System.out.println("methode: annuler()");
		
		//
		this.F02A.close();
		
	}
	
	// ================================================
	
}
