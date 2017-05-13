package outil;

import constantes.ConstantesJeu;
import modeles.Carte;
import modeles.CarteDestination;
import modeles.CarteTransport;
import modeles.CarteTransportBateau;

/**
 * Created by bertran95u on 03/04/2017.
 */

/**
 * Classe regroupant des methodes qui permettent d'interagir avec les fichiers sur disque
 */
public class OutilES {

    /**
     * Methode qui renvoie le chemin de l'image de la carte
     * @param ct carte
     * @return String chemin de l'image associee de la carte
     */
    public static String determinerUrl(Carte ct) {
        String chemin = "";
        if(ct instanceof CarteTransport){
            if (((CarteTransport)ct).getCouleur()!= ConstantesJeu.JOKER) {
                if (ct instanceof CarteTransportBateau) {
                    if (((CarteTransportBateau) ct).isBateauDouble()) {
                        chemin = ConstantesJeu.CHEMIN_BASE_CARTE_BATEAU_DOUBLE + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    } else {
                        chemin = ConstantesJeu.CHEMIN_BASE_CARTE_BATEAU_SIMPLE + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }
                } else {
                    if(((CarteTransport) ct).isPort()){
                        chemin = ConstantesJeu.CHEMIN_BASE_CARTE_WAGON_PORT + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }else{
                        chemin = ConstantesJeu.CHEMIN_BASE_CARTE_WAGON + ((CarteTransport)ct).getUrlAssociatedWithColor();
                    }
                }
            } else {
                chemin = ConstantesJeu.CHEMIN_BASE_TRANSPORT + ConstantesJeu.JOKER + ConstantesJeu.EXTENSION_IMAGES;
            }
        }else{
            //Si carte destination
            chemin = ConstantesJeu.CHEMIN_BASE_CARTE_DESTINATIONS_ITINERAIRES + ((CarteDestination)ct).getNomsVilles() + ConstantesJeu.EXTENSION_IMAGES;
        }
        return chemin;
    }

}
