package controllers;

import constantes.ConstantesJeu;
import interfaces.INTJ;
import events.Thrower;
import interfaces.JeuListener;
import javafx.stage.Stage;
import outil.*;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import modeles.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.event.*;

import java.io.File;
import java.net.*;
import java.util.*;

public class PiochesController implements Initializable, JeuListener {

    //On pourrait encampsuler l'empechement des actions mais le refractor ne marche pas convenablement !

    //Gere les alert a partir d'un classe externe
    private OutilDialog outilDialog = new OutilDialog();

    //Gere les objets graphiques a partir d'une classe externe
    private OutilGraphique outilGraphique = new OutilGraphique();

    private static int compteurPositionNouveauBoutonsCartesVisibles = 0;

    @FXML
    private AnchorPane mainPane;

    //Cartes transport du joueur
    @FXML
    private HBox listeBouttonsUserCourant = new HBox();

    //Cartes visibles du jeu
    @FXML
    private VBox listeBouttonsCartesVisibles = new VBox();

    //Cartes destinations du joueur
    @FXML
    private VBox listeDestinations = new VBox();

    //informations du joueur (elements textuels)
    @FXML
    private Text textPseudoJoueur = new Text();

    @FXML
    private Text textScoreJoueur = new Text();

    //Listes pour les cartes destinations
    private static ArrayList<String> choixUtilisateursCartesDestinations = new ArrayList<String>();

    private ArrayList<CarteDestination> carteDestinations = new ArrayList<CarteDestination>();

    //Evenements
    private Thrower thrower = new Thrower();

    private static int nombresTourTotauxRestants;

    /*
    *
	* INITIALISATION DU CONTROLEUR
	*
	*/

    public void initialize(URL location, ResourceBundle resources) {
        //Ajoute ce controller comme listener
        Jeu.getInstance().addListener(this);

        //Affiche les cartes visibles en dynamique
        this.initializationCartesVisibles();

        //Ajoute une cible a ce controlleur qui emet des evenements aussi..

        thrower.addThrowListener(Jeu.getInstance());

        //permet d'afficher les cartes initialisees (7 bateaux and 3 wagons)
        this.refreshInterface();

        nombresTourTotauxRestants = Jeu.getInstance().determinerNombreToursTotauxRestants();
    }

    public void testAInitialiserSesCartesDestinations(){
        if(!Jeu.getInstance().getJoueurCourant().isaInitialiseeSesCartesDestinations()){
            this.gererPiocheDestination();
            Jeu.getInstance().getJoueurCourant().setaInitialiseeSesCartesDestinations(true);
        }
    }

    public void initializationCartesVisibles() {
        compteurPositionNouveauBoutonsCartesVisibles = 0;
        //Get des cartes visibles
        ArrayList<CarteTransport> cartesVisibles = Jeu.getInstance().getGestionnairePioches().getCartesVisibles();

        //Ajoute ces cartes au vbox
        this.addToVBoxBoutton(cartesVisibles);

        //verifie la regle des 3 jokers (aurait pu etre fait en amont)
        verifierTraiterJokersSansDialog();
    }

    /*
	*
	* FONCTIONS
	*
	*/

