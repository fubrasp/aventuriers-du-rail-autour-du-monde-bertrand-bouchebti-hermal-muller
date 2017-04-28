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
    Route routeTerrestre; // id : "w_j_3_yaku_beij"

    // Route maritime de taille 4 couleur vert
    Route routeMaritimeVerte4; // id : "b_ve_4_reyk_murm"
    Route routeMaritimeGrise; // id : "b_g_6_rio_luan"

    @BeforeEach
    public void executedBeforeEach() {
        jeu = Jeu.getInstance();
        joueur = jeu.getJoueurCourant();
        routeTerrestre = jeu.getRoutes().get("w_j_3_yaku_beij");
        routeMaritimeVerte4 = jeu.getRoutes().get("b_ve_4_reyk_murm");
        routeMaritimeGrise = jeu.getRoutes().get("b_g_6_rio_luan");

        // Clear card before each test
        joueur.getCartesTransportWagon().clear();
        joueur.getCartesTransportBateau().clear();
        joueur.getCartesTransportJoker().clear();
    }

    @Test
    public void isRoadTaken(){
        Assert.assertFalse(routeTerrestre.isTaken());

        routeTerrestre.setPossesseur(joueur);

        Assert.assertTrue(routeTerrestre.isTaken());
    }

    /*
        Test take colored road , all colors except grey and special road
     */
    @Test
    public void takeColoredRoad(){
        ArrayList<CarteTransport> requiredCard = new ArrayList<>();

        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.JAUNE,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.JAUNE,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.JAUNE,false,false));

        joueur.getColoredCards(routeMaritimeVerte4,requiredCard);

        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));

        joueur.getColoredCards(routeMaritimeVerte4,requiredCard);

        Assert.assertEquals(4,requiredCard.size());
    }

    /*
        Test if player has required card in his hand
     */
    @Test
    public void hasRequiredCardInHand(){
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.JAUNE,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.JAUNE,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.JAUNE,false,false));

        Assert.assertEquals(false,joueur.hasRequiredCardsInHand(routeTerrestre));
        Assert.assertEquals(false,joueur.hasRequiredCardsInHand(routeMaritimeVerte4));

        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        Assert.assertEquals(false,joueur.hasRequiredCardsInHand(routeMaritimeVerte4));

        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.getCartesTransportJoker().add(new CarteTransport(CarteTransport.JOKER,false));
        Assert.assertEquals(true,joueur.hasRequiredCardsInHand(routeMaritimeVerte4));
        Assert.assertEquals(1,joueur.getCartesTransportJoker().size());
        Assert.assertEquals(3,joueur.getCartesTransportBateau().size());
    }

    @Test
    public void testBateauDouble(){
        ArrayList<CarteTransport> carteTransports = new ArrayList<>();
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,true));
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));

        // Bateau size = 3
        Assert.assertEquals(3,joueur.getColoredCards(routeMaritimeVerte4,carteTransports));

        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,true));
        joueur.getColoredCards(routeMaritimeVerte4,carteTransports);
        // Bateau size = 5
        Assert.assertEquals(5,carteTransports.size());
    }

    @Test
    public void useJoker(){
        joueur.getCartesTransportBateau().add(new CarteTransportBateau(Couleur.VERT,false,false));
        Assert.assertFalse(joueur.hasRequiredCardsInHand(routeMaritimeVerte4));

        joueur.getCartesTransportJoker().add(new CarteTransport(CarteTransport.JOKER,false));
        Assert.assertFalse(joueur.hasRequiredCardsInHand(routeMaritimeVerte4));

        joueur.getCartesTransportJoker().add(new CarteTransport(CarteTransport.JOKER,false));
        Assert.assertTrue(joueur.hasRequiredCardsInHand(routeMaritimeVerte4));
    }
}
