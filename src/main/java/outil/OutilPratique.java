package outil;

import modeles.CarteTransport;
import modeles.Jeu;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilPratique {
    public static int nbAleat(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static CarteTransport piocherCarteTransportRandom() {
        int choixPioche = OutilPratique.nbAleat(1, 2);

        if (choixPioche == 1) {
            return (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
        } else {
            return (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
        }
    }
}