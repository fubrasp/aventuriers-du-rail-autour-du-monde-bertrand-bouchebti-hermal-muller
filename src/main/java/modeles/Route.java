package modeles;

public class Route {
	private Ville villeDepart;
	private Ville villeArrivee;
	private int nombreEtapes;
	private boolean maritime;
	private Joueur possesseur;
	
	public Route(Ville villeDepart, Ville villeArrivee, int nombreEtapes, boolean maritime) {
		super();
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nombreEtapes = nombreEtapes;
		this.maritime = maritime;
		this.possesseur = null;
	}

	public Ville getvilleDepart() {
		return villeDepart;
	}

	public void setvilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Ville getvilleArrivee() {
		return villeArrivee;
	}

	public void setvilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public int getNombreEtapes() {
		return nombreEtapes;
	}

	public void setNombreEtapes(int nombreEtapes) {
		this.nombreEtapes = nombreEtapes;
	}

	public boolean isMaritime() {
		return maritime;
	}

	public void setMaritime(boolean maritime) {
		this.maritime = maritime;
	}

	public Joueur getPossesseur() {
		return possesseur;
	}

	public void setPossesseur(Joueur possesseur) {
		this.possesseur = possesseur;
	}
	

}
