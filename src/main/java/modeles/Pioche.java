package modeles;

import outil.OutilDialog;

import java.util.*;

public abstract class Pioche {
	// Cartes d'un deck servant de pioche
	protected ArrayList<Carte> cartes = new ArrayList<Carte>();
	protected ArrayList<Carte> cartesDefaussees = new ArrayList<Carte>();


	public Pioche(ArrayList<Carte> cartes) {
		super();
		this.cartes = cartes;
	}

	public Pioche() {
		super();
		this.cartes = new ArrayList<Carte>();
	}

	public void melanger() {
		Collections.shuffle(this.cartes);
	}

	public Carte piocherCarte() {
		Carte carteTransportRetournee = null;

		int indexDernierElement=this.cartes.size() - 1;

		if(indexDernierElement>=0){
			Carte cartePiochee=this.cartes.get(indexDernierElement);

			this.cartes.remove(indexDernierElement);
			
			carteTransportRetournee = cartePiochee;
		}else{
			boolean resultat = reconstruirePiocheAvecDefausse();
			if(resultat){
				carteTransportRetournee = new CarteTransport(CarteTransport.PIOCHE_REFAITE, false);
			}else{
				carteTransportRetournee = new CarteTransport(CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE, false);
			}
		}
		return carteTransportRetournee;
	}

	//All is working here tested manually..
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

	public void ajouterCarte(Carte c) {
		this.cartes.add(c);
	}

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
