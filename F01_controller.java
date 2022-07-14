package application.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.model.Livre;
import application.util.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// class qui affiche la fenetre principale de la sect° livre
// et qui permet d'effacer 1 livre
public class F01_controller implements Initializable {
	
	// ================================================
	// variables
	
	//
	private Main main;
	
	//
	private Stage F01;
	
	//
	ObservableList<Livre> list01 = FXCollections.observableArrayList();
	
	//
	ObservableList<Livre> list02 = FXCollections.observableArrayList();

	//
	ObservableList<Livre> list03 = FXCollections.observableArrayList();

	// ================================================
	
	@FXML
	private AnchorPane AP;
	
	@FXML
	private Pane P;
	
	@FXML
	private TableView<Livre> TV;
	
	@FXML
	private TableColumn<Livre, Integer> col_ID;
	
	@FXML
	private TableColumn<Livre, String> col_AUTEUR;
	
	@FXML
	private TableColumn<Livre, String> col_TITRE;
	
	@FXML
	private Label lbl_presente_ID;
	
	@FXML
	private Label lbl_presente_AUTEUR;
	
	@FXML
	private Label lbl_presente_TITRE;
	
	@FXML
	private Label lbl_contient_ID;
	
	@FXML
	private Label lbl_contient_AUTEUR;
	
	@FXML
	private Label lbl_contient_TITRE;
	
	@FXML
	private Button btn_quitter;
	
	@FXML
	private Button btn_creer;
	
	@FXML
	private Button btn_editer;
	
	@FXML
	private Button btn_effacer;
	
	@FXML
	private Button btn_ouvrirAlerte;
	
	// ================================================
	
	// CONSTRUCTEURS
	
	// 01
	public F01_controller() { }
	
	// ================================================
	
