package modeles;

import constantes.ConstantesJeu;

import java.util.ArrayList;

public class Joueur {

	//carte de destinations ou itineraire que possede le joueur concerne
	private ArrayList<CarteDestination> cartesDestination = new ArrayList<CarteDestination>();
	
	//cartes transports que possede le joueur concerne
	private ArrayList<CarteTransport> cartesTransport = new ArrayList<CarteTransport>();

	private String couleur = "pink";

	private int capaciteJeu = 2;

	private String pseudo;

	private int scoreCourant = 0;

	public Joueur(ArrayList<CarteDestination> cartesDestination, ArrayList<CarteTransport> cartesTransport) {
		super();
		this.cartesDestination = cartesDestination;
		this.cartesTransport = cartesTransport;
	}

	public Joueur(){

	}

	public Joueur(String pseudo){
		this.pseudo = pseudo;
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

	public int getCapaciteJeu() {
		return capaciteJeu;
	}

	public void setCapaciteJeu(int capaciteJeu) {
		this.capaciteJeu = capaciteJeu;
	}

	public boolean aLaCapaciteDeJouer(){
		return this.capaciteJeu>0;
	}

	public boolean aLaCapaciteDePiocherDesCartesDestinations(){
		return this.capaciteJeu>=2;
	}



	public void diminuerCapaciteJoueur(int value){
		this.capaciteJeu-=value;
		System.out.println("CAPACITE DU JOUEUR : "+capaciteJeu);
	}

	public void augmenterCapaciteJoueur(int value){
		this.capaciteJeu+=value;
		System.out.println("CAPACITE DU JOUEUR : "+capaciteJeu);
	}

	public void reseterCapaciteJoueur(){
		this.capaciteJeu= ConstantesJeu.VALEUR_ACTIONS;
		System.out.println("CAPACITE DU JOUEUR : "+capaciteJeu);
	}

	public boolean peutPiocherJokerCartesVisibles(){
		return this.capaciteJeu>=2;
	}

	public String getPseudo() {
		return pseudo;
	}

	public int getScoreCourant() {
		return scoreCourant;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setScoreCourant(int scoreCourant) {
		this.scoreCourant = scoreCourant;
	}

	public ArrayList<CarteTransport> initialiserMainJoueur() {
		//We don't verify uneuseful cases :
		// (we have enough card this method is called at the start of the game :))
		ArrayList<CarteTransport> cartesTransportsInitialisation = new ArrayList<CarteTransport>();
		CarteTransport carteAAjouter;
		for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_BATEAU_INITIALISATION; i++) {
			carteAAjouter = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
			cartesTransportsInitialisation.add(carteAAjouter);
		}

		for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_WAGON_INITIALISATION; i++) {
			carteAAjouter = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
			cartesTransportsInitialisation.add(carteAAjouter);
		}
		return cartesTransportsInitialisation;
	}
}
