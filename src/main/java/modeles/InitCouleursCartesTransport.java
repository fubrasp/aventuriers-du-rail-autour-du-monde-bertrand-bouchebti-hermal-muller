package modeles;

import java.util.ArrayList;

/**
 * Created by bertran95u on 13/05/2017.
 */

/**
 * Classe modelisant l'initialisation des couleurs des cartes transport
 */
public class InitCouleursCartesTransport {

    public static ArrayList<Integer> couleursDesCartesTransport(){
        ArrayList<Integer> couleursDesCartesTransport = new ArrayList<Integer>();

        couleursDesCartesTransport.add(Couleur.BLANC);
        couleursDesCartesTransport.add(Couleur.NOIR);
        couleursDesCartesTransport.add(Couleur.VERT);
        couleursDesCartesTransport.add(Couleur.JAUNE);
        couleursDesCartesTransport.add(Couleur.ROUGE);
        couleursDesCartesTransport.add(Couleur.VIOLET);

        return couleursDesCartesTransport;
    }
}
