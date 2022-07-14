package application.model;

import java.sql.Date;
import java.time.LocalDate;

import application.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Emprunt {
	
	// ================================================
	// variables
	
	// champs de class
	private static int count_emprunt = 0;
	
	// ========================================================
	
	// attributs d'instance
	
	// cle primaire
	private IntegerProperty id_emprunt;
	
	private IntegerProperty id_livre;
	private IntegerProperty id_abonne;
	private ObjectProperty<Date> date_sortie;
	private ObjectProperty<Date> date_rendu;
	
	// autres variables
	
	// ================================================
	
	// constructeur(s)
	
	// ================================================
	
	// constructeur 01
	public Emprunt() {
		
		//
		this.id_emprunt = new SimpleIntegerProperty();
		this.id_livre = new SimpleIntegerProperty();
		this.id_abonne = new SimpleIntegerProperty();
		this.date_sortie = new SimpleObjectProperty<Date>();
		this.date_rendu = new SimpleObjectProperty<Date>();
	}
	
	// ================================================
	
	// constructeur 02
	public Emprunt(int p_id_livre,
					int p_id_abonne,
					Date p_date_sortie,
					Date p_date_rendu) {
		
		//
		this.id_livre = new SimpleIntegerProperty(p_id_livre);
		this.id_abonne = new SimpleIntegerProperty(p_id_abonne);
		this.date_sortie = new SimpleObjectProperty<Date>(p_date_sortie);
		this.date_rendu = new SimpleObjectProperty<Date>(p_date_rendu);
		
		//
		this.id_emprunt = new SimpleIntegerProperty(++count_emprunt);
		
		
	}

	// ========================================================
	
	// getters - setters
		
	// ========================================================
	
	// count_emprunt
	public static int getCount_emprunt() {
		return count_emprunt;
	}

	public static void setCount_emprunt(int count_emprunt) {
		Emprunt.count_emprunt = count_emprunt;
	}
	
	// ========================================================
	
	// id_emprunt
	public IntegerProperty getId_emprunt() {
		return id_emprunt;
	}

	public void setId_emprunt(IntegerProperty id_emprunt) {
		this.id_emprunt = id_emprunt;
	}

	// Ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public IntegerProperty id_empruntProperty() {
		return id_emprunt;
	}
	
	// ajouté par mes soins
	// entraine la modification d'1 ligne ds
	// fichier: Main.java
	// methode: getEmpruntData()
	// ds la boucle while
	public void setId_emprunt_(int p_id_emprunt) {
		this.id_emprunt.set(p_id_emprunt);
	}
	
	// ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public int getId_emprunt_() {
		return id_emprunt.get();
	}
	
	// ========================================================
	
	// id_livre
	public IntegerProperty getId_livre() {
		return id_livre;
	}

	public void setId_livre(IntegerProperty id_livre) {
		this.id_livre = id_livre;
	}
	
	// Ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public IntegerProperty id_livreProperty() {
		return id_livre;
	}
	
	// ajouté par mes soins
	// entraine la modification d'1 ligne ds
	// fichier: Main.java
	// methode: getEmpruntData()
	// ds la boucle while
	public void setId_livre_(int p_id_livre) {
		this.id_livre.set(p_id_livre);
	}
	
	// ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public int getId_livre_() {
		return id_livre.get();
	}
	
	// ========================================================
	
	// id_abonne
	public IntegerProperty getId_abonne() {
		return id_abonne;
	}

	public void setId_abonne(IntegerProperty id_abonne) {
		this.id_abonne = id_abonne;
	}
	
	// Ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public IntegerProperty id_abonneProperty() {
		return id_abonne;
	}
	
	// ajouté par mes soins
	// entraine la modification d'1 ligne ds
	// fichier: Main.java
	// methode: getEmpruntData()
	// ds la boucle while
	public void setId_abonne_(int p_id_abonne) {
		this.id_abonne.set(p_id_abonne);
	}
	
	// ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public int getId_abonne_() {
		return id_abonne.get();
	}
	
	// ========================================================
	
	// date_sortie
	public ObjectProperty<Date> getDate_sortie() {
		return date_sortie;
	}

	public void setDate_sortie(ObjectProperty<Date> date_sortie) {
		this.date_sortie = date_sortie;
	}
	
	// Ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public ObjectProperty<Date> date_sortieProperty() {
		return date_sortie;
	}
	
	// ajouté par mes soins
	// entraine la modification d'1 ligne ds
	// fichier: Main.java
	// methode: getEmpruntData()
	// ds la boucle while
	public void setDate_sortie_(Date p_date_sortie) {
		this.date_sortie.set(p_date_sortie);
	}
	
	// ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public Date getDateSortie_() {
		return date_sortie.get();
	}
	
	// ========================================================
	
	// date_rendu
	public ObjectProperty<Date> getDate_rendu() {
		return date_rendu;
	}

	public void setDate_rendu(ObjectProperty<Date> date_rendu) {
		this.date_rendu = date_rendu;
	}
	
	// Ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public ObjectProperty<Date> date_renduProperty() {
		return date_rendu;
	}
	
	// ajouté par mes soins
	// entraine la modification d'1 ligne ds
	// fichier: Main.java
	// methode: getEmpruntData()
	// ds la boucle while
	public void setDate_rendu_(Date p_date_rendu) {
		this.date_rendu.set(p_date_rendu);
	}
	
	// ajouté par mes soins en accord avec
	// https://code.makery.ch/fr/library/javafx-tutorial/part2/
	// d'après le code de la class Person.java
	public Date getDateRendu_() {
		return date_rendu.get();
	}
	
	// ========================================================
	
	// autres methodes

}
