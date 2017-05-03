import modeles.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class SelectPortTest {
    Jeu jeu;
    Joueur joueur;
    // Route terrestre de taille 3 couleur jaune
    Route routeTerrestreJaune3; // id : "w_j_3_yaku_beij"

    // Route maritime de taille 4 couleur vert
    Route routeMaritimeVerte4; // id : "b_ve_4_reyk_murm"
    Route routeMaritimeGrise6; // id : "b_g_6_rio_luan"

    Ville luanda;
    Ville rio;
    Ville yaku;
    Ville beij;

    @BeforeEach
    public void executedBeforeEach() {
        jeu = Jeu.getInstance();
        joueur = new Joueur("flo","red");
        Jeu.getInstance().setJoueurCourant(joueur);

        routeTerrestreJaune3 = jeu.getRoutes().get("w_j_3_yaku_beij");
        routeMaritimeVerte4 = jeu.getRoutes().get("b_ve_4_reyk_murm");
        routeMaritimeGrise6 = jeu.getRoutes().get("b_g_6_rio_luan");

        luanda = Jeu.getInstance().getVilles().get("luan");
        rio = Jeu.getInstance().getVilles().get("rio");
        yaku = Jeu.getInstance().getVilles().get("yaku");
        beij = Jeu.getInstance().getVilles().get("beij");

        // Clear card before each test
        joueur.getSelectedCards().clear();
        joueur.getRoutesPossedees().clear();
    }

    @Test
    public void setVillePossesseurTest(){
        Assert.assertFalse(luanda.hasPossesseur());
        Assert.assertFalse(beij.hasPossesseur());
        Assert.assertFalse(yaku.hasPossesseur());
        Assert.assertFalse(rio.hasPossesseur());

        luanda.setPossesseur(joueur);
        rio.setPossesseur(joueur);
        yaku.setPossesseur(joueur);

        Assert.assertTrue(luanda.hasPossesseur());
        Assert.assertFalse(beij.hasPossesseur());
        Assert.assertTrue(yaku.hasPossesseur());
        Assert.assertTrue(rio.hasPossesseur());
    }

    @Test
    public void isRoadConstructedFunctionTest(){
        Assert.assertFalse(joueur.isRoadConstructed(rio));
        Assert.assertFalse(joueur.isRoadConstructed(luanda));
        Assert.assertFalse(joueur.isRoadConstructed(yaku));

        joueur.ajouterRoute(routeMaritimeGrise6);

        Assert.assertTrue(joueur.isRoadConstructed(rio));
        Assert.assertTrue(joueur.isRoadConstructed(luanda));
        Assert.assertFalse(joueur.isRoadConstructed(yaku));
    }

    @Test
    public void checkIfTwoShipTwoRailByColorMethod(){
        ArrayList<CarteTransport> usedCards = new ArrayList<>();
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,true,false));

        Assert.assertEquals(1, joueur.checkIfTwoShipTwoRailByColor(Couleur.VERT,usedCards));

        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,true,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,true));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,true,false));
        joueur.addSelectCard(new CarteTransportWagon(Couleur.VERT,true));

        Assert.assertEquals(3, joueur.checkIfTwoShipTwoRailByColor(Couleur.VERT,usedCards));

        joueur.addSelectCard(new CarteTransportWagon(Couleur.VERT,true));
        joueur.addSelectCard(new CarteTransportWagon(Couleur.VERT,true));

        Assert.assertEquals(4, joueur.checkIfTwoShipTwoRailByColor(Couleur.VERT,usedCards));
    }


}
