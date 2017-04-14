package interfaces;

import modeles.Jeu;

/**
 * Created by bertran95u on 14/04/2017.
 */
public class INTJ {

    //Actions of the gamer
    //Verify
    public static boolean verifierCapaciteJoueur() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDeJouer();
    }

    public static boolean verifierALaCapaciteDePiocherDesCartesDestinations() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDePiocherDesCartesDestinations();
    }

    public static boolean joueurPeutPrendreJokerCartesVisibles() {
        return Jeu.getInstance().getJoueurCourant().peutPiocherJokerCartesVisibles();
    }

    //Manage
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