import modeles.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(JUnitPlatform.class)
public class SelectRouteTest {
    Jeu jeu;
    Joueur joueur;
    // Route terrestre de taille 3 couleur jaune
    Route routeTerrestreJaune3; // id : "w_j_3_yaku_beij"

    // Route maritime de taille 4 couleur vert
    Route routeMaritimeVerte4; // id : "b_ve_4_reyk_murm"
    Route routeMaritimeGrise6; // id : "b_g_6_rio_luan"

    @BeforeEach
    public void executedBeforeEach() {
        jeu = Jeu.getInstance();
        joueur = new Joueur("flo","red");
        jeu.setJoueurCourant(joueur);
        routeTerrestreJaune3 = jeu.getRoutes().get("w_j_3_yaku_beij");
        routeMaritimeVerte4 = jeu.getRoutes().get("b_ve_4_reyk_murm");
        routeMaritimeGrise6 = jeu.getRoutes().get("b_g_6_rio_luan");

        // Clear card before each test
        joueur.getSelectedCards().clear();
    }

    @Test
    public void isRoadTaken(){
        Assert.assertFalse(routeTerrestreJaune3.isTaken());

        routeTerrestreJaune3.setPossesseur(joueur);

        Assert.assertTrue(routeTerrestreJaune3.isTaken());
    }

    @Test
    public void nbJokerNeeded(){
        Assert.assertEquals(2,joueur.nbJokerNeeded(routeTerrestreJaune3.getNombreEtapes()));
        Assert.assertEquals(3,joueur.nbJokerNeeded(routeMaritimeGrise6.getNombreEtapes()));
        Assert.assertEquals(2,joueur.nbJokerNeeded(routeMaritimeVerte4.getNombreEtapes()));
    }

    @Test
    public void sizeRoadCanBeTakeTest(){
        ArrayList<CarteTransport> requiredCard = new ArrayList<>();

        joueur.addSelectCard(new CarteTransportBateau(Couleur.JAUNE,false,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.JAUNE,false,false));

        Assert.assertEquals(0,joueur.getSizeRoadCanBeTake(routeMaritimeVerte4,requiredCard));
        Assert.assertEquals(0,requiredCard.size());

        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));

        joueur.addSelectCard(new CarteTransportWagon(Couleur.VERT,false));
        joueur.addSelectCard(new CarteTransportWagon(Couleur.VERT,false));

        Assert.assertEquals(2,joueur.getSizeRoadCanBeTake(routeMaritimeVerte4,requiredCard));
        Assert.assertEquals(2,requiredCard.size());
        requiredCard.clear();

        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,true));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));
        Assert.assertEquals(4,joueur.getSizeRoadCanBeTake(routeMaritimeVerte4,requiredCard));
        Assert.assertEquals(3,requiredCard.size());
    }

    @Test
    public void useJoker(){
        ArrayList<CarteTransport> requiredCard = new ArrayList<>();
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));

        Assert.assertFalse(joueur.useJoker(routeMaritimeVerte4.getNombreEtapes(),requiredCard));

        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));

        Assert.assertTrue(joueur.useJoker(joueur.nbJokerNeeded(2),requiredCard));

        requiredCard.clear();
        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));
        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));
        Assert.assertTrue(joueur.useJoker(joueur.nbJokerNeeded(routeMaritimeVerte4.getNombreEtapes()),requiredCard));
        Assert.assertEquals(2,requiredCard.size());

        requiredCard.clear();
        joueur.getSelectedCards().clear();
        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));
        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));
        int jokerNeeded = joueur.nbJokerNeeded(routeMaritimeGrise6.getNombreEtapes());
        Assert.assertFalse(joueur.useJoker(jokerNeeded,requiredCard));
        Assert.assertEquals(0,requiredCard.size());
    }
}
