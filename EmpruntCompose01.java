package application.model;

public class EmpruntCompose01 {

	//==================================================
	
	// champs de classe
	
	//
	private static int countEmpruntCompose01 = 0;
	
	//==================================================
	
	// attributs d'instance
	
	//
	private int idEmpruntCompose01;
	
	//
	private Client client;
	
	//
	public String prenom;
	
	//
	private Livre livre;
	
	//
	public String titre;
	
	//==================================================
	
	// autres variables
	
	//==================================================
	
	// constructeur(s)
	
	// constructeur 01
	public EmpruntCompose01(Client p_client,
							String p_prenom,
							Livre p_livre,
							String p_titre) {
		
		// correspondance entre les attributs d'instance
		// et les parametres constructeur
		this.client = p_client;
		this.prenom = client.getClientPrenom();
		this.livre = p_livre;
		this.titre = livre.getTitre_livre();
		
		//
		this.idEmpruntCompose01 = ++countEmpruntCompose01;
		
	}
	
	//==================================================
	
	// Getters - Setters

	public String getPrenom() {
		return prenom;
	}

	public String getTitre() {
		return titre;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public static int getCountEmpruntCompose01() {
		return countEmpruntCompose01;
	}

	public int getIdEmpruntCompose01() {
		return idEmpruntCompose01;
	}

	public Client getClient() {
		return client;
	}

	public Livre getLivre() {
		return livre;
	}

	public static void setCountEmpruntCompose01(int countEmpruntCompose01) {
		EmpruntCompose01.countEmpruntCompose01 = countEmpruntCompose01;
	}

	public void setIdEmpruntCompose01(int idEmpruntCompose01) {
		this.idEmpruntCompose01 = idEmpruntCompose01;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	
	//==================================================
	
	// autres méthodes
}
