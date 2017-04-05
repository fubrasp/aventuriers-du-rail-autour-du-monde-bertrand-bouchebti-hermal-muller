package Vues;

import Outil.OutilDialog;
import Outil.OutilGraphique;
import Outil.OutilPratique;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import Application.*;
import Modeles.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import javafx.event.*;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

public class PiochesController implements Initializable{


        private OutilDialog outilDialog = new OutilDialog();

        private static int compteurPositionNouveauBoutonsCartesVisibles = 0;

        @FXML
        private HBox listeBouttonsUserCourant = new HBox();

        @FXML
        private VBox listeBouttonsCartesVisibles = new VBox();

        @FXML
        private VBox listeDestinations = new VBox();


        private MainApp mainApp;

        private static ArrayList<String> choixUtilisateursCartesDestinations = new ArrayList<String>();

        private ArrayList<CarteDestination> carteDestinations = new ArrayList<CarteDestination>();

        public PiochesController() {
            mainApp = new MainApp();
        }

        public void initialize(URL location, ResourceBundle resources) {
            this.initializationCartesVisibles();
        }

        public void initializationCartesVisibles() {
            compteurPositionNouveauBoutonsCartesVisibles = 0;
            ArrayList<CarteTransport> cartesVisiblesWagons = this.mainApp.getJeu().getPiocheCartesTransportWagon().getCartesVisibles();
            ArrayList<CarteTransport> cartesVisiblesBateaux = this.mainApp.getJeu().getPiocheCartesTransportBateau().getCartesVisibles();

            this.addToVBoxBoutton(cartesVisiblesBateaux);
            this.addToVBoxBoutton(cartesVisiblesWagons);

            verifierTraiterJokers();

            //this.initialiserMainJoueur();
            //CARTE DESTINATIONS CHOISIES AU DEBUT A AJOUTER
        }

        @FXML
        private void handleInitialiserMainJoueur() {
            System.out.println("TEST");
            CarteTransport carteAAjouter;
            for (int i = 0; i < 7; i++) {
                carteAAjouter = (CarteTransport) this.mainApp.getJeu().getPiocheCartesTransportBateau().piocherCarte();
                gererAjoutCarteMain(carteAAjouter);
            }
            for (int i = 0; i < 3; i++) {
                carteAAjouter = (CarteTransport) this.mainApp.getJeu().getPiocheCartesTransportWagon().piocherCarte();
                gererAjoutCarteMain(carteAAjouter);
            }
        }

        /**
         * Methode gerant le click sur la carte pioche bateau du plateau
         */
        @FXML
        private void handlePiocheBateau() {
            //On pioche directement une carte transport bateau
            CarteTransport carteTransportBateauPiochee = (CarteTransport) this.mainApp.getJeu().getPiocheCartesTransportBateau().piocherCarte();

            //On l'ajoute a la main du joueur (en horizontal)
            gererAjoutCarteMain(carteTransportBateauPiochee);
        }

        /**
         * Methode analogue a celle ci dessus pour la pioche wagon
         */
        @FXML
        private void handlePiocheWagon() {
            CarteTransport carteTransportWagonPiochee = (CarteTransport) this.mainApp.getJeu().getPiocheCartesTransportWagon().piocherCarte();
            gererAjoutCarteMain(carteTransportWagonPiochee);
        }

        /**
         * Methode gerant le click sur le boutton d'ajout de destination
         */
        @FXML
        private void handleDialogNouvelleDestination() {
            PiocheDestination piocheDestination = this.mainApp.getJeu().getPiochesDestinations();
            this.carteDestinations = piocheDestination.piocherCartesDestination();

            outilDialog.montrerDialogChoixCartesDestination(carteDestinations);
            //Nouvel element dans listeDestinations
            ajouterDestinationUser();
        }


        /**
         * Methode permmettant de savoir si une carte est deja visible depuis la main du joueur permet de concatener plusieurs carte en 1 boutton
         *
         * @param carte carte d'un type donne pourlaquelle on opere la verification
         */
        private void gererAjoutCarteMain(CarteTransport carte) {
            //On compte le nombre de carte du type donne dans la main du joueur
            int nombreApparitionCarte = this.mainApp.getJeu().getJoueurCourant().dejaDansLaMainDuJoueur(carte);

            //on associe dans tous les cas la carte au joueur
            this.ajouterCarteJoueurCourant(carte);

            //Si il y a deja une carte
            if (nombreApparitionCarte > 0) {
                //On ajoute/modifie le texte sur l'image
                for (Node n : this.listeBouttonsUserCourant.getChildren()) {
                    Button bouttonConcerne = (Button) n;
                    if (bouttonConcerne.getText().equals(carte.getReference())) {
                        //BAD HACK TO IMPROVE THAT
                        //if(bouttonConcerne.getChildrenUnmodifiable().size()>2){
                        ((Text) bouttonConcerne.getChildrenUnmodifiable().get(1)).setText("X" + (nombreApparitionCarte + 1));
                        //}
                    }
                }
                //Si il n'y a pas cette carte dans la main du joueur

            } else {
                //On creer un nouveau boutton
                this.listeBouttonsUserCourant.getChildren().add(OutilGraphique.creerBoutton(carte));
            }
        }

