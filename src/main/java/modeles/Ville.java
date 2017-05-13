package modeles;

public class Ville {

	//Nom de la ville
	private String nom;

	//Capacite de la ville a acceuillir un port
	private boolean capacitePort;

	//Personne qui a construit un port (on aurait pu initialiser un joueur particulier pour eviter de gerer des null)
	private Joueur possesseur;

	/*
    *
	* CONSTRUCTEUR
	*
	*/

	/**
	 * Construit une ville
	 * @param nom de la ville
	 * @param capacitePort capacite de la ville a acceuillir un port
	 */
	public Ville(String nom, boolean capacitePort) {
		super();
		this.nom = nom;
		this.capacitePort = capacitePort;
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public String getNom() {
		return nom;
	}

	public boolean isCapacitePort() {
		return capacitePort;
	}

	public void setPossesseur(Joueur possesseur) {
		this.possesseur = possesseur;
	}

	public boolean hasPossesseur(){
		return this.possesseur != null;
	}
	
	
}
