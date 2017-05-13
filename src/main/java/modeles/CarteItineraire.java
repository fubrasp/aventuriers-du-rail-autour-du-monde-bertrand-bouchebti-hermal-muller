package modeles;

import java.util.ArrayList;

/**
 * Created by bertran95u on 22/04/2017.
 */

/**
 * Classe modelisant une carte itineraire
 * Il s'agit d'une carte destination particuliere
 */
public class CarteItineraire extends CarteDestination {

    //Points que l'on peut gagner si l'on a des routes passant par toute les villes sans toutefois passer par le chemin de la carte
    private int pointsVillesReliesSansRouteCorrespondante;

    //Points que l'on perd si la carte itineraire n'est pas du tout validee
    private int pointsPerdus;

    /*
    *
	* CONSTRUCTEUR
	*
	*/

    /**
     *
     * @param pointsScoreAssoccies points pour une carte completement valide (chemin + villes)
     * @param pointsVillesReliesSansRouteCorrespondante points lorsque seulement toute les villes sont atteintes
     * @param pointsPerdus
     * @param villes correspondant a la carte itineraire
     */
    public CarteItineraire(int pointsScoreAssoccies, int pointsVillesReliesSansRouteCorrespondante, int pointsPerdus, ArrayList<Ville> villes) {
        super(pointsScoreAssoccies, villes);
        this.pointsVillesReliesSansRouteCorrespondante = pointsVillesReliesSansRouteCorrespondante;
        this.pointsPerdus = pointsPerdus;
    }
}
