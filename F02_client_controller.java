package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.model.Client;
import application.model.Livre;
import application.util.Connexion;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// class de la fenetre client
// qui permet aussi d'effacer 1 client
public class F02_client_controller implements Initializable {

	// ================================================
	// variables
	
	//
	private Main main;
	
	//
	private Stage F02_client;
	
	//
	public ObservableList<Client> liste_client = FXCollections.observableArrayList();
	
	// ================================================
	
	@FXML
	private AnchorPane AP;
	
	@FXML
	private Pane P;
	
	@FXML
	private TableView<Client> TV;
	
	@FXML
	private TableColumn<Client, Integer> col_id_abonne;
	
	@FXML
	private TableColumn<Client, String> col_prenom;
	
	@FXML
	private TableColumn<Client, String> col_nom;
	
	@FXML
	private TableColumn<Client, String> col_telephone;
	
	@FXML
	private TableColumn<Client, String> col_mail;
	
	@FXML
	private Button btn_quitter_client;
	
	@FXML
	private Button btn_creer_client;
	
	@FXML
	private Button btn_editer_client;
	
	@FXML
	private Button btn_effacer_client;
	
	@FXML
	private Label lbl_presente;
	
	@FXML
	private Label lbl_contient;
	
	// ================================================
	
	// CONSTRUCTEURS
	
	// 01
	public F02_client_controller() { }
	
	// ================================================
	
	// GETTERS - SETTERS
	
	// ================================================
	
	// btn effacer client	
	public Button getBtn_effacer_client() {
		return btn_effacer_client;
	}

	public void setBtn_effacer_client(Button btn_effacer_client) {
		this.btn_effacer_client = btn_effacer_client;
	}

	// col_id_abonne
	public TableColumn<Client, Integer> getCol_id_abonne() {
		return col_id_abonne;
	}

	public void setCol_id_abonne(TableColumn<Client, Integer> col_id_abonne) {
		this.col_id_abonne = col_id_abonne;
	}
	
	// col_prenom
	public TableColumn<Client, String> getCol_prenom() {
		return col_prenom;
	}

	public void setCol_prenom(TableColumn<Client, String> col_prenom) {
		this.col_prenom = col_prenom;
	}

	// col_nom
	public TableColumn<Client, String> getCol_nom() {
		return col_nom;
	}

	public void setCol_nom(TableColumn<Client, String> col_nom) {
		this.col_nom = col_nom;
	}

	// col_telephone
	public TableColumn<Client, String> getCol_telephone() {
		return col_telephone;
	}

	public void setCol_telephone(TableColumn<Client, String> col_telephone) {
		this.col_telephone = col_telephone;
	}

	// col_mail
	public TableColumn<Client, String> getCol_mail() {
		return col_mail;
	}

	public void setCol_mail(TableColumn<Client, String> col_mail) {
		this.col_mail = col_mail;
	}
	
	// TableView
	public TableView<Client> getTV() {
		return TV;
	}

	public void setTV(TableView<Client> tV) {
		TV = tV;
	}
	
	// ================================================

	@Override
	public void initialize(URL p_url, ResourceBundle p_rp_bdle) {
		
		// methode testIMPRESSION()
		// implementee ds
		// fichier: Main.java
		// la méthode n'est pas éxécutée
		// main.testIMPRESSION();
		
		//
		afficherTblClient();
		
		// renvoie erreur: this.main is null
		// main.getClient_test();
		
	}
	
	// ================================================
	
	// invoquée depuis
	// fichier: Main.java
	// methode: 
	public void setMain(Main p_main) {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: setMain()");
		
		//
		this.main = p_main;
		
		// methode testIMPRESSION()
		// implementee ds
		// fichier: Main.java
		// invoquée depuis ici,
		// la méthode est éxécutée
		// main.testIMPRESSION();
		
		// ajout test 20220705115600, etape 03
		// methode implementee ds Main.java
		// l'appel a la methode fonctionne
		// main.getClient_test();
		
		// test 20220705152100, 01
		// aucun contenu ds la table
		// TV.setItems(liste_client);
		
		// pas de MAJ de la TV
		// TV.setItems(main.getClient_test());
		
	}
	
	// ================================================
	
