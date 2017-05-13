package modeles;

import constantes.ConstantesJeu;
import java.util.*;

/**
 * Classe modelisant une pioche
 */
public abstract class Pioche {

	// Cartes de la pioche
	protected ArrayList<Carte> cartes = new ArrayList<Carte>();

	// Defausse associee a la pioche
	protected ArrayList<Carte> cartesDefaussees = new ArrayList<Carte>();

	/*
    *
	* CONSTRUCTEURS
	*
	*/

	/**
	 * Construit une pioche
	 * @param cartes a utiliser pour la pioche
	 */
	public Pioche(ArrayList<Carte> cartes) {
		super();
		this.cartes = cartes;
	}

	public Pioche() {
		super();
		this.cartes = new ArrayList<Carte>();
	}

	/*
	*
	* FONCTIONS
	*
	*/

	public void melanger() {
		Collections.shuffle(this.cartes);
	}

	/**
	 * Gere la pioche
	 * @return
	 */
	public Carte piocherCarte() {
		Carte carteTransportRetournee = null;

		int indexDernierElement=this.cartes.size() - 1;

		//Cas nominal
		if(indexDernierElement>=0){
			Carte cartePiochee=this.cartes.get(indexDernierElement);

			this.cartes.remove(indexDernierElement);
			
			carteTransportRetournee = cartePiochee;
		//Pioche vide
		}else{
			//Reconstruction de la pioche
			boolean resultat = reconstruirePiocheAvecDefausse();
			if(resultat){
				carteTransportRetournee = new CarteTransport(ConstantesJeu.PIOCHE_REFAITE, false);
			//resconstruction impossible
			}else{
				carteTransportRetournee = new CarteTransport(ConstantesJeu.PAS_DE_CARTE_DANS_LA_DEFAUSSE, false);
			}
		}
		//Pas d'objet null renvoye
		return carteTransportRetournee;
	}

	public boolean reconstruirePiocheAvecDefausse(){
		//Si on a bien une defausse avec des cartes
		if(this.cartesDefaussees.size()>=1){
			//On met les cartes defaussees dans la pioche
			this.cartes=(ArrayList<Carte>) this.cartesDefaussees.clone();

			//On doit supprimer les cartes de la defausse
			this.cartesDefaussees.clear();

			//On melange notre nouvelle pioche
			melanger();
			return true;
		}else{
			return false;
		}
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public void ajouterCarteDefausse(Carte carteDefaussee){
		this.cartesDefaussees.add(carteDefaussee);
	}

	public abstract int taille();

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<Carte> cartes) {
		this.cartes = cartes;
	}

	public ArrayList<Carte> getCartesDefaussees() {
		return cartesDefaussees;
	}
}
