package modeles;

import java.util.ArrayList;

public class Joueur {

	//carte de destinations ou itineraire que possede le joueur concerne
	private ArrayList<CarteDestination> cartesDestination = new ArrayList<CarteDestination>();
	
	//cartes transports que possede le joueur concerne
	private ArrayList<CarteTransport> cartesTransport = new ArrayList<CarteTransport>();

	private String couleur = "pink";

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
		//System.out.println("");
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

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	/*public boolean construirePort(Route routeSelectionne){
		//On regarde dans la main du joueur si on a 4 cartes (2 wagons ports 2 bateaux bateaux simples.. ou 2 jokers) de la couleur de la route selectionnee ou de joker
		//==> utilisation d'une methode privee de comptage
		//On regarde si on a des pions ports ou non
		//On construit
		//On impacte au niveau graphique
	}*/
}
