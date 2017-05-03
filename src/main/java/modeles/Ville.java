package modeles;

public class Ville {
	private String nom;
	private boolean capacitePort;
	private Joueur possesseur;

	public Ville(String nom, boolean capacitePort) {
		super();
		this.nom = nom;
		this.capacitePort = capacitePort;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isCapacitePort() {
		return capacitePort;
	}

	public void setCapacitePort(boolean capacitePort) {
		this.capacitePort = capacitePort;
	}

	public void setPossesseur(Joueur possesseur) {
		this.possesseur = possesseur;
	}

	public boolean hasPossesseur(){
		return this.possesseur != null;
	}
	
	
}
