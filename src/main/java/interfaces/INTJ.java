package interfaces;

import modeles.CarteTransport;
import modeles.Jeu;

/**
 * Created by bertran95u on 14/04/2017.
 */

/**
 * Classe qui permet de faire des raccourcis dans les appels du singleton
 */
public class INTJ {

    //Cartes transport du joueur
    public static void ajouterCarteJoueurCourant(CarteTransport ct) {
        Jeu.getInstance().getJoueurCourant().ajouterCarteTransport(ct);
    }

    //Actions du joueur

    //Verifier
    public static boolean verifierCapaciteJoueur() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDeJouer();
    }

    public static boolean verifierCapaciteJoueurPrendreRoute() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDePrendreRoute();
    }

    public static boolean verifierALaCapaciteDePiocherDesCartesDestinations() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDePiocherDesCartesDestinations();
    }

    public static boolean joueurPeutPrendreJokerCartesVisibles() {
        return Jeu.getInstance().getJoueurCourant().peutPiocherJokerCartesVisibles();
    }

    //Gerer
    public static void diminuerCapaciteJoueur(int value) {
        Jeu.getInstance().getJoueurCourant().diminuerCapaciteJoueur(value);
    }

    public static void augmenterCapaciteJoueur(int value) {
        Jeu.getInstance().getJoueurCourant().augmenterCapaciteJoueur(value);
    }

    public static void resterCapaciteJoueur(){
        Jeu.getInstance().getJoueurCourant().setCapaciteJeu(2);
    }

}
