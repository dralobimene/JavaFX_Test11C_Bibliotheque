package application.model;

/*
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
*/

public class Livre {

	// ========================================================
	
	// champs de classe
	private static int count_livre = 0;
	
	// ========================================================
	
	// attributs d'instance
	private int id_livre;	
	private String auteur_livre;
	private String titre_livre;
	
	// ========================================================
	
	// autres variables
	
	// ========================================================
	
	// constructeur(s)
	
	// ========================================================
	
	// 01
	public Livre() { }
	
	// ========================================================
	
	// 02
	public Livre(String p_auteur_livre,
					String p_titre_livre) {
		
		// correspondance entre attributs
		// et parametres constructeur
		this.auteur_livre = p_auteur_livre;
		this.titre_livre = p_titre_livre;
		
		//
		this.id_livre = ++count_livre;
	}

	// ========================================================
	
	// Getters - Setters
	
	// ========================================================
	
	public static int getCount_livre() {
		return count_livre;
	}

	public int getId_livre() {
		return id_livre;
	}

	public String getAuteur_livre() {
		return auteur_livre;
	}

	public String getTitre_livre() {
		return titre_livre;
	}

	public static void setCount_livre(int count_livre) {
		Livre.count_livre = count_livre;
	}

	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}

	public void setAuteur_livre(String auteur_livre) {
		this.auteur_livre = auteur_livre;
	}

	public void setTitre_livre(String titre_livre) {
		this.titre_livre = titre_livre;
	}
	
	// methodes
	
}
