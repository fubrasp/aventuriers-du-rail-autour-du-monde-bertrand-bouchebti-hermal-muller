package modeles;

import java.util.ArrayList;

/**
 * Created by bertran95u on 22/04/2017.
 */
public class CarteItineraire extends CarteDestination {

    private int pointsVillesReliesSansRouteCorrespondante;
    private int pointsPerdus;

    public CarteItineraire(int pointsScoreAssoccies, int pointsVillesReliesSansRouteCorrespondante, int pointsPerdus, ArrayList<Ville> villes) {
        super(pointsScoreAssoccies, villes);
        this.pointsVillesReliesSansRouteCorrespondante = pointsVillesReliesSansRouteCorrespondante;
        this.pointsPerdus = pointsPerdus;
    }
}
