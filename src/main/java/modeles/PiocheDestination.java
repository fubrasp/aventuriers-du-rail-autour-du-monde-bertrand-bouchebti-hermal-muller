package modeles;

import constantes.ConstantesJeu;

import java.util.*;

public class PiocheDestination extends Pioche {


	private ArrayList<CarteDestination> cartesPrecedentes = new ArrayList<CarteDestination>();

	public PiocheDestination(ArrayList<CarteDestination> cartesDestinations) {
		super();
		cartesDestinations = cartesDestinations;
	}

	public PiocheDestination() {
		super();
		//cartesDestinations = new ArrayList<CarteDestination>();
		cartes = new ArrayList<Carte>();
	}

	public ArrayList<CarteDestination> piocherCartesDestination(){
		this.cartesPrecedentes.clear();
		ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs = new ArrayList<CarteDestination>();
		int nombreCartesDestinations = this.cartes.size()-1;

		if(!this.cartes.isEmpty()){
			CarteDestination carteCourante = null;
			for(int i = nombreCartesDestinations; i>nombreCartesDestinations-4;i--){
				carteCourante = ((CarteDestination)this.cartes.get(i));
				cartesPiocheesPourChoixUtilisateurs.add(carteCourante);
				this.cartesPrecedentes.add(carteCourante);
				if((i-1)<0){
					//On retourne seulement les cartes restantes :)
					supprimerCarteDeLaPioche(cartesPiocheesPourChoixUtilisateurs);
					return cartesPiocheesPourChoixUtilisateurs;
				}
			}
			supprimerCarteDeLaPioche(cartesPiocheesPourChoixUtilisateurs);
			//Si il n'y a plus de carte la liste est vide (0 elements)
		}
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

	public boolean estVide(){
		return this.cartes.isEmpty();
	}
	@Override
	public int taille() {
		return this.cartes.size();
	}

	public ArrayList<CarteDestination> getCartesPrecedentes() {
		return cartesPrecedentes;
	}
}
