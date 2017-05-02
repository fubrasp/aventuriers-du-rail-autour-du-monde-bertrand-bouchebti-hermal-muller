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

	private HashMap<Integer,ArrayList<CarteTransport>> selectedCards = new HashMap<>();

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

	public HashMap<Integer, ArrayList<CarteTransport>> getSelectedCards() {
		return selectedCards;
	}

	public void setSelectedCards(HashMap<Integer, ArrayList<CarteTransport>> selectedCards) {
		this.selectedCards = selectedCards;
	}

	public void addSelectCard(CarteTransport carteTransport){
		if(selectedCards.containsKey(carteTransport.getCouleur())){
			selectedCards.get(carteTransport.getCouleur()).add(carteTransport);
		}else{
			ArrayList<CarteTransport> cards = new ArrayList<>();
			cards.add(carteTransport);
			selectedCards.put(carteTransport.getCouleur(),cards);
		}
	}

	public void removeSelectCard(CarteTransport carteTransport){
		if(selectedCards.containsKey(carteTransport.getCouleur())){
			selectedCards.get(carteTransport.getCouleur()).remove(carteTransport);
		}
	}

	public int nbOccurenceCarteSelectionnee(CarteTransport carteSelectionnee){
		int nbOccurence = 0;
		if(selectedCards.containsKey(carteSelectionnee.getCouleur())){
			ArrayList<CarteTransport> cards = selectedCards.get(carteSelectionnee.getCouleur());
			for(int i=0;i<cards.size();i++){
				if(cards.get(i) == carteSelectionnee){
					nbOccurence = nbOccurence+1;
				}
			}
		}

		return nbOccurence;
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

		if(this.getScore()<0){
			this.setScore(0);
		}

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

	/*
		Check if road is not taken and if player has the required card, select road then
		@param road : selected road
		@return boolean , true if player has selected successfully the road
	 */
	public boolean selectRoad(Route road){
		boolean result = false;
		if(road.isTaken()){
			System.out.println("Impossible d'attribuer la route : Route déjà prise");
		}else{
			if(hasRequiredCardsInHand(road)){
				road.setPossesseur(this);
				result=true;
				this.majScoreRoadTaken(road.getNombreEtapes());
			}else{
				System.out.println("Impossible d'attribuer la route : Pas de carte requise");
			}
		}
		return result;
	}

	/*
		Check if player has required cards in hand to take the selected road,
		remove card from hand if true
		@param road : selected road
		@return boolean , true if player has the required card to take the selected road
	 */
	public boolean hasRequiredCardsInHand(Route road){
		boolean hasRequiredCardsInHand = true;
		int nbGoodCardInHand = 0;
		ArrayList<CarteTransport> usedCard = new ArrayList<>(); // List card retained to take a road
		switch (road.getCouleur()){
			case Couleur.SPE :{
				System.out.println("Route spéciale non traité");
				break;
			}
			case Couleur.GRIS:{
				nbGoodCardInHand = takeGreyRoad(road,usedCard);
				break;
			}
			default:{
				nbGoodCardInHand = getSizeRoadCanBeTake(road,usedCard);
				break;
			}
		}

		int nbCardMissed = road.getNombreEtapes()-nbGoodCardInHand;
		if(nbCardMissed > 0){
			int jokerNeeded = nbJokerNeeded(nbCardMissed);
			hasRequiredCardsInHand = useJoker(jokerNeeded,usedCard);
		}else{
			hasRequiredCardsInHand = true;
		}

		if(hasRequiredCardsInHand){
			removeCardInHand(usedCard);
		}

		return hasRequiredCardsInHand;
	}

	/*
		Get the maximum road size that can be take with selected card
		@param road : selected road
		@param useCards : Array contains cards used to take a road
		@return integer, maximum road size that can be take with selected card
	 */
	public int getSizeRoadCanBeTake(Route road, ArrayList<CarteTransport> useCards){
		// Get all card with road color
		int nbOccurence = 0;
		if(selectedCards.containsKey(road.getCouleur())){
			ArrayList<CarteTransport> carteTransports = selectedCards.get(road.getCouleur());
			if(road.isMaritime()){
				int i=0;
				while(nbOccurence<road.getNombreEtapes() && i<carteTransports.size() ){
					if(carteTransports.get(i) instanceof CarteTransportBateau){
						if(((CarteTransportBateau) carteTransports.get(i)).isBateauDouble()){
							nbOccurence = nbOccurence+2;
						}else{
							nbOccurence = nbOccurence+1;
						}
						useCards.add(carteTransports.get(i));
					}
					i++;
				}
			}else{
				int i=0;
				while(nbOccurence<road.getNombreEtapes() && i<carteTransports.size() ){
					if(carteTransports.get(i) instanceof CarteTransportWagon){
						nbOccurence = nbOccurence+1;
						useCards.add(carteTransports.get(i));
					}
					i++;
				}
			}
		}
		return nbOccurence;
	}

	/*
		Use joker if enough joker in hand, return true if enough joker
		@param jokerNeeded : number joker need to take a road
		@param useCard : card used to take a road, add joker in this list if enough joker
		@return boolean , true if enough joker otherwise false
	 */
	public boolean useJoker(int jokerNeeded,ArrayList<CarteTransport> useCard){
		boolean asEnoughJoker = false;
		if(selectedCards.containsKey(CarteTransport.JOKER)){
			ArrayList<CarteTransport> jokerSelected = selectedCards.get(CarteTransport.JOKER);
			if(jokerSelected.size() >= jokerNeeded){
				int i=0;
				while (i<jokerNeeded){
					useCard.add(jokerSelected.get(i));
					i++;
				}
				asEnoughJoker=true;
			}
		}

		return asEnoughJoker;
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
		return jokerNeeded;
	}


	/*
		Remove a list of card in hand
		@param cardUsed list of card used to take a road
	 */
	public void removeCardInHand(ArrayList<CarteTransport> cardUsed){
		for(int i=0;i<cardUsed.size();i++){
			cartesTransport.remove(cardUsed.get(i));
			removeSelectCard(cardUsed.get(i));
		}
	}

	/*
		Return number card select that can be placed
		@param road selected road
		@param useCard card used to complete a road
		@return integer
	 */
	public int takeGreyRoad(Route roadSelected,ArrayList<CarteTransport> useCard){
		int result = 0;
		Integer colorWhichHasMaxOccurence = -1;
		for(Integer key : selectedCards.keySet()){
			if(!key.equals(CarteTransport.JOKER)){
				int colorOccurence = getOccurenceByColor(roadSelected,key,useCard);
				if( colorOccurence > colorWhichHasMaxOccurence){
					colorWhichHasMaxOccurence = key;
				}
			}
		}

		useCard.clear();
		if(selectedCards.containsKey(colorWhichHasMaxOccurence)){
			result = getOccurenceByColor(roadSelected,colorWhichHasMaxOccurence,useCard);
		}

		return result;
	}

	/*
		Return number card occurence by color, double bateau will incremente number by 2
		@param road selected road
		@param cardColor color to get occurence
		@param useCard card used to complete a road
		@return number card occurence by color
	 */
	public int getOccurenceByColor(Route road, Integer cardColor, ArrayList<CarteTransport> useCard){
		// Get all card with road color
		int nbOccurence = 0;
		if(selectedCards.containsKey(cardColor)){
			ArrayList<CarteTransport> carteTransports = selectedCards.get(cardColor);
			if(road.isMaritime()){
				int i=0;
				while(nbOccurence<road.getNombreEtapes() && i<carteTransports.size() ){
					if(carteTransports.get(i) instanceof CarteTransportBateau){
						if(((CarteTransportBateau) carteTransports.get(i)).isBateauDouble()){
							nbOccurence = nbOccurence+2;
						}else{
							nbOccurence = nbOccurence+1;
						}
						useCard.add(carteTransports.get(i));
					}
					i++;
				}
			}else{
				int i=0;
				while(nbOccurence<road.getNombreEtapes() && i<carteTransports.size() ){
					if(carteTransports.get(i) instanceof CarteTransportWagon){
						nbOccurence = nbOccurence+1;
						useCard.add(carteTransports.get(i));
					}
					i++;
				}
			}
		}
		return nbOccurence;
	}

	public void majScoreRoadTaken(int nbCases){
		switch (nbCases){
			case 1:
				this.setScore(this.getScore()+1);
				break;
			case 2:
				this.setScore(this.getScore()+2);
				break;
			case 3:
				this.setScore(this.getScore()+4);
				break;
			case 4:
				this.setScore(this.getScore()+7);
				break;
			case 5:
				this.setScore(this.getScore()+10);
				break;
			case 6:
				this.setScore(this.getScore()+15);
				break;
			case 7:
				this.setScore(this.getScore()+18);
				break;
			case 8:
				this.setScore(this.getScore()+21);
				break;
		}

		if(this.getScore()<0){
			this.setScore(0);
		}
	}

}
