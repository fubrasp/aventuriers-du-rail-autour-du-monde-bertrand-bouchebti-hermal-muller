package outil;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modeles.Carte;

import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import modeles.CarteDestination;
import modeles.Jeu;

import java.io.*;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilGraphique {

    private OutilDialog outilDialog = new OutilDialog();

    //CREATE CLICKABLE IMAGES
    /**
     *
     * @param carte
     * @return
     */
    public static AnchorPane creerAnchorPane(Carte carte) {
        //On utilise un AnchorPane
        AnchorPane anchorPaneAssocieeNouveauBoutton = new AnchorPane();
        anchorPaneAssocieeNouveauBoutton.setAccessibleText(carte.getReference());
        anchorPaneAssocieeNouveauBoutton.setPrefHeight(60);
        anchorPaneAssocieeNouveauBoutton.setPrefWidth(80);

        //On prepare l'image et le texte qui doivent apparaître sur le boutton
        ImageView imageAssocieeNouveauBoutton = OutilGraphique.creerImageView(OutilES.determinerUrl(carte));
        imageAssocieeNouveauBoutton.setFitWidth(80);
        imageAssocieeNouveauBoutton.setFitHeight(60);

        Text textAssocieeNouveauBoutton = new Text("");
        Font police = Font.font("Verdana", FontWeight.BOLD, 20);
        textAssocieeNouveauBoutton.setFont(police);
        textAssocieeNouveauBoutton.setLayoutX(50);
        textAssocieeNouveauBoutton.setLayoutY(20);


        //On a les ajoute a l'AnchorPane
        anchorPaneAssocieeNouveauBoutton.getChildren().add(imageAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textAssocieeNouveauBoutton);

        return anchorPaneAssocieeNouveauBoutton;
    }

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
     *
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
    public static void refreshUserInformations(Text textPseudoJoueur, Text textScoreJoueur){
        textPseudoJoueur.setText("Joueur : "+ Jeu.getInstance().getJoueurCourant().getPseudo());
        textScoreJoueur.setText("Score :  "+Jeu.getInstance().getJoueurCourant().getScoreCourant());
    }
}
