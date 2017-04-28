package modeles;

import constantes.ConstantesJeu;

import java.util.*;

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
	private ArrayList<CarteTransportWagon> cartesTransportWagon = new ArrayList<>();
	private ArrayList<CarteTransportBateau> cartesTransportBateau = new ArrayList<>();
	private ArrayList<CarteTransport> cartesTransportJoker = new ArrayList<>();

	private ArrayList<CarteTransport> cartesSelectionnee = new ArrayList<>();

	//cartes transports que possede le joueur concerne
	private ArrayList<CarteTransport> cartesTransport = new ArrayList<CarteTransport>();

	private String couleur = "pink";

	private int capaciteJeu = 2;

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
		if(cartePioche.getCouleur() == CarteTransport.JOKER){
			this.cartesTransportJoker.add(cartePioche);
			System.out.println("Add joker");
		}else{
			if(cartePioche instanceof CarteTransportBateau){
				this.cartesTransportBateau.add((CarteTransportBateau) cartePioche);
				System.out.println("Add carte bateau");
			}else if(cartePioche instanceof CarteTransportWagon){
				this.cartesTransportWagon.add((CarteTransportWagon) cartePioche);
				System.out.println("Add carte wagon");
			}
		}
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

	public ArrayList<CarteTransportWagon> getCartesTransportWagon() {
		return cartesTransportWagon;
	}

	public void setCartesTransportWagon(ArrayList<CarteTransportWagon> cartesTransportWagon) {
		this.cartesTransportWagon = cartesTransportWagon;
	}

	public ArrayList<CarteTransportBateau> getCartesTransportBateau() {
		return cartesTransportBateau;
	}

	public void setCartesTransportBateau(ArrayList<CarteTransportBateau> cartesTransportBateau) {
		this.cartesTransportBateau = cartesTransportBateau;
	}

	public ArrayList<CarteTransport> getCartesTransportJoker() {
		return cartesTransportJoker;
	}

	public void setCartesTransportJoker(ArrayList<CarteTransport> cartesTransportJoker) {
		this.cartesTransportJoker = cartesTransportJoker;
	}

	public ArrayList<CarteTransport> getCartesSelectionnee() {
		return cartesSelectionnee;
	}

	public void setCartesSelectionnee(ArrayList<CarteTransport> cartesSelectionnee) {
		this.cartesSelectionnee = cartesSelectionnee;
	}

	/* TAKE ROAD METHOD */

	/*
		**********       INCOMPLETE   ***************
		Return true if road is taken successfully, else false
		@return boolean
	 */
	public boolean takeRoad(Route road){
		boolean result = false;
		System.out.println("TakeRoad method");

		if(road.isTaken()){
			System.out.println("Impossible d'attribuer la route : Route déjà prise");
		}else{
			if(hasRequiredCardsInHand(road)){
				road.setPossesseur(this);
				result=true;
			}else{
				System.out.println("Impossible d'attribuer la route : Pas de carte requise");
			}
		}

		return result;
	}

	public boolean hasRequiredCardsInHand(Route road){
		boolean hasRequiredCardsInHand = true;
		int nbGoodCardInHand;
		ArrayList<CarteTransport> requiredCards = new ArrayList<>();
		switch (road.getCouleur()){
			case Couleur.SPE :{
				System.out.println("Route spéciale non traité");
				break;
			}
			case Couleur.GRIS:{
				System.out.println("Route grise non traité");
				break;
			}
			default:{
				nbGoodCardInHand = getColoredCards(road,requiredCards);
				break;
			}
		}

		int nbCardMissed = road.getNombreEtapes()-requiredCards.size();
		if(requiredCards.size() < road.getNombreEtapes()){
			hasRequiredCardsInHand = addJoker(requiredCards,nbCardMissed);
		}

		if(hasRequiredCardsInHand){
			removeCardInHand(requiredCards);
		}

		return hasRequiredCardsInHand;
	}

	public int getColoredCards(Route road, ArrayList<CarteTransport> coloredCards){
		int nbCards = 0;

		if(road.isMaritime()){
			int i=0;

			while(nbCards<road.getNombreEtapes() && i < this.cartesTransportBateau.size()){
				CarteTransportBateau bateau = cartesTransportBateau.get(i);
				if(bateau.getCouleur() == road.getCouleur()){
					if(bateau.isBateauDouble()){
						nbCards = nbCards+2;
					}else{
						nbCards = nbCards+1;
					}
					coloredCards.add(bateau);
				}
				i++;
			}
		}else{
			for(CarteTransport carteTransport : this.cartesTransportWagon){
				if(carteTransport.getCouleur() == road.getCouleur() && coloredCards.size()<road.getNombreEtapes()){
					coloredCards.add(carteTransport);
				}
			}
			nbCards = coloredCards.size();
		}

		return nbCards;
	}

	/*
		Return true if add joker successfully , false if not enough joker
		@param cards list of card required
		@return integer
	 */
	public boolean addJoker(ArrayList<CarteTransport> cards, int nbCardMissed){
		int nbJokerNeeded = nbJokerNeeded(nbCardMissed);
		if(nbJokerNeeded > cartesTransportJoker.size()){
			return false;
		}else{
			for(int i=0; i<nbJokerNeeded;i++){
				cards.add(cartesTransportJoker.get(i));
			}
			return true;
		}
	}

	/*
		Return the number of joker needed
		@param nbCardMissed the number of card need to complete a road
		@return integer
	 */
	public int nbJokerNeeded(int nbCardMissed){
		int jokerNeeded = 0;
		if(nbCardMissed>0){
			// Divide by 2 because a joker = 2 card
			jokerNeeded = (int)Math.ceil((double)nbCardMissed/2);
		}

		System.out.println("Joker needed : "+jokerNeeded);
		return jokerNeeded;
	}

	public void removeCardInHand(ArrayList<CarteTransport> requiredCard){
		this.cartesTransport.removeAll(requiredCard);
		for(CarteTransport transportCard : requiredCard){
			if(transportCard.getCouleur() == CarteTransport.JOKER){
				this.cartesTransportJoker.remove(transportCard);
			}else{
				if(transportCard instanceof CarteTransportBateau){
					this.cartesTransportBateau.remove(transportCard);
				}else{
					if(transportCard instanceof CarteTransportWagon){
						this.cartesTransportWagon.remove(transportCard);
					}
				}
			}
		}
	}

}
