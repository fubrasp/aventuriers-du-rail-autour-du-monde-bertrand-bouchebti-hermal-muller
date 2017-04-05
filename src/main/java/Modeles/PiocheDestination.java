package Modeles;

import Outil.OutilDialog;

import java.util.ArrayList;

public class PiocheDestination extends Pioche {

	private ArrayList<CarteDestination> cartesDestinations;
	public PiocheDestination(ArrayList<CarteDestination> cartesDestinations) {
		super();
		cartesDestinations = cartesDestinations;
	}

	public PiocheDestination() {
		super();
		cartesDestinations = new ArrayList<CarteDestination>();
	}

	public ArrayList<CarteDestination> piocherCartesDestination(){
		ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs = new ArrayList<CarteDestination>();
		int nombreCartesDestinations = this.cartesDestinations.size()-1;
		for(int i = nombreCartesDestinations; i>nombreCartesDestinations-4;i--){
			if(this.cartesDestinations.get(i)==null){
				outilDialog.montrerDialogErreurPiocheDestination();
			}
			cartesPiocheesPourChoixUtilisateurs.add(this.cartesDestinations.get(i));

			if(this.cartesDestinations.get(i-1)==null){
				//On retourne seulement les cartes restantes :)
				supprimerCarteDeLaPioche(cartesPiocheesPourChoixUtilisateurs);
				return cartesPiocheesPourChoixUtilisateurs;
			}
		}
		supprimerCarteDeLaPioche(cartesPiocheesPourChoixUtilisateurs);
		//Si il n'y a plus de carte la liste est vide (0 elements)
		return cartesPiocheesPourChoixUtilisateurs;
	}

	private void supprimerCarteDeLaPioche(ArrayList<CarteDestination> cartesPiocheesPourChoixUtilisateurs){
		for (CarteDestination cd:
				cartesPiocheesPourChoixUtilisateurs) {
			this.cartesDestinations.remove(cd);
		}
	}

	public void initialiserPiocheDestinations(){

		//CODE PROVISOIRE SERVANT AU TESTING NOUS AVONS BESOIN DU JEU EN VRAI POUR CODER LES DESTINATIONS ET LES ITINERAIRES
		ArrayList<Ville> listeVilleTypes = new ArrayList<Ville>();

		Ville paris = new Ville("Paris", false);
		Ville marseille = new Ville("Marseille", true);

		listeVilleTypes.add(paris);
		listeVilleTypes.add(marseille);

		CarteDestination carteType = new CarteDestination(10, listeVilleTypes);

		for(int i=0; i<65; i++){
			this.cartesDestinations.add(carteType);
		}
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return 0;
	}

}
