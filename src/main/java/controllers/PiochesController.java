package controllers;

import application.MainApp;
import constantes.ConstantesJeu;
import interfaces.INTJ;
import events.Thrower;
import interfaces.JeuListener;
import outil.OutilDialog;
import outil.OutilGraphique;
import outil.OutilPratique;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import modeles.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.event.*;

import java.net.*;
import java.util.*;

public class PiochesController implements Initializable, JeuListener {

    //faudrait encampsuler l'empechement des actions mais le refractor ne marche pas convenablement !

    //Manage alert with this external class
    private OutilDialog outilDialog = new OutilDialog();

    //Manage graphic interactions
    private OutilGraphique outilGraphique = new OutilGraphique();

    private static int compteurPositionNouveauBoutonsCartesVisibles = 0;

    //User's hand (cartes transport)
    @FXML
    private HBox listeBouttonsUserCourant = new HBox();

    //Games's cartes visibles
    @FXML
    private VBox listeBouttonsCartesVisibles = new VBox();

    //User's cartes destinations
    @FXML
    private VBox listeDestinations = new VBox();

    //These two text items display user informations
    @FXML
    private Text textPseudoJoueur = new Text();

    private final static int PIOCHE_BATEAU = 1;
    private final static int PIOCHE_WAGON = 2;

    @FXML
    private Text textScoreJoueur = new Text();

    //These two list are for choices of cartes destinations
    private static ArrayList<String> choixUtilisateursCartesDestinations = new ArrayList<String>();

    private ArrayList<CarteDestination> carteDestinations = new ArrayList<CarteDestination>();

    //EVENTS
    private Thrower thrower = new Thrower();


    //INITIALIZE
    public void initialize(URL location, ResourceBundle resources) {
        Jeu.getInstance().addListener(this);

        //Display the cartes visibles (it is dynamic!)
        this.initializationCartesVisibles();
        //Add a target for event (when turn is finished we throw an event)
        thrower.addThrowListener(Jeu.getInstance());
        //allow us to display the pre-loaded cards when initialize the gamer for the first time (7 bateaux and 3 wagons)
        this.rafraichirInterface();
    }

    public void initializationCartesVisibles() {
        compteurPositionNouveauBoutonsCartesVisibles = 0;
        //Get cartes visibles
        ArrayList<CarteTransport> cartesVisibles = Jeu.getInstance().getGestionnairePioches().getCartesVisibles();
        //Add them to the graphic element
        this.addToVBoxBoutton(cartesVisibles);
        //Verify the rule of not displaying 3 jokers on cartes visibes is respected
        verifierTraiterJokersSansDialog();
    }

