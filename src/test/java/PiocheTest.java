import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constantes.ConstantesJeu;
import modeles.*;
import outil.OutilPratique;

import java.util.*;

public class PiocheTest {

    //Definir des attributs pour tester
    private Jeu jeu;
    private PiocheTransport piocheBateau;
    private PiocheTransport piocheWagon;

    @BeforeEach
    public void executedBeforeEach() {
        this.jeu = new Jeu();
        this.jeu.initialiserJeu();
        this.piocheWagon = this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon();
        this.piocheBateau = this.jeu.getGestionnairePioches().getPiocheCartesTransportBateau();
    }

    // On ne teste pas la methode melanger qui utlise une methode du JDK

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
        //
        Pioche pioche = selectRandomPioche();

        int tailleAvantPioche = pioche.taille();

        Carte cartePiochee = pioche.piocherCarte();

        int tailleApresPioche = pioche.taille();

        assertEquals("la carte n'a pas ete retiree de la pioche!",tailleAvantPioche - 1, tailleApresPioche);
        assertTrue("La pioche doit renvoyer une carte transport!", cartePiochee instanceof CarteTransport);
    }

    //On evite les null avec les type de carte specifique renvoyes par la pioche
    //Dans ces methodes
    @Test
    public void testPiocheVideAvecDefausseVide() {
        Pioche pioche = selectRandomPioche();

        //On supprime toutes les cartes de la pioches
        //on ne remplit pas la defausse
        pioche.getCartes().clear();

        int tailleAvantPioche = pioche.taille();

        //On utilise piocher sur une pioche vide
        Carte cartePiochee = pioche.piocherCarte();

        assertEquals("La carte transport renvoyee ne correspond pas au cas ou la pioche est vide et n'a pas de defausse", ConstantesJeu.PAS_DE_CARTE_DANS_LA_DEFAUSSE,((CarteTransport)cartePiochee).getCouleur());
        this.testCartePiocheRetiree(tailleAvantPioche, pioche.taille());
    }

    @Test
    public void testPiocheVideAvecDefausseRemplie() {
        Pioche pioche = selectRandomPioche();

        pioche.getCartes().clear();

        int tailleAvantPioche = pioche.taille();

        Carte cartePiochee = pioche.piocherCarte();

        assertEquals("La carte transport renvoyee ne correspond pas au cas ou la pioche est vide et a une defausse", ConstantesJeu.PAS_DE_CARTE_DANS_LA_DEFAUSSE,((CarteTransport)cartePiochee).getCouleur());
        this.testCartePiocheRetiree(tailleAvantPioche, pioche.taille());
    }

    private ArrayList<CarteTransport> mettreDesCartesDansLaListeDeCartesVisibles(){
        ArrayList<CarteTransport> cartesVisibles = new ArrayList<CarteTransport>();
        for (int i=0; i<3; i++){
            cartesVisibles.add(new CarteTransportWagon(ConstantesJeu.JOKER, true));
        }
        cartesVisibles.add(new CarteTransportBateau(Couleur.BLANC, true, false));
        cartesVisibles.add(new CarteTransportBateau(Couleur.ROUGE, false, true));
        cartesVisibles.add(new CarteTransportWagon(Couleur.VERT, false));
        this.jeu.getGestionnairePioches().setCartesVisibles(cartesVisibles);
        //pareil que this.jeu.getGestionnairePioches().getCartesVisibles()
        return cartesVisibles;
    }

    @Test
    public void testDetecterTropJokersVisibles() {
        ArrayList<CarteTransport> cartesVisibles = this.mettreDesCartesDansLaListeDeCartesVisibles();
        assertTrue(jeu.detecterTropJokersVisibles());

        //Enleve une carte joker donc - de 3 cartes jokers
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
        //
        this.mettreDesCartesDansLaListeDeCartesVisibles();

        this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes().clear();

        this.jeu.getGestionnairePioches().reseterCartesVisibles();
        assertEquals("", 2,this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes().size());
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
        //On sait qu'il y a 65 elements dans la pioche destination..
        piocheDestination.getCartes().subList(0, 63).clear();
        ArrayList<CarteDestination> cartesDestinationsPiochees = piocheDestination.piocherCartesDestination();
        assertEquals("Dans le cas ou il n y a pas assez de cartes, on doit proposer au joueur 1 carte",1, cartesDestinationsPiochees.size());
    }

    @Test
    public void testPiocheDestinationsRemiseSousLeTas(){
        PiocheDestination piocheDestination = this.jeu.getGestionnairePioches().getPiocheCartesDestination();
        ArrayList<CarteDestination> cartesPiochees = piocheDestination.piocherCartesDestination();
        //On simule que le joueur ait pris une carte destination (Cas nominal)
        cartesPiochees.remove(0);
        //On simule l'action de remettre sous la pioche la carte
        piocheDestination.remettreSousLaPioche(cartesPiochees);
        //On verifie qu'on a bien les trois cartes destinations attendus sous la pioche
        ArrayList<Carte> cartesAfterTreatments = piocheDestination.getCartes();
        assertEquals("pas la meme carte", cartesPiochees.get(0).getReference(), cartesAfterTreatments.get(0).getReference());
        assertEquals("pas la meme carte", cartesPiochees.get(1).getReference(), cartesAfterTreatments.get(1).getReference());
        assertEquals("pas la meme carte", cartesPiochees.get(2).getReference(), cartesAfterTreatments.get(2).getReference());
    }


    @Test
    public void testInitialisationPiocheDestinations(){
        PiocheDestination piocheDestination = this.jeu.getGestionnairePioches().getPiocheCartesDestination();
        //Les cartes fournis et rentrees manuellement different de 1...
        assertEquals("", ConstantesJeu.NOMBRES_DE_CARTES_PIOCHE_DESTINATION, piocheDestination.taille()+1);
    }

    @Test
    public void testInitialisationPiocheWagons() {

        PiocheTransport piocheWagon = this.jeu.getGestionnairePioches().getPiocheCartesTransportWagon();
        HashMap<Integer, ArrayList<Integer>> compteCouleurs = this.compterCarteCouleur(piocheWagon);

        //Nombre de cartes de la pioche au depart
        assertEquals("Nombre de carte wagons au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_WAGON, piocheWagon.taille());

        //Nombre de carte de chaque couleur et de jokers
        Iterator it = compteCouleurs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            ArrayList<Integer> values = ((ArrayList<Integer>) pair.getValue());

            if (!pair.getKey().equals(ConstantesJeu.JOKER)) {
                assertEquals("Nombre de carte de couleur " + pair.getKey() + " ne correspondant pas a la regle ", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR, ((int) values.get(0)));
                assertEquals("Nombre de carte de ports inadequat pour la couleur " + pair.getKey() + " ne correspondant pas a la regle ", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR, ((int) values.get(1)));
            } else {
                assertEquals("Nombre de jokers ne correpondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE, ((int) values.get(0)));
                assertEquals("un joker est forcement une carte port: probleme de regles", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE, ((int) values.get(1)));
            }
            it.remove(); // Evite une ConcurrentModificationException
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

        //Nombre de cartes de la pioche au depart
        assertEquals("Nombre de carte bateaux au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU, piocheBateau.taille());

        //Nombre de cartes bateau simple de la pioche au depart
        assertEquals("Nombre de carte bateaux simples au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE, nombreBateauxSimples);

        //nombre de cartes bateau double de la pioche au depart
        assertEquals("Nombre de carte bateaux doubles au debut du jeu ne correspondant pas a la regle", ConstantesJeu.NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE, nombreBateauxDoubles);


        HashMap<Integer, ArrayList<Integer>> compteCouleursBateauxSimples = compterCarteCouleur(ptbs);
        HashMap<Integer, ArrayList<Integer>> compteCouleursBateauxDoubles = compterCarteCouleur(ptbd);

        //Nombre de carte de chaque couleur et de jokers
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

}
