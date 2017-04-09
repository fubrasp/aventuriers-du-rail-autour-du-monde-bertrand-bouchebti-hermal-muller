package modeles;

import java.util.*;

public class PiocheTransport extends Pioche {

	private ArrayList<Integer> couleursDesCartesTransport = new ArrayList<Integer>();

	public PiocheTransport(ArrayList<Carte> cartes) {
		super(cartes);
		this.initCouleurs();
	}

	private void initCouleurs(){
		this.couleursDesCartesTransport.add(Couleur.BLANC);
		this.couleursDesCartesTransport.add(Couleur.NOIR);
		this.couleursDesCartesTransport.add(Couleur.VERT);
		this.couleursDesCartesTransport.add(Couleur.JAUNE);
		this.couleursDesCartesTransport.add(Couleur.ROUGE);
		this.couleursDesCartesTransport.add(Couleur.VIOLET);
	}

	public PiocheTransport(){
		super();
		this.initCouleurs();
	}

	@Override
	public int taille() {
		return super.cartes.size();
	}

	private void initialiserJokers(Object o){
		for(int i=0; i < Jeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE; i++){
			//un peu space
			this.cartes.add((CarteTransport) o);
		}
	}
	
	public void initialiserPiocheWagons() {
		for (int i = 0; i < Jeu.NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR; i++) {
			for (Integer couleur : this.couleursDesCartesTransport) {
				if (i < Jeu.NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR) {
					this.cartes.add(new CarteTransportWagon(couleur, true));
				} else {
					this.cartes.add(new CarteTransportWagon(couleur, false));
				}
			}
		}
		this.initialiserJokers(new CarteTransportWagon(CarteTransport.JOKER, true));
	}

	public void initialiserPiocheBateaux() {
		for(int i=0; i<Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR; i++){
			for (Integer couleur : this.couleursDesCartesTransport) {
					this.cartes.add(new CarteTransportBateau(couleur, true, false));
			}
		}

		for(int i=0; i<Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR; i++){
			for (Integer couleur : couleursDesCartesTransport) {
					this.cartes.add(new CarteTransportBateau(couleur, false, true));
			}
		}
	}

	public ArrayList<CarteTransport> renvoyerTroisDernieresCarteVisibles(){
		ArrayList<CarteTransport> cartesARenvoyer = new ArrayList<CarteTransport>();
		int tailleCartes = this.cartes.size()-1;
		for (int i = tailleCartes; i > tailleCartes-3; i--) {
			if(i-1>0){
				CarteTransport carteTransportPiochee = (CarteTransport) this.cartes.get(i);
				cartesARenvoyer.add(carteTransportPiochee);
				this.cartes.remove(carteTransportPiochee);
			}else{
				return cartesARenvoyer;
			}
		}
		return cartesARenvoyer;
	}

	@Override
	public String toString() {
		String str="";
		for (Carte carte:this.cartes) {
			str+=carte.toString()+"\n";
		}
		return str;
	}

	public ArrayList<Integer> getCouleursDesCartesTransport() {
		return couleursDesCartesTransport;
	}

}
