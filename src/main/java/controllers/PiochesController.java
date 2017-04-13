package controllers;

import constantes.ConstantesJeu;
import events.Thrower;
import javafx.scene.input.*;
import outil.OutilDialog;
import outil.OutilGraphique;
import outil.OutilPratique;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import modeles.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.event.*;

import java.net.*;
import java.util.*;

public class PiochesController implements Initializable {

    //faudrait encampsuler l'empechement des actions

    private OutilDialog outilDialog = new OutilDialog();

    private static int compteurPositionNouveauBoutonsCartesVisibles = 0;

    @FXML
    private HBox listeBouttonsUserCourant = new HBox();

    @FXML
    private VBox listeBouttonsCartesVisibles = new VBox();

    @FXML
    private VBox listeDestinations = new VBox();

    private final static int PIOCHE_BATEAU = 1;
    private final static int PIOCHE_WAGON = 2;

    private static ArrayList<String> choixUtilisateursCartesDestinations = new ArrayList<String>();

    private ArrayList<CarteDestination> carteDestinations = new ArrayList<CarteDestination>();

    //TEST EVENTS
    Thrower thrower = new Thrower();


    public void initialize(URL location, ResourceBundle resources) {
        this.initializationCartesVisibles();
    }

    public void initializationCartesVisibles() {
        compteurPositionNouveauBoutonsCartesVisibles = 0;

        ArrayList<CarteTransport> cartesVisibles = Jeu.getInstance().getGestionnairePioches().getCartesVisibles();

        this.addToVBoxBoutton(cartesVisibles);

        verifierTraiterJokers();

        this.initialiserMainJoueur();
        //CARTE DESTINATIONS CHOISIES AU DEBUT A AJOUTER


        //TEST EVENTS
        thrower.addThrowListener(Jeu.getInstance());
    }