    @FXML
    private void handleEchangerPions() {
        if(INTJ.verifierCapaciteJoueur()){
            outilDialog.montrerDialogEchangePions();
            INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_ECHANGE_PIONS);
        }else{
            this.notifierEtPasserLeTour("vous ne pouvez plus echanger de pions");
        }
    }

    @FXML
    private void handlePiocheBateau() {
        this.piocher(ConstantesJeu.PIOCHE_BATEAU);
    }

    /**
     * Method which manage click on pioche wagon button
     */
    @FXML
    private void handlePiocheWagon() {
        this.piocher(ConstantesJeu.PIOCHE_WAGON);
    }

    /**
     * Method which manage click on add destinations button
     */
    @FXML
    private void handleDialogNouvelleDestination() {
        this.gererPiocheDestination();
    }

    /**
     * Method which manage click on TR_FINI button
     */
    @FXML
    private void handleTourFini() {
        this.lancerEvenement();
    }

    /**
     * Method which manage selected and unselected elements in dialog ajout carte destination/itineraire
     */
    @FXML
    public static void handleButtonAction(ActionEvent e) {
        int count = 0;
        String choices = "";

        //REFRESH CHOICES...
        choices = "";
        choixUtilisateursCartesDestinations.clear();

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

    //PROVISOIRE
    @FXML
    private void handleRESET() {
        INTJ.resterCapaciteJoueur();
        //System.out.println("CAPACITE DU JOUEUR : " + Jeu.getInstance().getJoueurCourant().getCapaciteJeu());
    }


    /**
     * Method which manage pioches transport
     */
    private void piocher(int typePioche) {
        if (INTJ.verifierCapaciteJoueur()) {
            CarteTransport carteTransportPiochee;
            String nomPiocheVide = "";
            if (typePioche == ConstantesJeu.PIOCHE_BATEAU) {
                //We directly pioche carte transport bateau
                carteTransportPiochee = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
                //Use after for error messages
                nomPiocheVide = "BATEAUX";
            } else {
                carteTransportPiochee = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
                nomPiocheVide = "WAGONS";
            }

            //If the pioche has been resfreshed
            if (carteTransportPiochee.getCouleur() == CarteTransport.PIOCHE_REFAITE) {
                outilDialog.montrerDialogPiocheEpuisee();
                //We know that the pioche has been resfreshed, we can pioche a new card, so recursive approach
                if(typePioche == ConstantesJeu.PIOCHE_BATEAU){
                    handlePiocheBateau();
                }else {
                    handlePiocheWagon();
                }
            } else {
                //If there are no card in defausses
                if (carteTransportPiochee.getCouleur() == CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE) {
                    outilDialog.montrerDialogDefausseVide(nomPiocheVide);
                } else {
                    //We are in nominal case
                    //We decrease the level of action ability
                    INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_PIOCHEE);
                    //We had the card to the gamer hand (horizontal)
                    gererAjoutCarteMain(carteTransportPiochee);
                }
            }
        }else{
            //Else we display the error case
            this.notifierEtPasserLeTour("piocher de cartes transport");
        }
    }

    private void notifierEtPasserLeTour(String message){
        outilDialog.montrerDialogActionNonPossible(message);
        this.lancerEvenement();
    }

    /**
     * Method which manage pioches destinations/itineraire
     */
    private void gererPiocheDestination() {
        //Quand on utilise la methode generique on a un plantage
        //On est donc obliger de faire de la duplicatin de code en quelque sorte
        if (INTJ.verifierALaCapaciteDePiocherDesCartesDestinations()) {
            PiocheDestination piocheDestination = Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination();

            if (piocheDestination.estVide()) {
                //We know there are no defausse system in the destination pioche
                outilDialog.montrerDialogErreurPiocheDestination();
            } else {
                //We take cards in order to choose one or more of them
                this.carteDestinations = piocheDestination.piocherCartesDestination();
                INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_DESTINATIONS_PIOCHEES);
                //Error Case, we have to choose at least one card
                if (this.carteDestinations.isEmpty()) {
                    outilDialog.montrerDialogErreurPiocheDestination();
                    INTJ.augmenterCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_DESTINATIONS_PIOCHEES);
                } else {
                    outilDialog.montrerDialogChoixCartesDestination(this.carteDestinations);
                    //Add the destinations to the user list of destinations
                    ajouterDestinationUser();
                }
            }
        } else {
            this.notifierEtPasserLeTour("piocher de cartes destination");
        }
    }

    /**
     * Methode which allow to concatenate card with small number text (X2 for instance)
     * Did we create a new button or update an existing button accordind to the reference card ?
     * @param carte carte transport which is checked
     */
    private void gererAjoutCarteMain(CarteTransport carte) {
        //Count the number of card(s) with this reference
        int nombreApparitionCarte = Jeu.getInstance().getJoueurCourant().dejaDansLaMainDuJoueur(carte);

        //In all cases this card is associated with the gamer
        INTJ.ajouterCarteJoueurCourant(carte);

        //If we have a card in the gamer's hand
        if (nombreApparitionCarte > 0) {
            //We update the text
            for (Node n : this.listeBouttonsUserCourant.getChildren()) {
                AnchorPane anchorPaneClickable = (AnchorPane) n;
                if (anchorPaneClickable.getAccessibleText().equals(carte.getReference())) {
                    Text textCourant = ((Text) anchorPaneClickable.getChildren().get(1));
                    if (nombreApparitionCarte >= 9) {
                        textCourant.setLayoutX(35);
                    }
                    textCourant.setText("X" + (nombreApparitionCarte + 1));
                }
            }
            //If there are no card with this reference
        } else {
            //Create a button
            //this.listeBouttonsUserCourant.getChildren().add(OutilGraphique.creerAnchorPane(carte));

            // Added these 2 lines
            this.listeBouttonsUserCourant.getChildren().clear();
            this.rafraichirInterface();
        }
    }

    /**
     * Method which put in the cartes visibles display the clickables images
     * @param listeCartesVisibles which have to been initialized
     */
    private void addToVBoxBoutton(ArrayList<CarteTransport> listeCartesVisibles) {
        for (CarteTransport ctw :
                listeCartesVisibles) {
            this.listeBouttonsCartesVisibles.getChildren().add(creerBouttonImageCarteVisibles(ctw));
        }
    }

    /**
     * Method with create and add behavior to carte visibles buttons
     * @param ct carte that we want create a carte visible image clickable
     * @return image clickable anchorPane
     */
    private AnchorPane creerBouttonImageCarteVisibles(CarteTransport ct) {
        final AnchorPane nouveauAnchorPaneClickable = OutilGraphique.creerAnchorPane(ct);
        nouveauAnchorPaneClickable.setId("" + compteurPositionNouveauBoutonsCartesVisibles++);

        nouveauAnchorPaneClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //We know the element position int the VBOx

                //System.out.println("" + nouveauAnchorPaneClickable.getId());

                //When the element is click put the element in the gamer's hand
                transfererCarteVisibleALaMainDuJoueur("" + nouveauAnchorPaneClickable.getId());

                //Verify we haven't 3 jokers displayed (On peut changer eventuellement la position de ça!!!)
                verifierTraiterJokers();
            }
        });

        return nouveauAnchorPaneClickable;
    }

    /**
     * Method which verify if there are too many jokers
     * @return
     */
    private boolean verifierTraiterJokersSansDialog(){
        if (Jeu.getInstance().detecterTropJokersVisibles()) {
            this.reseterCartesVisibles();
            System.out.println("Verifier joker sans dialog");
            return true;
        }
        return false;
    }

    //Use previous method
    private void verifierTraiterJokers() {
        if(this.verifierTraiterJokersSansDialog()){
            outilDialog.montrerDialogErreurJokers();
        }
    }

    /**
     * Method which done the reset
     */
    private void reseterCartesVisibles() {
        Jeu.getInstance().getGestionnairePioches().reseterCartesVisibles();
        listeBouttonsCartesVisibles.getChildren().clear();
        this.initializationCartesVisibles();
    }

    /**
     * Method wich allow to transfer card from cartes visibles area
     * @param id of the card we have to transfer
     */
    private void transfererCarteVisibleALaMainDuJoueur(String id) {
        if (INTJ.verifierCapaciteJoueur()) {
            int idInt = Integer.parseInt(id);

            //Get cartes visibles
            ArrayList<CarteTransport> cartesVisibles = Jeu.getInstance().getGestionnairePioches().getCartesVisibles();

            CarteTransport carteTransportATransferer;
            CarteTransport carteTransportPiochee;

            //Know which card we have to transfer
            carteTransportATransferer = cartesVisibles.get(idInt);

            //Pioche a new card in order to replace carte transferee
            carteTransportPiochee = OutilPratique.piocherCarteTransportRandom();

            //Some errors verifications
            if (carteTransportPiochee.getCouleur() != CarteTransport.PAS_DE_CARTE_DANS_LA_DEFAUSSE) {
                if (carteTransportATransferer.getCouleur() == CarteTransport.JOKER) {
                    if (INTJ.joueurPeutPrendreJokerCartesVisibles()) {
                        INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_JOKER_VISIBLE);
                        this.impacterJeuEtCartesVisibles(idInt, carteTransportATransferer, carteTransportPiochee);
                    } else {
                        outilDialog.montrerDialogActionNonPossible("piocher carte joker, piocher une autre carte (pioche ou carte visible non joker)");
                    }
                } else {
                    INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_PIOCHEE);
                    //Done changes, nominal case
                    this.impacterJeuEtCartesVisibles(idInt, carteTransportATransferer, carteTransportPiochee);
                }
            } else {
                AnchorPane anchorePaneSansCarte = (AnchorPane) this.listeBouttonsCartesVisibles.getChildren().get(idInt);
                anchorePaneSansCarte.getChildren().set(0, OutilGraphique.creerImageView("src/main/resources/images/cartes/transport/vide.jpeg"));
                anchorePaneSansCarte.setMaxWidth(50);
                anchorePaneSansCarte.setMaxHeight(50);
                //BIZARRE
                this.listeBouttonsCartesVisibles.getChildren().set(idInt, anchorePaneSansCarte);

            }
        } else {
            this.notifierEtPasserLeTour("piocher de cartes visibles");
        }
    }

    /**
     * Method which physically done changes of transfererCarteVisibleALaMainDuJoueur
     * @param idInt
     * @param carteTransportATransferer carte which we transfer
     * @param carteTransportPiochee carte which has been pioche
     */
    private void impacterJeuEtCartesVisibles(int idInt, CarteTransport carteTransportATransferer, CarteTransport carteTransportPiochee) {
        //We replace the card
        Jeu.getInstance().getGestionnairePioches().getCartesVisibles().set(idInt, carteTransportPiochee);

        //We replace the clickable image
        AnchorPane anchorePaneAChanger = creerBouttonImageCarteVisibles(carteTransportPiochee);
        anchorePaneAChanger.setId("" + idInt);
        this.listeBouttonsCartesVisibles.getChildren().set(idInt, anchorePaneAChanger);

        gererAjoutCarteMain(carteTransportATransferer);
    }

    /**
     * Method which add gamer's destinations
     */
    private void ajouterDestinationUser() {
        //Get the alert result
        //Error case
        if (choixUtilisateursCartesDestinations.isEmpty()) {
            //A VERIFIER
            outilDialog.montrerDialogAuMoinsUnerCarte();
            outilDialog.montrerDialogChoixCartesDestination(Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination().getCartesPrecedentes());
            ajouterDestinationUser();
        } else {
            ArrayList<CarteDestination> cartesDestinationsChoisies = CarteDestination.renvoyerCarteChoisies(this.carteDestinations, choixUtilisateursCartesDestinations);
            //If not choose it means we have to put this under the pioche destination

            //TESTING
            ArrayList<CarteDestination> cartesNonChoisies = (ArrayList<CarteDestination>) this.carteDestinations.clone();
            cartesNonChoisies.removeAll(cartesDestinationsChoisies);
            Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination().remettreSousLaPioche(cartesNonChoisies);

            Jeu.getInstance().getJoueurCourant().ajouterCartesDestination(cartesDestinationsChoisies);

            //We add the clickable image on the gamer's board
            for (CarteDestination carteDestinationChoisie :
                    cartesDestinationsChoisies) {
                outilGraphique.creerAnchorPaneDestination(carteDestinationChoisie, this.listeDestinations);
            }
            choixUtilisateursCartesDestinations.clear();
        }
    }

    /**
     * Method which fire events
     */
    private void lancerEvenement() {
        //We say to Jeu ==> Hi! we have to pass to the other gamer
        this.thrower.Throw();

        //We the display for the user
        //We refresh the 2 views where gamer cards are displayed..
        this.listeBouttonsUserCourant.getChildren().clear();
        this.listeDestinations.getChildren().clear();
        this.rafraichirInterface();
    }

    /**
     * Method which refresh the gamer's interface
     */
    public void rafraichirInterface(){
        Jeu.getInstance().getJoueurCourant().getSelectedCards().clear();

        OutilGraphique.refreshUserInformations(textPseudoJoueur, textScoreJoueur);
        outilGraphique.refreshUserDestinationCards(this.listeDestinations);
        outilGraphique.refreshUserTransportCards(this.listeBouttonsUserCourant);
    }

    @Override
    public void refreshInterface() {
        this.listeBouttonsUserCourant.getChildren().clear();
        this.rafraichirInterface();
    }
}
