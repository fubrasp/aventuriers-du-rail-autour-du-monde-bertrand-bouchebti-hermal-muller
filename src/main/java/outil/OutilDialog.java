package outil;

import javafx.scene.control.Label;
import javafx.scene.text.Text;
import modeles.CarteDestination;
import controllers.PiochesController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modeles.Jeu;
import modeles.Ville;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import java.util.*;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilDialog {
    //public static String nomFichier = "src/main/resources/images/cartes/destinations/CarteDestinationCasablancaMarseille.png";

    //controls needed for app:
    public static ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    private ArrayList<ImageView> imagesViewsCartesDestinations = new ArrayList<ImageView>();

    public static Label lbltotal, lbllist;

    //2 VBoxes for the labels and checkboxes
    private HBox vbchecks, vbImages,vblabels;

    private HBox hboxWM, hboxWR, hboxBM, hboxBR;

    private Label nbBM, nbBR, nbWM, nbWR;

    private Alert makeDialog(String titre, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titre);
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
    }

    public void montrerDialogErreurJokers() {
        String titreDialog = "INFORMATION : JOKERS";
        String messageDialog = "Les cartes visibles ont ete reinitialisees car il y a trop de jokers visibles.";
        makeDialog(titreDialog, messageDialog);
    }

    //We have to create a new object in order to make it working
    public void montrerDialogErreurPiocheDestination() {
        String titreDialog = "INFORMATION : PIOCHE DESTINATION";
        String messageDialog = "Il n'y a plus de cartes destinations dans la pioche.";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogPiocheEpuisee() {
        String titreDialog = "INFORMATION : PIOCHE EPUISEE";
        String messageDialog = "La pioche a ete reinitialise a partir de sa defausse.";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogActionNonPossible(String typeAction) {
        String titreDialog = "INFORMATION : ACTION IMPOSSIBLE";
        String messageDialog = "Vous ne pouvez plus "+typeAction+" (cf. regles tour de jeu)";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogDefausseVide(String nomPioche) {
        String titreDialog = "INFORMATION : IMPOSSIBLE DE PIOCHER DANS PIOCHE" + nomPioche;
        String messageDialog = "La pioche " + nomPioche + " est vide et sa defausse aussi.";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogDefaussesVides() {
        String titreDialog = "INFORMATION : IMPOSSIBLE DE PIOCHER";
        String messageDialog = "les pioches sont vides et leurs defausses aussi.";
        makeDialog(titreDialog, messageDialog);
    }

    private void setHBoxStyle(ArrayList<HBox> hBoxs){
        for (HBox hBox:
             hBoxs) {
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(20));
        }
    }

    /**
     * Method which manage destinations/itineraires alert
     * @param cartesAChoisir
     */
    public void montrerDialogChoixCartesDestination(ArrayList<CarteDestination> cartesAChoisir) {

        //hbox for checkboxes
        vbchecks = new HBox();

        //hbox for images
        vbImages = new HBox();

        //hbox for labels
        vblabels = new HBox();
        this.setHBoxStyle(new ArrayList<HBox>(Arrays.asList(vbchecks, vbImages, vblabels)));


        for (CarteDestination cd :
                cartesAChoisir) {
            //Create checkbox from 1 to 4, depending of what remain in the pioche
            this.checkBoxes.add(new CheckBox(cd.getReference()));

            //Create imageView for cartes destination
            //On mettera le nom de fichier en dynamique (determine grace a la reference) quand on aura toutes les cartes..
            ImageView nouvelleImageView = OutilGraphique.creerImageView(OutilES.determinerUrl(cd));
            this.imagesViewsCartesDestinations.add(nouvelleImageView);
            this.vbImages.getChildren().add(nouvelleImageView);
        }
        //Create two labels in order to inform the gamer
        lbltotal = new Label("Cartes destination/itineraire choisies");
        lbllist = new Label("None");

        //Add all things to vboxes
        //Checkboxes et image views list have the same size
        CheckBox elementCourant;
        for (int i = 0; i < this.checkBoxes.size(); i++) {
            elementCourant = this.checkBoxes.get(i);
            elementCourant.setOnAction(e -> PiochesController.handleButtonAction(e));
            this.vbchecks.getChildren().add(elementCourant);
            //this.vbImages.getChildren().add(this.imagesViewsCartesDestinations.get(i));
        }
        vblabels.getChildren().addAll(lbltotal, lbllist);

        //Create main container and add vboxes to it
        FlowPane root = new FlowPane();
        root.setMinWidth(800);
        root.setMinHeight(200);
        root.setHgap(20);
        root.getChildren().addAll(vbImages, vbchecks, vblabels);

        Alert alertCustomDestinations = new Alert(Alert.AlertType.INFORMATION);
        alertCustomDestinations.getDialogPane().contentProperty().set(root);

        alertCustomDestinations.showAndWait();

        vblabels.getChildren().clear();
        vbchecks.getChildren().clear();
        vbImages.getChildren().clear();

        checkBoxes.clear();
    }

    public void montrerDialogCarteDestinationClickee(CarteDestination carteDestinationLieeAuBouton){
        String titreDialog = "INFORMATION : CARTE DESTINATION "+carteDestinationLieeAuBouton.getReference();
        String messageDialog = "Villes de passages :\n";
        for (Ville villePassage:
             carteDestinationLieeAuBouton.getVilles()) {
            messageDialog+=villePassage.getNom()+"\n";
        }
        Alert alertDialogCarteDestination = new Alert(Alert.AlertType.INFORMATION);
        alertDialogCarteDestination.setHeaderText(null);
        alertDialogCarteDestination.setTitle(titreDialog);
        ImageView imageAssocieeALaCarteDestination = OutilGraphique.creerImageView(OutilES.determinerUrl(carteDestinationLieeAuBouton));
        alertDialogCarteDestination.setGraphic(imageAssocieeALaCarteDestination);
        alertDialogCarteDestination.setContentText(messageDialog);
        alertDialogCarteDestination.showAndWait();
    }

    public void montrerDialogAuMoinsUnerCarte(){
        String titreDialog = "ERREUR : SELECTIONNEZ AU MOINS UNE CARTE";
        String messageDialog = "Le Jeu impose le choix d'au moins 1 carte destination.";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogFinDuJeuProche(){
        String titreDialog = "INFORMATION : FIN DU JEU DANS 2 TOURS";
        String messageDialog = "Un joueur a moins de 6 pions.";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogFinJeu(){
        String titreDialog = "INFORMATION : JEU TERMINE";
        String messageDialog = "Jeu termine.";
        makeDialog(titreDialog, messageDialog);
    }

    public void montrerDialogEchangePions(Text textPseudoJoueur,Text textScoreJoueur){

        VBox lignes = new VBox();

        //HBox pour les bateaux dans la main courante
        hboxBM = new HBox();
        hboxBM.setSpacing(10);
        hboxBM.setPadding(new Insets(20));

        //HBox pour les bateaux dans la réserve
        hboxBR = new HBox();
        hboxBR.setSpacing(10);
        hboxBR.setPadding(new Insets(20));

        //HBox pour les wagons dans la main courante
        hboxWM = new HBox();
        hboxWM.setSpacing(10);
        hboxWM.setPadding(new Insets(20));

        //HBox pour les wagons dans la réserve
        hboxWR = new HBox();
        hboxWR.setSpacing(10);
        hboxWR.setPadding(new Insets(20));

        Label infoBM = new Label("Nombre de bateaux dans la main :");
        nbBM = new Label(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsBateau()));
        Button BMPlus = new Button("+");
        Button BMMoins = new Button("-");
        Label infoBR = new Label("Nombre de bateaux dans la réserve :");
        nbBR = new Label(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsBateauReserve()));
        Label infoWM = new Label("Nombre de wagons dans la main :");
        nbWM = new Label(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsWagons()));
        Button WMPlus = new Button("+");
        Button WMMoins = new Button("-");
        Label infoWR = new Label("Nombre de wagons dans la réserve :");
        nbWR = new Label(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsWagonsReserve()));

        int BMinit = Jeu.getInstance().getJoueurCourant().getNbPionsBateau();
        int WMinit = Jeu.getInstance().getJoueurCourant().getNbPionsWagons();

        //Actions sur les boutons
        BMPlus.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(Jeu.getInstance().getJoueurCourant().getNbPionsWagonsReserve()>0) {
                    majDialogEchangePions("BM+");
                }
            }
        });

        BMMoins.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(Jeu.getInstance().getJoueurCourant().getNbPionsBateau()>BMinit) {
                    majDialogEchangePions("BM-");
                }
            }
        });

        WMPlus.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(Jeu.getInstance().getJoueurCourant().getNbPionsBateauReserve()>0) {
                    majDialogEchangePions("WM+");
                }
            }
        });

        WMMoins.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent e){
                if(Jeu.getInstance().getJoueurCourant().getNbPionsWagons()>WMinit) {
                    majDialogEchangePions("WM-");
                }
            }
        });

        hboxBM.getChildren().addAll(infoBM, nbBM, BMMoins, BMPlus);
        hboxBR.getChildren().addAll(infoBR, nbBR);
        hboxWM.getChildren().addAll(infoWM, nbWM, WMMoins, WMPlus);
        hboxWR.getChildren().addAll(infoWR, nbWR);

        HBox hboxBoutons = new HBox();
        ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonOk = new ButtonType("Échanger", ButtonBar.ButtonData.OK_DONE);

        lignes.getChildren().addAll(hboxBM, hboxBR, hboxWM, hboxWR, hboxBoutons);


        //create main container and add vboxes to it
        FlowPane root = new FlowPane();
        root.setMinWidth(800);
        root.setMinHeight(400);
        root.setHgap(20);
        root.getChildren().addAll(lignes);

        Alert alertCustomEchange = new Alert(Alert.AlertType.NONE);
        alertCustomEchange.getDialogPane().contentProperty().set(root);
        alertCustomEchange.getButtonTypes().setAll(buttonOk, buttonAnnuler);


        Optional<ButtonType> result = alertCustomEchange.showAndWait();
        if(!result.isPresent()){
            //Alert is exited, no button pressed
        } else if (result.get() == buttonOk){

            //Appel à la fonction qui met à jour le score
            Jeu.getInstance().getJoueurCourant().majScoreEchange(BMinit,WMinit);
            OutilGraphique.refreshUserInformations(textPseudoJoueur, textScoreJoueur);

        } else if (result.get() == buttonAnnuler){
            //Cancel button pressed
        }
    }

    public void majDialogEchangePions(String cas){
        switch(cas){
            case "BM+":
                Jeu.getInstance().getJoueurCourant().setNbPionsBateau(Jeu.getInstance().getJoueurCourant().getNbPionsBateau() + 1);
                Jeu.getInstance().getJoueurCourant().setNbPionsWagonsReserve(Jeu.getInstance().getJoueurCourant().getNbPionsWagonsReserve() - 1);
                nbBM.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsBateau()));
                nbWR.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsWagonsReserve()));
                break;

            case "BM-":
                Jeu.getInstance().getJoueurCourant().setNbPionsBateau(Jeu.getInstance().getJoueurCourant().getNbPionsBateau() - 1);
                Jeu.getInstance().getJoueurCourant().setNbPionsWagonsReserve(Jeu.getInstance().getJoueurCourant().getNbPionsWagonsReserve() + 1);
                nbBM.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsBateau()));
                nbWR.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsWagonsReserve()));
                break;

            case "WM+":
                Jeu.getInstance().getJoueurCourant().setNbPionsWagons(Jeu.getInstance().getJoueurCourant().getNbPionsWagons() + 1);
                Jeu.getInstance().getJoueurCourant().setNbPionsBateauReserve(Jeu.getInstance().getJoueurCourant().getNbPionsBateauReserve() - 1);
                nbWM.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsWagons()));
                nbBR.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsBateauReserve()));
                break;

            case "WM-":
                Jeu.getInstance().getJoueurCourant().setNbPionsWagons(Jeu.getInstance().getJoueurCourant().getNbPionsWagons() - 1);
                Jeu.getInstance().getJoueurCourant().setNbPionsBateauReserve(Jeu.getInstance().getJoueurCourant().getNbPionsBateauReserve() + 1);
                nbWM.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsWagons()));
                nbBR.setText(String.valueOf(Jeu.getInstance().getJoueurCourant().getNbPionsBateauReserve()));
                break;
        }

    }
}
