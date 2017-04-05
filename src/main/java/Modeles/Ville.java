package Modeles;

public class Ville {
	private String nom;
	private boolean capacitePort;

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
	
	
}
