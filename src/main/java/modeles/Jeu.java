package modeles;

import java.util.*;

public class Jeu {


	// Joeur qui est entrain d'effectuer un coup
	private Joueur joueurCourant = new Joueur();
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
		this.villes = GestionnairePlateau.initCities();
		this.routes = GestionnairePlateau.initRoads(GestionnairePlateau.parsePlateau());
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
		determinerIndexJoueurSuivant();
		this.joueurCourant = this.joueurs.get(this.indexJeu);
	}
}
