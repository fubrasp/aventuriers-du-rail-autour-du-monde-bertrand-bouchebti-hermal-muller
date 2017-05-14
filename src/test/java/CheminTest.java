import modeles.Jeu;
import modeles.Joueur;
import modeles.Route;
import modeles.Ville;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class CheminTest {
    Jeu jeu;
    Joueur joueur;
    // Route terrestre de taille 3 couleur jaune
    Route routeTerrestreJaune3; // id : "w_j_3_yaku_beij"

    // Route maritime de taille 4 couleur vert
    Route routeMaritimeVerte4; // id : "b_ve_4_reyk_murm"
    Route routeMaritimeGrise6RioLuanda; // id : "b_g_6_rio_luan"
    Route routeTerrestreVerte4;
    Route routeMaritimeRouge7CaraLago;
    Route routeTerrestreVerte4CaraRio;
    Route routeTerrestreJaune2LimaCara;
    Route routeTerrestreGrise2LimaValp;
    Route routeMaritimeVerte3BuenValp;
    Route routeTerrestreBlanche1RioBuen;

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
        routeMaritimeGrise6RioLuanda = jeu.getRoutes().get("b_g_6_rio_luan");
        routeTerrestreVerte4CaraRio = jeu.getRoutes().get("w_ve_4_cara_rio");
        routeMaritimeRouge7CaraLago = jeu.getRoutes().get("b_r_7_cara_lago");
        routeTerrestreJaune2LimaCara = jeu.getRoutes().get("w_j_2_lima_cara");
        routeTerrestreGrise2LimaValp = jeu.getRoutes().get("w_g_2_valp_lima_1");
        routeMaritimeVerte3BuenValp = jeu.getRoutes().get("b_ve_3_valp_buen");
        routeTerrestreBlanche1RioBuen = jeu.getRoutes().get("w_b_1_buen_rio");

        luanda = Jeu.getInstance().getVilles().get("luan");
        rio = Jeu.getInstance().getVilles().get("rio");
        yaku = Jeu.getInstance().getVilles().get("yaku");
        beij = Jeu.getInstance().getVilles().get("beij");

        // Clear card before each test
        joueur.getSelectedCards().clear();
        joueur.getRoutesPossedees().clear();
    }

    @Test
    public void roadContainsCityTest(){
        assertFalse(routeMaritimeGrise6RioLuanda.containsCity(yaku));
        assertTrue(routeMaritimeGrise6RioLuanda.containsCity(rio));
        assertTrue(routeMaritimeGrise6RioLuanda.containsCity(luanda));
        assertFalse(routeMaritimeGrise6RioLuanda.containsCity(beij));
    }

    @Test
    public void constructCheminTest(){
        ArrayList<ArrayList<Route>> chemins = new ArrayList<>();

        joueur.constructChemin(chemins,routeTerrestreJaune2LimaCara);

        assertEquals(routeTerrestreJaune2LimaCara,chemins.get(0).get(0));

        joueur.constructChemin(chemins,routeTerrestreVerte4CaraRio);

        assertEquals(routeTerrestreVerte4CaraRio,chemins.get(0).get(1));

        joueur.constructChemin(chemins,routeMaritimeVerte3BuenValp);

        assertEquals(routeMaritimeVerte3BuenValp,chemins.get(1).get(0));

        joueur.constructChemin(chemins,routeTerrestreGrise2LimaValp);

        assertEquals(routeTerrestreGrise2LimaValp,chemins.get(0).get(3));


    }
}
