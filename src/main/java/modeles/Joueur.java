package modeles;

import constantes.ConstantesJeu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Joueur {

	//carte de destinations ou itineraire que possede le joueur concerne
	private ArrayList<CarteDestination> cartesDestination = new ArrayList<CarteDestination>();

	//Pseudo du joueur
	private String pseudo;

	//Pions en possession du joueur
	private int nbPionsBateau;
	private int nbPionsBateauReserve;
	private int nbPionsWagons;
	private int nbPionsWagonsReserve;

	//Score du joueur
	private int score;

	//cartes transports que possede le joueur concerne
	private ArrayList<CarteTransport> cartesTransport = new ArrayList<CarteTransport>();

	private String couleur = "pink";

	private int capaciteJeu = 2;

	private int scoreCourant = 0;

	public Joueur(ArrayList<CarteDestination> cartesDestination, ArrayList<CarteTransport> cartesTransport) {
		super();
		this.cartesDestination = cartesDestination;
		this.cartesTransport = cartesTransport;
	}
	/*
	*
	* CONSTRUCTEUR
	*
	*/

	public Joueur(String pseudo, String couleur) {
	    this.pseudo = pseudo;
	    this.score = 0;
		this.cartesDestination = new ArrayList<CarteDestination>();
		this.nbPionsBateau = 20;
		this.nbPionsBateauReserve = 5;
		this.nbPionsWagons = 40;
		this.nbPionsWagonsReserve = 10;
		this.cartesTransport = new ArrayList<CarteTransport>();
		this.couleur = couleur;
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbPionsBateau() {
		return nbPionsBateau;
	}

	public void setNbPionsBateau(int nbPionsBateau) {
		this.nbPionsBateau = nbPionsBateau;
	}

	public int getNbPionsBateauReserve() {
		return nbPionsBateauReserve;
	}

	public void setNbPionsBateauReserve(int nbPionsBateauReserve) {
		this.nbPionsBateauReserve = nbPionsBateauReserve;
	}

	public int getNbPionsWagons() {
		return nbPionsWagons;
	}

	public void setNbPionsWagons(int nbPionsWagons) {
		this.nbPionsWagons = nbPionsWagons;
	}

	public int getNbPionsWagonsReserve() {
		return nbPionsWagonsReserve;
	}

	public void setNbPionsWagonsReserve(int nbPionsWagonsReserve) {
		this.nbPionsWagonsReserve = nbPionsWagonsReserve;
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

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	/*
	*
	* FONCTIONS
	*
	*/

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

	public void majScoreEchange(int nbWInit, int nbBInit){

		//Nombre de pions dans la main à la fin de l'échange
		int WMFin = this.getNbPionsWagons();
		int BMFin = this.getNbPionsBateau();

		//Calcul du malus à appliquer au score
		int malus = (WMFin-nbWInit) + (BMFin-nbBInit);

		//Modification du score
		this.setScore(this.getScore()-malus);

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
		//System.out.println("CAPACITE DU JOUEUR : "+capaciteJeu);
	}

	public void augmenterCapaciteJoueur(int value){
		this.capaciteJeu+=value;
		//System.out.println("CAPACITE DU JOUEUR : "+capaciteJeu);
	}

	public void reseterCapaciteJoueur(){
		this.capaciteJeu= ConstantesJeu.VALEUR_ACTIONS;
		//System.out.println("CAPACITE DU JOUEUR : "+capaciteJeu);
	}

	public boolean peutPiocherJokerCartesVisibles(){
		return this.capaciteJeu>=2;
	}


	public int getScoreCourant() {
		return scoreCourant;
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

	//Elle marche visiblement cette methode
	//<reference card, number of occurence>
	public HashMap<String, Integer> compterOccurencesCartes(){
		//TRES LOURD, A TRAITER AUTREMENT
		HashMap<String, Integer> comptes = new HashMap<String, Integer>();
		int compteurCourant = 0;
		for (CarteTransport carteCourante:
			 this.cartesTransport) {
			compteurCourant = 0;
			for (CarteTransport carteCourantedeux:
				 this.cartesTransport) {
				if(carteCourante.getReference().equals(carteCourantedeux.getReference()))
					compteurCourant++;
			}
			comptes.put(carteCourante.getReference(), compteurCourant);
		}

		//System.out.println("CHECK-:");
		//this.printMap(comptes);
		//System.out.println("CHECK-:");


		return comptes;
	}

	public void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

}
