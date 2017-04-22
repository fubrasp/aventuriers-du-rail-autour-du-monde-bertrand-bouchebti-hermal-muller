package outil;

import modeles.Carte;
import modeles.CarteDestination;
import modeles.CarteTransport;
import modeles.CarteTransportBateau;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilES {

    private static final String CHEMIN_BASE_TRANSPORT = "src/main/resources/images/cartes/transport/";
    private static final String CHEMIN_BASE_CARTE_WAGON = CHEMIN_BASE_TRANSPORT + "wagons/wagons";
    private static final String CHEMIN_BASE_CARTE_WAGON_PORT = CHEMIN_BASE_TRANSPORT + "wagons/port/wagons";
    private static final String CHEMIN_BASE_CARTE_BATEAU = CHEMIN_BASE_TRANSPORT + "bateaux/";
    private static final String CHEMIN_BASE_CARTE_BATEAU_SIMPLE = CHEMIN_BASE_CARTE_BATEAU + "bateaux";
    private static final String CHEMIN_BASE_CARTE_BATEAU_DOUBLE = CHEMIN_BASE_CARTE_BATEAU + "doubles/bateaux";
    private static final String CHEMIN_BASE_CARTE_DESTINATIONS_ITINERAIRES = "src/main/resources/images/cartes/destinations/";
    public static final String EXTENSION_IMAGES = ".png";

    public static String determinerUrl(Carte ct) {
        String chemin = "";
        if(ct instanceof CarteTransport){
            if (((CarteTransport)ct).getCouleur()!=CarteTransport.JOKER) {
                if (ct instanceof CarteTransportBateau) {
                    if (((CarteTransportBateau) ct).isBateauDouble()) {
                        chemin = CHEMIN_BASE_CARTE_BATEAU_DOUBLE + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    } else {
                        chemin = CHEMIN_BASE_CARTE_BATEAU_SIMPLE + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }
                } else {
                    if(((CarteTransport) ct).isPort()){
                        chemin = CHEMIN_BASE_CARTE_WAGON_PORT + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }else{
                        chemin = CHEMIN_BASE_CARTE_WAGON + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }
                }
            } else {
                chemin = CHEMIN_BASE_TRANSPORT + CarteTransport.JOKER + EXTENSION_IMAGES;
            }
        }else{
            //Si carte destination
            chemin = CHEMIN_BASE_CARTE_DESTINATIONS_ITINERAIRES + ((CarteDestination)ct).getNomsVilles() + EXTENSION_IMAGES;
        }
        return chemin;
    }

}
