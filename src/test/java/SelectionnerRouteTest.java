import modeles.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import outil.OutilCarte;

import java.util.ArrayList;

/**
 * Created by Flo on 10/05/2017.
 */
public class SelectionnerRouteTest {
    Jeu jeu;
    Joueur joueur;
    // Route terrestre de taille 3 couleur jaune
    Route routeTerrestreJaune3YakuBeij; // id : "w_j_3_yaku_beij"

    // Route maritime de taille 4 couleur vert
    Route routeMaritimeVerte4ReykMurm; // id : "b_ve_4_reyk_murm"
    Route routeMaritimeGrise6RioLuan; // id : "b_g_6_rio_luan"

    Route routePaire3Cases;

    @BeforeEach
    public void executedBeforeEach() {
        jeu = Jeu.getInstance();
        joueur = new Joueur("flo","red");
        jeu.setJoueurCourant(joueur);
        routeTerrestreJaune3YakuBeij = jeu.getRoutes().get("w_j_3_yaku_beij");
        routeMaritimeVerte4ReykMurm = jeu.getRoutes().get("b_ve_4_reyk_murm");
        routeMaritimeGrise6RioLuan = jeu.getRoutes().get("b_g_6_rio_luan");
        routePaire3Cases = jeu.getRoutes().get("w_spe_3_beij_labo");

        // Clear card before each test
        joueur.getSelectedCards().clear();
    }

    @Test
    public void setPossesseurToRouteTest(){
        // Test la fonction isTaken qui renvoie false si la route n'a pas de posesseur et qui renvoie true dans le cas contraire
        Assert.assertFalse(routeTerrestreJaune3YakuBeij.isTaken());
        routeTerrestreJaune3YakuBeij.setPossesseur(joueur);
        Assert.assertTrue(routeTerrestreJaune3YakuBeij.isTaken());
    }

    @Test
    public void nbJokerBesoinTest(){
        // Test la fonction nbJokerBesoin qui renvoie le nombre de joker besoin en fonction du nombre de carte manquante
        Assert.assertEquals(0, OutilCarte.nbJokerBesoin(0));
        Assert.assertEquals(1, OutilCarte.nbJokerBesoin(2));
        Assert.assertEquals(2,OutilCarte.nbJokerBesoin(4));
        Assert.assertEquals(3,OutilCarte.nbJokerBesoin(5));
        Assert.assertEquals(3,OutilCarte.nbJokerBesoin(6));
    }

