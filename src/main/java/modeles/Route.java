package modeles;

public class Route {
	private Ville villeDepart;
	private Ville villeArrivee;
	private int nombreEtapes;
	private boolean maritime;
	private Joueur possesseur;
	private int couleur;

	public Route(){}
	
	public Route(Ville villeDepart, Ville villeArrivee, int nombreEtapes, boolean maritime) {
		super();
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nombreEtapes = nombreEtapes;
		this.maritime = maritime;
		this.possesseur = null;
	}

	/*
		**********       INCOMPLETE   ***************
		Return true if road is taken successfully, else false
		@return boolean
	 */
	public boolean takeRoad(Joueur joueur){
		if(isTaken()){
			System.out.println("Impossible d'attribuer la route : Route déjà prise");
			return false;
		}else{
			return true;
		}
	}

	/*
		Return true if has a possesssor
		@return boolean
	 */
	public boolean isTaken(){
		if(possesseur == null){
			return false;
		}else{
			return true;
		}
	}

	public Ville getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Ville getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
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
