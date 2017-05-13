package modeles;

/**
 * Classe modelisant une route
 */
public class Route {


	private Ville villeDepart;
	private Ville villeArrivee;

	//Nombre de cases qui forment la route sur le plateau
	private int nombreEtapes;

	private boolean maritime;

	//Joueur qui a pri la route
	private Joueur possesseur;

	private int couleur;

	/*
    *
	* CONSTRUCTEURS
	*
	*/

	public Route(){}

	/**
	 * Construit une route
	 * @param villeDepart
	 * @param villeArrivee
	 * @param nombreEtapes
	 * @param maritime
	 */
	public Route(Ville villeDepart, Ville villeArrivee, int nombreEtapes, boolean maritime) {
		super();
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nombreEtapes = nombreEtapes;
		this.maritime = maritime;
		this.possesseur = null;
	}

	/*
	*
	* FONCTIONS
	*
	*/

	/**
	 * Methode qui determine si oui ou non le joueur peut prendre la route
	 * @param joueur
	 * @return boolean
	 */
	public boolean takeRoad(Joueur joueur){
		if(this.isTaken()){
			System.out.println("Impossible d'attribuer la route : Route déjà prise");
			return false;
		}else{
			return true;
		}
	}

	/**
	 *
	 * Return true if has a possesssor
	 * @return boolean
	 */
	public boolean isTaken(){
		if(possesseur == null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Verifie si une ville donné est une ville depart ou arrivé
	 * @param ville donné
	 * @return true si la ville donné est une ville départ ou arrivé
	 */
	public boolean containsCity(Ville ville) {
		return villeArrivee.equals(ville) || villeDepart.equals(ville);
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

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
