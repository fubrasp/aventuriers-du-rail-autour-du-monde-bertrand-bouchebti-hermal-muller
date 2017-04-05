import static org.junit.Assert.*;

import Modeles.*;
import org.junit.*;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class PiocheTest {

    Jeu jeu;
    PiocheTransport pb;
    PiocheTransport pw;
    ArrayList<CarteTransport> cartesVisibles;

    @Before
    public void executedBeforeEach() {

        this.jeu = new Jeu();
        this.jeu.initialiserJeu();
        this.pw = this.jeu.getPiocheCartesTransportWagon();
        this.pb = this.jeu.getPiocheCartesTransportBateau();
        this.cartesVisibles = this.jeu.getPiocheCartesTransportWagon().getCartesVisibles();
    }

    // on ne test pas la methode melanger ==> on va pas retester JAVA SE
    @Test
    public void testPiocherCarte() {
        int tailleAvantPioche = pw.taille();
        Carte c = pw.piocherCarte();
        int tailleApresPioche = pw.taille();

        assertEquals("la carte n'a pas ete retiree de la pioche!", tailleAvantPioche - 1, tailleApresPioche);
        assertTrue(c instanceof CarteTransport);

        //BIZARRE
        assertFalse("La carte ne devrait plus etre dans la pioche!", pw.getCartes().contains(c));

    }

    @Test
    public void testDetecterTropJokersVisibles() {
        ArrayList<CarteTransport> cartesVisibles = new ArrayList<CarteTransport>();
        for (int i=0; i<3; i++){
            cartesVisibles.add(new CarteTransport(CarteTransport.JOKER, true));
        }
        CarteTransport derniereCarteVisible = cartesVisibles.get(0);
        this.jeu.getPiocheCartesTransportWagon().setCartesVisibles(cartesVisibles);

        assertTrue(jeu.detecterTropJokersVisibles());

        //enleve une carte joker donc - de 3 cartes jokers
        this.jeu.getPiocheCartesTransportWagon().getCartesVisibles().remove(cartesVisibles.get(0));

        assertFalse(jeu.detecterTropJokersVisibles());

    }

    @Test
    public void test_initialisation_pioche_wagons() {

        PiocheTransport pw = this.jeu.getPiocheCartesTransportWagon();
        HashMap<String, ArrayList<Integer>> compteCouleurs = this.compterCarteCouleur(pw);

        //nombre de cartes de la pioche au depart
        assertEquals("Nombre de carte wagons au debut du jeu ne correspondant pas a la regle", Jeu.NOMBRE_CARTES_TRANSPORT_WAGON, pw.taille());

        //nombre de carte de chaque couleur et de jokers
        Iterator it = compteCouleurs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            ArrayList<Integer> values = ((ArrayList<Integer>) pair.getValue());

            if (!pair.getKey().equals(CarteTransport.JOKER)) {
                assertEquals("Nombre de carte de couleur " + pair.getKey() + " ne correspondant pas a la regle ", Jeu.NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR, ((int) values.get(0)));
                assertEquals("Nombre de carte de ports inadequat pour la couleur " + pair.getKey() + " ne correspondant pas a la regle ", Jeu.NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR, ((int) values.get(1)));
            } else {
                assertEquals("Nombre de jokers ne correpondant pas a la regle", Jeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE, ((int) values.get(0)));
                assertEquals("un joker est forcement une carte port: probleme de regles", Jeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE, ((int) values.get(1)));
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    @Test
    public void test_initialisation_pioche_bateaux() {
        Jeu j = new Jeu();
        j.initialiserJeu();

        PiocheTransport pb = j.getPiocheCartesTransportBateau();
        PiocheTransport ptbs = new PiocheTransport();
        PiocheTransport ptbd = new PiocheTransport();

        int nombreBateauxSimples = 0, nombreBateauxDoubles = 0;

        ArrayList<Carte> ptbdL = new ArrayList<Carte>();
        ArrayList<Carte> ptbsL = new ArrayList<Carte>();

        for (Carte ct :
                pb.getCartes()) {
            CarteTransportBateau ctb = ((CarteTransportBateau)ct);
            if(ctb.isBateauDouble()){
                ptbdL.add(ctb);
                nombreBateauxDoubles++;
                if(ctb.isPort())
                    fail("bateau double NE DOIT PAS etre avec un port");
            }else{
                ptbsL.add(ctb);
                nombreBateauxSimples++;
                if(!ctb.isPort())
                    fail("bateau simple DOIT etre avec un port");
            }
        }

        ptbd.setCartes(ptbdL);
        ptbs.setCartes(ptbsL);

        //nombre de cartes de la pioche au depart
        assertEquals("Nombre de carte bateaux au debut du jeu ne correspondant pas a la regle", Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU, pb.taille());

        //nombre de cartes bateau simple de la pioche au depart
        assertEquals("Nombre de carte bateaux simples au debut du jeu ne correspondant pas a la regle", Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE, nombreBateauxSimples);

        //nombre de cartes bateau double de la pioche au depart
        assertEquals("Nombre de carte bateaux doubles au debut du jeu ne correspondant pas a la regle", Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE, nombreBateauxDoubles);



        HashMap<String, ArrayList<Integer>> compteCouleursBateauxSimples = compterCarteCouleur(ptbs);
        HashMap<String, ArrayList<Integer>> compteCouleursBateauxDoubles = compterCarteCouleur(ptbd);



        //nombre de carte de chaque couleur et de jokers
        Iterator it = compteCouleursBateauxSimples.entrySet().iterator();
        Iterator it2 = compteCouleursBateauxDoubles.entrySet().iterator();

        this.verifierCouleur(it, Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR, Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR);
        this.verifierCouleur(it2, Jeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR, 0);

    }

    private void verifierCouleur(Iterator it, int cstNombreCartesParCouleur, int cstNombrePortParCouleur){
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ArrayList<Integer> values = ((ArrayList<Integer>) pair.getValue());

            assertEquals("Nombre de carte de couleur " + pair.getKey() + " ne correspondant pas a la regle ", cstNombreCartesParCouleur, ((int) values.get(0)));
            assertEquals("Nombre de carte de ports inadequat pour la couleur " + pair.getKey() + " ne correspondant pas a la regle ", cstNombrePortParCouleur, ((int) values.get(1)));

            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    // A REFRACTOR FONCTIONNEMENT INCOMPREHENSIBLE
    private HashMap<String, ArrayList<Integer>> compterCarteCouleur(PiocheTransport pt) {
        HashMap<String, ArrayList<Integer>> compteCouleurs = new HashMap<String, ArrayList<Integer>>();
        for (Carte ct : pt.getCartes()) {
            CarteTransport ctt = ((CarteTransport) ct);
            String couleur = ctt.getCouleur();

            Integer compteCouleur = 0;
            Integer comptePort = 0;
            ArrayList<Integer> values = new ArrayList<Integer>();
            values.add(0);
            values.add(0);

            if (compteCouleurs.containsKey(couleur)) {
                compteCouleur = compteCouleurs.get(couleur).get(0);
                compteCouleur++;
                values.set(0, compteCouleur);

                if (ctt.isPort()) {
                    comptePort = compteCouleurs.get(couleur).get(1);
                    comptePort++;
                    values.set(1, comptePort);
                }
            } else {
                values.set(0, 1);
                if (ctt.isPort()) {
                    values.set(1, 1);
                }
                compteCouleurs.put(couleur, values);
            }

            if (values.get(1) != 0) {
                compteCouleurs.put(couleur, values);
            } else {
                values.set(1, compteCouleurs.get(couleur).get(1));
                compteCouleurs.put(couleur, values);
            }

        }

        return compteCouleurs;
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

}