	@Override
	public void initialize(URL p_url, ResourceBundle p_r_bdle) {
		
		//
		afficherTblLivre();
		
		//
		afficherDetailsLivre(null);
		
		/*
		 * ChangeListener sur la TableView
		 * en utilisant la méthode afficherDetailsLivre().
		 * nous obtenons la selectedItemProperty
		 * de la table de livre et lui ajoutons un listener.
		 * Chaque fois que l’utilisateur sélectionne une
		 * livre dans la table, notre expression lambda
		 * est exécutée. Nous prenons le livre nouvellement
		 * sélectionnée pour la transmettre à la
		 * méthode afficherDetailsLivre(...).
		 * PERMET:
		 * Les Labels st mis a jour.
		 * grace a la methode afficherDetailsLivre()
		 */
		 TV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsLivre(newValue));
		 
	}
	
	// ================================================

	// SETTER DE LA FENETRE PRINCIPALE
	// invoquée depuis
	// fichier: Main.java
	// methode: init_F01()
	public void setMain(Main p_main) {
		
		//
		this.main = p_main;
		
	}
	
	// ================================================
	
	// SETTER DE CETTE FENETRE
	public void set_F01(Stage p_fenetre) {
		
		//
		System.out.println("fichier: F01_controller.java");
		System.out.println("methode: set_F01()");
		
		//
		this.F01 = p_fenetre;
		
		// evenement qd on ferme la fenetre
		// avec le bouton en haut a droite
		this.F01.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    
			@Override 
		    public void handle(WindowEvent event) {
		    	
		    	System.out.println("Possibilité de programmer");
		    	System.out.println("des actions qd on ferme la");
		    	System.out.println("fenetre depuis le btn heut - droite");
		    	
		    } 
		});
	}
	
	// ================================================
	
	/**
	 * Javadoc de fct°: getLivre()<br>
	 * Descript°:<br>
	 * Retourne 1 liste des livres<br>
	 * présents ds la db.<br>
	 * @return: ObservableList<Livre> list
	 */
	public ObservableList<Livre> getLivre() {
    	
		// les variables
		
		// passée en variable de portée class
        // ObservableList<Livre> list01 = FXCollections.observableArrayList();
        String select;
        Connection con;
        PreparedStatement p_stmt;
        ResultSet rs;
        Livre livre;
 
        // redact° de la requete
        select = "SELECT * FROM livre;";
        
        // initialisat° d'1 variable de type Connection
        // obtenir la connex° grâce a la class Connexion
        con = Connexion.getCon();
        
        try {
        	
        	// initialisat° d'1 variable de type
        	// PreparedStatement
        	// prépare la requete
            p_stmt = con.prepareStatement(select);
            
            // initialisat° d'1 variable de type
            // ResultSet
            // execute la requete
            rs = p_stmt.executeQuery();
            
            //
            while (rs.next()) {
            	
                livre = new Livre();
                
                livre.setId_livre(rs.getInt(1));
                livre.setAuteur_livre(rs.getString(2));
                livre.setTitre_livre(rs.getString(3));
                
                list01.add(livre);
                
            }
            
        } catch (SQLException except) {
        	
        	System.out.println("Except°:");
        	System.out.println("Fichier: FXML_scene01Layout_CONTROLLER.java");
        	System.out.println("Methode: getLivre()");
            Logger.getLogger(F01_controller.class.getName()).log(Level.SEVERE, null, except);
        }
        
        return list01;
 
    }
	
	// ================================================
	
	/**
	 * Javadoc de fct°: afficherTblLivre()<br>
	 * Descript°:<br>
	 * Affiche ds la TableView 1 liste des livres<br>
	 * présents ds la db.<br>
	 */
	public void afficherTblLivre() {
    	
		//
		// TV.refresh();
		
		//
		list02.removeAll(list02);
		
		// ObservableList qu'on remplira avec les
		// ergts de la table de la db
        list02 = getLivre();
        
        // parametre chacune des colonnes de la TableView
        // avec les attributs déclarés ds la class Livre
        col_ID.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id_livre"));
        col_AUTEUR.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur_livre"));
        col_TITRE.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre_livre"));
        
        // parametre la TableView
        // avec la ObservableList
        // pas de MAJ
        TV.setItems(list01);
        
        //
        // TV.refresh();
 
    }
	
	// ================================================
	
	/**
	 * Javadoc de fct° afficherDetailsLivre()<br>
	 * Descript°:<br>
	 * Affiche les détails d'1 livre (détails issus<br>
	 * d'1 db) ds les labels déclarés correspondants<br>
	 * A l'initialisat°, le parametre est null.
	 */
	public void afficherDetailsLivre(Livre livre) {
		
		System.out.println("fichier: F01_controller.java");
		System.out.println("méthode: afficherDetailsLivre()");
		
		//
		if(livre != null) {
			
			lbl_contient_ID.setText(Integer.toString(livre.getId_livre()));
			lbl_contient_AUTEUR.setText(livre.getAuteur_livre());
			lbl_contient_TITRE.setText(livre.getTitre_livre());
			
		} else {
			
			lbl_contient_ID.setText("");
			lbl_contient_AUTEUR.setText("");
			lbl_contient_TITRE.setText("");
		}
		
		/*
		System.out.println("- ID:");
		System.out.println(lbl_contient_ID.getText());
		System.out.println("- Auteur:");
		System.out.println(lbl_contient_AUTEUR.getText());
		System.out.println("- Titre:");
		System.out.println(lbl_contient_TITRE.getText());
		*/
	}
	
	// ================================================
	
	@FXML
	private void creerLivre() {
		
		//
		main.init_F01A_creer_livre();
	}
	
	// ================================================
	
	@FXML
	private void editerLivre() {
		
		// Faisait apparaitre la fenetre edit° d'1 livre ms sans
		// que les TxtF contiennent les infos du livre sélectionné
		//
		// a été remplacée par les instruct° de la MARQUE 02
		// (cf ci-dessous)
		// main.init_F01B_MAJ_livre();		
		
		// MARQUE 02, premiere partie
		// permet d'obtenir le livre sélectionné depuis la TV
		Livre selectedLivre = TV.getSelectionModel().getSelectedItem();
		// FIN DE MARQUE 02, premiere partie
		
		if(selectedLivre == null) {
			
			// System.out.println("Aucun livre n'a été sélectionné");
			alerte_aucun_livre_selection();
			
		} else {
			
			// System.out.println("1 livre a bien été sélectionné");
			
			// MARQUE 02, seconde partie
			// Passe le livre sélectionné à la méthode qui initialise
			// la fenetre edition d'1 livre. Les TxtF de la fenetre pvent
			// désorms contenir les infos du livre sélectionné.
			main.init_F01B_MAJ_livre(selectedLivre);
			// FIN DE MARQUE 02, seconde partie
			
		}
	}
	
	// ================================================
	
	// methode implémentée de maniere similaire
	// à la méthode editerLivre()
	// efface l'ergt sélectionné de la db
	// et MAJ la TableView
	@FXML
	private void effacerLivre() {
		
		//
		System.out.println("Fichier: F01_controller.java");
		System.out.println("methode: effacerLivre()");
		
		// les variables
		Livre selectedLivre;
		String delete_query;
		Connection con;
		PreparedStatement p_stmt;
		
		// initialisat° de la variable selectedLivre
		// permet d'obtenir le livre sélectionné depuis la TV
		selectedLivre = TV.getSelectionModel().getSelectedItem();
		
		if(selectedLivre == null) {
			
			// System.out.println("Aucun livre n'a été sélectionné");
			alerte_aucun_livre_selection();
			
		} else {
			
			/*
			System.out.println("1 livre a bien été sélectionné");
			System.out.println("Effacement de ce livre de la db");
			System.out.println("ID du livre a effacé: " + selectedLivre.getId_livre());
			*/
			
			// redact° de la requete
			delete_query = "DELETE from livre \n"
					+ "WHERE id_livre = "
					+ selectedLivre.getId_livre()
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
				afficherTblLivre();
				
			} catch(SQLException except) {
				
				//
				System.out.println("Exception");
				System.out.println("fichier: F01_controller.java");
				System.out.println("methode: effacerLivre()");
				//Logger.getLogger(delete_query);
				Logger.getLogger(F01_controller.class.getName()).log(Level.SEVERE, null, except);
			
			}
		}
		
	}
	
	// ================================================
	
	//
	@FXML
	private void quitter_section_livre() {
		
		//
		this.F01.close();
		
	}
	
	// ================================================
	
	// Affiche une boite de dialogue de type ALERT
	// Informe l'utilisateur qu'il n'a sélectionné aucun livre
	private void alerte_aucun_livre_selection() {
		
		//
		Alert alerte_aucun_livre_selection = new Alert(AlertType.WARNING);
		alerte_aucun_livre_selection.initOwner(main.getF01());
		alerte_aucun_livre_selection.setTitle("Aucune sélect°");
		alerte_aucun_livre_selection.setHeaderText("Aucun livre sélectionné");
		alerte_aucun_livre_selection.setContentText("Veuillez sélectionner 1 livre");
		alerte_aucun_livre_selection.showAndWait();
		
	}
	
	// ================================================
	
	// evenement clavier: retourne la touche pressee
	// la methode appui_touche() est déclarée ds le fxml
	// sur l'AnchorPane et permet de fermer la fenetre si ESC pressée
	@FXML
	private void appui_touche(KeyEvent ke){
		
		//
		// System.out.println("Key Pressed: " + ke.getCode());
		
		//
		switch(ke.getCode().getCode()) {
			
			// 27 = ESC key
			case 27 : {
				
				// retourne le nom de la touche pressee
	            // System.out.println(ke.getCode() + ": ESC pressée");
	            
	            // retourne le code clavier de la touche pressee
	            // System.out.println(ke.getCode().getCode() + ": ESC pressée");
	            
				F01.close();
	            break;
	            
	        }
			
	        default:  {
	        	
	            // System.out.println("Unrecognized key");
	        	
	        }
		}
	}
	
	// ================================================	
	
}
