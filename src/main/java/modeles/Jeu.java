package modeles;

public class Jeu {

	public static final int NOMBRE_CARTES_TRANSPORT_WAGON = 80;
	public static final int NOMBRE_CARTES_TRANSPORT_BATEAU = 60;
	public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR = 4;
	public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR = 6;
	public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE = 24;
	public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE = 36;
	public static final int NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR = 11;
	public static final int NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR = 4;
	public static final int NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE = 14;

	// Joeur qui est entrain d'effectuer un coup
	private Joueur joueurCourant = new Joueur();
	// private int nombreDeCartePioche;

	// on peut utiliser un dictionnary pour les perfs..
	// HashMap associant un joueur a un score pour un jeu donne
	// private HashMap<String, Integer> scoresJoueurs;

	// joueurs participants a une partie
	// private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();

	// routes d'une partie
	// private ArrayList<Route> routes = new ArrayList<Route>();

	/*
	 * pions de la partie dans la "Banque" ==> les pions qui n'ont pas ete
	 * distribue aux joueurs et qui seront utiles pour les échanges de pions
	 * avec les joeurs
	 */
	// private ArrayList<Pion> pions = new ArrayList<Pion>();

	// private ArrayList<Carte> cartesTransportWagonDefausse = new
	// ArrayList<Carte>();
	// private ArrayList<Carte> cartesTransportBateauDefausse = new
	// ArrayList<Carte>();

	private GestionnairePioches gestionnairePioches;

	public void piocherCarteTransport(Object o) {
		Carte cartePioche;
		if (o instanceof Bateau) {
			cartePioche = this.gestionnairePioches.getPiocheCartesTransportBateau().piocherCarte();
		} else {
			cartePioche = this.gestionnairePioches.getPiocheCartesTransportWagon().piocherCarte();
		}
		this.joueurCourant.ajouterCarteTransport((CarteTransport) cartePioche);

	}

	public Jeu(GestionnairePioches gestionnairePioches) {
		super();
		this.gestionnairePioches = gestionnairePioches;
	}

	public Jeu(){
		this.gestionnairePioches = new GestionnairePioches();
	}

	public boolean detecterTropJokersVisibles() {
		return (this.gestionnairePioches.detecterTropJokersVisibles()>=3);
	}


	public void initialiserJeu() {
		this.gestionnairePioches.initialiserPioches();
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public GestionnairePioches getGestionnairePioches() {
		return gestionnairePioches;
	}
}
