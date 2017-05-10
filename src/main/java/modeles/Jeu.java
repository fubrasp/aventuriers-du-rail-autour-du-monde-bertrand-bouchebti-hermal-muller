package modeles;
import java.util.*;

import constantes.ConstantesJeu;
import events.ThrowListener;
import interfaces.JeuListener;

import java.util.Map;

public class Jeu implements ThrowListener {

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
	//private Joueur joueurCourant = new Joueur("Joueur 1","red");
	private Joueur joueurCourant = null;
	// private int nombreDeCartePioche;

	// on peut utiliser un dictionnary pour les perfs..
	// HashMap associant un joueur a un score pour un jeu donne
	// private HashMap<String, Integer> scoresJoueurs;

	// joueurs participants a une partie
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private int indexJeu = 0;

	// Routes d'une partie
	private Map<String,Route> routes;

	// Villes d'une partie
	private Map<String,Ville> villes;

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

	private static class JeuWrapper{
		private static Jeu instanceJeu = new Jeu();
	}

	public static Jeu getInstance(){
		return JeuWrapper.instanceJeu;
	}


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
		this.villes = GestionnairePlateau.cities;
		this.routes = GestionnairePlateau.initRoads(GestionnairePlateau.parsePlateau());
	}

	public boolean detecterTropJokersVisibles() {
		return (this.gestionnairePioches.detecterTropJokersVisibles()>=3);
	}


	public void initialiserJeu() {
		this.gestionnairePioches.initialiserPioches();

		//FOR TESTING
		System.out.println("REFRACTOR THIS L82, Jeu.java");

		/*Joueur deuxiemeJoueur = new Joueur("Guillaume");
		this.joueurs.add(this.joueurCourant);
		this.joueurs.add(deuxiemeJoueur);*/
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public GestionnairePioches getGestionnairePioches() {
		return gestionnairePioches;
	}

	public Map<String, Route> getRoutes() {
		return routes;
	}

	public Map<String, Ville> getVilles() {
		return villes;
	}

	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public void determinerIndexJoueurSuivant(){
		if(this.indexJeu<joueurs.size()-1){
			this.indexJeu++;
		}else{
			this.indexJeu=0;
		}
	}

	public void realiserTourDeJeu(){
		//We change the joueurCourant
		this.joueurCourant.reseterCapaciteJoueur();
		determinerIndexJoueurSuivant();
		this.joueurCourant = this.joueurs.get(this.indexJeu);
		//System.out.println("JOUEUR PRECEDENT : "+this.joueurCourant.getPseudo());
	}

	//EVENTS
	@Override
	public void Catch() {
		//System.out.println("Ok je passe au joueur suivant :) !!");
		this.realiserTourDeJeu();
	}

	private List<JeuListener> listeners = new ArrayList<JeuListener>();

	public void addListener(JeuListener listener) {
		listeners.add(listener);
	}

	public void refreshInterface() {
		// Notify everybody that may be interested.
		for (JeuListener jl : listeners)
			jl.refreshInterface();
	}

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public boolean determinerFinJeu(){
		for (Joueur joueurConcerne:
				this.joueurs) {
			if (joueurConcerne.getNbBateauxEtWagonsConfondus()<= ConstantesJeu.NOMBRE_PIONS_FIN_JEU){
				return true;
			}
		}
		return false;
	}

	public int determinerNombreToursTotauxRestants(){
		return this.joueurs.size()*ConstantesJeu.NOMBRE_TOUR_PAR_JOUEUR_FIN_JEU;
	}
}
