import modeles.*;

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
    }

    @AfterEach
    public void clear() throws Exception {
    }

    //Test de la méthode de mise à jour du score s'il n'y a pas d'échange
    @Test
    public void testMajScoreSansEchange(){
        System.out.println("TEST MAJ SCORE SANS ECHANGE DE PIONS");
        int scoreExpected= this.joueur.getScore();
        this.joueur.majScoreEchange(this.joueur.getNbPionsWagons(),this.joueur.getNbPionsBateau());
        assertEquals(scoreExpected, this.joueur.getScore());
    }


    //Test de la méthode de mise à jour du score lorsqu'il y a un échange de pions
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








}
