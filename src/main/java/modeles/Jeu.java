package modeles;
import java.util.*;

import constantes.ConstantesJeu;
import events.ThrowListener;
import interfaces.JeuListener;

import java.util.Map;

/**
 * Classe modelisant le jeu (toute la partie metier)
 */
public class Jeu implements ThrowListener {

	// Joeur qui est entrain d'effectuer un coup
	private Joueur joueurCourant = null;

	// joueurs participants a une partie
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private int indexJeu = 0;

	// Routes d'une partie
	private Map<String,Route> routes;

	// Villes d'une partie
	private Map<String,Ville> villes;

	// Gestionnaire de pioches d'une partie
	private GestionnairePioches gestionnairePioches;

	// Listeners du jeu
	private List<JeuListener> listeners = new ArrayList<JeuListener>();


	/*
    *
	* SINGLETON "efficace"
	*
	*/

	private static class JeuWrapper{
		private static Jeu instanceJeu = new Jeu();
	}

	public static Jeu getInstance(){
		return JeuWrapper.instanceJeu;
	}


	/*
    *
	* CONSTRUCTEURS
	*
	*/

	/**
	 * Construit un jeu (utiliee pour le testing..)
	 * @param gestionnairePioches
	 */
	public Jeu(GestionnairePioches gestionnairePioches) {
		super();
		this.gestionnairePioches = gestionnairePioches;
	}

	/**
	 * Construit un jeu
	 */
	public Jeu(){
		this.gestionnairePioches = new GestionnairePioches();
		this.villes = GestionnairePlateau.cities;
		this.routes = GestionnairePlateau.initRoads(GestionnairePlateau.parsePlateau());
	}

	/*
	*
	* FONCTIONS
	*
	*/

	public boolean detecterTropJokersVisibles() {
		return (this.gestionnairePioches.detecterTropJokersVisibles()>=3);
	}


	public void initialiserJeu() {
		this.gestionnairePioches.initialiserPioches();
	}

	public void initialiserMainDesJoueurs(){
		for (Joueur joueurCourant:
			 this.joueurs) {
			joueurCourant.initialiserMainJoueur();
		}
	}

	/**
	 * Methode qui aide a simuler le fonctionnement d'une liste circulaire pour faire ("a chacun son tour")
	 */
	public void determinerIndexJoueurSuivant(){
		if(this.indexJeu<joueurs.size()-1){
			this.indexJeu++;
		}else{
			this.indexJeu=0;
		}
	}

	/**
	 * Methode realisant un tour de jeu
	 * Reset les points d'action du joueur precedent (le danger est ridicule..)
	 * Change le joueur courant
	 */
	public void realiserTourDeJeu(){
		this.joueurCourant.reseterCapaciteJoueur();
		this.determinerIndexJoueurSuivant();
		this.joueurCourant = this.joueurs.get(this.indexJeu);
	}

	/**
	 * Methode determinant si chaque joueur n'est pas dans la regle de fin du jeu (6 pions restants)
	 * @return
	 */
	public boolean determinerFinJeu(){
		for (Joueur joueurConcerne:
				this.joueurs) {
			if (joueurConcerne.getNbBateauxEtWagonsConfondus()<= ConstantesJeu.NOMBRE_PIONS_FIN_JEU){
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne le suivant le nombre de joueurs le nombre total de tour a realiser
	 * en vue d'annoncer au moment opportun la fin du jeu
	 * @return
	 */
	public int determinerNombreToursTotauxRestants(){
		return this.joueurs.size()*ConstantesJeu.NOMBRE_TOUR_PAR_JOUEUR_FIN_JEU;
	}

	/*
	*
	* EVENEMENTS ET LISTENERS
	*
	*/

	public void addListener(JeuListener listener) {
		listeners.add(listener);
	}

	/**
	 * Methode permettant de rafraichir l'interface de maniere intelligence
	 * sans faire un () --> (while (true) refresh())
	 */
	public void refreshInterface() {
		// Notifie tous les listeners
		for (JeuListener jl : listeners)
			jl.refreshInterface();
	}

	/**
	 * Methode permettant de recuperer des evenements
	 */
	@Override
	public void Catch() {
		this.realiserTourDeJeu();
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

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

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}
}
