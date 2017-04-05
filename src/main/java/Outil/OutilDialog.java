package Outil;

import Modeles.CarteDestination;
import Modeles.CarteTransport;
import Vues.PiochesController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilDialog {
    public static String nomFichier = "src/main/resources/images/cartes/destinations/CarteDestinationCasablancaMarseille.png";

    //controls needed for app:
    //CheckBox chksport1, chksport2, chksport3, chksport4;
    public static ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    ArrayList<ImageView> imagesViewsCartesDestinations = new ArrayList<ImageView>();

    public static Label lbltotal, lbllist;

    //2 VBoxes for the labels and checkboxes
    HBox vbchecks, vblabels;

    private Alert alert;

    public OutilDialog() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alert.setHeaderText(null);
    }

    private void setDialogTitreTexte(String titre, String message) {
        this.alert.setTitle(titre);
        this.alert.setContentText(message);
        this.alert.showAndWait();
    }

    public void montrerDialogErreurJokers() {
        String titreDialog = "INFORMATION : JOKERS";
        String messageDialog = "Les cartes visibles ont ete reinitialisees car il y a trop de jokers visibles.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

    public void montrerDialogErreurPiocheDestination() {
        String titreDialog = "INFORMATION : PIOCHE DESTINATION";
        String messageDialog = "Il n'y a plus de cartes destinations dans la pioche.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

    public void montrerDialogPiocheEpuisee() {
        String titreDialog = "INFORMATION : PIOCHE EPUISEE";
        String messageDialog = "La pioche a ete reinitialise a partir de sa defausse.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

    public void montrerDialogDefausseVide(String nomPioche) {
        String titreDialog = "INFORMATION : IMPOSSIBLE DE PIOCHER DANS PIOCHE" + nomPioche;
        String messageDialog = "La pioche " + nomPioche + " est vide et sa defausse aussi.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

    public void montrerDialogDefaussesVides() {
        String titreDialog = "INFORMATION : IMPOSSIBLE DE PIOCHER";
        String messageDialog = "les pioches sont vides et leurs defausses aussi.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

    public void montrerDialogChoixCartesDestination(ArrayList<CarteDestination> cartesAChoisir) {

        //vbox for checkboxes
        vbchecks = new HBox();
        vbchecks.setSpacing(10);
        vbchecks.setPadding(new Insets(20));

        //vbox for labels
        vblabels = new HBox();
        vblabels.setSpacing(10);
        vblabels.setPadding(new Insets(20));



        for (CarteDestination cd :
                cartesAChoisir) {
            //Creer des checkbox pour chaque carte de 1 a 4 seulon ce qui reste dans la pioche
            this.checkBoxes.add(new CheckBox(cd.getReference()));

            //Creer des images views a partir des cartes destination
            //On mettera le nom de fichier en dynamique (determine grace a la reference) quand on aura toutes les cartes..
            this.imagesViewsCartesDestinations.add(OutilGraphique.creerImageView(nomFichier));
        }
        //On creer deux labels pour informer l'utilisateur
        lbltotal = new Label("Cartes destination/itineraire choisies");
        lbllist = new Label("None");

        //add all things to vboxes

        //vbchecks.getChildren().addAll(chksport1, mv,chksport2, mv2,chksport3, mv3,chksport4,mv4);
        //On sait pertinament que les liste checkboxes et image views ont la meme taille..
        CheckBox elementCourant;
        for (int i = 0; i < this.checkBoxes.size(); i++) {
            elementCourant = this.checkBoxes.get(i);
            elementCourant.setOnAction(e -> PiochesController.handleButtonAction(e));
            this.vbchecks.getChildren().add(elementCourant);
            this.vbchecks.getChildren().add(this.imagesViewsCartesDestinations.get(i));
        }
        vblabels.getChildren().addAll(lbltotal, lbllist);

        //create main container and add 2 vboxes to it
        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.getChildren().addAll(vbchecks, vblabels);

        this.alert.getDialogPane().contentProperty().set(root);
        //this.alert.showAndWait();
        Optional<ButtonType> result = this.alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            if(checkBoxes.isEmpty()){
                montrerDialogAuMoinsUnerCarte();
                montrerDialogChoixCartesDestination(cartesAChoisir);
            }
        }
    }

    public void montrerDialogAuMoinsUnerCarte(){
        String titreDialog = "ERREUR : SELECTIONNEZ AU MOINS UNE CARTE";
        String messageDialog = "Le Jeu impose le choix d'au moins 1 carte destination.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

    public void montrerDialogFinDuJeuProche(){
        String titreDialog = "INFORMATION : FIN DU JEU DANS 2 TOURS";
        String messageDialog = "Un joueur a moins de 6 pions.";
        setDialogTitreTexte(titreDialog, messageDialog);
    }

}