    private void initialiserMainJoueur(){
        CarteTransport carteAAjouter;
        for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_BATEAU_INITIALISATION; i++) {
            carteAAjouter = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
            gererAjoutCarteMain(carteAAjouter);
        }
        for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_WAGON_INITIALISATION; i++) {
            carteAAjouter = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
            gererAjoutCarteMain(carteAAjouter);
        }
    }

    private void piocher(int typePioche) {
        if(verifierCapaciteJoueur()){
            CarteTransport carteTransportPiochee;
            String nomPiocheVide = "";
            if (typePioche == PIOCHE_BATEAU) {
                //We directly pioche carte transport bateau
                carteTransportPiochee = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
                nomPiocheVide = "BATEAUX";
            } else {
                carteTransportPiochee = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
                nomPiocheVide = "WAGONS";
            }

            if (carteTransportPiochee.getCouleur()==CarteTransport.PIOCHE_REFAITE) {
                outilDialog.montrerDialogPiocheEpuisee();
                //We know that the pioche has been resfreshed, we can pioche a new card, so recursive approach
                handlePiocheBateau();
            } else {
                if (carteTransportPiochee.getCouleur()==CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE) {
                    outilDialog.montrerDialogDefausseVide(nomPiocheVide);
                } else {
                    //On l'ajoute a la main du joueur (en horizontal)
                    diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_PIOCHEE);
                    gererAjoutCarteMain(carteTransportPiochee);
                }
            }
        }else{
            outilDialog.montrerDialogActionNonPossible("piocher de cartes transport");

            //EVENTS
            this.lancerEvenement();
        }
    }

    /**
     * Method which manage click on pioche bateau button
     */
    @FXML
    private void handlePiocheBateau() {
        this.piocher(PIOCHE_BATEAU);
    }

    /**
     * Method which manage click on pioche wagon button
     */
    @FXML
    private void handlePiocheWagon() {
        this.piocher(PIOCHE_WAGON);
    }

    /**
     * Methode gerant le click sur le boutton d'ajout de destination
     */
    @FXML
    private void handleDialogNouvelleDestination() {
        this.gererPiocheDestination();
    }

    private void gererPiocheDestination(){
        if(this.verifierALaCapaciteDePiocherDesCartesDestinations()){
            PiocheDestination piocheDestination = Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination();

            if (piocheDestination.estVide()) {
                outilDialog.montrerDialogErreurPiocheDestination();
            } else {
                this.carteDestinations = piocheDestination.piocherCartesDestination();
                this.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_DESTINATIONS_PIOCHEES);
                if (this.carteDestinations.isEmpty()) {
                    outilDialog.montrerDialogErreurPiocheDestination();
                    this.augmenterCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_DESTINATIONS_PIOCHEES);
                } else {
                    outilDialog.montrerDialogChoixCartesDestination(this.carteDestinations);
                    //Nouvel element dans listeDestinations
                    ajouterDestinationUser();
                }
            }
        }else{
            outilDialog.montrerDialogActionNonPossible("piocher de cartes destination");

            //EVENTS
            this.lancerEvenement();
        }
    }


    /**
     * Methode permmettant de savoir si une carte est deja visible depuis la main du joueur permet de concatener plusieurs carte en 1 boutton
     *
     * @param carte carte d'un type donne pourlaquelle on opere la verification
     */
    private void gererAjoutCarteMain(CarteTransport carte) {
        //On compte le nombre de carte du type donne dans la main du joueur
        int nombreApparitionCarte = Jeu.getInstance().getJoueurCourant().dejaDansLaMainDuJoueur(carte);

        //on associe dans tous les cas la carte au joueur
        this.ajouterCarteJoueurCourant(carte);

        //Si il y a deja une carte
        if (nombreApparitionCarte > 0) {
            //On ajoute/modifie le texte sur l'image
            for (Node n : this.listeBouttonsUserCourant.getChildren()) {
                AnchorPane anchorPaneClickable = (AnchorPane) n;
                if (anchorPaneClickable.getAccessibleText().equals(carte.getReference())) {
                    //BAD HACK TO IMPROVE THAT
                    //if(anchorPaneClickable.getChildrenUnmodifiable().size()>2){
                    //((Text) anchorPaneClickable.getChildrenUnmodifiable().get(1)).setText("X" + (nombreApparitionCarte + 1));
                    Text textCourant = ((Text) anchorPaneClickable.getChildren().get(1));
                    if(nombreApparitionCarte>=9){
                        textCourant.setLayoutX(35);
                    }
                    textCourant.setText("X" + (nombreApparitionCarte + 1));

                    //}
                }
            }
            //Si il n'y a pas cette carte dans la main du joueur

        } else {
            //On creer un nouveau boutton
            this.listeBouttonsUserCourant.getChildren().add(OutilGraphique.creerAnchorPane(carte));
        }
    }

    private void ajouterCarteJoueurCourant(CarteTransport ct) {
        Jeu.getInstance().getJoueurCourant().ajouterCarteTransport(ct);
    }

    private void addToVBoxBoutton(ArrayList<CarteTransport> listeCartesVisibles) {
        for (CarteTransport ctw :
                listeCartesVisibles) {
            this.listeBouttonsCartesVisibles.getChildren().add(creerBouttonImageCarteVisibles(ctw));
        }
    }

    private AnchorPane creerBouttonImageCarteVisibles(CarteTransport ct) {
        final AnchorPane nouveauAnchorPaneClickable = OutilGraphique.creerAnchorPane(ct);
        nouveauAnchorPaneClickable.setId("" + compteurPositionNouveauBoutonsCartesVisibles++);

        nouveauAnchorPaneClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //avoir la postion du boutton dans le vbox

                //System.out.println("" + nouveauAnchorPaneClickable.getId());

                //en deduire la position de la carte a transferer dans les une des listes respectives
                //transferer la carte a la main du joueur
                transfererCarteVisibleALaMainDuJoueur("" + nouveauAnchorPaneClickable.getId());
                //remplacer la carte visible concernee par une nouvelle carte piochee au hasard dans une des deux pioches ???

                //checker la regle des 3 jokers
                verifierTraiterJokers();
            }
        });

        return nouveauAnchorPaneClickable;
    }

    private void verifierTraiterJokers() {
        if (Jeu.getInstance().detecterTropJokersVisibles()) {
            reseterCartesVisibles();
            outilDialog.montrerDialogErreurJokers();
        }
    }

    private void reseterCartesVisibles() {
        Jeu.getInstance().getGestionnairePioches().reseterCartesVisibles();
        listeBouttonsCartesVisibles.getChildren().clear();
        initializationCartesVisibles();
    }


    private void transfererCarteVisibleALaMainDuJoueur(String id) {
        if(verifierCapaciteJoueur()){
            int idInt = Integer.parseInt(id);

            ArrayList<CarteTransport> cartesVisibles = Jeu.getInstance().getGestionnairePioches().getCartesVisibles();

            CarteTransport carteTransportATransferer;
            CarteTransport carteTransportPiochee;

            carteTransportATransferer = cartesVisibles.get(idInt);

            //On pioche une nouvelle carte
            carteTransportPiochee = OutilPratique.piocherCarteTransportRandom();

            if(carteTransportPiochee.getCouleur()!=CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE){
                if(carteTransportATransferer.getCouleur()==CarteTransport.JOKER){
                    if(this.joueurPeutPrendreJokerCartesVisibles()){
                        this.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_JOKER_VISIBLE);
                        this.impacterJeuEtCartesVisibles(idInt,carteTransportATransferer,carteTransportPiochee);
                    }else{
                        outilDialog.montrerDialogActionNonPossible("piocher carte joker, piocher une autre carte (pioche ou carte visible non joker)");
                    }
                }else{
                    this.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_PIOCHEE);
                    this.impacterJeuEtCartesVisibles(idInt,carteTransportATransferer,carteTransportPiochee);
                }
            }else{
                AnchorPane anchorePaneSansCarte = (AnchorPane) this.listeBouttonsCartesVisibles.getChildren().get(idInt);
                anchorePaneSansCarte.getChildren().set(0, OutilGraphique.creerImageView("src/main/resources/images/cartes/transport/vide.jpeg"));
                anchorePaneSansCarte.setMaxWidth(50);
                anchorePaneSansCarte.setMaxHeight(50);
                //BIZARRE
                this.listeBouttonsCartesVisibles.getChildren().set(idInt, anchorePaneSansCarte);

            }
        }else{
            outilDialog.montrerDialogActionNonPossible("piocher de cartes visibles");

            //EVENTS
            this.lancerEvenement();
        }
    }

    private void impacterJeuEtCartesVisibles(int idInt, CarteTransport carteTransportATransferer, CarteTransport carteTransportPiochee){
        //On remplace la carte dans la liste des cartes visibles
        Jeu.getInstance().getGestionnairePioches().getCartesVisibles().set(idInt, carteTransportPiochee);

        //On remplace le boutton
        AnchorPane anchorePaneAChanger = creerBouttonImageCarteVisibles(carteTransportPiochee);
        anchorePaneAChanger.setId("" + idInt);
        this.listeBouttonsCartesVisibles.getChildren().set(idInt, anchorePaneAChanger);

        gererAjoutCarteMain(carteTransportATransferer);
    }


    @FXML
    public static void handleButtonAction(ActionEvent e) {
        int count = 0;
        String choices = "";

        for (CheckBox cb :
                OutilDialog.checkBoxes) {
            if (cb.isSelected()) {
                count++;
                choices += cb.getText() + "\n";
                choixUtilisateursCartesDestinations.add(cb.getText());
            }
        }

        OutilDialog.lbltotal.setText("Cartes destination/itineraire choisies" + count);
        OutilDialog.lbllist.setText(choices);
    }

    private void ajouterDestinationUser() {
        //On recupere le resultat de l'alert
        if (choixUtilisateursCartesDestinations.isEmpty()){
            //A VERIFIER
            outilDialog.montrerDialogAuMoinsUnerCarte();
            outilDialog.montrerDialogChoixCartesDestination(Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination().getCartesPrecedentes());
            ajouterDestinationUser();
        }else{
            for (String choix :
                    choixUtilisateursCartesDestinations) {
                System.out.println(choix);
            }
            ArrayList<CarteDestination> cartesDestinationsChoisies = CarteDestination.renvoyerCarteChoisies(this.carteDestinations, choixUtilisateursCartesDestinations);
            Jeu.getInstance().getJoueurCourant().ajouterCartesDestination(cartesDestinationsChoisies);

            //On impacte de maniere graphique le VBOW concerne
            AnchorPane bouttonDestinationCree = null;
            for (CarteDestination carteDestinationChoisie :
                    cartesDestinationsChoisies) {
                bouttonDestinationCree = OutilGraphique.creerAnchorPane(carteDestinationChoisie);
                bouttonDestinationCree.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        outilDialog.montrerDialogCarteDestinationClickee(carteDestinationChoisie);
                    }
                });
                this.listeDestinations.getChildren().add(bouttonDestinationCree);
            }
            choixUtilisateursCartesDestinations.clear();
        }
    }

    private boolean verifierCapaciteJoueur(){
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDeJouer();
    }

    private boolean verifierALaCapaciteDePiocherDesCartesDestinations(){
        return Jeu.getInstance().getJoueurCourant().aLaCapaciteDePiocherDesCartesDestinations();
    }

    private void diminuerCapaciteJoueur(int value){
        Jeu.getInstance().getJoueurCourant().diminuerCapaciteJoueur(value);
    }

    private void augmenterCapaciteJoueur(int value){
        Jeu.getInstance().getJoueurCourant().augmenterCapaciteJoueur(value);
    }

    private boolean joueurPeutPrendreJokerCartesVisibles(){
        return Jeu.getInstance().getJoueurCourant().peutPiocherJokerCartesVisibles();
    }

    @FXML
    private void handleRESET(){
        Jeu.getInstance().getJoueurCourant().setCapaciteJeu(2);
        System.out.println("CAPACITE DU JOUEUR : "+Jeu.getInstance().getJoueurCourant().getCapaciteJeu());
    }

    private void lancerEvenement(){
        thrower.Throw();
    }
}
