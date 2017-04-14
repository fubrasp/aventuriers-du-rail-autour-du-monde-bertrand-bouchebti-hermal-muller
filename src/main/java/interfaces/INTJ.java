package interfaces;

import modeles.Jeu;

/**
 * Created by bertran95u on 14/04/2017.
 */
public class INTJ {

    public static boolean verifierCapaciteJoueur() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDeJouer();
    }

    public static boolean verifierALaCapaciteDePiocherDesCartesDestinations() {
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDePiocherDesCartesDestinations();
    }

    public static void diminuerCapaciteJoueur(int value) {
        Jeu.getInstance().getJoueurCourant().diminuerCapaciteJoueur(value);
    }

    public static void augmenterCapaciteJoueur(int value) {
        Jeu.getInstance().getJoueurCourant().augmenterCapaciteJoueur(value);
    }
}
