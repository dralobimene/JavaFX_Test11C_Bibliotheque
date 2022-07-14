package application.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;

import javax.xml.transform.Result;

import application.Main;
import application.model.Client;
import application.model.Emprunt;
import application.model.EmpruntCompose01;
import application.model.Livre;
import application.util.Connexion;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

// classe qui ouvre la section emprunt
public class F03_emprunt_controller implements Initializable {

	// ================================================
	// variables
	private Main main;
	
	//
	private Stage F03;
	
	//
	ObservableList<Emprunt> liste_emprunt = FXCollections.observableArrayList();
	
	// ================================================
	
	@FXML
	private AnchorPane AP;
	
	@FXML
	private Pane P;
	
	@FXML
	private TableView<Emprunt> TV;
	
	@FXML
	private TableColumn<Emprunt, Integer> col_id_emprunt;
	
	@FXML
	private TableColumn<Emprunt, Integer> col_id_livre;
	
	@FXML
	private TableColumn<Emprunt, Integer> col_id_abonne;
	
	@FXML
	private TableColumn<Emprunt, Date> col_date_sortie;
	
	@FXML
	private TableColumn<Emprunt, Date> col_date_rendu;
	
	@FXML
	private Label lbl_presente_id_emprunt;
	
	@FXML
	private Label lbl_presente_id_livre;
	
	@FXML
	private Label lbl_presente_id_abonne;
	
	@FXML
	private Label lbl_presente_date_sortie;
	
	@FXML
	private Label lbl_presente_date_rendu;
	
	@FXML
	private Label lbl_contient_id_emprunt;
	
	@FXML
	private Label lbl_contient_id_livre;
	
	@FXML
	private Label lbl_contient_id_abonne;
	
	@FXML
	private Label lbl_contient_date_sortie;
	
	@FXML
	private Label lbl_contient_date_rendu;
	
	//************************************************
	
	// variables pr TV01
	
	// contient le retour de test01_()
	// sert a remplir la TV01
	ObservableList<ObservableList<String>> list_;

	// Les 3 servent ds la methode test01_()
	// contient les 2 ObservableList qui suivent
	ObservableList<ObservableList<String>> liste_testGeneral_ = FXCollections.observableArrayList();
	
	// contient le titre des livres empruntés
	ObservableList<String> liste_test01_ = FXCollections.observableArrayList();
	
	// contient le prenom des emprunteurs
	ObservableList<String> liste_test02_ = FXCollections.observableArrayList();
	
	@FXML
	private TableView<String> TV01 = new TableView<String>();
	
	@FXML
	private TableColumn<String, String> TV01_col_prenom;
	
	@FXML
	private TableColumn<String, String> TV01_col_titre;
	
	//*******************************************************************
	
	// variables pr TV02
	
	// variables	
	
	@FXML
	private TableView<String> TV02;
	
	@FXML
	private TableColumn<String, String> TV02_col_prenom;
	
	@FXML
	private TableColumn<String, String> TV02_col_titre;
	
	@FXML
	private Button btn_creer;
	
	@FXML
	private Button btn_effacer;
	
	@FXML
	private Button btn_editer;
	
	@FXML
	private Button btn_quitter;
	
	//*******************************************************************
	
	// variables pr TV03
	
	//
	private ObservableList<ObservableList<Result>> queryResult;
	
	@FXML
	private TableView<ObservableList <Result>> TV03;
	
	@FXML
	private TableColumn<ObservableList<Result>, Result> TV03_col_prenom;
	
	@FXML
	private TableColumn<ObservableList<Result>, Result> TV03_col_titre;
	
	
	//*******************************************************************
	
	// ================================================
	
	// Constructeur(s)
	
	// 01
	public F03_emprunt_controller() { }
	
	// ================================================
	
