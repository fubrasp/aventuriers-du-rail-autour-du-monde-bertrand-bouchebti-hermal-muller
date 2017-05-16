import constantes.ConstantesJeu;
import modeles.*;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import outil.ChargerPartie;
import outil.OutilCarte;

import java.util.ArrayList;

/**
 * Created by Flo on 15/05/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChargerPartieTest {
    Jeu jeu;
    Joueur joueur1;
    Joueur joueur2;
    // Route terrestre de taille 3 couleur jaune
    Route routeTerrestreJaune3YakuBeij; // id : "w_j_3_yaku_beij"

    // Route maritime de taille 4 couleur vert
    Route routeMaritimeVerte4ReykMurm; // id : "b_ve_4_reyk_murm"
    Route routeMaritimeGrise6RioLuan; // id : "b_g_6_rio_luan"

    Route routePaire3Cases;

    @BeforeEach
    public void executedBeforeEach() {
        jeu = Jeu.getInstance();

        jeu.getGestionnairePioches().initialiserPioches();
        jeu.getGestionnairePioches().preparerPioches();

        joueur1 = new Joueur("flo","red");
        joueur2 = new Joueur("gui","yellow");
        jeu.getJoueurs().add(joueur1);
        jeu.getJoueurs().add(joueur2);
        jeu.setJoueurCourant(joueur2);
        routeTerrestreJaune3YakuBeij = jeu.getRoutes().get("w_j_3_yaku_beij");
        routeMaritimeVerte4ReykMurm = jeu.getRoutes().get("b_ve_4_reyk_murm");
        routeMaritimeGrise6RioLuan = jeu.getRoutes().get("b_g_6_rio_luan");
        routePaire3Cases = jeu.getRoutes().get("w_spe_3_beij_labo");

        // Clear card before each test
        joueur1.getSelectedCards().clear();
    }

    @Test
    public void AsauvegarderPartie(){
        joueur1.getRoutesPossedees().add(routeMaritimeVerte4ReykMurm);
        joueur1.getRoutesPossedees().add(routeMaritimeGrise6RioLuan);

        joueur1.getVillesPossedees().add(jeu.getVilles().get("casa"));

        joueur1.getCartesDestination().add((CarteDestination)jeu.getGestionnairePioches().getPiocheCartesDestination().getCartes().get(0));

        jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartesDefaussees().add(
                new CarteTransportWagon(Couleur.ROUGE,true)
        );


        joueur2.getRoutesPossedees().add(routePaire3Cases);
        ChargerPartie.sauvegarderPartie(jeu,ChargerPartie.fileSave);
    }

    @Test
    public void BchargerPartie(){
        Jeu jeu = ChargerPartie.chargerPartie(ChargerPartie.file);
        Assert.assertEquals("gui",jeu.getJoueurCourant().getPseudo());
    }

    @Test
    public void createCarteByReferenceTest(){
        ArrayList<CarteTransport> carteTransports = new ArrayList<>();
        carteTransports.add(new CarteTransportWagon(Couleur.ROUGE,true));
        carteTransports.add(new CarteTransportWagon(Couleur.JAUNE,false));
        carteTransports.add(new CarteTransportBateau(Couleur.NOIR,false,true));
        carteTransports.add(new CarteTransportBateau(Couleur.VERT,true,false));
        carteTransports.add(new CarteTransportBateau(ConstantesJeu.JOKER,true,false));

        CarteTransport carteCreated;
        for(CarteTransport carteTransport : carteTransports){
            carteCreated = OutilCarte.createCarteTransportByReference(carteTransport.getReference());
            Assert.assertTrue(carteTransport.compare(carteCreated));
        }
    }

    @Test
    public void createCarteDestination(){
        String ref = "14_atbi_mani";

        CarteDestination carteDestination = OutilCarte.createCarteDestinationByReference(ref);
        Assert.assertEquals(14,carteDestination.getPointsScoreAssoccies());
        Assert.assertEquals("atbi",carteDestination.getVilles().get(0).getId());
        Assert.assertEquals("mani",carteDestination.getVilles().get(1).getId());
    }
}
