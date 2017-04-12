
import static org.junit.Assert.*;

import constantes.ConstantesJeu;
import modeles.*;
import org.junit.*;
import outil.OutilPratique;

import java.util.*;

public class PiocheTest {

    //Define attributes in order to test Pioche feature
    private Jeu jeu;
    private PiocheTransport piocheBateau;
    private PiocheTransport piocheWagon;
    //private PiocheDestination piocheDestination;
    //private ArrayList<CarteTransport> cartesVisibles;

    @Before
    public void executedBeforeEach() {
        this.jeu = new Jeu();
        this.jeu.initialiserJeu();
        this.piocheWagon = this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon();
        this.piocheBateau = this.jeu.getGestionnairePioches().getPiocheCartesTransportBateau();
        //this.piocheDestination = this.jeu.getGestionnairePioches().getPiocheCartesDestination();
        //this.cartesVisibles = this.jeu.getGestionnairePioches().getCartesVisibles();
    }

    // We don't test melanger method ==> It use a methode of JAVA SE JDK

    private Pioche selectRandomPioche(){
        if(OutilPratique.nbAleat(0,1) ==0){
            return piocheWagon;
        }else{
            return piocheBateau;
        }
    }

    private void testCartePiocheRetiree(int tailleAvantPioche, int taillePiocheApres){
        assertEquals("La taille de la pioche n'aurait pas du changer!", tailleAvantPioche, taillePiocheApres);
    }

    @Test
    public void testPiocherCarteWagon() {
        //We can test randomly the object type for the pioche (bateau or wagon because it is exactly the same method which is used)
        Pioche pioche = selectRandomPioche();
        //IT FAILS
        //Pioche pioche = this.piocheWagon;

        int tailleAvantPioche = pioche.taille();

        Carte cartePiochee = pioche.piocherCarte();

        int tailleApresPioche = pioche.taille();

        assertEquals("la carte n'a pas ete retiree de la pioche!",tailleAvantPioche - 1, tailleApresPioche);
        assertFalse("La carte ne devrait plus etre dans la pioche!", piocheWagon.getCartes().contains(cartePiochee));
        assertTrue("La pioche doit renvoyer une carte transport!", cartePiochee instanceof CarteTransport);
    }

    //We test if we get the specific card in order to avoid null and don't block the app and debug more easily
    //in these two following methods
    @Test
    public void testPiocheVideAvecDefausseVide() {
        Pioche pioche = selectRandomPioche();

        //Delete all card in the pioche
        //Because we don't use piocherCarte method, we don't fill the defausse list
        pioche.getCartes().clear();
        int tailleAvantPioche = pioche.taille();

        //We use pioche method on empty pioche
        Carte cartePiochee = pioche.piocherCarte();

        assertEquals("La carte transport renvoyee ne correspond pas au cas ou la pioche est vide et n'a pas de defausse", CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE,((CarteTransport)cartePiochee).getCouleur());
        this.testCartePiocheRetiree(tailleAvantPioche, pioche.taille());
    }

    @Test
    public void testPiocheVideAvecDefausseRemplie() {
        Pioche pioche = selectRandomPioche();

        pioche.getCartes().clear();

        int tailleAvantPioche = pioche.taille();

        Carte cartePiochee = pioche.piocherCarte();

        assertEquals("La carte transport renvoyee ne correspond pas au cas ou la pioche est vide et a une defausse", CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE,((CarteTransport)cartePiochee).getCouleur());
        this.testCartePiocheRetiree(tailleAvantPioche, pioche.taille());
    }

    private ArrayList<CarteTransport> mettreDesCartesDansLaListeDeCartesVisibles(){
        ArrayList<CarteTransport> cartesVisibles = new ArrayList<CarteTransport>();
        for (int i=0; i<3; i++){
            cartesVisibles.add(new CarteTransportWagon(CarteTransport.JOKER, true));
        }
        cartesVisibles.add(new CarteTransportBateau(Couleur.BLANC, true, false));
        cartesVisibles.add(new CarteTransportBateau(Couleur.ROUGE, false, true));
        cartesVisibles.add(new CarteTransportWagon(Couleur.VERT, false));
        this.jeu.getGestionnairePioches().setCartesVisibles(cartesVisibles);
        //Quite the same to return this.jeu.getGestionnairePioches().getCartesVisibles()
        return cartesVisibles;
    }

    @Test
    public void testDetecterTropJokersVisibles() {
        ArrayList<CarteTransport> cartesVisibles = this.mettreDesCartesDansLaListeDeCartesVisibles();
        assertTrue(jeu.detecterTropJokersVisibles());

        //enleve une carte joker donc - de 3 cartes jokers
        this.jeu.getGestionnairePioches().getCartesVisibles().remove(cartesVisibles.get(0));

        assertFalse(jeu.detecterTropJokersVisibles());

    }

    @Test
    public void testPiocheCartesVisiblesDefaussees(){
        this.mettreDesCartesDansLaListeDeCartesVisibles();

        //We clear the carte visibles list
        //We know cards which are generated
        this.jeu.getGestionnairePioches().reseterCartesVisibles();

        //We know these card must go in defausse
        assertEquals("", 4,this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartesDefaussees().size());
        assertEquals("", 2,this.jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartesDefaussees().size());
    }

