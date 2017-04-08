package outil;

import application.MainApp;
import modeles.CarteTransport;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilPratique {
    public static int nbAleat(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static CarteTransport piocherCarteTransportRandom(MainApp mainApp) {
        int choixPioche = OutilPratique.nbAleat(1, 2);

        if (choixPioche == 1) {
            return (CarteTransport) mainApp.getJeu().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
        } else {
            return (CarteTransport) mainApp.getJeu().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
        }
    }
}