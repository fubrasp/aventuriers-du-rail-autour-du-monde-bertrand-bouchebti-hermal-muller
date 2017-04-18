package outil;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import modeles.Carte;

import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import modeles.CarteDestination;
import modeles.CarteTransport;
import modeles.Jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilGraphique {

    private OutilDialog outilDialog = new OutilDialog();

    //CREATE CLICKABLE IMAGES
    /**
     * Method which create a custom clickable image
     * @param carte card concerned
     * @return anchorPane
     */
    public static AnchorPane creerAnchorPane(Carte carte) {
        //We use an anchorPane
        AnchorPane anchorPaneAssocieeNouveauBoutton = new AnchorPane();
        anchorPaneAssocieeNouveauBoutton.setAccessibleText(carte.getReference());
        anchorPaneAssocieeNouveauBoutton.setPrefHeight(60);
        anchorPaneAssocieeNouveauBoutton.setPrefWidth(80);

        //We prepare the image and we create an imageview
        ImageView imageAssocieeNouveauBoutton = OutilGraphique.creerImageView(OutilES.determinerUrl(carte));
        imageAssocieeNouveauBoutton.setFitWidth(80);
        imageAssocieeNouveauBoutton.setFitHeight(60);

        Text textAssocieeNouveauBoutton = new Text("");
        Font police = Font.font("Verdana", FontWeight.BOLD, 20);
        textAssocieeNouveauBoutton.setFont(police);
        textAssocieeNouveauBoutton.setLayoutX(50);
        textAssocieeNouveauBoutton.setLayoutY(20);


        //We add children to parent (anchorPane)
        anchorPaneAssocieeNouveauBoutton.getChildren().add(imageAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textAssocieeNouveauBoutton);

        return anchorPaneAssocieeNouveauBoutton;
    }

    /**
     * Method which create a custom clickable image for destinations
     * @param carteDestinationChoisie
     * @param listeDestinations
     */
    public void creerAnchorPaneDestination(CarteDestination carteDestinationChoisie, VBox listeDestinations) {
        AnchorPane bouttonDestinationCree = null;
        bouttonDestinationCree = OutilGraphique.creerAnchorPane(carteDestinationChoisie);
        bouttonDestinationCree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                outilDialog.montrerDialogCarteDestinationClickee(carteDestinationChoisie);
            }
        });
        listeDestinations.getChildren().add(bouttonDestinationCree);
    }

    /**
     * Method which create a custom imageView
     * @param cheminFichier
     * @return imageview creer a partir du fichier
     */
    public static ImageView creerImageView(String cheminFichier) {
        ImageView imv = new ImageView();
        File file = new File(cheminFichier);
        Image image = new Image(file.toURI().toString());
        imv.setImage(image);
        imv.setFitHeight(100);
        imv.setFitWidth(150);
        return imv;
    }


    //REFRESH

    /**
     * Method which refresh gamer's informations
     * @param textPseudoJoueur gamer's pseudo
     * @param textScoreJoueur gamer's score
     */
    public static void refreshUserInformations(Text textPseudoJoueur, Text textScoreJoueur){
        textPseudoJoueur.setText("Joueur : "+ Jeu.getInstance().getJoueurCourant().getPseudo());
        textScoreJoueur.setText("Score :  "+Jeu.getInstance().getJoueurCourant().getScoreCourant());
    }

    /**
     * Method which refresh gamer's destinations cards
     * @param listeDestinations
     */
    public void refreshUserDestinationCards(VBox listeDestinations) {
        ArrayList<CarteDestination> cartesDestinationsUserCourant = Jeu.getInstance().getJoueurCourant().getCartesDestination();
        for (CarteDestination carteDestinationUser :
                cartesDestinationsUserCourant) {
            this.creerAnchorPaneDestination(carteDestinationUser, listeDestinations);
        }
    }

    /**
     * Method which refresh gamer's transport cards
     * Append directly cards in one time according the count method
     * @param listeBouttonsUserCourant
     */
    public void refreshUserTransportCards(HBox listeBouttonsUserCourant){
        HashMap<String, Integer> occurenceCartesTransport = Jeu.getInstance().getJoueurCourant().compterOccurencesCartes();

        for (CarteTransport carteTransportCourante:
                Jeu.getInstance().getJoueurCourant().getCartesTransport()) {

            int nombreApparitions = occurenceCartesTransport.get(carteTransportCourante.getReference());

            //System.out.println("CARTE : "+carteTransportCourante.getReference() + " NB ==> " +nombreApparitions);

            if(!listeBouttonsUserCourantContientCarte(listeBouttonsUserCourant, carteTransportCourante)){

                AnchorPane anchorPaneAAjouter = OutilGraphique.creerAnchorPane(carteTransportCourante);
                if(nombreApparitions>1){
                    Text textCourant = ((Text) anchorPaneAAjouter.getChildren().get(1));
                    if (nombreApparitions >= 9) {
                        textCourant.setLayoutX(35);
                    }
                    textCourant.setText("X" + nombreApparitions);
                }

                listeBouttonsUserCourant.getChildren().add(anchorPaneAAjouter);

            }
        }
    }

    /**
     * Method which determine if a card is contain in visibles gamer's transport cards
     * @param listeBouttonsUserCourant gamer's hand cartes transport view
     * @param carteTransportCourante carte transport concerned
     * @return boolean
     */
    private boolean listeBouttonsUserCourantContientCarte(HBox listeBouttonsUserCourant, CarteTransport carteTransportCourante){
        for (Node ancCourant:
                listeBouttonsUserCourant.getChildren()) {
            if(((AnchorPane)ancCourant).getAccessibleText().equals(carteTransportCourante.getReference())){
                return true;
            }
        }
        return false;
    }



}