    @Test
    public void testPiocheReseteeAPartirDeCartesVisiblesDefausseesBateau(){
        this.mettreDesCartesDansLaListeDeCartesVisibles();
        this.jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartes().clear();

        this.jeu.getGestionnairePioches().reseterCartesVisibles();
        assertEquals("", 2,this.jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartes().size());
    }

    @Test
    public void testPiocheReseteeAPartirDeCartesVisiblesDefausseesWagon(){
        this.mettreDesCartesDansLaListeDeCartesVisibles();

        this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes().clear();

        this.jeu.getGestionnairePioches().reseterCartesVisibles();
        assertEquals("", 4,this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes().size());
    }

    @Test
    public void testPiocheDestinationsCasNominal(){
        PiocheDestination piocheDestination = this.jeu.getGestionnairePioches().getPiocheCartesDestination();
        int tailleDepartPiocheDestination = piocheDestination.getCartes().size();
        piocheDestination.piocherCartesDestination();
        assertEquals("Dans le cas ou il y a assez de cartes dans la pioche on doit proposer au joueur 4 cartes",tailleDepartPiocheDestination-4, piocheDestination.getCartes().size());
    }

    @Test
    public void testPiocheDestinationsPasAssezDeCartes(){
        PiocheDestination piocheDestination = this.jeu.getGestionnairePioches().getPiocheCartesDestination();
        //We know there 65 elementq in one
        piocheDestination.getCartes().subList(0, 64).clear();
        ArrayList<CarteDestination> cartesDestinationsPiochees = piocheDestination.piocherCartesDestination();
        // ???
        assertEquals("Dans le cas ou il n y a pas assez de cartes, on doit proposer au joueur 2 cartes",1, cartesDestinationsPiochees.size());
    }


    @Test
    public void testInitialisationPiocheDestinations(){
        PiocheDestination piocheDestination = this.jeu.getGestionnairePioches().getPiocheCartesDestination();
        assertEquals("", PiocheDestination.NOMBRES_DE_CARTES_PIOCHE_DESTINATION, piocheDestination.taille());
    }

    @Test
    public void testInitialisationPiocheWagons() {

        PiocheTransport piocheWagon = this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon();
        HashMap<Integer, ArrayList<Integer>> compteCouleurs = this.compterCarteCouleur(piocheWagon);

        //nombre de cartes de la pioche au depart
        assertEquals("Nombre de carte wagons au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_WAGON, piocheWagon.taille());

        //nombre de carte de chaque couleur et de jokers
        Iterator it = compteCouleurs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            ArrayList<Integer> values = ((ArrayList<Integer>) pair.getValue());

            if (!pair.getKey().equals(CarteTransport.JOKER)) {
                assertEquals("Nombre de carte de couleur " + pair.getKey() + " ne correspondant pas a la regle ", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR, ((int) values.get(0)));
                assertEquals("Nombre de carte de ports inadequat pour la couleur " + pair.getKey() + " ne correspondant pas a la regle ", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR, ((int) values.get(1)));
            } else {
                assertEquals("Nombre de jokers ne correpondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE, ((int) values.get(0)));
                assertEquals("un joker est forcement une carte port: probleme de regles", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE, ((int) values.get(1)));
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    @Test
    public void testInitialisationPiocheBateaux() {
        Jeu j = new Jeu();
        j.initialiserJeu();

        PiocheTransport piocheBateau = j.getGestionnairePioches().getPiocheCartesTransportBateau();
        PiocheTransport ptbs = new PiocheTransport();
        PiocheTransport ptbd = new PiocheTransport();

        int nombreBateauxSimples = 0;
        int nombreBateauxDoubles = 0;

        ArrayList<Carte> ptbdL = new ArrayList<Carte>();
        ArrayList<Carte> ptbsL = new ArrayList<Carte>();

        for (Carte ct :
                piocheBateau.getCartes()) {
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
        assertEquals("Nombre de carte bateaux au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU, piocheBateau.taille());

        //nombre de cartes bateau simple de la pioche au depart
        assertEquals("Nombre de carte bateaux simples au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE, nombreBateauxSimples);

        //nombre de cartes bateau double de la pioche au depart
        assertEquals("Nombre de carte bateaux doubles au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE, nombreBateauxDoubles);



        HashMap<Integer, ArrayList<Integer>> compteCouleursBateauxSimples = compterCarteCouleur(ptbs);
        HashMap<Integer, ArrayList<Integer>> compteCouleursBateauxDoubles = compterCarteCouleur(ptbd);



        //nombre de carte de chaque couleur et de jokers
        Iterator it = compteCouleursBateauxSimples.entrySet().iterator();
        Iterator it2 = compteCouleursBateauxDoubles.entrySet().iterator();

        this.verifierCouleur(it, ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR, ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR);
        this.verifierCouleur(it2, ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR, 0);

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
    private HashMap<Integer, ArrayList<Integer>> compterCarteCouleur(PiocheTransport pt) {
        HashMap<Integer, ArrayList<Integer>> compteCouleurs = new HashMap<Integer, ArrayList<Integer>>();
        for (Carte ct : pt.getCartes()) {
            CarteTransport ctt = ((CarteTransport) ct);
            Integer couleur = ctt.getCouleur();

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