        private void ajouterCarteJoueurCourant(CarteTransport ct) {
            this.mainApp.getJeu().getJoueurCourant().ajouterCarteTransport(ct);
        }

        private void addToVBoxBoutton(ArrayList<CarteTransport> listeCartesVisibles) {
            for (CarteTransport ctw :
                    listeCartesVisibles) {
                this.listeBouttonsCartesVisibles.getChildren().add(creerBouttonImageCarteVisibles(ctw));
            }
        }

        private Button creerBouttonImageCarteVisibles(CarteTransport ct) {
            final Button nouveauBoutton = OutilGraphique.creerBoutton(ct);
            nouveauBoutton.setId("" + compteurPositionNouveauBoutonsCartesVisibles++);

            nouveauBoutton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    //avoir la postion du boutton dans le vbox

                    System.out.println("" + nouveauBoutton.getId());

                    //en deduire la position de la carte a transferer dans les une des listes respectives
                    //transferer la carte a la main du joueur
                    transfererCarteVisibleALaMainDuJoueur("" + nouveauBoutton.getId());
                    //remplacer la carte visible concernee par une nouvelle carte piochee au hasard dans une des deux pioches ???

                    //checker la regle des 3 jokers
                    verifierTraiterJokers();
                }
            });

            return nouveauBoutton;
        }

        private void verifierTraiterJokers() {
            if (mainApp.getJeu().detecterTropJokersVisibles()) {
                reseterCartesVisibles();
                outilDialog.montrerDialogErreurJokers();
            }
        }

        private void reseterCartesVisibles() {
            mainApp.getJeu().reseterCartesVisibles();
            listeBouttonsCartesVisibles.getChildren().clear();
            initializationCartesVisibles();
        }


        private void transfererCarteVisibleALaMainDuJoueur(String id) {
            int idInt = Integer.parseInt(id);

            ArrayList<CarteTransport> cartesVisiblesWagons = this.mainApp.getJeu().getPiocheCartesTransportWagon().getCartesVisibles();
            ArrayList<CarteTransport> cartesVisiblesBateaux = this.mainApp.getJeu().getPiocheCartesTransportBateau().getCartesVisibles();

            CarteTransport carteTransportATransferer, carteTransportPiochee;
            if (idInt <= 2) {
                carteTransportATransferer = cartesVisiblesBateaux.get(idInt);

                //On pioche une nouvelle carte
                carteTransportPiochee = piocherCarteTransportRandom();

                //On remplace la carte dans la liste des cartes visibles
                this.mainApp.getJeu().getPiocheCartesTransportBateau().getCartesVisibles().set(idInt, carteTransportPiochee);

                //On remplace le boutton
                Button b = creerBouttonImageCarteVisibles(carteTransportPiochee);
                b.setId("" + idInt);
                this.listeBouttonsCartesVisibles.getChildren().set(idInt, b);
            } else {
                idInt -= 3;

                carteTransportATransferer = cartesVisiblesWagons.get(idInt);

                carteTransportPiochee = piocherCarteTransportRandom();

                this.mainApp.getJeu().getPiocheCartesTransportWagon().getCartesVisibles().set(idInt, carteTransportPiochee);

                idInt += 3;

                Button b = creerBouttonImageCarteVisibles(carteTransportPiochee);
                b.setId("" + idInt);
                this.listeBouttonsCartesVisibles.getChildren().set(idInt, b);
            }

            gererAjoutCarteMain(carteTransportATransferer);

        }

        private CarteTransport piocherCarteTransportRandom() {
            int choixPioche = OutilPratique.nbAleat(1, 2);

            CarteTransport carteTransportPiochee;

            if (choixPioche == 1) {
                return carteTransportPiochee = (CarteTransport) this.mainApp.getJeu().getPiocheCartesTransportBateau().piocherCarte();
            } else {
                return carteTransportPiochee = (CarteTransport) this.mainApp.getJeu().getPiocheCartesTransportWagon().piocherCarte();
            }
        }


        @FXML
        CheckBox chksport1;
        @FXML
        CheckBox chksport2;
        @FXML
        CheckBox chksport3;
        @FXML
        CheckBox chksport4;
        @FXML
        Label lbltotal;
        @FXML
        Label lbllist;

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
            if(choixUtilisateursCartesDestinations.isEmpty())
                System.out.println("MINCE ??? CA NE FIRE PAS L'ALERT!");
            for (String choix:
                 choixUtilisateursCartesDestinations) {
                System.out.println(choix);
            }
            ArrayList<CarteDestination> cartesDestinationsChoisies = CarteDestination.renvoyerCarteChoisies(this.carteDestinations, choixUtilisateursCartesDestinations);
            this.mainApp.getJeu().getJoueurCourant().ajouterCartesDestination(cartesDestinationsChoisies);

            //On impacte de maniere graphique le VBOW concerne
            Button bouttonDestinationCree = null;
            for (CarteDestination cdc:
                 cartesDestinationsChoisies) {
               bouttonDestinationCree = OutilGraphique.creerBoutton(cdc);
                this.listeDestinations.getChildren().add(bouttonDestinationCree);
            }
            choixUtilisateursCartesDestinations.clear();
        }
}
