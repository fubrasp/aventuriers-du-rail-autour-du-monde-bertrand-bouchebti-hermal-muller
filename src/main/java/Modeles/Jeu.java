package Modeles;

import java.util.*;

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

	// private ArrayList<Carte> cartesTransportWagon = new ArrayList<Carte>();
	// private ArrayList<Carte> cartesTransportBateau = new ArrayList<Carte>();

	// private ArrayList<Carte> cartesTransportWagonDefausse = new
	// ArrayList<Carte>();
	// private ArrayList<Carte> cartesTransportBateauDefausse = new
	// ArrayList<Carte>();

	private PiocheTransport piocheCartesTransportWagon;
	private PiocheTransport piocheCartesTransportBateau;
	private PiocheDestination piochesDestinations;

	// private ArrayList<PiocheDestination> cartesDestination = new
	// ArrayList<PiocheDestination>();

	// private Pioche defaussePiocheCartesTransportWagon = new
	// Pioche(cartesTransportWagonDefausse);
	// private Pioche defaussePiocheCartesTransportBateau = new
	// Pioche(cartesTransportBateauDefausse);

	public void piocherCarteTransport(Object o) {
		Carte cartePioche;
		if (o instanceof Bateau) {
			cartePioche = this.piocheCartesTransportBateau.piocherCarte();
		} else {
			cartePioche = this.piocheCartesTransportWagon.piocherCarte();
		}
		this.joueurCourant.ajouterCarteTransport((CarteTransport) cartePioche);

	}

	public Jeu(PiocheTransport piocheCartesTransportWagon, PiocheTransport piocheCartesTransportBateau,
			PiocheDestination cartesDestination) {
		super();
		this.piocheCartesTransportWagon = piocheCartesTransportWagon;
		this.piocheCartesTransportBateau = piocheCartesTransportBateau;
		this.piochesDestinations = cartesDestination;
	}

	public Jeu(){
		this.piocheCartesTransportWagon = new PiocheTransport();
		this.piocheCartesTransportBateau = new PiocheTransport();
		this.piochesDestinations = new PiocheDestination();
	}

	public boolean detecterTropJokersVisibles() {
		if (this.compterJokers(this.piocheCartesTransportWagon)
				+ this.compterJokers(this.piocheCartesTransportBateau) >= 3) {
			return true;
		}
		return false;
	}

	private int compterJokers(PiocheTransport pt) {
		int compteur = 0;
		for (CarteTransport carteTransport : pt.getCartesVisibles()) {
			if (carteTransport.getCouleur().equals(CarteTransport.JOKER)) {
				compteur++;
			}
			// eviter des parcours inutiles
			if (compteur >= 3) {
				return 3;
			}
		}
		return compteur;
	}

	public void initialiserJeu() {
		this.piocheCartesTransportBateau.initialiserPiocheBateaux();
		this.piocheCartesTransportWagon.initialiserPiocheWagons();
		this.piochesDestinations.initialiserPiocheDestinations();
	}

	public void preparerLesPioches(){
		this.piocheCartesTransportWagon.preparerPioche();
		this.piocheCartesTransportBateau.preparerPioche();
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public PiocheTransport getPiocheCartesTransportWagon() {
		return piocheCartesTransportWagon;
	}

	public PiocheTransport getPiocheCartesTransportBateau() {
		return piocheCartesTransportBateau;
	}

	public PiocheDestination getPiochesDestinations() {
		return piochesDestinations;
	}

	public void reseterCartesVisibles(){
		this.piocheCartesTransportWagon.reseterCartesVisibles();
		this.piocheCartesTransportBateau.reseterCartesVisibles();
	}
}
