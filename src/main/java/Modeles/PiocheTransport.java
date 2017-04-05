package Modeles;

import Outil.OutilDialog;

import java.util.*;

public class PiocheTransport extends Pioche {
	// carte face recto dans une pioche de carte transport
	private ArrayList<CarteTransport> cartesVisibles = new ArrayList<CarteTransport>();

	private ArrayList<String> couleursDesCartesTransport = new ArrayList<String>();

	public PiocheTransport(ArrayList<Carte> cartes, ArrayList<CarteTransport> cartesVisibles) {
		super(cartes);
		this.cartesVisibles = cartesVisibles;
		this.initCouleurs();
	}

	private void initCouleurs(){
		// A AMELIORER
		this.couleursDesCartesTransport.add(CarteTransport.BLANC);
		this.couleursDesCartesTransport.add(CarteTransport.NOIR);
		this.couleursDesCartesTransport.add(CarteTransport.VERT);
		this.couleursDesCartesTransport.add(CarteTransport.JAUNE);
		this.couleursDesCartesTransport.add(CarteTransport.ROUGE);
		this.couleursDesCartesTransport.add(CarteTransport.VIOLET);
	}

	public PiocheTransport(){
		super();
		this.cartesVisibles = new ArrayList<CarteTransport>();
		this.initCouleurs();
	}

	public ArrayList<CarteTransport> getCartesVisibles() {
		return cartesVisibles;
	}

	public void setCartesVisibles(ArrayList<CarteTransport> cartesVisibles) {
		this.cartesVisibles = cartesVisibles;
	}

	@Override
	public int taille() {
		return super.cartes.size() + this.cartesVisibles.size();
	}

	private void initialiserJokers(Object o){
		for(int i=0; i < Jeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE; i++){
			//un peu space
			this.cartes.add((CarteTransport) o);
		}
	}
	
	public void initialiserPiocheWagons() {
		for (int i = 0; i < Jeu.NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR; i++) {
			for (String couleur : this.couleursDesCartesTransport) {
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
			for (String couleur : this.couleursDesCartesTransport) {
					this.cartes.add(new CarteTransportBateau(couleur, true, false));
			}
		}

		for(int i=0; i<Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR; i++){
			for (String couleur : couleursDesCartesTransport) {
					this.cartes.add(new CarteTransportBateau(couleur, false, true));
			}
		}
	}
	
	public void preparerPioche(){
		this.melanger();
		this.ajouterTroisDernieresCarteVisibles();
	}
	
	public void ajouterTroisDernieresCarteVisibles(){
		int tailleCartes = this.cartes.size()-1;
		for (int i = tailleCartes; i > tailleCartes-3; i--) {
			CarteTransport t = (CarteTransport) this.cartes.get(i);
			boolean res = this.ajouterCarteVisible(t);
			if(res)
				this.cartes.remove(t);
		}
	}
	
	public boolean ajouterCarteVisible(CarteTransport t){
		if(this.cartesVisibles.size()<=3){
			this.cartesVisibles.add(t);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String str="";
		for (Carte carte:this.cartes) {
			str+=carte.toString()+"\n";
		}
		return str;
	}

	public ArrayList<String> getCouleursDesCartesTransport() {
		return couleursDesCartesTransport;
	}

	public void reseterCartesVisibles(){
		this.cartesVisibles.clear();
		this.ajouterTroisDernieresCarteVisibles();
		//TO DO AJOUTER A LA DEFAUSSE
	}

}
