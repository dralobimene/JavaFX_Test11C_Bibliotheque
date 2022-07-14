package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// class Model Client
public class Client {

	// ========================================================
	// variables
	
	// champs de class
	private static int count_client = 0;

	// ========================================================
	
	// attributs d'instance
	
	// clé primaire
	private IntegerProperty client_id;
	
	private StringProperty client_prenom;
	private StringProperty client_nom;
	private StringProperty client_telephone;
	private StringProperty client_mail;
	
	// autres variables
	
	// ========================================================
	
	// constructeur(s)
	
	// ========================================================
	
	// constructeur 01
	public Client() {
		
		//
		this.client_id = new SimpleIntegerProperty();
		this.client_prenom = new SimpleStringProperty();
		this.client_nom = new SimpleStringProperty();
		this.client_telephone = new SimpleStringProperty();
		this.client_mail = new SimpleStringProperty();
		
	}
	
	// ========================================================
	
	// constructeur 02
	public Client(String p_client_prenom,
					String p_client_nom,
					String p_client_telephone,
					String p_client_mail) {
		
		//
		this.client_prenom = new SimpleStringProperty(p_client_prenom);
		this.client_nom = new SimpleStringProperty(p_client_nom);
		this.client_telephone = new SimpleStringProperty(p_client_telephone);
		this.client_mail = new SimpleStringProperty(p_client_mail);
		
		//
		this.client_id = new SimpleIntegerProperty(++count_client);
	}
	
	// ========================================================
	
	// Getters - Setters
	
	// ========================================================
	
	// client_id
    public int getClientId() {
        return client_id.get();
    }
    
    public void setClientId(int p_clientId){
        this.client_id.set(p_clientId);
    }
    
    public IntegerProperty clientIdProperty(){
        return client_id;
    }
    
    // ========================================================
    
    // client_prenom
    public String getClientPrenom() {
        return client_prenom.get();
    }
    
    public void setClientPrenom(String p_clientPrenom){
        this.client_prenom.set(p_clientPrenom);
    }
    
    public StringProperty clientPrenomProperty(){
        return client_prenom;
    }
    
    // ========================================================
    
    // client_nom
    public String getClientNom() {
        return client_nom.get();
    }
    
    public void setClientNom(String p_clientNom){
        this.client_nom.set(p_clientNom);
    }
    
    public StringProperty clientNomProperty(){
        return client_nom;
    }
    
    // ========================================================
    
    // client_telephone
    public String getClientTelephone() {
        return client_telephone.get();
    }
    
    public void setClientTelephone(String p_clientTelephone){
        this.client_telephone.set(p_clientTelephone);
    }
    
    public StringProperty clientTelephoneProperty(){
        return client_telephone;
    }
    
    // ========================================================
    
    // client_mail
    public String getClientMail() {
        return client_mail.get();
    }
    
    public void setClientMail(String p_clientMail){
        this.client_mail.set(p_clientMail);
    }
    
    public StringProperty clientMailProperty(){
        return client_mail;
    }
    
    // ========================================================
	
}