	//
	@Override
	public void initialize(URL p_url, ResourceBundle p_r_bdle) {
		
		//
		
		FXMLLoader loaderFP;
		Main_controller ctrl_FP;
		
		loaderFP = new FXMLLoader();
        loaderFP.setLocation(Main.class.getResource("view/main_fxml.fxml"));
        try {
			P = (Pane) loaderFP.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        ctrl_FP = new Main_controller();
        ctrl_FP = loaderFP.getController();
		
		//
		
		System.out.println("fichier: F03_emprunt_controller");
		System.out.println("methode: initialize()");
		
		// la méthode appelée est située ds le Main.java
		// erreur: this.main is null
		// REMARQUE:
		// Ca serait bien de savoir utiliser la methode
		// qui est implementée ds le Main.java
		// main.afficherDetailsEmprunt(null);
		
		// utilise la copie de la methode du fichier Main.java
		// methode: main.afficherDetailsEmprunt()
		// la copie nommée afficherDetailsEmprunt_()
		// est implementée ds le fichier: F03_emprunt_controller.java
		// l'emprunt est défini a null par defaut
		afficherDetailsEmprunt_(null);
		
		// les colonnes de la TV
		col_id_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("id_emprunt"));
		col_id_livre.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("id_livre"));
		col_id_abonne.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("id_abonne"));
		col_date_sortie.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("date_sortie"));
		col_date_rendu.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("date_rendu"));
		
		// les colonnes de la TV01
		
		System.out.println("Depuis la methode initialize()");
		System.out.println("Recept° de la valeur de retour de la methode test01()");
		list_ = test01_();
		
		System.out.println("Longueur de list_: " + list_.size());
		
		// imprime la ObservableList list
		for(ObservableList<String> empt : list_) {
     	  System.out.println("01: " + empt);
		}
		
		System.out.println("Contenu de list_.get(0): ");
		System.out.println(list_.get(0));
		System.out.println("Nbre d'elts ds list_.get(0)");
		System.out.println(list_.get(0).size());
		
		System.out.println("Contenu de list_.get(1): ");
		System.out.println(list_.get(1));
		System.out.println("Nbre d'elts ds list_.get(1)");
		System.out.println(list_.get(1).size());
		
		System.out.println("Acces au 1° elt de list._get(0)");
		System.out.println(list_.get(0).get(0));
		
		System.out.println("Acces au 1° elt de list._get(1)");
		System.out.println(list_.get(1).get(0));
		
		// entraine 1 erreur: ClassCastException
		// TV01_col_prenom.setCellValueFactory((Callback<CellDataFeatures<String, String>, ObservableValue<String>>) test01_().get(0));
		
		// erreur a la syntaxe
		// TV01_col_prenom.setCellValueFactory(feature -> new SimpleObjectProperty<String>(test01_().get(0)));
		
		
		// affiche les titres des livres empruntes
		
		TV01_col_titre.setCellValueFactory(feature -> {
		    String value = feature.getValue();
		    return new SimpleStringProperty(value);
		});
		
		
		/*
		// fonctionne
		TV01_col_prenom.setCellValueFactory(feature -> {
		    String value = feature.getValue();
		    System.out.println("value 01: " + value);
		    return new SimpleObjectProperty<>(value);
		});
		*/
		
		/*
		TV01_col_prenom.setCellValueFactory(feature -> {
			String value = feature.getValue();
		    return new SimpleStringProperty(list_.get(0).get(1));
		});
		*/
		
		// erreur syntaxe
		// TV01_col_prenom.setCellValueFactory(() -> list_.get(0));
		
		// erreur: non correspondance des types
		/*
		TV01_col_prenom.setCellValueFactory(feature -> {
		    String value = test01_().get(0);
		    return new SimpleStringProperty(value);
		});
		*/
		
		// boucle sans fin
		/*
		TV01_col_prenom.setCellValueFactory(feature -> {
		    //String elem = feature.getValue();
		    return new SimpleObjectProperty<String>(test01_().get(0), null);
		});
		*/
		
		// boucle sans fin
		/*
		TV01_col_prenom.setCellValueFactory(feature -> {
		    String elem = feature.getValue();
		    return new SimpleObjectProperty<>(elem.valueOf(test01_().get(0)));
		});
		*/
		
		// entraine 1 erreur
		/*
		TV01_col_prenom.setCellValueFactory(feature -> {
		    return (ObservableValue<String>) test01_().get(0);
		});
		*/
				
		/*
		TV01_col_titre.setCellValueFactory(feature -> {
		    String value = feature.getValue();
		    return new SimpleObjectProperty<>(value);
		});
		*/
			
		// la colonne prenom de la TV02
		
		// A quoi cela sert il?
		// TV02.getColumns().setAll(TV02_col_prenom, TV02_col_titre);
				
		//
		ObservableList<String> list__01 = FXCollections.observableArrayList();
		
		// ajout des prenoms
		list__01.addAll(list_.get(1));
		
		//
		System.out.println("Nbre d'elts ds list__01:");
		System.out.println(list__01.size());
		
		// contient les titres
		System.out.println("contenu de list__01");
		System.out.println(list__01);
		
		
		//
		TV02_col_prenom.setCellValueFactory(feature -> {
		    String value = feature.getValue();
		    return new SimpleStringProperty(value);
		});
		
		
		// la colonne titre de la TV02
		
		//
		ObservableList<String> list__02 = FXCollections.observableArrayList();
		
		// ajout des titres
		list__02.addAll(list_.get(0));
		
		//
		System.out.println("Nbre d'elts ds list__02:");
		System.out.println(list__02.size());
		
		// contient les titres
		System.out.println("contenu de list__02");
		System.out.println(list__02);
		
		// fonctionne
		
		TV02_col_titre.setCellValueFactory(feature1 -> {
		    String value1 = feature1.getValue();
		    return new SimpleStringProperty(value1);
		});
				
		// erreur:
		// Can not retrieve property 'prenom' in PropertyValueFactory:
		// javafx.scene.control.cell.PropertyValueFactory@4a15866a with provided class type: class java.lang.String
        // TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, String>("prenom"));
		
		// erreur a la syntaxe:
		// Type mismatch: cannot convert from ObservableList<String> to ObservableValue<String>
		// TV02_col_prenom.setCellValueFactory(cellData -> (list__01));
		
		// erreur:
		// AVERTISSEMENT: Can not retrieve property 'client.prenom' in PropertyValueFactory:
		// javafx.scene.control.cell.PropertyValueFactory@4a15866a with provided class type: class java.lang.String
		// java.lang.IllegalStateException: Cannot read from unreadable property client.prenom
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, String>("client.prenom"));
		
		// erreur:
		// AVERTISSEMENT: Can not retrieve property 'id_abonne' in PropertyValueFactory:
		// javafx.scene.control.cell.PropertyValueFactory@53779ac3 with provided class type: class java.lang.String
		// java.lang.IllegalStateException: Cannot read from unreadable property id_abonne
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, String>("id_abonne"));
		
		// erreur
		// AVERTISSEMENT: Can not retrieve property 'titre' in PropertyValueFactory:
		// javafx.scene.control.cell.PropertyValueFactory@4a15866a with provided class type: class java.lang.String
		// java.lang.IllegalStateException: Cannot read from unreadable property titre
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, String>("titre"));
		
		// erreur
		// AVERTISSEMENT: Can not retrieve property 'C1' in PropertyValueFactory:
		// javafx.scene.control.cell.PropertyValueFactory@4d9d6253 with provided class type: class java.lang.String
		// java.lang.IllegalStateException: Cannot read from unreadable property C1
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, String>("C1"));
		
		// erreur
		// AVERTISSEMENT: Can not retrieve property 'C2' in PropertyValueFactory:
		// javafx.scene.control.cell.PropertyValueFactory@53779ac3 with provided class type: class java.lang.String
		// java.lang.IllegalStateException: Cannot read from unreadable property C2
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, String>("C2"));
		
		// erreurs:
		// la methode n'est pas applicable pr les arguments
		// constructeur indefini
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<String, ObservableList>(test01_().get(0)));
		
		// erreurs:
		// la methode n'est pas applicable pr les arguments
		// constructeur indefini
		// TV02_col_prenom.setCellValueFactory(new PropertyValueFactory<ObservableList, String>(test01_().get(0)));
		
		// erreur: type mismatch
		// TV02_col_prenom.setCellValueFactory(cellData -> (list__01));
		
		// erreur
		//  java.lang.ClassCastException:
		// class com.sun.javafx.collections.ObservableListWrapper cannot be cast to
		// class javafx.util.Callback (com.sun.javafx.collections.ObservableListWrapper
		// and javafx.util.Callback are in module javafx.base@18.0.1 of loader 'app')
		// TV02_col_prenom.setCellValueFactory((Callback<CellDataFeatures<String, String>, ObservableValue<String>>) list__01);
		
		//*********************************************************************************
		//TV03
		
		TV03.setItems(queryResult);
		//test03_();
		
		
		
		
		//*********************************************************************************
		
		/*
		 * ChangeListener sur la TableView
		 * en utilisant la méthode afficherDetailsEmprunt_().
		 * nous obtenons la selectedItemProperty
		 * de la table d'emprunt et lui ajoutons un listener.
		 * Chaque fois que l’utilisateur sélectionne un
		 * emprunt dans la table, notre expression lambda
		 * est exécutée. Nous prenons l'emprunt nouvellement
		 * sélectionnée pour le transmettre à la
		 * méthode afficherDetailsEmprunt_(...).
		 * PERMET:
		 * Les Labels st mis a jour.
		 * grace a la methode afficherDetailsEmprunt_()
		 */
		TV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsEmprunt_(newValue));
		
		// erreur: this.main is null
		// meme avec le parametrage des colonnes
		// (sect° juste au-dessus)
		// TV.setItems(main.getEmpruntData());
		
		// la methode est executee
		TV01.setItems(list_.get(0));
		
		// la methode est executée
		// ms la derniere prend le pas sur la premiere
		// TV02.setItems(list__01);
		TV02.setItems(list__02);
		
	}
		
	// ================================================
	
	public TableView<Emprunt> getTV() {
		return TV;
	}

	public TableColumn<Emprunt, Integer> getCol_id_emprunt() {
		return col_id_emprunt;
	}

	public TableColumn<Emprunt, Integer> getCol_id_livre() {
		return col_id_livre;
	}

	public TableColumn<Emprunt, Integer> getCol_id_abonne() {
		return col_id_abonne;
	}

	public TableColumn<Emprunt, Date> getCol_date_sortie() {
		return col_date_sortie;
	}

	public TableColumn<Emprunt, Date> getCol_date_rendu() {
		return col_date_rendu;
	}

	public Label getLbl_presente_id_emprunt() {
		return lbl_presente_id_emprunt;
	}

	public Label getLbl_presente_id_livre() {
		return lbl_presente_id_livre;
	}

	public Label getLbl_presente_id_abonne() {
		return lbl_presente_id_abonne;
	}

	public Label getLbl_presente_date_sortie() {
		return lbl_presente_date_sortie;
	}

	public Label getLbl_presente_date_rendu() {
		return lbl_presente_date_rendu;
	}

	public Label getLbl_contient_id_emprunt() {
		return lbl_contient_id_emprunt;
	}

	public Label getLbl_contient_id_livre() {
		return lbl_contient_id_livre;
	}

	public Label getLbl_contient_id_abonne() {
		return lbl_contient_id_abonne;
	}

	public Label getLbl_contient_date_sortie() {
		return lbl_contient_date_sortie;
	}

	public Label getLbl_contient_date_rendu() {
		return lbl_contient_date_rendu;
	}

	public void setTV(TableView<Emprunt> tV) {
		TV = tV;
	}

	public void setCol_id_emprunt(TableColumn<Emprunt, Integer> col_id_emprunt) {
		this.col_id_emprunt = col_id_emprunt;
	}

	public void setCol_id_livre(TableColumn<Emprunt, Integer> col_id_livre) {
		this.col_id_livre = col_id_livre;
	}

	public void setCol_id_abonne(TableColumn<Emprunt, Integer> col_id_abonne) {
		this.col_id_abonne = col_id_abonne;
	}

	public void setCol_date_sortie(TableColumn<Emprunt, Date> col_date_sortie) {
		this.col_date_sortie = col_date_sortie;
	}

	public void setCol_date_rendu(TableColumn<Emprunt, Date> col_date_rendu) {
		this.col_date_rendu = col_date_rendu;
	}

	public void setLbl_presente_id_emprunt(Label lbl_presente_id_emprunt) {
		this.lbl_presente_id_emprunt = lbl_presente_id_emprunt;
	}

	public void setLbl_presente_id_livre(Label lbl_presente_id_livre) {
		this.lbl_presente_id_livre = lbl_presente_id_livre;
	}

	public void setLbl_presente_id_abonne(Label lbl_presente_id_abonne) {
		this.lbl_presente_id_abonne = lbl_presente_id_abonne;
	}

	public void setLbl_presente_date_sortie(Label lbl_presente_date_sortie) {
		this.lbl_presente_date_sortie = lbl_presente_date_sortie;
	}

	public void setLbl_presente_date_rendu(Label lbl_presente_date_rendu) {
		this.lbl_presente_date_rendu = lbl_presente_date_rendu;
	}

	public void setLbl_contient_id_emprunt(Label lbl_contient_id_emprunt) {
		this.lbl_contient_id_emprunt = lbl_contient_id_emprunt;
	}

	public void setLbl_contient_id_livre(Label lbl_contient_id_livre) {
		this.lbl_contient_id_livre = lbl_contient_id_livre;
	}

	public void setLbl_contient_id_abonne(Label lbl_contient_id_abonne) {
		this.lbl_contient_id_abonne = lbl_contient_id_abonne;
	}

	public void setLbl_contient_date_sortie(Label lbl_contient_date_sortie) {
		this.lbl_contient_date_sortie = lbl_contient_date_sortie;
	}

	public void setLbl_contient_date_rendu(Label lbl_contient_date_rendu) {
		this.lbl_contient_date_rendu = lbl_contient_date_rendu;
	}
	
	// ================================================

	public void setMain(Main p_main) {
		
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("méthode: setMain()");
		
		//
		this.main = p_main;
		
		// la methode appelée est implémentée ds Main.java
		// si placée ds la methode initialize()
		// retourne erreur: this.main is null
		// placée ici, pas d'erreur ms les lbl
		// ne se mettent pas a jour
		// main.afficherDetailsEmprunt(null);
		// dc
		// utilisat° de la copie de la méthode
		// (ds la methode initialize())
		// la copie est implémentée ds ce fichier meme
		
		// les colonnes de la TV
		// sect° qui peut-être placée ici ou
		// ds la méthode initialize()
		/*
		col_id_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("id_emprunt"));
		col_id_livre.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("id_livre"));
		col_id_abonne.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("id_abonne"));
		col_date_sortie.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("date_sortie"));
		col_date_rendu.setCellValueFactory(new PropertyValueFactory<Emprunt, Date>("date_rendu"));
		*/
		
		// les colonnes de la TV01
		// sect° qui peut-être placée ici ou
		// ds la méthode initialize()
		
		// la TV est remplie grace aussi au
		// parametrage de chacune de ses colonnes
		// realisé ds la methode initialize()
		TV.setItems(main.getEmpruntData());	
		
		//
		// Ligne qui fonctionne
		// TV01.setItems(list_.get(0));
		
		// TV01.setItems(list_);
		// TV01.setItems(test01_());
		// TV01.setItems(list_.setAll(list_));
		
		/*
		for(int i = 0; i < list_.size(); i++) {
			TV01.setItems(list_.get(i));
		}
		*/
		
		// s'applique a TV02
		// test02_();
		// ATTENTION LA METHODE EST
		// PR LE MOMENT COMMENTÉE
		
		//
		// TV02.getColumns().setAll(TV02_col_prenom, TV02_col_titre);
		
		//
		// TV02.setItems(list_.get(0));
		
		// erreur: nom dupliqué pr les colonnes
		// TV02.getColumns().addAll(TV02_col_prenom, TV02_col_titre);
		// TV02.setItems(liste_test01_);
		// list__01
	}
	
	// ================================================
	
	public void setF03(Stage p_stage) {
		
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("méthode: setF03()");
		
		//
		this.F03 = p_stage;
		
	}
	
	// ================================================
	
	// COPIE de la methode implementée ds
	// fichier: Main.java
	// ds la F03, l'accueil des emprunts:
	// parametres les lbl qui affichent
	// les détails d'1 emprunt qd l'utilisateur
	// en selectionne 1 ds la TV. sinon ils st
	// vides
	public void afficherDetailsEmprunt_(Emprunt emprunt) {
		
		//
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("méthode: afficherDetailsEmprunt()");
		
		//
		if(emprunt != null) {
			
			// NOTES:
			// les suffixes 01_ pr la requete 01 et ses traitements
			// 01: MAJ du label qui affiche l'id de l'emprunt
			// les suffixes 02_ pr la requete 02 et ses traitements
			// 02: MAJ du label qui affiche le titre
			// les suffixes 03_ pr la requete 03 et ses traitements
			// 03: MAJ du label qui affiche le prenom
			
			// variables
			
			String selectQuerytest01_;
			String selectQuerytest02_;
			String selectQuerytest03_;
			Connection contest01_;
			Connection contest02_;
			Connection contest03_;
			PreparedStatement p_stmttest01_;
			PreparedStatement p_stmttest02_;
			PreparedStatement p_stmttest03_;
			ResultSet rstest01_;
			ResultSet rstest02_;
			ResultSet rstest03_;
			String contient_titre02 = null;
			String contient_titre03 = null;
			
			// redact° de la requete 01
			selectQuerytest01_ = "SELECT client.prenom, livre.titre\n"
					+ "FROM client, livre\n"
					+ "INNER JOIN emprunt\n"
					+ "WHERE client.id_abonne = emprunt.id_abonne\n"
					+ "AND emprunt.id_livre = livre.id_livre";
			
			// initialisat° de la variable de type Connection
			// obtenir la connex° grâce à la classe Connexion
			contest01_ = Connexion.getCon();
			
			try {
				
				// initialisat° d'1 variable de type
	        	// PreparedStatement
	        	// prépare la requete
	            p_stmttest01_ = contest01_.prepareStatement(selectQuerytest01_);
	            
	            // initialisat° d'1 variable de type
	            // ResultSet
	            // execute la requete
	            rstest01_ = p_stmttest01_.executeQuery();
	            
	            while (rstest01_.next()) {
	            	
	            	// Possible value [livre.titre, titre, client.prenom, prenom]
	            	System.out.println("Les valeurs du rstest01_ st:");
	            	System.out.println(rstest01_.getString("titre"));
	            	System.out.println(rstest01_.getString("prenom"));
	                
	              }
				
			} catch(SQLException except) {
				
				//
				System.out.println("Except°:");
	        	System.out.println("Fichier: F03_emprunt_controller.java");
	        	System.out.println("Methode: afficherDetailsEmprunt_()");
	        	System.out.println("sect° id de l'emprunt");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
	            
			}
			
			// MAJ du Label qui affiche l'ID de l'emprunt
			// effectué grace a la requete 01
			System.out.println(Integer.toString(emprunt.getId_emprunt_()));
			getLbl_contient_id_emprunt().setText(Integer.toString(emprunt.getId_emprunt_()));
			
			// ****************************************************
			
			// MAJ du Label qui affiche le titre du livre emprunté
			// effectué grace a la requete 02
			
			// la requete
			selectQuerytest02_ = "SELECT livre.titre\n"
					+ "FROM emprunt, livre\n"
					+ "WHERE emprunt.id_livre = livre.id_livre\n"
					+ "AND emprunt.id_emprunt = '"
					+ emprunt.getId_emprunt_()
					+ "';";
			
			//
			/*
			System.out.println("***");
			System.out.println(selectQuerytest02_);
			*/
			
			// initialisat° de la variable de type Connection
			// obtenir la connex° grâce à la classe Connexion
			contest02_ = Connexion.getCon();
			
			try {
				
				// initialisat° d'1 variable de type
	        	// PreparedStatement
	        	// prépare la requete
	            p_stmttest02_ = contest02_.prepareStatement(selectQuerytest02_);
	            
	            // initialisat° d'1 variable de type
	            // ResultSet
	            // execute la requete
	            rstest02_ = p_stmttest02_.executeQuery();
	            
	            while (rstest02_.next()) {
	            	
	            	/*
	            	System.out.println("***");
		            System.out.println(rstest02_.getString("titre"));
		            */
	            	
	            	//
		            contient_titre02 = rstest02_.getString("titre");		            
		            
	            }	            
				
			} catch(SQLException except) {
				
				//
				System.out.println("Except°:");
	        	System.out.println("Fichier: F03_emprunt_controller.java");
	        	System.out.println("Methode: afficherDetailsEmprunt_()");
	        	System.out.println("sect° titre");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
	            
			}
			
			/*
			System.out.println("***");
            System.out.println(contient_titre02);
            */
			
            // MAJ du Label contenant le titre après execut°
            // de la requete
            getLbl_contient_id_livre().setText(contient_titre02);
            
			// ****************************************************
            
            // MAJ du Label qui affiche le prenom de l'emprunteur
         	// effectué grace a la requete 03
            
            // la requete
            selectQuerytest03_ = "SELECT client.prenom\n"
					+ "FROM emprunt, client\n"
					+ "WHERE emprunt.id_abonne = client.id_abonne\n"
					+ "AND emprunt.id_emprunt = '"
					+ emprunt.getId_emprunt_()
					+ "';";
            
            //
			/*
			System.out.println("***");
			System.out.println(selectQuerytest02_);
			*/
            
            // initialisat° de la variable de type Connection
         	// obtenir la connex° grâce à la classe Connexion
         	contest03_ = Connexion.getCon();
         	
         	try {
         		
         		// initialisat° d'1 variable de type
	        	// PreparedStatement
	        	// prépare la requete
	            p_stmttest03_ = contest03_.prepareStatement(selectQuerytest03_);
	            
	            // initialisat° d'1 variable de type
	            // ResultSet
	            // execute la requete
	            rstest03_ = p_stmttest03_.executeQuery();
	            
	            while (rstest03_.next()) {
	            	
	            	/*
	            	System.out.println("***");
		            System.out.println(rstest03_.getString("prenom"));
		            */
	            	
	            	//
		            contient_titre03 = rstest03_.getString("prenom");
		            
	            }
	            
         		
         	} catch(SQLException except) {
         		
         		//
				System.out.println("Except°:");
	        	System.out.println("Fichier: F03_emprunt_controller.java");
	        	System.out.println("Methode: afficherDetailsEmprunt_()");
	        	System.out.println("section: prenom");
	            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
	            
         	}
         	
         	/*
			System.out.println("***");
            System.out.println(contient_titre03);
            */
			
            // MAJ du Label contenant le prenom après execut°
            // de la requete
            getLbl_contient_id_abonne().setText(contient_titre03);
			
			//****************************************
            
			// formate l'objet Date en String et affichage
			// ds le lbl correspondant
			Date dateFormat_sortie = emprunt.getDateSortie_();
			String dateSortieToString = String.format("%1$tY-%1$tm-%1$td", dateFormat_sortie);
			getLbl_contient_date_sortie().setText(dateSortieToString);
			
			// ****************************************************
			
			// formate l'objet Date en String et affichage
			// ds le lbl correspondant
			Date dateFormat_rendu = emprunt.getDateRendu_();
			String dateRenduToString = String.format("%1$tY-%1$tm-%1$td", dateFormat_rendu);
			getLbl_contient_date_rendu().setText(dateRenduToString);
			
		} else {
			
			//
			getLbl_contient_id_emprunt().setText("");
			getLbl_contient_id_livre().setText("");
			getLbl_contient_id_abonne().setText("");
			getLbl_contient_date_sortie().setText("");
			getLbl_contient_date_rendu().setText("");				
			
		}
		
	}
	
	// ================================================
	
	public ObservableList<ObservableList<String>> test01_() {
		
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("methode: test01_()");
		
		// les variables
		
		String selectQuerytest01_;
		Connection contest01_;
		PreparedStatement p_stmttest01_;
		ResultSet rstest01_;
		
		// redact° de la requete
		selectQuerytest01_ = "SELECT client.prenom, livre.titre\n"
				+ "FROM client, livre\n"
				+ "INNER JOIN emprunt\n"
				+ "WHERE client.id_abonne = emprunt.id_abonne\n"
				+ "AND emprunt.id_livre = livre.id_livre";
		
		// initialisat° de la variable de type Connection
		// obtenir la connex° grâce à la classe Connexion
		contest01_ = Connexion.getCon();
		
		try {
			
			// initialisat° d'1 variable de type
        	// PreparedStatement
        	// prépare la requete
            p_stmttest01_ = contest01_.prepareStatement(selectQuerytest01_);
            
            // initialisat° d'1 variable de type
            // ResultSet
            // execute la requete
            rstest01_ = p_stmttest01_.executeQuery();
            
            while (rstest01_.next()) {
            	
            	// Possible value [livre.titre, titre, client.prenom, prenom]
                liste_test01_.add(rstest01_.getString("titre"));
                liste_test02_.add(rstest01_.getString("prenom"));
                
              }
            
		} catch(SQLException except) {
			
			//
			System.out.println("Except°:");
        	System.out.println("Fichier: F03_emprunt_controller.java");
        	System.out.println("Methode: test01_()");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
            
		}
		
		System.out.println("Found " + liste_test01_.size() + " livre.titre");
		System.out.println("Found " + liste_test02_.size() + " client.prenom");
		
		
		// imprime la ObservableList liste_test01_: les titres
		for(String empt : liste_test01_) {
     	  System.out.println("-: " + empt);
		}
		
		// remplit la ObservableList liste_testGeneral_
		// avec les 2 autres
		liste_testGeneral_.add(liste_test01_);
		liste_testGeneral_.add(liste_test02_);
		
		// imprime la ObservableList liste_testGeneral
		for(ObservableList<String> empt : liste_testGeneral_) {
     	  System.out.println("--: " + empt);
		}
	
		// permet d'acceder a la 1° ObservableArrayList
		System.out.println(liste_testGeneral_.get(0));
		// permet d'acceder a la 2° ObservableArrayList
		System.out.println(liste_testGeneral_.get(1));
		// permet d'acceder au 1° elt de la 1° ObservableArrayList
		System.out.println(liste_testGeneral_.get(0).get(0));
		
		return liste_testGeneral_;
	    // return liste_test01_;
		
	}
	
	// ================================================
	/*
	public void test02_() {
		
		//
		// TV02
		// les variables
		
		//
		ObservableList<EmpruntCompose01> Olist02 = FXCollections.observableArrayList();
		String selectQuerytest02;
		Connection contest02;
		PreparedStatement p_stmttest02;
		ResultSet rstest02;
		Client client02 = new Client();
		Livre livre02 = new Livre();
		
		// redact° de la requete
		selectQuerytest02 = "SELECT client.prenom, livre.titre\n"
				+ "FROM client, livre\n"
				+ "INNER JOIN emprunt\n"
				+ "WHERE client.id_abonne = emprunt.id_abonne\n"
				+ "AND emprunt.id_livre = livre.id_livre";
		
		// initialisat° de la variable de type Connection
		// obtenir la connex° grâce à la classe Connexion
		contest02 = Connexion.getCon();
		
		try {
			
			// initialisat° d'1 variable de type
        	// PreparedStatement
        	// prépare la requete
            p_stmttest02 = contest02.prepareStatement(selectQuerytest02);
            
            // initialisat° d'1 variable de type
            // ResultSet
            // execute la requete
            rstest02 = p_stmttest02.executeQuery();
            
            while (rstest02.next()) {
            	
            	// Possible value [livre.titre, titre, client.prenom, prenom]
            	Olist02.add(
                        new EmpruntCompose01(client02, rstest02.getString("prenom"), livre02, rstest02.getString("titre")));
            	
            }            
			
		} catch(SQLException except) {
			
			//
			System.out.println("Except°:");
        	System.out.println("Fichier: F03_emprunt_controller.java");
        	System.out.println("Methode: test02_()");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
			
		}
		
		// imprime la ObservableList Oliste02
		// les valeurs st null:
		// normal on affiche des infos, on ne selectionne rien
		for(EmpruntCompose01 empt : Olist02) {
     	  System.out.println("---: " + empt.prenom);
     	  System.out.println("---: " + empt.titre);
		}
		
		// les colonnes de la TV02
		TV02_col_prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        TV02_col_titre.setCellValueFactory(new PropertyValueFactory("titre"));
		
		TV02.setItems(null);
        TV02.setItems(Olist02);
		
	}
	*/
	
	// ================================================
	/*
	public void test03_() {
		final Service<ObservableList<Result>> service = new Service<ObservableList<Result>>() { 
			  
		    @Override 
		    protected Task<ObservableList<Result>> createTask() { 
		        return new Task<ObservableList<Result>>() { 
		  
		            @Override 
		            protected ObservableList<Result> call() throws Exception { 
		                
		            	// les variables
		            	ObservableList<String> obList01Test03_ = FXCollections.observableArrayList();
		            	String selectQueryTest03_;
		        		Connection conTest03_;
		        		PreparedStatement p_stmtTest03_;
		        		ResultSet rsTest03_;
		        		
		        		// redact° de la requete
		        		selectQueryTest03_ = "SELECT client.prenom, livre.titre\n"
		        				+ "FROM client, livre\n"
		        				+ "INNER JOIN emprunt\n"
		        				+ "WHERE client.id_abonne = emprunt.id_abonne\n"
		        				+ "AND emprunt.id_livre = livre.id_livre";
		        		
		        		// initialisat° de la variable de type Connection
		        		// obtenir la connex° grâce à la classe Connexion
		        		conTest03_ = Connexion.getCon();
		        		
		        		//
		        		try {
		        			
		        			// initialisat° de la variable de type
		                	// PreparedStatement
		                	// prépare la requete
		                    p_stmtTest03_ = conTest03_.prepareStatement(selectQueryTest03_);
		                    
		                    // initialisat° de la variable de type
		                    // ResultSet
		                    // execute la requete
		                    rsTest03_ = p_stmtTest03_.executeQuery();
		                    
		                    while (rsTest03_.next()) {
		                    	
		                    	obList01Test03_.add(rsTest03_.getString("prenom"));
		                    	
		                    }           
		        			
		        		} catch(SQLException except) {
		        			
		        			//
		        			System.out.println("Except°:");
		                	System.out.println("Fichier: F03_emprunt_controller.java");
		                	System.out.println("Methode: test02_()");
		                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
		        			
		        		}
		                
		                System.out.println("contenu de obList01Test03_");
		                System.out.println(obList01Test03_);
		                return obList01Test03_;
		            }                             
		        }; 
		    }                     
		};
	}
	*/
	
	
	
	// ================================================
	
	@FXML
	private void creer_emprunt() {
		
		//
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("methode: creer_emprunt()");
		
		main.init_F03_creer_emprunt();
		
	}
	
	// ================================================
	
	public ObservableList<Emprunt> getEmprunt() {
		
		//
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("methode getEmprunt()");
		
		// les variables
		String select_query;
		Connection con;
		PreparedStatement p_stmt;
		ResultSet rs;
		Emprunt emprunt;
		
		// redact° de la requete
		select_query = "SELECT * FROM emprunt";
		
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
            	
                emprunt = new Emprunt();
                
                emprunt.setId_emprunt_(rs.getInt(1));
                
                emprunt.setId_livre_(rs.getInt(2));
                
                emprunt.setId_abonne_(rs.getInt(3));
                
                emprunt.setDate_sortie_(rs.getDate(4));
                
                emprunt.setDate_rendu_(rs.getDate(5));
                
                liste_emprunt.add(emprunt);
                
            }
			
		} catch(SQLException except) {
			
			//
			System.out.println("Except°:");
        	System.out.println("Fichier: F03_emprunt_controller.java");
        	System.out.println("Methode: getEmprunt()");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, except);
            
		}
		
		//
		return liste_emprunt;
		
	}
	// ================================================
	
	@FXML
	private void effacer_emprunt() {
		
		//
		System.out.println("fichier: F03_emprunt_controlller.java");
		System.out.println("methode: effacer_emprunt()");
		
		// les variables
		Emprunt selectedEmprunt;
		String delete_query;
		Connection con;
		PreparedStatement p_stmt;
		
		// initialisat° de la variable selectedEmprunt
		// permet d'obtenir l'emprunt sélectionné depuis la TV
		selectedEmprunt = TV.getSelectionModel().getSelectedItem();
		
		if(selectedEmprunt == null) {
			
			// System.out.println("Aucun emprunt n'a été sélectionné");
			alerte_aucun_emprunt_selection();
			
		} else {
			
			/*
			System.out.println("1 emprunt a bien été sélectionné");
			System.out.println("Effacement de cet emprunt de la db");
			System.out.println("ID de l'emprunt a effacer: " + selectedEmprunt.getId_emprunt_());
			*/
			
			// redact° de la requete
			delete_query = "DELETE from emprunt \n"
					+ "WHERE id_emprunt = "
					+ selectedEmprunt.getId_emprunt_()
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
				afficherTblEmprunt();
				
			} catch(SQLException except) {
				
				//
				System.out.println("Exception");
				System.out.println("fichier: F03_emprunt_controller.java");
				System.out.println("methode: effacerEmprunt()");
				//Logger.getLogger(delete_query);
				Logger.getLogger(F03_emprunt_controller.class.getName()).log(Level.SEVERE, null, except);
				
			}
		}
		
	}
	
	// ================================================
	
	// Affiche une boite de dialogue de type ALERT
	// Informe l'utilisateur qu'il n'a sélectionné aucun emprunt
	private void alerte_aucun_emprunt_selection() {
		
		//
		Alert alerte_aucun_emprunt_selection = new Alert(AlertType.WARNING);
		alerte_aucun_emprunt_selection.initOwner(main.getF03());
		alerte_aucun_emprunt_selection.setTitle("Aucune sélect°");
		alerte_aucun_emprunt_selection.setHeaderText("Aucun emprunt sélectionné");
		alerte_aucun_emprunt_selection.setContentText("Veuillez sélectionner 1 emprunt");
		alerte_aucun_emprunt_selection.showAndWait();
		
	}
	
	// ================================================
	
	public void afficherTblEmprunt() {
		
		//
		System.out.println("fichier: F03_emprunt_controller.java");
		System.out.println("methode: afficherTblEmprunt()");
		
		// variables
		// AUCUNE
        
        // pr debug
        // affiche seulement les adresses memoire
        // System.out.println(list);
        
        // pr debug
        // affiche les id de chaque ergt
        /*
        for (Emprunt emprunt: liste_emprunt) {
            System.out.println(emprunt.getId_emprunt());
            // ou
            System.out.println(emprunt.getId_emprunt_());
        }
        */
        
        // parametre chacune des colonnes de la TableView
        // (déclarées ds le fichier: F03_emprunt_controller.java)
        // avec les attributs déclarés ds la class Emprunt
		
        //getCol_id_abonne().setCellValueFactory(cellData -> cellData.getValue().clientIdProperty().asObject());
		getCol_id_emprunt().setCellValueFactory(cellData -> cellData.getValue().id_empruntProperty().asObject());
		
        //getCol_prenom().setCellValueFactory(cellData -> cellData.getValue().clientPrenomProperty());
		getCol_id_livre().setCellValueFactory(cellData -> cellData.getValue().id_livreProperty().asObject());
		
        //getCol_nom().setCellValueFactory(cellData -> cellData.getValue().clientNomProperty());
		getCol_id_abonne().setCellValueFactory(cellData -> cellData.getValue().id_abonneProperty().asObject());
		
        //getCol_telephone().setCellValueFactory(cellData -> cellData.getValue().clientTelephoneProperty());
		getCol_date_sortie().setCellValueFactory(cellData -> cellData.getValue().date_sortieProperty());
		
        //getCol_mail().setCellValueFactory(cellData -> cellData.getValue().clientMailProperty());
		getCol_date_rendu().setCellValueFactory(cellData -> cellData.getValue().date_renduProperty());
        
        // parametre la TableView
        // avec la ObservableList
        // retournée par la méthode getEmprunt()
        
        // L originale qui fonctionne
        getTV().setItems(getEmprunt());
	}
	
	// ================================================
	
	@FXML
	private void editer_emprunt() {
		
		//
		System.out.println("fichier: F03_emprunt_controlller.java");
		System.out.println("methode: editer_emprunt()");
		
	}
	
	// ================================================
	
	@FXML
	private void quitter_emprunt() {
		
		//
		System.out.println("fichier: F03_emprunt_controlller.java");
		System.out.println("methode: quitter_emprunt()");
		
		//
		this.F03.close();
		
	}
	
	// ================================================
	
}
