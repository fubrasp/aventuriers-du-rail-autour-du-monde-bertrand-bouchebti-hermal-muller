package modeles;

import java.util.ArrayList;

public class Joueur {

	//carte de destinations ou itineraire que possede le joueur concerne
	private ArrayList<CarteDestination> cartesDestination = new ArrayList<CarteDestination>();
	
	//cartes transports que possede le joueur concerne
	private ArrayList<CarteTransport> cartesTransport = new ArrayList<CarteTransport>();

	public Joueur(ArrayList<CarteDestination> cartesDestination, ArrayList<CarteTransport> cartesTransport) {
		super();
		this.cartesDestination = cartesDestination;
		this.cartesTransport = cartesTransport;
	}

	public Joueur(){

	}

	public ArrayList<CarteDestination> getCartesDestination() {
		return cartesDestination;
	}

	public void setCartesDestination(ArrayList<CarteDestination> cartesDestination) {
		this.cartesDestination = cartesDestination;
	}

	public ArrayList<CarteTransport> getCartesTransport() {
		return cartesTransport;
	}

	public void setCartesTransport(ArrayList<CarteTransport> cartesTransport) {
		this.cartesTransport = cartesTransport;
	}
	
	public void ajouterCarteTransport(CarteTransport cartePioche){
		this.cartesTransport.add(cartePioche);
	}

	public void ajouterCarteDestination(CarteDestination cd){
		this.cartesDestination.add(cd);
	}

	public void ajouterCartesDestination(ArrayList<CarteDestination> carteDestinations){
		this.cartesDestination.addAll(carteDestinations);
	}

	public int dejaDansLaMainDuJoueur(CarteTransport ct){
		int compteur=0;
		//System.out.println("PIOCHEE :"+ct.getReference());
		for (CarteTransport ctt:
			 this.cartesTransport) {
			//System.out.println("MAIN :"+ctt.getReference());
			if(ctt.getReference().equals(ct.getReference())){
				compteur++;
			}
		}
		return compteur;
	}
}