    @Test
    public void prendreRouteTerrestreJauneTaille3(){
        /*
        Test de la fonction prendreRouteCouleur qui renvoie le nombre de case que peut prendre un joueur
        (maximum taille de la route)
         */

        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();

        // Ajoute 2 cartes transport bateau jaune simple au joueur
        joueur.addSelectCard(new CarteTransportBateau(Couleur.JAUNE,false,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.JAUNE,false,false));

        // Selectionne une route terrestre jaune de 3 cases
        Assert.assertEquals(0,OutilCarte.prendreRouteCouleur(routeTerrestreJaune3YakuBeij,
                cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(0,cartesUtilisees.size());

        // Ajoute 2 cartes transport wagon jaune simple au joueur
        joueur.addSelectCard(new CarteTransportWagon(Couleur.JAUNE,false));
        joueur.addSelectCard(new CarteTransportWagon(Couleur.JAUNE,false));

        // Selectionne une route terrestre jaune de 3 cases
        Assert.assertEquals(2,OutilCarte.prendreRouteCouleur(routeTerrestreJaune3YakuBeij,
                cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(2,cartesUtilisees.size());
    }

    @Test
    public void prendreRouteMaritimeVerteTaille4AvecBateauDouble(){
        /*
        Test de la fonction prendreRouteCouleur qui renvoie le nombre de case que peut prendre un joueur
        (maximum taille de la route)
        Avec cette fois ci l'utilisation des bateaux doubles
         */

        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();

        // Le joueur selectionne deux bateau vert simple et un bateau rouge simple
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.ROUGE,false,false));
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,false));

        Assert.assertEquals(2,OutilCarte.prendreRouteCouleur(routeMaritimeVerte4ReykMurm,
                cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(2,cartesUtilisees.size());

        cartesUtilisees.clear();

        // Selectionne un bateau vert double en plus
        joueur.addSelectCard(new CarteTransportBateau(Couleur.VERT,false,true));
        Assert.assertEquals(4,OutilCarte.prendreRouteCouleur(routeMaritimeVerte4ReykMurm,
                cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(3,cartesUtilisees.size());
    }

    @Test
    public void utiliserJoker(){
        /*
        Test de la fonction utiliser joker qui renvoie true si les jokers ont bien été utilisés, false si pas assez
        de joker selectionnee
        Dans ce cas la nous demandons 2 jokers
         */
        int nbJokerBesoin = 2;
        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();

        // Doit renvoyer false car pas assez de joker selectionné
        Assert.assertFalse(OutilCarte.utiliserJoker(nbJokerBesoin,cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(0,cartesUtilisees.size());

        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));

        // Doit renvoyer false car pas assez de joker selectionné
        Assert.assertFalse(OutilCarte.utiliserJoker(nbJokerBesoin,cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(0,cartesUtilisees.size());

        joueur.addSelectCard(new CarteTransport(CarteTransport.JOKER,false));
        // Doit renvoyer true car 2 jokers sélectionnés
        Assert.assertTrue(OutilCarte.utiliserJoker(nbJokerBesoin,cartesUtilisees,joueur.getSelectedCards()));
        Assert.assertEquals(2,cartesUtilisees.size());
    }

    @Test
    public void getScorePourUneCouleurTest(){
        /*
        Test de la fonction getScorePourUneCouleur qui sélectionne la meilleur couleur pour la remplir
         et renvoie le score (nombre de case qu'une couleur peut remplir)
         */
        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();
        CarteTransportBateau carteTransportBateauVerte = new CarteTransportBateau(Couleur.VERT,false,false);
        CarteTransportBateau carteTransportBateauDoubleVerte = new CarteTransportBateau(Couleur.VERT,false,true);

        // Doit renvoyer 0 car 0 carte sélectionné
        Assert.assertEquals(0,OutilCarte.getScorePourUneCouleur(routeMaritimeVerte4ReykMurm,Couleur.VERT,cartesUtilisees,
                joueur.getSelectedCards()));
        Assert.assertEquals(0,cartesUtilisees.size()); // Verifie le nombre de carte utilisée

        joueur.addSelectCard(carteTransportBateauVerte);
        joueur.addSelectCard(carteTransportBateauDoubleVerte);

        // Doit renvoyer 3 car ajout d'un bateau simple vert(score 1) + ajout d'un bateau vert double (score 2)
        Assert.assertEquals(3,OutilCarte.getScorePourUneCouleur(routeMaritimeVerte4ReykMurm,Couleur.VERT,cartesUtilisees,
                joueur.getSelectedCards()));
        Assert.assertEquals(2,cartesUtilisees.size());
    }

    @Test
    public void prendreRouteGriseTest(){
        /*
        Test de la fonction prendreRouteGrise qui renvoie le score (nombre de case qu'une couleur peut remplir)
        pour une couleur et route donnée
         */
        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();
        CarteTransportBateau carteTransportBateauVerte = new CarteTransportBateau(Couleur.VERT,false,false);
        CarteTransportBateau carteTransportBateauDoubleVerte = new CarteTransportBateau(Couleur.VERT,false,true);
        CarteTransportBateau carteTransportBateauRouge = new CarteTransportBateau(Couleur.ROUGE,false,false);
        CarteTransportBateau carteTransportBateauRougeDouble = new CarteTransportBateau(Couleur.ROUGE,false,true);

        // Doit renvoyer 0 car 0 carte sélectionné
        Assert.assertEquals(0,OutilCarte.prendreRouteGrise(routeMaritimeGrise6RioLuan,cartesUtilisees,
                joueur.getSelectedCards()));
        Assert.assertEquals(0,cartesUtilisees.size()); // Verifie le nombre de carte utilisée

        joueur.addSelectCard(carteTransportBateauVerte);
        joueur.addSelectCard(carteTransportBateauDoubleVerte);

        // Doit renvoyer 3 car ajout d'un bateau simple vert(score 1) + ajout d'un bateau vert double (score 2)
        Assert.assertEquals(3,OutilCarte.prendreRouteGrise(routeMaritimeGrise6RioLuan,cartesUtilisees,
                joueur.getSelectedCards()));
        Assert.assertEquals(2,cartesUtilisees.size());

        cartesUtilisees.clear(); // Reinitialise carte utilisé
        joueur.getSelectedCards().clear(); // Reinitalise les cartes selectionnées

        joueur.addSelectCard(carteTransportBateauVerte);
        joueur.addSelectCard(carteTransportBateauDoubleVerte);

        //scoreCarteRouge = 0
        joueur.addSelectCard(carteTransportBateauRouge); //scoreCarteRouge + 1
        joueur.addSelectCard(carteTransportBateauRouge); //scoreCarteRouge + 1
        joueur.addSelectCard(carteTransportBateauRougeDouble); //scoreCarteRouge +2
        joueur.addSelectCard(carteTransportBateauRouge); //scoreCarteRouge + 1
        joueur.addSelectCard(carteTransportBateauRougeDouble); //scoreCarteRouge +2

        // Doit renvoyer 7 car rouge est la couleur dont le score est plus élevé
        Assert.assertEquals(7,OutilCarte.prendreRouteGrise(routeMaritimeGrise6RioLuan,cartesUtilisees,
                joueur.getSelectedCards()));
        Assert.assertEquals(5,cartesUtilisees.size());
    }

    @Test
    public void defausserCarteMain(){
        /*
        Test de la méthode defausserCartesUtilisees qui supprime les cartes utilisée pour prendre une route/port de la main
         */
        CarteTransportBateau carteTransportBateauRouge = new CarteTransportBateau(Couleur.ROUGE,false,false);
        CarteTransportBateau carteTransportBateauRougeDouble = new CarteTransportBateau(Couleur.ROUGE,false,true);
        CarteTransport carteTransportJoker = new CarteTransport(CarteTransport.JOKER,false);

        joueur.ajouterCarteTransport(carteTransportBateauRouge);
        joueur.ajouterCarteTransport(new CarteTransportBateau(Couleur.ROUGE,false,false));
        joueur.ajouterCarteTransport(carteTransportBateauRougeDouble);
        joueur.ajouterCarteTransport(carteTransportJoker);
        joueur.ajouterCarteTransport(new CarteTransportWagon(Couleur.ROUGE,true));

        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();
        cartesUtilisees.add(new CarteTransportBateau(Couleur.VERT,false,false));
        cartesUtilisees.add(new CarteTransportBateau(Couleur.ROUGE,false,false));
        cartesUtilisees.add(new CarteTransportBateau(Couleur.ROUGE,false,false));
        cartesUtilisees.add(new CarteTransportBateau(Couleur.ROUGE,false,false));

        joueur.defausserCartesUtilisees(cartesUtilisees);

        Assert.assertEquals(3,joueur.getCartesTransport().size());
    }

    @Test
    public void prendreRoutePaireTest(){
        // Test de la méthode prendreRoutePaire
        ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>();
        CarteTransportWagon carteTransportWagonRouge = new CarteTransportWagon(Couleur.ROUGE,false);
        CarteTransportWagon carteTransportWagonJaune = new CarteTransportWagon(Couleur.JAUNE,false);
        CarteTransportWagon carteTransportWagonVert = new CarteTransportWagon(Couleur.VERT,false);

        joueur.addSelectCard(carteTransportWagonRouge);
        joueur.addSelectCard(carteTransportWagonRouge);
        joueur.addSelectCard(carteTransportWagonRouge);

        Assert.assertEquals(1,OutilCarte.prendreRoutePaire(routePaire3Cases,cartesUtilisees,
                joueur.getSelectedCards()));

        joueur.addSelectCard(carteTransportWagonJaune);

        Assert.assertEquals(1,OutilCarte.prendreRoutePaire(routePaire3Cases,cartesUtilisees,
                joueur.getSelectedCards()));

        joueur.addSelectCard(carteTransportWagonJaune);
        Assert.assertEquals(2,OutilCarte.prendreRoutePaire(routePaire3Cases,cartesUtilisees,
                joueur.getSelectedCards()));

        cartesUtilisees.clear();

        joueur.addSelectCard(carteTransportWagonJaune);
        joueur.addSelectCard(carteTransportWagonJaune);
        Assert.assertEquals(3,OutilCarte.prendreRoutePaire(routePaire3Cases,cartesUtilisees,
                joueur.getSelectedCards()));

        Assert.assertEquals(6,cartesUtilisees.size());
    }

}