	// methode qui set cette fenetre
	// NOTE:
	// methode qui prend en parametre cette Stage
	// (variable declarée plus haut)
	// invoquée depuis
	// fichier: Main.java
	// methode: 
	// obligat° de declarer cette methode si l'on veut
	// fermer la fenetre au clic btn
	public void setF02_client(Stage p_stage) {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: setF02_client()");
		
		//
		this.F02_client = p_stage;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.F02_client.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    
			@Override 
		    public void handle(WindowEvent event) {
		    	
		    	System.out.println("Possibilité de programmer");
		    	System.out.println("des actions qd on ferme la");
		    	System.out.println("fenetre depuis le btn haut - droite");
		    	
		    } 
		});
		
		// test 20220705122200, 01
		// on a commenté l'appel à la methode qui se
		// trouve ds la methode initialize() pr
		// le mettre ici, Ne modifie
		// pas le comportement de la TV
		// afficherTblClient();
		
	}

	// ================================================
	
	// ================================================
	
	@FXML
	private void quitter_client() {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: quitter_client()");
		
		//
		this.F02_client.close();
		
	}
	
	// ================================================
	
	@FXML
	private void creer_client() {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: creer_client()");
		
		//
		main.init_F02A();
		
	}
	
	// ================================================
	
	@FXML
	private void editer_client() {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: editer_client()");
	
		// MARQUE 02, premiere partie
		// permet d'obtenir le client sélectionné depuis la TV
		Client selectedClient = TV.getSelectionModel().getSelectedItem();
		// FIN DE MARQUE 02, premiere partie
		
		if(selectedClient == null) {
			
			// System.out.println("Aucun client n'a été sélectionné");
			alerte_aucun_client_selection();
			
		} else {
			
			// System.out.println("1 client a bien été sélectionné");
			
			// MARQUE 02, seconde partie
			// Passe le client sélectionné à la méthode qui initialise
			// la fenetre edition d'1 client. Les TxtF de la fenetre pvent
			// désorms contenir les infos du client sélectionné.
			main.init_F02B(selectedClient);
			// FIN DE MARQUE 02, seconde partie
			
		}
		
		// a supprimer si l'edit° d'1 client fonctionne
		// main.init_F02B();
		
	}
	
	// ================================================
	
	@FXML
	private void effacer_client() {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: effacer_client()");
		
		// les variables
		Client selectedClient;
		String delete_query;
		Connection con;
		PreparedStatement p_stmt;
		
		// initialisat° de la variable selectedClient
		// permet d'obtenir le client sélectionné depuis la TV
		selectedClient = TV.getSelectionModel().getSelectedItem();
		
		if(selectedClient == null) {
			
			// System.out.println("Aucun client n'a été sélectionné");
			alerte_aucun_client_selection();
			
		} else {
			
			/*
			System.out.println("1 client a bien été sélectionné");
			System.out.println("Effacement de ce client de la db");
			System.out.println("ID du client a effacer: " + selectedClient.getClientId());
			*/
			
			// redact° de la requete
			delete_query = "DELETE from client \n"
					+ "WHERE id_abonne = "
					+ selectedClient.getClientId()
					+ ";";
			
			// imprime la requete pr debug
			// System.out.println(delete_query);
			
			// initialisat° de la variable de type Connection
			// obtenir la connexion grace a la class Connexion
			con = Connexion.getCon();
			
			try {
				
				// initialisat° de la variable de type PreparedStatement
				p_stmt = con.prepareStatement(delete_query);
				
				// execute la requete
				p_stmt.executeUpdate();
				
				// ferme la connex° a la db
				con.close();
				
				// MAJ de la TableView
				afficherTblClient();
				
			} catch(SQLException except) {
				
				//
				System.out.println("Exception");
				System.out.println("fichier: F02_client_controller.java");
				System.out.println("methode: effacerClient()");
				//Logger.getLogger(delete_query);
				Logger.getLogger(F02_client_controller.class.getName()).log(Level.SEVERE, null, except);
				
			}
		}
		
	}
	
	// ================================================
	
	// Affiche une boite de dialogue de type ALERT
	// Informe l'utilisateur qu'il n'a sélectionné aucun client
	private void alerte_aucun_client_selection() {
		
		//
		Alert alerte_aucun_client_selection = new Alert(AlertType.WARNING);
		alerte_aucun_client_selection.initOwner(main.getF02());
		alerte_aucun_client_selection.setTitle("Aucune sélect°");
		alerte_aucun_client_selection.setHeaderText("Aucun client sélectionné");
		alerte_aucun_client_selection.setContentText("Veuillez sélectionner 1 client");
		alerte_aucun_client_selection.showAndWait();
		
	}
	
	// ================================================
	
	public ObservableList<Client> getClient() {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: getClient()");
		
		// les variables
		
		// liste_client est 1 variable de portée de class
		// ObservableList<Client> liste_client = FXCollections.observableArrayList();
		
		// test 20220705123300, 01
		// la TV n'est pas MAJ
		// liste_client.removeAll();
		// la TV n'est pas MAJ
		// liste_client.removeAll(liste_client);
		
		String select_query;
		Connection con;
		PreparedStatement p_stmt;
		ResultSet rs;
		Client client;
		
		// redact° de la requete
		select_query = "SELECT * FROM client";
		
		// initialisat° de la variable de type Connection
        // obtenir la connex° grâce a la class Connexion
		con = Connexion.getCon();
		
		try {
			
			// initialisat° d'1 variable de type
        	// PreparedStatement
        	// prépare la requete
            p_stmt = con.prepareStatement(select_query);
            
            // initialisat° d'1 variable de type
            // ResultSet
            // execute la requete
            rs = p_stmt.executeQuery();
            
            //
            while (rs.next()) {
            	
                client = new Client();
                
                client.setClientId(rs.getInt(1));
                
                client.setClientPrenom(rs.getString(2));
                
                client.setClientNom(rs.getString(3));
                
                client.setClientTelephone(rs.getString(4));
                
                client.setClientMail(rs.getString(5));
                
                liste_client.add(client);
                
            }
            
		} catch(SQLException except) {
			
			//
			System.out.println("Except°:");
        	System.out.println("Fichier: F02_client_controller.java");
        	System.out.println("Methode: getClient()");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
		
		}
		
		//
		return liste_client;
		
	}
	
	// ================================================
	
	// methode afficherTblClient()
	// 1] - utilise le return de la methode getClient()
	// pr afficher les ergts de la table client de la db
	public void afficherTblClient() {
		
		//
		System.out.println("fichier: F02_client_controller.java");
		System.out.println("methode: afficherTblClient()");
		
		// variables
		// AUCUNE
        
        // pr debug
        // affiche seulement les adresses memoire
        // System.out.println(list);
        
        // pr debug
        // affiche les id de chaque ergt
        /*
        for (Client client: liste_client) {
            System.out.println(client.getClientId());
        }
        */
        
        // parametre chacune des colonnes de la TableView
        // (déclarées ds le fichier: F02_client_controller.java)
        // avec les attributs déclarés ds la class Client
        getCol_id_abonne().setCellValueFactory(cellData -> cellData.getValue().clientIdProperty().asObject());	        
        getCol_prenom().setCellValueFactory(cellData -> cellData.getValue().clientPrenomProperty());
        getCol_nom().setCellValueFactory(cellData -> cellData.getValue().clientNomProperty());
        getCol_telephone().setCellValueFactory(cellData -> cellData.getValue().clientTelephoneProperty());
        getCol_mail().setCellValueFactory(cellData -> cellData.getValue().clientMailProperty()); 
        
        // parametre la TableView
        // avec la ObservableList
        // retournée par la méthode getClient()
        // (implémentée juste au-dessus
        // liste_client.removeAll(liste_client);
        
        // L originale qui fonctionne
        getTV().setItems(getClient());
        
        // --------------------------------------------
        // test 20220705142200, 01
        FXMLLoader loaderX = new FXMLLoader();
        loaderX.setLocation(Main.class.getResource("view/main_fxml.fxml"));
        try {
        	AP = (AnchorPane) loaderX.load();
        } catch(IOException except) {
        	except.printStackTrace();
        }
        Main_controller ctrlX = new Main_controller();
        ctrlX = loaderX.getController();
        // --------------------------------------------
        
        // entraine 1 erreur: this.main is null
        // avec ou sans le test 20220705142200, 01 (juste au-dessus)
        // getTV().setItems(main.getClient_test());
        
        // ne fonctionne pas
        // getTV().refresh();
        
        // aucun contenu ds la table
        // FXCollections.copy(liste_client, getClient());
        
	}
	
	// ================================================
	
	// affiche ds la console le contenu de la table client
	public void affiche_console_tblClient() {
		
		//
		System.out.println("fichier: F02_client_controller");
		System.out.println("methode: affiche_console_tblClient()");
		
		//
		for (Client client: liste_client) {
            System.out.println(client.getClientId());
        }
		
	}
	
	// ================================================
	
}
