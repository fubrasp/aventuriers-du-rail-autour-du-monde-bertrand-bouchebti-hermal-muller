package modeles;

import constantes.ConstantesJeu;

import java.util.*;

public class PiocheTransport extends Pioche {

	//Liste utilisee pour l'initialisation
	private ArrayList<Integer> couleursDesCartesTransport = InitCouleursCartesTransport.couleursDesCartesTransport();

	/*
    *
	* CONSTRUCTEUR
	*
	*/

	public PiocheTransport(ArrayList<Carte> cartes) {
		super(cartes);
		this.initCouleurs();
	}

	private void initCouleurs(){
	}

	public PiocheTransport(){
		super();
		this.initCouleurs();
	}


	/*
	*
	* FONCTIONS
	*
	*/

	private void initialiserJokers(Object o){
		for(int i = 0; i < ConstantesJeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE; i++){
			this.cartes.add((CarteTransport) o);
		}
	}
	
	public void initialiserPiocheWagons() {
		for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR; i++) {
			for (Integer couleur : this.couleursDesCartesTransport) {
				if (i < ConstantesJeu.NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR) {
					this.cartes.add(new CarteTransportWagon(couleur, true));
				} else {
					this.cartes.add(new CarteTransportWagon(couleur, false));
				}
			}
		}
		this.initialiserJokers(new CarteTransportWagon(ConstantesJeu.JOKER, true));
	}

	public void initialiserPiocheBateaux() {
		for(int i=0; i<ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR; i++){
			for (Integer couleur : this.couleursDesCartesTransport) {
					this.cartes.add(new CarteTransportBateau(couleur, true, false));
			}
		}

		for(int i=0; i<ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR; i++){
			for (Integer couleur : couleursDesCartesTransport) {
					this.cartes.add(new CarteTransportBateau(couleur, false, true));
			}
		}
	}

	/**
	 * Methode permetant de renvoyer les cartes visibles de la pioche donnee (uniquement a l'initialisation)
	 * @return ArrayList<CarteTransport> liste des 3 cartes pour l'initialisation
	 */
	public ArrayList<CarteTransport> renvoyerTroisDernieresCarteVisibles(){
		ArrayList<CarteTransport> cartesARenvoyer = new ArrayList<CarteTransport>();
		int tailleCartes = this.cartes.size()-1;
		for (int i = tailleCartes; i > tailleCartes-3; i--) {
			//Cas nominal
			if(i-1>0){
				CarteTransport carteTransportPiochee = (CarteTransport) this.cartes.get(i);
				cartesARenvoyer.add(carteTransportPiochee);
				this.cartes.remove(carteTransportPiochee);
			//Condition d'arret
			}else{
				return cartesARenvoyer;
			}
		}
		return cartesARenvoyer;
	}


	/*
	*
	* GETTER & SETTER
	*
	*/

	@Override
	public int taille() {
		return super.cartes.size();
	}


	/*
	*
	* TO STRING ET AFFICHAGES
	*
	*/

	@Override
	public String toString() {
		String str="";
		for (Carte carte:this.cartes) {
			str+=carte.toString()+"\n";
		}
		return str;
	}

}
