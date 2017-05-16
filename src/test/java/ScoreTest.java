import modeles.*;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


/**
 * Created by vincenthermal on 24/04/2017.
 */
@RunWith(JUnitPlatform.class)
public class ScoreTest {

    Joueur joueur;
    ArrayList<CarteDestination> destinationsReussies;

    Ville sydney;
    Ville buenos;
    Ville bangkok;
    Ville lima;
    Ville lagos;

    CarteDestination buenosSydney;
    CarteDestination limaLagos;
    CarteDestination buenosBangkok;

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @BeforeEach
    public void executeBeforeAll(){
        this.joueur = new Joueur("Joueur test", "test");
        this.joueur.setScore(randomInt(50,100));
        this.joueur.setNbPionsBateau(randomInt(10,25));
        this.joueur.setNbPionsBateauReserve(25-joueur.getNbPionsBateau());
        this.joueur.setNbPionsWagons(60-joueur.getNbPionsBateau());
        this.joueur.setNbPionsWagonsReserve(50-joueur.getNbPionsWagons());

        sydney = new Ville("sydn","Sydney", true);
        buenos = new Ville("buen","Buenos Aires", true);
        bangkok = new Ville("bang","Bangkok", true);
        lima = new Ville("lima","Lima", true);
        lagos = new Ville("lago","Lagos", true);

        ArrayList<Ville> CD1 = new ArrayList<Ville>();
        CD1.add(buenos);
        CD1.add(sydney);
        buenosSydney = new CarteDestination(10, CD1);

        ArrayList<Ville> CD2 = new ArrayList<Ville>();
        CD2.add(buenos);
        CD2.add(bangkok);
        buenosBangkok = new CarteDestination(20, CD2);

        ArrayList<Ville> CD3 = new ArrayList<Ville>();
        CD3.add(lima);
        CD3.add(lagos);
        limaLagos = new CarteDestination(20, CD3);



    }

    @AfterEach
    public void clear() throws Exception {
    }

    //Test de la méthode majScoreEchange lorsqu'il n'y a pas d'échange de pions
    @Test
    public void testMajScoreSansEchange(){
        System.out.println("TEST MAJ SCORE SANS ECHANGE DE PIONS");
        int scoreExpected= this.joueur.getScore();
        this.joueur.majScoreEchange(this.joueur.getNbPionsWagons(),this.joueur.getNbPionsBateau());
        assertEquals(scoreExpected, this.joueur.getScore());
    }


    //Test de la méthode majScoreEchange lorsqu'il y a un échange de pions
    @Test
    public void testMajScoreEchange(){
        System.out.println("TEST MAJ SCORE AVEC ECHANGE DE PIONS");
        int nbPionsWagonsDebut = this.joueur.getNbPionsWagons();
        int nbPionsBateauDebut = this.joueur.getNbPionsBateau();
        int a = randomInt(0,20);
        int b = randomInt(0,20);
        this.joueur.setNbPionsBateau(nbPionsBateauDebut+a);
        this.joueur.setNbPionsWagons(nbPionsWagonsDebut+b);
        int scoreExpected = this.joueur.getScore()-a-b;
        this.joueur.majScoreEchange(nbPionsWagonsDebut,nbPionsBateauDebut);
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScoreRoadTaken lorsqu'il y a prise de possession d'une route
    @Test
    public void testMajScoreRoute(){
        System.out.println("TEST MAJ SCORE AVEC PRISE DE POSSESSION D'UNE ROUTE");
        int scoreExpected = this.joueur.getScore()+21;
        //Le joueur prend possession d'une route qui est longue de 8 cases et qui devrait donc apporter 21 points au score
        this.joueur.majScoreRoadTaken(8);
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScorePort lorsqu'aucune carte destination n'a été réussie
    @Test
    public void testMajScorePortsSansCartesReussies(){
        System.out.println("TEST MAJ SCORE SANS CARTES DESTINATIONS REUSSIES");
        this.destinationsReussies=new ArrayList<CarteDestination>();
        this.joueur.getVillesPossedees().add(sydney);
        this.joueur.getVillesPossedees().add(buenos);
        int scoreExpected = this.joueur.getScore();
        this.joueur.majScorePort(this.joueur.getVillesPossedees(),destinationsReussies);
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScorePort lorsque le joueur a des cartes destinations réussies mais ne possède pas de ports
    @Test
    public void testMajScoreAvecCartesReussies(){
        System.out.println("TEST MAJ SCORE AVEC CARTES DESTINATIONS REUSSIES SANS PORTS POSSEDES");
        int scoreExpected = this.joueur.getScore();
        this.destinationsReussies=new ArrayList<CarteDestination>();
        this.destinationsReussies.add(buenosBangkok);
        this.destinationsReussies.add(buenosSydney);
        this.joueur.majScorePort(this.joueur.getVillesPossedees(),this.destinationsReussies);
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScorePort lorsque le joueur n'a pas de ports
    @Test
    public void testMajScorePortsSansPorts(){
        System.out.println("TEST MAJ SCORE SANS PORTS POSSEDES");
        this.destinationsReussies=new ArrayList<CarteDestination>();
        int scoreExpected = this.joueur.getScore();
        this.joueur.majScorePort(this.joueur.getVillesPossedees(),destinationsReussies);
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScorePortNonUtilises lorsque le joueur n'a pas utilisé de ports
    @Test
    public void testMajScorePortsNonUtilises(){
        System.out.println("TEST MAJ SCORE SANS PORTS UTILISES");
        int scoreExpected = this.joueur.getScore()-3*4;
        this.joueur.majScorePortsNonUtilises();
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScorePortNonUtilises lorsque le joueur a utilisé les 3 ports
    @Test
    public void testMajScorePortsUtilises(){
        System.out.println("TEST MAJ SCORE AVEC PORTS UTILISES");
        int scoreExpected = this.joueur.getScore();
        this.joueur.getVillesPossedees().add(sydney);
        this.joueur.getVillesPossedees().add(buenos);
        this.joueur.getVillesPossedees().add(bangkok);
        this.joueur.majScorePortsNonUtilises();
        assertEquals(scoreExpected, this.joueur.getScore());
    }

    //Test de la méthode majScorePort lorsque le joueur a des cartes destinations réussies et possède un port
    @Test
    public void testMajScoreAvecCartesReussiesEtPort(){
        System.out.println("TEST MAJ SCORE AVEC CARTES DESTINATIONS REUSSIES ET PORT POSSEDE");
        int scoreExpected = this.joueur.getScore()+30;
        this.destinationsReussies=new ArrayList<CarteDestination>();
        this.destinationsReussies.add(buenosBangkok);
        this.destinationsReussies.add(buenosSydney);
        this.joueur.getVillesPossedees().add(buenos);
        this.joueur.majScorePort(this.joueur.getVillesPossedees(),this.destinationsReussies);
        assertEquals(scoreExpected, this.joueur.getScore());
    }
}
