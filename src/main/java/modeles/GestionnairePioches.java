package modeles;

import constantes.ConstantesJeu;

import java.util.*;

/**
 * Created by bertran95u on 07/04/2017.
 */

/**
 * Classe modelisant la gestion des differentes pioches de cartes
 */
public class GestionnairePioches {

    //eponymes
    private PiocheTransport piocheCartesTransportWagon;
    private PiocheTransport piocheCartesTransportBateau;
    private PiocheDestination piocheCartesDestination;

    //Les 6 cartes visibles du jeu
    private ArrayList<CarteTransport> cartesVisibles = new ArrayList<CarteTransport>();

    /*
    *
	* CONSTRUCTEURS
	*
	*/

    /**
     * Construit un systeme de pioches
     */
    public GestionnairePioches(){
        this.piocheCartesTransportBateau = new PiocheTransport();
        this.piocheCartesTransportWagon = new PiocheTransport();
        this.piocheCartesDestination = new PiocheDestination();
    }

    /*
	*
	* FONCTIONS
	*
	*/

    public void initialiserPioches() {
        this.piocheCartesTransportBateau.initialiserPiocheBateaux();
        this.piocheCartesTransportWagon.initialiserPiocheWagons();
        this.piocheCartesDestination.initialiserPiocheDestinations();
    }

    private void initialiserCarteVisibles(){
        initialisationCarteVisiblesPioche(this.piocheCartesTransportBateau);
        initialisationCarteVisiblesPioche(this.piocheCartesTransportWagon);
    }

    /**
     * Gere la recherche de 3 cartes pour les cartes visibles
     * @param piocheTransport a traiter
     */
    private void initialisationCarteVisiblesPioche(PiocheTransport piocheTransport){
        //Cas nominal
        if(!piocheTransport.getCartes().isEmpty()){
            this.ajouterCartesVisibles(piocheTransport.renvoyerTroisDernieresCarteVisibles());
        //Cas ou il n'y a plus de cartes dans la pioche donnee
        }else {
            //Reconstruction de la pioche a partir de sa defausse
            boolean b = piocheTransport.reconstruirePiocheAvecDefausse();
            if(b)
                this.ajouterCartesVisibles(piocheTransport.renvoyerTroisDernieresCarteVisibles());
        }
    }

    /**
     * Methode qui melange les pioches et pioche les cartes visibles
     */
    public void preparerPioches(){
        this.piocheCartesTransportWagon.melanger();
        this.piocheCartesTransportBateau.melanger();
        this.piocheCartesDestination.melanger();

        this.initialiserCarteVisibles();
    }

    /**
     * Methode utilisee notamment pour la regle des 3 jokers
     */
    public void reseterCartesVisibles(){
        this.dispatcherCartesDefausses(this.cartesVisibles);
        this.cartesVisibles.clear();
        this.initialiserCarteVisibles();
    }

    /**
     * Methode permettant de gerer la contrainte de typage des pioches
     * et ainsi de rediriger vers la bonne pioche les differente cartes transports defaussees (prise de route + reset des cartes visibles du aux 3 jokers)
     * @param cartesADefausser
     */
    public void dispatcherCartesDefausses(ArrayList<CarteTransport> cartesADefausser){
        for (CarteTransport carteVisibleADefausser:
                cartesADefausser) {
            if(carteVisibleADefausser instanceof CarteTransportBateau){
                this.piocheCartesTransportBateau.ajouterCarteDefausse(carteVisibleADefausser);
            }else{
                if (carteVisibleADefausser instanceof CarteTransportWagon){
                    this.piocheCartesTransportWagon.ajouterCarteDefausse(carteVisibleADefausser);
                }
            }
        }
    }

    public int detecterTropJokersVisibles() {
        int compteur = 0;
        for (CarteTransport carteTransport : this.cartesVisibles) {
            if (carteTransport.getCouleur()== ConstantesJeu.JOKER) {
                compteur++;
            }
            // Evite des parcours inutiles
            if (compteur >= 3) {
                return 3;
            }
        }
        return compteur;
    }

    public void ajouterCartesVisibles(ArrayList<CarteTransport> cartesAAjouter){
        this.cartesVisibles.addAll(cartesAAjouter);
    }

    /*
	*
	* GETTER & SETTER
	*
	*/

    public PiocheTransport getPiocheCartesTransportWagon() {
        return piocheCartesTransportWagon;
    }

    public PiocheTransport getPiocheCartesTransportBateau() {
        return piocheCartesTransportBateau;
    }

    public PiocheDestination getPiocheCartesDestination() {
        return this.piocheCartesDestination;
    }

    public ArrayList<CarteTransport> getCartesVisibles() {
        return cartesVisibles;
    }

    public void setCartesVisibles(ArrayList<CarteTransport> cartesVisibles) {
        this.cartesVisibles = cartesVisibles;
    }
}