    /**
     * Methode qui gere l'echange de pions
     */
    @FXML
    private void handleEchangerPions() {
        this.testAInitialiserSesCartesDestinations();
        if(INTJ.verifierCapaciteJoueur()){
            outilDialog.montrerDialogEchangePions(textPseudoJoueur, textScoreJoueur);
            INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_ECHANGE_PIONS);
            Jeu.getInstance().getJoueurCourant().calculerScoreFinal();
        }else{
            this.notifierEtPasserLeTour("vous ne pouvez plus echanger de pions");
        }
    }

    /**
     * Methode qui gere la pioche bateau
     */
    @FXML
    private void handlePiocheBateau() {
        this.testAInitialiserSesCartesDestinations();
        this.piocher(ConstantesJeu.PIOCHE_BATEAU);
    }

    /**
     * Methode qui gere la pioche wagon
     */
    @FXML
    private void handlePiocheWagon() {
        this.testAInitialiserSesCartesDestinations();
        this.piocher(ConstantesJeu.PIOCHE_WAGON);
    }

    /**
     * Methode qui gere l'ajout de nouvelles cartes destinations
     */
    @FXML
    private void handleDialogNouvelleDestination() {
        this.testAInitialiserSesCartesDestinations();
        this.gererPiocheDestination();
    }

    /**
     * Methode qui gere le boutton tour fini
     */
    @FXML
    private void handleTourFini() {
        this.testAInitialiserSesCartesDestinations();
        this.lancerEvenement();
    }

    /**
     * Methode qui gere la popup des destiantions
     */
    @FXML
    public static void handleButtonAction(ActionEvent e) {
        int count = 0;
        String choices = "";

        //RAFRAICHIR LES CHOIX...
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

    //Methode de triche facilitant le devellopement
    @FXML
    private void handleRESET() {
        INTJ.resterCapaciteJoueur();
    }


    /**
     * Methode qui gere l'action de piocher
     */
    private void piocher(int typePioche) {
        if (INTJ.verifierCapaciteJoueur()) {
            CarteTransport carteTransportPiochee;
            String nomPiocheVide = "";
            if (typePioche == ConstantesJeu.PIOCHE_BATEAU) {
                //on pioche la carte bateau
                carteTransportPiochee = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
                //Ceci est utilise pour les message d'erreurs
                nomPiocheVide = "BATEAUX";
            } else {
                carteTransportPiochee = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
                nomPiocheVide = "WAGONS";
            }

            //Si la pioche a ete refaite a partir de la defausse
            if (carteTransportPiochee.getCouleur() == ConstantesJeu.PIOCHE_REFAITE) {
                outilDialog.montrerDialogPiocheEpuisee();
                //On peut donc piocher une nouvelle carte ! on procede de maniere recursive
                if(typePioche == ConstantesJeu.PIOCHE_BATEAU){
                    handlePiocheBateau();
                }else {
                    handlePiocheWagon();
                }
            } else {
                //Si on ne peut pas reseter le jeu faute de cartes dans les defausses
                if (carteTransportPiochee.getCouleur() == ConstantesJeu.PAS_DE_CARTE_DANS_LA_DEFAUSSE) {
                    outilDialog.montrerDialogDefausseVide(nomPiocheVide);
                } else {
                    //Cas nominal
                    //On enleve 1 piont d'action au joueur
                    INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_PIOCHEE);
                    //On ajoute la carte a la main du joeur
                    gererAjoutCarteMain(carteTransportPiochee);
                }
            }
        }else{
            //Sinon on afiche un message d'erreur
            this.notifierEtPasserLeTour("piocher de cartes transport");
        }
    }

    /**
     * Methode qui affiche un message et passe directement au joueur suivant
     * @param message
     */
    private void notifierEtPasserLeTour(String message){
        outilDialog.montrerDialogActionNonPossible(message);
        this.lancerEvenement();
    }

    /**
     * Methode qui gere la picohes de cartes destinations/itinieraire
     */
    private void gererPiocheDestination() {
        //Quand on utilise la methode generique on a un plantage
        //On est donc obliger de faire de la duplication de code en quelque sorte
        if (INTJ.verifierALaCapaciteDePiocherDesCartesDestinations()) {
            PiocheDestination piocheDestination = Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination();

            if (piocheDestination.estVide()) {
                //il n'y a pas de systeme de defausse pour la pioche de cartes destinations/itineraire
                outilDialog.montrerDialogErreurPiocheDestination();
            } else {
                //On choisit des cartes parmi celle ci
                if(Jeu.getInstance().getJoueurCourant().isaInitialiseeSesCartesDestinations()){
                    this.carteDestinations = piocheDestination.piocherCartesDestination();
                }else{
                    this.carteDestinations = piocheDestination.piocherCartesDestinationsInitialisation();
                }

                INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_DESTINATIONS_PIOCHEES);
                //Cas d'erreur si aucune carte n'a ete choisie
                if (this.carteDestinations.isEmpty()) {
                    outilDialog.montrerDialogErreurPiocheDestination();
                    INTJ.augmenterCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_DESTINATIONS_PIOCHEES);
                } else {
                    outilDialog.montrerDialogChoixCartesDestination(this.carteDestinations);
                    //Ajoutes les cartes destinations au joueur
                    ajouterDestinationUser();
                }
            }
        } else {
            this.notifierEtPasserLeTour("piocher de cartes destination");
        }
    }

    /**
     * Methode qui permet de concatener les cartes transport dans la main en une seule image clickable
     * Doit-on creer une nouveau bouton ou non ? cela depend de la reference de la carte..
     * @param carte carte transport which is checked
     */
    private void gererAjoutCarteMain(CarteTransport carte) {
        //Compte le noombre de cartes
        int nombreApparitionCarte = Jeu.getInstance().getJoueurCourant().dejaDansLaMainDuJoueur(carte);

        //Dans tous les cas on ajoute la carte a la main du joueur (modele)
        INTJ.ajouterCarteJoueurCourant(carte);

        //Si nous avons deja une carte dans la main du joueur
        if (nombreApparitionCarte > 0) {
            //On met a jour le texte de la carte
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
            //S'il n'y a pas de carte
        } else {
            //Creer un boutton
            //this.listeBouttonsUserCourant.getChildren().add(OutilGraphique.creerAnchorPane(carte));

            this.listeBouttonsUserCourant.getChildren().clear();
            this.rafraichirInterface();
        }
    }

    /**
     * Methode qui affiche les cartes visibles
     * @param listeCartesVisibles qui a ete initialisee
     */
    private void addToVBoxBoutton(ArrayList<CarteTransport> listeCartesVisibles) {
        for (CarteTransport ctw :
                listeCartesVisibles) {
            this.listeBouttonsCartesVisibles.getChildren().add(creerBouttonImageCarteVisibles(ctw));
        }
    }

    /**
     * Methode qui creer et ajoute un comportement aux cartes visibles
     * @param ct carte pour laquelle nous voulons creer une carte visible
     * @return image clickable anchorPane
     */
    private AnchorPane creerBouttonImageCarteVisibles(CarteTransport ct) {
        final AnchorPane nouveauAnchorPaneClickable = OutilGraphique.creerAnchorPane(ct);
        nouveauAnchorPaneClickable.setId("" + compteurPositionNouveauBoutonsCartesVisibles++);

        nouveauAnchorPaneClickable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                testAInitialiserSesCartesDestinations();

                //On connait la position dans le VBox

                //Lorsqu'un un click sur l'element est effectue on place la carte dans la main du joueur
                transfererCarteVisibleALaMainDuJoueur("" + nouveauAnchorPaneClickable.getId());

                //Nous verifions que nous avons 3 jokers suite a l'enlevement de la carte visible
                verifierTraiterJokers();
            }
        });

        return nouveauAnchorPaneClickable;
    }

    /**
     * Methode qui verifie qu'il n'y est pas trop de jokers
     * @return
     */
    private boolean verifierTraiterJokersSansDialog(){
        if (Jeu.getInstance().detecterTropJokersVisibles()) {
            this.reseterCartesVisibles();
            return true;
        }
        return false;
    }

    //Utilise la methode precedente
    private void verifierTraiterJokers() {
        if(this.verifierTraiterJokersSansDialog()){
            outilDialog.montrerDialogErreurJokers();
        }
    }

    /**
     * Methode qui effectue le reset des cartes visibles
     */
    private void reseterCartesVisibles() {
        Jeu.getInstance().getGestionnairePioches().reseterCartesVisibles();
        listeBouttonsCartesVisibles.getChildren().clear();
        this.initializationCartesVisibles();
    }

    /**
     * Methode qui permet le transfert des cartes visibles vers la main du joueur
     * @param id de la carte a transferer
     */
    private void transfererCarteVisibleALaMainDuJoueur(String id) {
        if (INTJ.verifierCapaciteJoueur()) {
            int idInt = Integer.parseInt(id);

            //Obtenir les cartes visibles
            ArrayList<CarteTransport> cartesVisibles = Jeu.getInstance().getGestionnairePioches().getCartesVisibles();

            CarteTransport carteTransportATransferer;
            CarteTransport carteTransportPiochee;

            //Connaitre celle a transferer
            carteTransportATransferer = cartesVisibles.get(idInt);

            //Piocher une nouvelle carte pour remplacer la carte transferee
            carteTransportPiochee = OutilPratique.piocherCarteTransportRandom();

            //Verifications de cas d'erreurs
            if (carteTransportPiochee.getCouleur() != ConstantesJeu.PAS_DE_CARTE_DANS_LA_DEFAUSSE) {
                if (carteTransportATransferer.getCouleur() == ConstantesJeu.JOKER) {
                    if (INTJ.joueurPeutPrendreJokerCartesVisibles()) {
                        INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_JOKER_VISIBLE);
                        this.impacterJeuEtCartesVisibles(idInt, carteTransportATransferer, carteTransportPiochee);
                    } else {
                        outilDialog.montrerDialogActionNonPossible("piocher carte joker, piocher une autre carte (pioche ou carte visible non joker)");
                    }
                } else {
                    INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_CARTE_TRANSPORT_PIOCHEE);
                    //Cas nominal, On effectue les changements
                    this.impacterJeuEtCartesVisibles(idInt, carteTransportATransferer, carteTransportPiochee);
                }
            } else {
                AnchorPane anchorePaneSansCarte = (AnchorPane) this.listeBouttonsCartesVisibles.getChildren().get(idInt);
                anchorePaneSansCarte.getChildren().set(0, OutilGraphique.creerImageView("src/main/resources/images/cartes/transport/vide.jpeg"));
                anchorePaneSansCarte.setMaxWidth(50);
                anchorePaneSansCarte.setMaxHeight(50);

                //cause un bug en RMI (on a des copie !)
                this.listeBouttonsCartesVisibles.getChildren().set(idInt, anchorePaneSansCarte);

            }
        } else {
            this.notifierEtPasserLeTour("piocher de cartes visibles");
        }
    }

    /**
     * Methode qui effectue les changements pour la methode de transfert de carte
     * @param idInt identifiant de la carte dans le vbox des cartes visibles
     * @param carteTransportATransferer carte a transferer
     * @param carteTransportPiochee carte qui a ete piochee
     */
    private void impacterJeuEtCartesVisibles(int idInt, CarteTransport carteTransportATransferer, CarteTransport carteTransportPiochee) {
        //On remplace la carte
        Jeu.getInstance().getGestionnairePioches().getCartesVisibles().set(idInt, carteTransportPiochee);

        //On remplace l'image clickable
        AnchorPane anchorePaneAChanger = creerBouttonImageCarteVisibles(carteTransportPiochee);
        anchorePaneAChanger.setId("" + idInt);
        this.listeBouttonsCartesVisibles.getChildren().set(idInt, anchorePaneAChanger);

        gererAjoutCarteMain(carteTransportATransferer);
    }

    /**
     * Methode qui ajoute les destinations du joueur
     */
    private void ajouterDestinationUser() {
        //On recupere le resultat de l'alert
        //Cas d'erreur, on recurse sur la demande de destinations
        if (choixUtilisateursCartesDestinations.isEmpty()||(!Jeu.getInstance().getJoueurCourant().isaInitialiseeSesCartesDestinations()&&choixUtilisateursCartesDestinations.size()<ConstantesJeu.NOMBRES_DE_CARTES_MINIMUM_DESTINATIONS_INITIALISATION)) {
            outilDialog.montrerDialogAuMoinsUneOuPlusieursrCarte(Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination().nombreDeCarteMinimaleDeCartesAChoisirPopup());
            outilDialog.montrerDialogChoixCartesDestination(Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination().getCartesPrecedentes());
            ajouterDestinationUser();
        } else {
            ArrayList<CarteDestination> cartesDestinationsChoisies = CarteDestination.renvoyerCarteChoisies(this.carteDestinations, choixUtilisateursCartesDestinations);
            //Si des cartes n'ont pas ete choisies on les met sous la pioche destination

            ArrayList<CarteDestination> cartesNonChoisies = (ArrayList<CarteDestination>) this.carteDestinations.clone();
            cartesNonChoisies.removeAll(cartesDestinationsChoisies);
            Jeu.getInstance().getGestionnairePioches().getPiocheCartesDestination().remettreSousLaPioche(cartesNonChoisies);

            Jeu.getInstance().getJoueurCourant().ajouterCartesDestination(cartesDestinationsChoisies);

            //On ajoute l'image clickable au dashboard du joueur
            for (CarteDestination carteDestinationChoisie :
                    cartesDestinationsChoisies) {
                outilGraphique.creerAnchorPaneDestination(carteDestinationChoisie, this.listeDestinations);
            }
            choixUtilisateursCartesDestinations.clear();
        }
    }

    /**
     * Methode qui lance les evenements
     */
    private void lancerEvenement() {
        //On dit a l'objet jeu de passer au joueur suivant

        this.thrower.Throw();

        //On affiche le nouvel user
        //On raffraichit l'ecran de l'utilisateur
        this.listeBouttonsUserCourant.getChildren().clear();
        this.listeDestinations.getChildren().clear();
        this.rafraichirInterface();

        //Traitements pour la fin du jeu
        if (Jeu.getInstance().determinerFinJeu() && nombresTourTotauxRestants > 0) {
            if (!Jeu.getInstance().getJoueurCourant().isAEuInformationFinDuJeu()) {
                outilDialog.montrerDialogFinDuJeuProche();
                Jeu.getInstance().getJoueurCourant().setAEuInformationFinDuJeu(true);
            }
            nombresTourTotauxRestants--;
        } else {
            if(nombresTourTotauxRestants==0){
                outilDialog.montrerDialogFinJeu();
                //Calcule le score final de chaque joueur
                for (Joueur joueur:Jeu.getInstance().getJoueurs()) {
                    joueur.calculerScoreFinal();
                }
            }
        }
    }

    /**
     * Methode qui raffraichit l'interface
     */
    public void rafraichirInterface(){
        Jeu.getInstance().getJoueurCourant().getSelectedCards().clear();

        OutilGraphique.refreshUserInformations(textPseudoJoueur, textScoreJoueur);
        this.listeDestinations.getChildren().clear();
        outilGraphique.refreshUserDestinationCards(this.listeDestinations);
        outilGraphique.refreshUserTransportCards(this.listeBouttonsUserCourant);
    }

    @Override
    public void refreshInterface() {
        this.listeBouttonsUserCourant.getChildren().clear();
        this.listeDestinations.getChildren().clear();
        this.rafraichirInterface();
    }

    @FXML
    public void handleSauvegarder(){
        Stage stage = (Stage)mainPane.getScene().getWindow();
        File fileSelected = FileChooserSample.saveFile(stage);
        ChargerPartie.sauvegarderPartie(Jeu.getInstance(),fileSelected);
    }

    @FXML
    public void handleCharger(){
        Stage stage = (Stage)mainPane.getScene().getWindow();
        File fileSelected = FileChooserSample.selectFile(stage);
        ChargerPartie.chargerPartie(fileSelected);
        refreshInterface();
        Jeu jeu = Jeu.getInstance();
    }
}
