package modeles;

import java.util.*;

public class PiocheDestination extends Pioche {

	public final static int NOMBRES_DE_CARTES_PIOCHE_DESTINATION = 65;

	//private ArrayList<CarteDestination> cartesDestinations;

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
		ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs = new ArrayList<CarteDestination>();
		int nombreCartesDestinations = this.cartes.size()-1;

		if(!this.cartes.isEmpty()){
			for(int i = nombreCartesDestinations; i>nombreCartesDestinations-4;i--){
				cartesPiocheesPourChoixUtilisateurs.add(((CarteDestination)this.cartes.get(i)));
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

	private void supprimerCarteDeLaPioche(ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs){
		for (CarteDestination cd:
				cartesPiocheesPourChoixUtilisateurs) {
			this.cartes.remove(cd);
		}
	}

	public void initialiserPiocheDestinations(){

		for(int i=0; i<NOMBRES_DE_CARTES_PIOCHE_DESTINATION; i++){
			//CODE PROVISOIRE SERVANT AU TESTING NOUS AVONS BESOIN DU JEU EN VRAI POUR CODER LES DESTINATIONS ET LES ITINERAIRES
			ArrayList<Ville> listeVilleTypes = new ArrayList<Ville>();

			Ville paris = new Ville("Paris"+i, false);
			Ville marseille = new Ville("Marseille"+i, true);

			listeVilleTypes.add(paris);
			listeVilleTypes.add(marseille);

			CarteDestination carteType = new CarteDestination(10, listeVilleTypes);

			this.cartes.add(carteType);
		}
	}

	public boolean estVide(){
		return this.cartes.isEmpty();
	}
	@Override
	public int taille() {
		return this.cartes.size();
	}

}
