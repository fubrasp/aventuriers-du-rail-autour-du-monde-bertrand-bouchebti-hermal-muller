import modeles.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class RouteTest {
    Jeu jeu;

    @Before
    public void executedBeforeEach() {
        jeu = Jeu.getInstance();
    }

    @Test
    public void takeRoad(){
        // Route terrestre de taille 3 couleur jaune
        Route route = jeu.getRoutes().get("w_j_3_yaku_beij");
        Joueur joueur = jeu.getJoueurCourant();
        joueur.getCartesTransport().add(new CarteTransportWagon("jaune",false));
        joueur.getCartesTransport().add(new CarteTransportWagon("jaune",false));
        joueur.getCartesTransport().add(new CarteTransportWagon("jaune",false));
        ArrayList<CarteTransport> carteTransportsJoueur = joueur.getCartesTransport();
        Assert.assertFalse(route.takeRoad(joueur));
    }
}
