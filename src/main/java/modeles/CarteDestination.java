package modeles;

import java.util.*;

/**
 * Classe modelisant une carte destination
 */
public class CarteDestination extends Carte {


	private int pointsScoreAssoccies;

	//Liste de villes auxquelles se raporte la carte destination/itinieraire
	private ArrayList<Ville> villes = new ArrayList<Ville>();


	/*
    *
	* CONSTRUCTEUR
	*
	*/

	/**
	 * Construit une carte destination
	 * @param pointsScoreAssoccies points que l'on peut perdre ou gagner en ayant cette carte destination en main
	 * @param villes auwquelles referent la carte destination
	 */
	public CarteDestination(int pointsScoreAssoccies, ArrayList<Ville> villes) {
		super();
		this.pointsScoreAssoccies = pointsScoreAssoccies;
		this.villes = villes;
	}

	/*
	*
	* FONCTIONS
	*
	*/

	/**
	 * Methode permettant d'analyser le choix des cartes destinations
	 * @param cartesProposees
	 * @param choixUtilisateurParReferenceCarte
	 * @return ArrayList<CarteDestination> liste des cartes choisies
	 */
	public static ArrayList<CarteDestination> renvoyerCarteChoisies(ArrayList<CarteDestination> cartesProposees, ArrayList<String> choixUtilisateurParReferenceCarte){
		ArrayList<CarteDestination> cartesARenvoyer = new ArrayList<CarteDestination>();
		//Un disjoint serait interessant, mais les types different
		CarteDestination carteCourante = null;
		for (int i = 0; i<cartesProposees.size(); i++){
			carteCourante = cartesProposees.get(i);
			if (carteCourante.referenceMatch(choixUtilisateurParReferenceCarte)){
				cartesARenvoyer.add(carteCourante);
			}
		}
		return cartesARenvoyer;
	}


	/*
	*
	* GETTER & SETTER
	*
	*/

	public String getNomsVilles(){
		String str = "";
		for (Ville v:
				this.villes) {
			str+=v.getNom();
		}
		return str;
	}

	public int getPointsScoreAssoccies() {
		return pointsScoreAssoccies;
	}

	public ArrayList<Ville> getVilles() {
		return villes;
	}

	public String getReference(){
		String str = this.getNomsVilles();
		return str;
	}


	/*
	*
	* EGALITES ET HASHCODES
	*
	*/

	private boolean referenceMatch(ArrayList<String> liste){
		for (String identifiantCarteChoisie:
				liste) {
			if(identifiantCarteChoisie.equals(this.getReference())){
				return true;
			}
		}
		return false;
	}


	/*
	*
	* TO STRING ET AFFICHAGES
	*
	*/

	@Override
	public String toString() {
		return "CarteDestination{" +
				"pointsScoreAssoccies=" + pointsScoreAssoccies +
				", villes=" + villes +
				'}';
	}

}
