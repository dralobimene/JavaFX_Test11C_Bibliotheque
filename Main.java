package application;

/*
 * UTILISE LA BASE DE DONNEES
 * java_formation_bibliotheque
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.model.Client;
import application.model.Emprunt;
import application.model.EmpruntCompose01;
import application.model.Livre;
import application.util.Connexion;
import application.util.DateUtil;
import application.view.F01A_controller;
import application.view.F01B_controller;
import application.view.F01_controller;
import application.view.F02A_controller;
import application.view.F02B_controller;
import application.view.F02_client_controller;
import application.view.F03_emprunt_controller;
import application.view.F03_emprunt_controller_creer_emprunt;
import application.view.Main_controller;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	// =============================================================
	// variables
	
	// le fenetre principale, l'accueil
	private Stage FP;
	
	// la fenetre qui ouvre la section livre
	private Stage F01;
	
	// la fenetre qui permet de creer 1 livre
	private Stage F01A_creer_livre;
	
	// la fenetre qui permet d'editer 1 livre
	private Stage F01B_MAJ_livre;
	
	// la fenetre qui ouvre la section client
	private Stage F02;
	
	// la fenetre qui permet de creer 1 client
	private Stage F02A;
	
	// la fenetre qui permet d'editer 1 client
	private Stage F02B;
	
	// la fenetre qui ouvre la section emprunt
	private Stage F03;
	
	// la fenetre qui permet de creer 1 emprunt
	private Stage F03_creer_emprunt;
	
	// la fenetre qui permet d'editer 1 emprunt
	
	// controleur du fichier Main_controller.java
	private Main_controller ctrl_FP;
	
	// MARQUE C01, B
	// le controleur est maintenant a portée de class pr que
	// les méthodes implémentées ds ce fichier (Main.java)
	// puissent accéder aux elts FXML déclarés ds le
	// fichier F02_client_controller.java
	// (voir MARQUE C01, A pr l'ancien emplacement)
	private F02_client_controller ctrlF02;
	
	//
	private F02A_controller ctrlF02A;
	
	//
	private F02B_controller ctrlF02B;
	
	// controleur de la F03
	private F03_emprunt_controller ctrlF03;
	
	// controleur de la F03A, creer 1 emprunt
	private F03_emprunt_controller_creer_emprunt ctrlF03_creer_emprunt;
	
	// controleur de la F03B
	
	// ObservableList pr les emprunts
	private ObservableList<Emprunt> empruntData = FXCollections.observableArrayList();
	
	//
	private ObservableList<EmpruntCompose01> empruntCompose01Data = FXCollections.observableArrayList();
	
	// =============================================================
	
	// declarer 1 contructeur vide
	public Main() { }
	   
	// =============================================================
	   
	@Override
	public void start(Stage p_fenetre) {

		// LFP = Layout Fenetre Principale
		initLFP();
			
	}
		
	// =============================================================
		
	// ouvre la FP
	public void initLFP() {
		
		// variables
		FXMLLoader loaderFP;
		AnchorPane ac_LFP;
		Scene sceneFP;
		
		// transformée en variable portee class
		// Main_controller ctrl_FP;
		
		try {
			
			//
			FP = new Stage();
			
			//
			FP.initModality(Modality.APPLICATION_MODAL);
			
			//
			loaderFP = new FXMLLoader();
	        loaderFP.setLocation(Main.class.getResource("view/main_fxml.fxml"));
	        ac_LFP = (AnchorPane) loaderFP.load();
	           
	        //
	        sceneFP = new Scene(ac_LFP);
	           
	        //
	        FP.setScene(sceneFP);
	            
	        //
	        FP.setTitle("fenetre principale");
	           
	        //
	        ctrl_FP = new Main_controller();
	        ctrl_FP = loaderFP.getController();
	            
	        //
	        ctrl_FP.setMain(this);
	        
	        //
	        ctrl_FP.set_FP(FP);
	            
	        //
	        FP.show();
				
			} catch(IOException except) {
				
				//
				except.printStackTrace();
			}
		}

		// =============================================================
		
		// ouvre la F01
		// fenetre d'acceuil de la sect° livre
	
		// ajout d'1 evenement clavier sur la touche ESC
		// methode appui_touche() implementée
		// ds fichier: F01_controller.java
		// déclarée ds le .fxml correspondant.
		// Sur l'AnchorPane
		public void init_F01() {
			
			// variables
			
			// transformée en variable de class
			// Stage F01;
			
			Scene sceneF01;
			AnchorPane AP;
			FXMLLoader loaderF01;
			F01_controller ctrlF01;
			
			try {
				
				//
				F01 = new Stage();
				
				//
				F01.initModality(Modality.APPLICATION_MODAL);
				
				//
				F01.initOwner(FP);
				
				//
				loaderF01 = new FXMLLoader();
				loaderF01.setLocation(Main.class.getResource("view/F01_fxml.fxml"));
				AP = (AnchorPane) loaderF01.load();
				
				//
				sceneF01 = new Scene(AP);
				
				//
				F01.setScene(sceneF01);
				
				//
				F01.setTitle("F01");
				
				//
				ctrlF01 = new F01_controller();
				ctrlF01 = loaderF01.getController();
				
				//
				ctrlF01.setMain(this);
				
				//
				ctrlF01.set_F01(F01);
				
				//
				F01.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}
		}
		
		// =============================================================
		
		// TESTS EVENEMENTS CLAVIER
		
		/*
		F01.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
		    System.out.println("Key pressed: " + event.toString());

		    switch(event.getCode().getCode()) {
		        case 27 : { // 27 = ESC key
		            F01.close();
		            break;
		        }
		        default:  {
		            System.out.println("Unrecognized key");
		        }
		    }
		});
		*/
		
		/*
		KeyCombination cntrlZ = new KeyCodeCombination(KeyCode.Z);
		myScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		    @Override
		    public void handle(KeyEvent event) {
		        if(contrlZ.match(event)){
		           //Do something
		        }
		    }
		});
		*/
		
		// =============================================================
		
		// fenetre qui permet de creer 1 livre
		public void init_F01A_creer_livre() {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: init_F01A_creer_livre()");
			
			// variables
			
			// transformée en variable de class
			// Stage F01A_creer_livre;
			
			Scene sceneF01A_creer_livre;
			AnchorPane AP;
			FXMLLoader loaderF01A_creer_livre;
			F01A_controller ctrlF01A_creer_livre;
			
			try {
				
				//
				F01A_creer_livre = new Stage();
				
				//
				F01A_creer_livre.initModality(Modality.APPLICATION_MODAL);
				
				//
				F01A_creer_livre.initOwner(F01);
				
				//
				loaderF01A_creer_livre = new FXMLLoader();
				loaderF01A_creer_livre.setLocation(Main.class.getResource("view/F01A_fxml.fxml"));
				AP = (AnchorPane) loaderF01A_creer_livre.load();
				
				//
				sceneF01A_creer_livre = new Scene(AP);
				
				//
				F01A_creer_livre.setScene(sceneF01A_creer_livre);
				
				//
				F01A_creer_livre.setTitle("F01: creer 1 livre");
				
				//
				ctrlF01A_creer_livre = new F01A_controller();
				ctrlF01A_creer_livre = loaderF01A_creer_livre.getController();
				
				// TRIPLET 01C: INVOCAT° DE LA METHODE
				// triplet 01A: la variable: ds fichier F01A_controller
				// triplet 01B: la méthode: ds fichier F01A_controller
				// ctrlF01A_creer_livre.setMain(this);
				
				// obligatoire pr fermer la fenetre
				ctrlF01A_creer_livre.set_F01A_creer_livre(F01A_creer_livre);
				
				//
				F01A_creer_livre.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}
		}
		
		// =============================================================
		
		// fenetre qui permet de MAJ 1 livre
		public void init_F01B_MAJ_livre(Livre livre) {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: init_F01B_MAJ_livre()");
			
			// variables
			
			// transformée en variable de class
			// Stage F01B_MAJ_livre;
			
			Scene sceneF01B_MAJ_livre;
			AnchorPane AC;
			FXMLLoader loaderF01B_MAJ_livre;
			F01B_controller ctrlF01B_MAJ_livre;
			
			try {
				
				//
				F01B_MAJ_livre = new Stage();
				
				//
				F01B_MAJ_livre.initModality(Modality.APPLICATION_MODAL);
				
				//
				F01B_MAJ_livre.initOwner(F01);
				
				//
				loaderF01B_MAJ_livre = new FXMLLoader();
				loaderF01B_MAJ_livre.setLocation(Main.class.getResource("view/F01B_fxml.fxml"));
				AC = (AnchorPane) loaderF01B_MAJ_livre.load();
				
				//
				sceneF01B_MAJ_livre = new Scene(AC);
				
				//
				F01B_MAJ_livre.setScene(sceneF01B_MAJ_livre);
				
				//
				F01B_MAJ_livre.setTitle("F01B: Editer 1 livre");
				
				//
				ctrlF01B_MAJ_livre = new F01B_controller();
				ctrlF01B_MAJ_livre = loaderF01B_MAJ_livre.getController();
				
				// TRIPLET 02C: INVOCAT° DE LA METHODE
				// triplet 02A: la variable: ds fichier F01B_controller
				// triplet 02B: la méthode: ds fichier F01B_controller
				ctrlF01B_MAJ_livre.setMain(this);
				
				// obligatoire pr fermer la fenetre
				ctrlF01B_MAJ_livre.set_F01B_MAJ_livre(F01B_MAJ_livre);
				
				// MARQUE 01
				// invoque la methode qui remplit les
				// TxtF presentant le livre a modifier
		        ctrlF01B_MAJ_livre.setLivre(livre);
				
				//
				F01B_MAJ_livre.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}
		}
		
		// =============================================================
		
		// GETTER DE LA FP
		// la fenetre qui permet de choisir entre
		// livre
		// emprunt
		// client
		public Stage getFP() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getFP()");
			
			//
			return FP;
			
		}
		
		// =============================================================

		// GETTER DE LA F01
		// la fenetre qui affiche la section livre
		public Stage getF01() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getF01()");
			
			//
			return F01;
			
		}
		
		// =============================================================
		
		// fenetre qui ouvre la section client
		public void init_F02() {
			
			// variables
			
			// transformée en variable de class
			// Stage F02;
			
			Scene sceneF02;
			AnchorPane AP;
			FXMLLoader loaderF02;
			
			// MARQUE C01, A
			// transformée en variable de class
			// (MARQUE C01, B)
			// F02_client_controller ctrlF02;
			
			try {
				
				//
				F02 = new Stage();
				
				//
				F02.initModality(Modality.APPLICATION_MODAL);
				
				//
				F02.initOwner(FP);
				
				//
				loaderF02 = new FXMLLoader();
				loaderF02.setLocation(Main.class.getResource("view/F02_client_fxml.fxml"));
				AP = (AnchorPane) loaderF02.load();
				
				//
				sceneF02 = new Scene(AP);
				
				//
				F02.setScene(sceneF02);
				
				//
				F02.setTitle("F02: Section clients");
				
				//
				ctrlF02 = new F02_client_controller();
				ctrlF02 = loaderF02.getController();
				
				//
				ctrlF02.setMain(this);
				
				//
				ctrlF02.setF02_client(F02);
				
				//
				F02.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}

		}
		
		// =============================================================
		
		// fenetre qui permet la creat° d1 client
		public void init_F02A() {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: init_F02A()");
			
			// variables
			
			// transformée en variable de class
			// Stage F02A;
			
			Scene sceneF02A;
			AnchorPane AP;
			FXMLLoader loaderF02A;
			
			// transformée en variable de portée class
			// F02A_controller ctrlF02A;
			
			try {
				
				//
				F02A = new Stage();
				
				//
				F02A.initModality(Modality.APPLICATION_MODAL);
				
				//
				F02A.initOwner(F02);
				
				//
				loaderF02A = new FXMLLoader();
				loaderF02A.setLocation(Main.class.getResource("view/F02A_fxml.fxml"));
				AP = (AnchorPane) loaderF02A.load();
				
				//
				sceneF02A = new Scene(AP);
				
				//
				F02A.setScene(sceneF02A);
				
				//
				F02A.setTitle("F02A: Creer 1 client");
				
				//
				ctrlF02A = new F02A_controller();
				ctrlF02A = loaderF02A.getController();
				
				// ajout test 20220705115600, etape 02
				// permet d'invoquer depuis le
				// fichier F02A_controller
				// les methodes qui st implementées ds le
				// fichier Main.java
				ctrlF02A.setMain(this);
				
				//
				ctrlF02A.setF02A(F02A);
				
				//
				F02A.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}
			
		}
		
		// =============================================================
		
		// methode qui permet d'editer 1 client
		// fenetre F02B
		public void init_F02B(Client client) {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: initF02B()");
			
			// variables
			
			// transformée en variable de portée class
			// Stage F02B;
			
			Scene sceneF02B;
			AnchorPane AP;
			FXMLLoader loaderF02B;
			
			// transformée en variable de portée class
			// F02B_controller ctrlF02B;
			
			//
			try {
				
				//
				F02B = new Stage();
				
				//
				F02B.initModality(Modality.APPLICATION_MODAL);
				
				//
				F02B.initOwner(F02);
				
				//
				loaderF02B = new FXMLLoader();
				loaderF02B.setLocation(Main.class.getResource("view/F02B_fxml.fxml"));
				AP = (AnchorPane) loaderF02B.load();
				
				//
				sceneF02B = new Scene(AP);
				
				//
				F02B.setScene(sceneF02B);
				
				//
				F02B.setTitle("F02B: Editer 1 client");
				
				//
				ctrlF02B = new F02B_controller();
				ctrlF02B = loaderF02B.getController();
				
				// ajout test 20220705165000, etape 02
				// permet d'invoquer depuis le
				// fichier F02B_controller
				// les methodes qui st implementées ds le
				// fichier Main.java
				ctrlF02B.setMain(this);
				
				//
				ctrlF02B.setF02B(F02B);
				
				// MARQUE 20220705175700, 01
				// invoque la methode qui remplit les
				// TxtF presentant le livre a modifier
		        ctrlF02B.setClient(client);
				
				//
				F02B.showAndWait();
				
			} catch(IOException except) {
				
				//
				except.printStackTrace();
				
			}
		}
		
		// =============================================================
		
		// GETTER de la F02: client, accueil
		public Stage getF02() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getF02()");
			
			//
			return F02; 
		}

		// =============================================================
		
		// GETTER de la F02A: creer 1 client
		public Stage getF02A() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getF02A()");
			
			//
			return F02A; 
		}
		
		// =============================================================
		
		// GETTER de la F02B: editer 1 client
		public Stage getF02B() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getF02B()");
			
			//
			return F02B; 
		}
		
		// =============================================================
		
		// fenetre qui ouvre la section emprunt
		public void init_F03() {
			
			// variables
			
			// transformée en variable de class
			// Stage F03;
			
			Scene sceneF03;
			AnchorPane AP;
			FXMLLoader loaderF03;
			
			// transformée en variable de class
			// F03_emprunt_controller ctrlF03;
			
			try {
				
				//
				F03 = new Stage();
				
				//
				F03.initModality(Modality.APPLICATION_MODAL);
				
				//
				F03.initOwner(FP);
				
				//
				loaderF03 = new FXMLLoader();
				loaderF03.setLocation(Main.class.getResource("view/F03_emprunt_fxml.fxml"));
				AP = (AnchorPane) loaderF03.load();
				
				//
				sceneF03 = new Scene(AP);
				
				//
				F03.setScene(sceneF03);
				
				//
				F03.setTitle("F03: Section Emprunts");
				
				//
				ctrlF03 = new F03_emprunt_controller();
				ctrlF03 = loaderF03.getController();
				
				//
				ctrlF03.setMain(this);
				
				//
				ctrlF03.setF03(F03);
				
				//
				F03.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}

		}
		
		// =============================================================
		
		// fenetre qui ouvre la section emprunt
		public void init_F03_creer_emprunt() {
			
			// variables
			
			// transformée en variable de class
			// Stage F03_creer_emprunt;
			
			Scene sceneF03_creer_emprunt;
			AnchorPane AP;
			FXMLLoader loaderF03_creer_emprunt;
			
			// transformée en variable de class
			// F03_emprunt_controller_creer_emprunt ctrlF03_creer_emprunt;
			
			try {
				
				//
				F03_creer_emprunt = new Stage();
				
				//
				F03_creer_emprunt.initModality(Modality.APPLICATION_MODAL);
				
				//
				F03_creer_emprunt.initOwner(F03);
				
				//
				loaderF03_creer_emprunt = new FXMLLoader();
				loaderF03_creer_emprunt.setLocation(Main.class.getResource("view/F03_emprunt_creer_emprunt_fxml.fxml"));
				AP = (AnchorPane) loaderF03_creer_emprunt.load();
				
				//
				sceneF03_creer_emprunt = new Scene(AP);
				
				//
				F03_creer_emprunt.setScene(sceneF03_creer_emprunt);
				
				//
				F03_creer_emprunt.setTitle("F03: Section Emprunts - creer emprunt");
				
				//
				ctrlF03_creer_emprunt = new F03_emprunt_controller_creer_emprunt();
				ctrlF03_creer_emprunt = loaderF03_creer_emprunt.getController();
				
				//
				ctrlF03_creer_emprunt.set_main(this);
				
				//
				ctrlF03_creer_emprunt.set_F03_creer_emprunt(F03_creer_emprunt);
				
				//
				F03_creer_emprunt.showAndWait();
				
			} catch (IOException except) {
				
				//
				except.printStackTrace();
				
			}

		}
		
		// =============================================================
		
		// GETTER de la F03: emprunt, accueil
		public Stage getF03() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getF03()");
			
			//
			return F03; 
		}
		
		// =============================================================
		
		// GETTER de la fenetre pr creer 1 emprunt
		public Stage getF03_creer_emprunt() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getF03_creer_emprunt()");
			
			//
			return F03_creer_emprunt; 
		}
		
		// =============================================================
		
		// retourne l'ObservableList empruntData qui a été déclarée
		// avec 1 portée class (dc en haut du fichier)
		// NOTE: avec les livres et les clients, leur methode respective
		// getLivre() et getClient() ont été implémentée ds leur
		// fichier controleur respectif, soit:
		// - getLivre() ds F01_controller.java
		// - getClient() ds F02_client_controller.java
		public ObservableList<Emprunt> getEmpruntData() {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: getEmpruntData()");
			
			// les variables
			ObservableList<Emprunt> liste_emprunt = FXCollections.observableArrayList();
			String selectQuery;
			Connection con;
			PreparedStatement p_stmt;
			ResultSet rs;
			Emprunt emprunt;
			
			// redact° de la requete
			selectQuery = "SELECT * FROM emprunt";
			
			// initialisat° de la variable de type Connection
			// obtenir la connex° grâce à la classe Connexion
			con = Connexion.getCon();
			
			try {
				
				// initialisat° d'1 variable de type
	        	// PreparedStatement
	        	// prépare la requete
	            p_stmt = con.prepareStatement(selectQuery);
	            
	            // initialisat° d'1 variable de type
	            // ResultSet
	            // execute la requete
	            rs = p_stmt.executeQuery();
	            
	            //
	            while (rs.next()) {
	            	
	                emprunt = new Emprunt();
	                
	                emprunt.setId_emprunt_(rs.getInt(1));                
	                
	                emprunt.setId_livre_(rs.getInt(2));
	                
	                emprunt.setId_abonne_(rs.getInt(3));
	                
	                emprunt.setDate_sortie_(rs.getDate(4));
	                
	                emprunt.setDate_rendu_(rs.getDate(5));
	                
	                // remplit la ObservableList
	                // liste_emprunt (de portée locale)
	                liste_emprunt.add(emprunt);
	                
	                // remplit la ObservableList
	                // empruntData (de portée class)
	                empruntData.add(emprunt);
	                
	            }
				
			} catch(SQLException except) {
				
				//
				System.out.println("Except°:");
	        	System.out.println("Fichier: Main.java");
	        	System.out.println("Methode: getEmpruntData()");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
			
			}
			
			/*
			// imprime la ObservableList liste_emprunt
			for(Emprunt empt : liste_emprunt) {
         	  System.out.println(empt.getId_emprunt());
         	  System.out.println(empt.getId_livre());
         	  System.out.println(empt.getId_abonne());
         	  System.out.println(empt.getDate_sortie());
         	  System.out.println(empt.getDate_rendu());
			}
			*/
			
			/*
			// imprime la ObservableList empruntData
			for(Emprunt empt : empruntData) {
         	  System.out.println(empt.getId_emprunt());
         	  System.out.println(empt.getId_livre());
         	  System.out.println(empt.getId_abonne());
         	  System.out.println(empt.getDate_sortie());
         	  System.out.println(empt.getDate_rendu());
			}
			*/
			
			//
			return liste_emprunt;
			// return empruntData;
			
		}
		
		// =============================================================
		
		// ds la F03, l'accueil des emprunts:
		// parametres les lbl qui affichent
		// les détails d'1 emprunt qd l'utilisateur
		// en selectionne 1 ds la TV. sinon ils st
		// vides
		public void afficherDetailsEmprunt(Emprunt emprunt) {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("méthode: afficherDetailsEmprunt()");
			
			//
			if(emprunt != null) {
				
				//
				ctrlF03.getLbl_contient_id_emprunt().setText(Integer.toString(emprunt.getId_emprunt_()));
				ctrlF03.getLbl_contient_id_livre().setText(Integer.toString(emprunt.getId_livre_()));
				ctrlF03.getLbl_contient_id_abonne().setText(Integer.toString(emprunt.getId_abonne_()));
				
				// formate l'objet Date en String pr pvoir l'afficher
				// ds le lbl correspondant
				DateFormat dateFormat_sortie = new SimpleDateFormat("yyyy-MM-dd");
				String dateSortieToString = dateFormat_sortie.format(emprunt.getDate_sortie());
				ctrlF03.getLbl_contient_date_sortie().setText(dateSortieToString);
				
				// formate l'objet Date en String pr pvoir l'afficher
				// ds le lbl correspondant
				DateFormat dateFormat_rendu = new SimpleDateFormat("yyyy-MM-dd");
				String dateRenduToString = dateFormat_rendu.format(emprunt.getDate_rendu());
				ctrlF03.getLbl_contient_date_rendu().setText(dateRenduToString);
				
			} else {
				
				//
				ctrlF03.getLbl_contient_id_emprunt().setText("");
				ctrlF03.getLbl_contient_id_livre().setText("");
				ctrlF03.getLbl_contient_id_abonne().setText("");
				ctrlF03.getLbl_contient_date_sortie().setText("");
				ctrlF03.getLbl_contient_date_rendu().setText("");				
				
			}
			
		}		
		
		// =============================================================
		/*
		public ObservableList<EmpruntCompose01> getEmpruntCompose01Data() {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: getEmpruntCompose01Data()");
			
			// les variables
			ObservableList<EmpruntCompose01> liste_empruntCompose01 = FXCollections.observableArrayList();
			String selectQuery01;
			Connection con01;
			PreparedStatement p_stmt01;
			ResultSet rs01;
			EmpruntCompose01 empruntCompose01;
			Client client01 = new Client();
			Livre livre01 = new Livre();
			
			// redact° de la requete
			selectQuery01 = "SELECT client.prenom, livre.titre\n"
					+ "FROM client, livre\n"
					+ "INNER JOIN emprunt\n"
					+ "WHERE client.id_abonne = emprunt.id_abonne\n"
					+ "AND emprunt.id_livre = livre.id_livre";
			
			// initialisat° de la variable de type Connection
			// obtenir la connex° grâce à la classe Connexion
			con01 = Connexion.getCon();
			
			try {
				
				// initialisat° d'1 variable de type
	        	// PreparedStatement
	        	// prépare la requete
	            p_stmt01 = con01.prepareStatement(selectQuery01);
	            
	            // initialisat° d'1 variable de type
	            // ResultSet
	            // execute la requete
	            rs01 = p_stmt01.executeQuery();
	            
	            //
	            while (rs01.next()) {
	            	
	                empruntCompose01 = new EmpruntCompose01(client01, livre01);                
	                
	                empruntCompose01.setLivre(livre01);
	                
	                empruntCompose01.setClient(client01);
	                
	                // remplit la ObservableList
	                // liste_emprunt (de portée locale)
	                liste_empruntCompose01.add(empruntCompose01);
	                
	                // remplit la ObservableList
	                // empruntData (de portée class)
	                empruntCompose01Data.add(empruntCompose01);
	                
	            }
				
			} catch(SQLException except) {
				
				//
				System.out.println("Except°:");
	        	System.out.println("Fichier: Main.java");
	        	System.out.println("Methode: getEmpruntCompose01Data()");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
			}
			
			
			// imprime la ObservableList liste_emprunt
			for(EmpruntCompose01 empt : liste_empruntCompose01) {
				// null
				// System.out.println("-- " + empt.getClient().clientPrenomProperty());
				// null
				// System.out.println("-- " + empt.getClient().getClientPrenom());
				// adresses memoire
				System.out.println("0-- " + empt.getClient());
				System.out.println("1-- " + empt.getIdEmpruntCompose01());
				System.out.println("2-- " + empt.getClient().getClientId());
				System.out.println("3-- " + empt.getClient().getClientPrenom());
				System.out.println("4-- " + empt.getClient().getClientMail());
				System.out.println("5-- " + empt.getClient().getClientNom());
				System.out.println("6-- " + empt);
				// null
				// System.out.println("-- " + empt.getClient().getClientPrenom());
         	
				// System.out.println(empt.getLivre());
			}
			
			
			
			// imprime la ObservableList empruntData
			//for(EmpruntCompose01 empt : empruntCompose01Data) {
         	  
         	  //System.out.println("- " + empt.getClient());
         	  //System.out.println(empt.getLivre());
			//}
			
			
			//
			return liste_empruntCompose01;
			// return empruntCompose01Data;
		}
		*/
		// =============================================================
		
		public ObservableList<String> test01() {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: test01()");
			
			// les variables
			ObservableList<String> liste_test01 = FXCollections.observableArrayList();
			String selectQuerytest01;
			Connection contest01;
			PreparedStatement p_stmttest01;
			ResultSet rstest01;
			
			// redact° de la requete
			selectQuerytest01 = "SELECT client.prenom, livre.titre\n"
					+ "FROM client, livre\n"
					+ "INNER JOIN emprunt\n"
					+ "WHERE client.id_abonne = emprunt.id_abonne\n"
					+ "AND emprunt.id_livre = livre.id_livre";
			
			// initialisat° de la variable de type Connection
			// obtenir la connex° grâce à la classe Connexion
			contest01 = Connexion.getCon();
			
			try {
				
				// initialisat° d'1 variable de type
	        	// PreparedStatement
	        	// prépare la requete
	            p_stmttest01 = contest01.prepareStatement(selectQuerytest01);
	            
	            // initialisat° d'1 variable de type
	            // ResultSet
	            // execute la requete
	            rstest01 = p_stmttest01.executeQuery();
	            
	            while (rstest01.next()) {
	            	// Possible value [livre.titre, titre, client.prenom, prenom]
	                liste_test01.add(rstest01.getString("livre.titre"));
	              }
	            
			} catch(SQLException except) {
				
				//
				System.out.println("Except°:");
	        	System.out.println("Fichier: Main.java");
	        	System.out.println("Methode: test01()");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
	            
			}
			
			System.out.println("Found " + liste_test01.size() + " livre.titre");
			
			
			// imprime la ObservableList liste_emprunt
			for(String empt : liste_test01) {
         	  System.out.println("01: " + empt);
			}
			

		    return liste_test01;
		}

		// =============================================================
		
		public void testIMPRESSION() {
			
			System.out.println("fichier: Main.java");
			System.out.println("methode: testIMPRESSION()");	
		}
		
		// =============================================================
		
		public static void main(String[] args) {
			launch(args);
		}
		
		// =============================================================
		
		// // ajout test 20220705115600, etape 01
		public ObservableList<Client> getClient_test() {
			
			//
			System.out.println("fichier: Main.java");
			System.out.println("methode: getClient_test()");
			
			// les variables
			
			ObservableList<Client> liste_client_test = FXCollections.observableArrayList();
			String select_query;
			Connection con;
			PreparedStatement p_stmt;
			ResultSet rs;
			Client client;
			
			// test 20220705124000, 01
			// la TV n'est pas MAJ
			// la requete SQL ne recupere pas non plus
			// le dernier ergt
			liste_client_test.removeAll(liste_client_test);
			
			// redact° de la requete
			select_query = "SELECT * FROM client";
			
			// initialisat° d'1 variable de type Connection
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
	            rs.close();
	            p_stmt.close();
	            con.close();
	            
	            //
	            while (rs.next()) {
	            	
	                client = new Client();
	                
	                // client.setId_livre(rs.getInt(1));
	                client.setClientId(rs.getInt(1));
	                
	                // client.setAuteur_livre(rs.getString(2));
	                client.setClientPrenom(rs.getString(2));
	                
	                // client.setTitre_livre(rs.getString(3));
	                client.setClientNom(rs.getString(3));
	                
	                client.setClientTelephone(rs.getString(4));
	                
	                client.setClientMail(rs.getString(5));
	                
	                liste_client_test.add(client);
	                
	            }
	            
			} catch(SQLException except) {
				
				//
				System.out.println("Except°:");
	        	System.out.println("Fichier: Main.java");
	        	System.out.println("Methode: getClient_test()");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
			}
			
			// affiche les adresses memoire
			// System.out.println(liste_client_test);
			
			// pr debug
            // affiche les ergts de la table client
			for (Client clien: liste_client_test) {
	            System.out.println(clien.getClientId());
	            System.out.println(clien.getClientPrenom());
	            System.out.println(clien.getClientNom());
	            System.out.println(clien.getClientTelephone());
	            System.out.println(clien.getClientMail());
	        }
			
			return liste_client_test;
			
		}
		
		// =============================================================
}
