package modeles;

import constantes.ConstantesJeu;

import java.util.*;

/**
 * Classe modelisant une pioche destination
 */
public class PiocheDestination extends Pioche {

	//Liste de cartes utilisee pour la regle du doit choisir au moins une carte
	private ArrayList<CarteDestination> cartesPrecedentes = new ArrayList<CarteDestination>();

	/*
    *
	* CONSTRUCTEURS
	*
	*/

	/**
	 * Construit une pioche destination
	 * @param cartesDestinations
	 */
	public PiocheDestination(ArrayList<CarteDestination> cartesDestinations) {
		super();
		cartesDestinations = cartesDestinations;
	}

	public PiocheDestination() {
		super();
		//cartesDestinations = new ArrayList<CarteDestination>();
		cartes = new ArrayList<Carte>();
	}

	/*
	*
	* FONCTIONS
	*
	*/

	/**
	 * Methode qui permet de piocher les 4 cartes destinations pour la fenetre de l'utilisateur
	 * @return ArrayList<CarteDestination> liste de 4 cartes ou moins (en fonction de l'etat de la pioche)
	 */
	public ArrayList<CarteDestination> piocherCartesDestination(){
		return this.piocherCartesDestinations(ConstantesJeu.NOMBRES_DE_CARTES_DESTINATIONS_TOUR_PAR_TOUR);
	}

	public ArrayList<CarteDestination> piocherCartesDestinationsInitialisation(){
		return this.piocherCartesDestinations(ConstantesJeu.NOMBRES_DE_CARTES_DESTINATIONS_INITIALISATION);
	}

	private ArrayList<CarteDestination> piocherCartesDestinations(int nombreDeCartes){
		this.cartesPrecedentes.clear();
		ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs = new ArrayList<CarteDestination>();
		int nombreCartesDestinations = this.cartes.size()-1;

		//Cas nominal
		if(!this.cartes.isEmpty()){
			CarteDestination carteCourante = null;
			for(int i = nombreCartesDestinations; i>nombreCartesDestinations-nombreDeCartes;i--){
				carteCourante = ((CarteDestination)this.cartes.get(i));
				cartesPiocheesPourChoixUtilisateurs.add(carteCourante);
				this.cartesPrecedentes.add(carteCourante);
				//Cas ou il n'y aurait pas assez de cartes restantes dans la pioche
				if((i-1)<0){
					//On retourne seulement les cartes restantes :)
					supprimerCarteDeLaPioche(cartesPiocheesPourChoixUtilisateurs);
					return cartesPiocheesPourChoixUtilisateurs;
				}
			}
			supprimerCarteDeLaPioche(cartesPiocheesPourChoixUtilisateurs);
		}
		//Si il n'y a plus de carte la liste est vide (0 elements)
		return cartesPiocheesPourChoixUtilisateurs;
	}

	public void remettreSousLaPioche(ArrayList<CarteDestination> cartesARemettreSousLaPioche){
		this.cartes.addAll(0, cartesARemettreSousLaPioche);
	}

	private void supprimerCarteDeLaPioche(ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs){
		this.cartes.removeAll(cartesPiocheesPourChoixUtilisateurs);
	}

	public void initialiserPiocheDestinations(){
		this.cartes.addAll(InitPiocheDestination.initDestinationsItineraires());
	}

	public int nombreDeCarteMinimaleDeCartesAChoisirPopup(){
		boolean b = Jeu.getInstance().getJoueurCourant().isaInitialiseeSesCartesDestinations();
		if(b){
			return ConstantesJeu.NOMBRES_DE_CARTES_DESTINATIONS_MINIMUM_APRES_INITIALISATION;
		}else{
			return ConstantesJeu.NOMBRES_DE_CARTES_MINIMUM_DESTINATIONS_INITIALISATION;
		}
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public boolean estVide(){
		return this.cartes.isEmpty();
	}

	public ArrayList<CarteDestination> getCartesPrecedentes() {
		return cartesPrecedentes;
	}

	@Override
	public int taille() {
		return this.cartes.size();
	}
}
