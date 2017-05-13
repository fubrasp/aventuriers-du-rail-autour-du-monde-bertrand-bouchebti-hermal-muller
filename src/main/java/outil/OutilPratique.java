package outil;

import modeles.CarteTransport;
import modeles.Jeu;

/**
 * Created by bertran95u on 03/04/2017.
 */

/**
 * Classe regroupant un ensemble de methode utiles dans l'ensemble du jeu
 */
public class OutilPratique {
    /**
     * Methode determinant un nombre aleatoire
     * @param min
     * @param max
     * @return int nombre aleatoire
     */
    public static int nbAleat(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    /**
     * Methode utilisee par les cartes visibles au moment du transfert de carte a la main du joueur
     * permet ainsi de remplacer la carte prise par le joueur
     * @return
     */
    public static CarteTransport piocherCarteTransportRandom() {
        int choixPioche = OutilPratique.nbAleat(1, 2);

        if (choixPioche == 1) {
            return (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
        } else {
            return (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
        }
    }
}