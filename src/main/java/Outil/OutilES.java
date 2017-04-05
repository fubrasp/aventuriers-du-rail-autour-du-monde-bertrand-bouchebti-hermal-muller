package Outil;

import Modeles.Carte;
import Modeles.CarteTransport;
import Modeles.CarteTransportBateau;
import Vues.PiochesController;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilES {

    private static final String CHEMIN_BASE_TRANSPORT = "src/main/resources/images/cartes/transport/";
    private static final String CHEMIN_BASE_CARTE_WAGON = CHEMIN_BASE_TRANSPORT + "wagons/wagons";
    private static final String CHEMIN_BASE_CARTE_BATEAU = CHEMIN_BASE_TRANSPORT + "bateaux/";
    private static final String CHEMIN_BASE_CARTE_BATEAU_SIMPLE = CHEMIN_BASE_CARTE_BATEAU + "bateaux";
    private static final String CHEMIN_BASE_CARTE_BATEAU_DOUBLE = CHEMIN_BASE_CARTE_BATEAU + "doubles/bateaux";
    public static final String EXTENSION_IMAGES = ".jpeg";


   /* public static String determinerUrl(CarteTransport ct) {
        String chemin = "";
        if (!ct.getCouleur().equals(CarteTransport.JOKER)) {
            if (ct instanceof CarteTransportBateau) {
                if (((CarteTransportBateau) ct).isBateauDouble()) {
                    chemin = CHEMIN_BASE_CARTE_BATEAU_DOUBLE + ct.getUrlAssociatedWithColor();
                } else {
                    chemin = CHEMIN_BASE_CARTE_BATEAU_SIMPLE + ct.getUrlAssociatedWithColor();
                }
            } else {
                chemin = CHEMIN_BASE_CARTE_WAGON + ct.getUrlAssociatedWithColor();
            }
        } else {
            chemin = CHEMIN_BASE_TRANSPORT + CarteTransport.JOKER + EXTENSION_IMAGES;
        }
        return chemin;
    }*/

    public static String determinerUrl(Carte ct) {
        String chemin = "";
        if(ct instanceof CarteTransport){
            if (!((CarteTransport)ct).getCouleur().equals(CarteTransport.JOKER)) {
                if (ct instanceof CarteTransportBateau) {
                    if (((CarteTransportBateau) ct).isBateauDouble()) {
                        chemin = CHEMIN_BASE_CARTE_BATEAU_DOUBLE + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    } else {
                        chemin = CHEMIN_BASE_CARTE_BATEAU_SIMPLE + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }
                } else {
                    chemin = CHEMIN_BASE_CARTE_WAGON + ((CarteTransport)ct).getUrlAssociatedWithColor();
                }
            } else {
                chemin = CHEMIN_BASE_TRANSPORT + CarteTransport.JOKER + EXTENSION_IMAGES;
            }
        }else{
            //Si carte destination
            chemin = OutilDialog.nomFichier;
            System.out.print("CHEMIN A MODIFIER DANS DETERMINER URL CLASSE OUTIL ES");
        }
        return chemin;
    }

}
