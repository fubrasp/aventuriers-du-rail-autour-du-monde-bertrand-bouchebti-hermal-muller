package outil;

import application.MainApp;
import controllers.PiochesController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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

/**
 * Classe gerant la pluplart des objets graphiques
 */
public class OutilGraphique {

    private OutilDialog outilDialog = new OutilDialog();

    /**
     * Methode qui creer des images clickables
     *
     * @param carte card concernee
     * @return anchorPane
     */
    public static AnchorPane creerAnchorPane(Carte carte) {
        //On utilise un anchorPane
        AnchorPane anchorPaneAssocieeNouveauBoutton = new AnchorPane();
        anchorPaneAssocieeNouveauBoutton.setAccessibleText(carte.getReference());
        anchorPaneAssocieeNouveauBoutton.setPrefHeight(60);
        anchorPaneAssocieeNouveauBoutton.setPrefWidth(80);

        //On prepare l'image de la carte avec une imageview
        ImageView imageAssocieeNouveauBoutton = OutilGraphique.creerImageView(OutilES.determinerUrl(carte));
        imageAssocieeNouveauBoutton.setFitWidth(80);
        imageAssocieeNouveauBoutton.setFitHeight(60);

        //On prepare les deux elements textuelles de la carte
        Text textAssocieeNouveauBoutton = new Text("");
        Font police = Font.font("Verdana", FontWeight.BOLD, 20);
        textAssocieeNouveauBoutton.setFont(police);
        textAssocieeNouveauBoutton.setLayoutX(50);
        textAssocieeNouveauBoutton.setLayoutY(20);

        Text textNbCardSelected = new Text("");
        police = Font.font("Verdana", FontWeight.BOLD, 20);
        textNbCardSelected.setFont(police);
        textNbCardSelected.setLayoutX(0);
        textNbCardSelected.setLayoutY(20);


        //On ajoute les 3 elements  a l'anchorPane
        anchorPaneAssocieeNouveauBoutton.getChildren().add(imageAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textNbCardSelected);

        return anchorPaneAssocieeNouveauBoutton;
    }

    /**
     * Methode qui creer des images clickables pour les cartes destinations
     *
     * @param carteDestinationChoisie
     * @param listeDestinations
     */
    public void creerAnchorPaneDestination(CarteDestination carteDestinationChoisie, VBox listeDestinations) {
        AnchorPane bouttonDestinationCree = null;
        bouttonDestinationCree = OutilGraphique.creerAnchorPane(carteDestinationChoisie);
        bouttonDestinationCree.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Micro Popup
                outilDialog.montrerDialogCarteDestinationClickee(carteDestinationChoisie);
            }
        });
        listeDestinations.getChildren().add(bouttonDestinationCree);
    }

    /**
     * Methode qui creer une image view sur mesure
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


    /**
     * Methode qui raffraichit les informations du joueur
     *
     * @param textPseudoJoueur identifiant du joueur
     * @param textScoreJoueur  score du joueur
     */
    public static void refreshUserInformations(Text textPseudoJoueur, Text textScoreJoueur) {
        textPseudoJoueur.setText("Joueur : " + Jeu.getInstance().getJoueurCourant().getPseudo());
        textScoreJoueur.setText("Score :  " + Jeu.getInstance().getJoueurCourant().getScore());
    }

    /**
     * Methode qui raffraichit les cartes destinations du joueur
     *
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
     * Methode qui raffraichit les cartes transport du joueur
     * Ajoute dynamiquement les cartes a la main du joueur
     *
     * @param listeBouttonsUserCourant
     */
    public void refreshUserTransportCards(HBox listeBouttonsUserCourant) {
        System.out.println("refreshUserTransportCards");
        HashMap<String, Integer> occurenceCartesTransport = Jeu.getInstance().getJoueurCourant().compterOccurencesCartes();

        for (CarteTransport carteTransportCourante :
                Jeu.getInstance().getJoueurCourant().getCartesTransport()) {

            int nombreApparitions = occurenceCartesTransport.get(carteTransportCourante.getReference());

            if (!listeBouttonsUserCourantContientCarte(listeBouttonsUserCourant, carteTransportCourante)) {

                AnchorPane anchorPaneAAjouter = OutilGraphique.creerAnchorPane(carteTransportCourante);
                if (nombreApparitions > 1) {
                    Text textCourant = ((Text) anchorPaneAAjouter.getChildren().get(1));
                    if (nombreApparitions >= 9) {
                        textCourant.setLayoutX(35);
                    }
                    textCourant.setText("X" + nombreApparitions);
                }

                anchorPaneAAjouter.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.SECONDARY) {
                                    // RIGHT CLICK
                                    Jeu.getInstance().getJoueurCourant().removeSelectCard(carteTransportCourante);
                                } else {
                                    // LEFT CLICK
                                    Jeu.getInstance().getJoueurCourant().addSelectCard(carteTransportCourante);
                                }
                                HashMap<String, Integer> occurenceCartesTransport = Jeu.getInstance()
                                        .getJoueurCourant().compterOccurencesCartes();
                                int nombreApparitions = occurenceCartesTransport.get(carteTransportCourante.getReference());
                                int nbOccurence = Jeu.getInstance()
                                        .getJoueurCourant()
                                        .nbOccurenceCarteSelectionnee(carteTransportCourante);
                                Text textCourant = ((Text) anchorPaneAAjouter.getChildren().get(2));
                                textCourant.setFill(Color.BLUE);
                                if (nbOccurence > 0) {
                                    if (nbOccurence > nombreApparitions) {
                                        // Permet au joueur de ne pas selectionner plus de carte que ce qu'il a dans la main
                                        Jeu.getInstance().getJoueurCourant().removeSelectCard(carteTransportCourante);
                                        nbOccurence = nbOccurence - 1;
                                    }
                                    anchorPaneAAjouter.setBorder(createBorder()); // ADD RED BORDER
                                    textCourant.setText("X" + nbOccurence);
                                } else {
                                    anchorPaneAAjouter.setBorder(null); // REMOVE BORDER
                                    textCourant.setText("");
                                }
                                System.out.println("NbOccurence : " + nbOccurence);
                                System.out.println("Carte de la meme couleur selectionnee : " + Jeu.getInstance()
                                        .getJoueurCourant()
                                        .getSelectedCards()
                                        .get(carteTransportCourante
                                                .getCouleur()).size());
                            }
                        });

                listeBouttonsUserCourant.getChildren().add(anchorPaneAAjouter);
            }
        }
    }

    /**
     * Method equi determine si une carte est deja dans la main du joueur
     *
     * @param listeBouttonsUserCourant main du joueur
     * @param carteTransportCourante   carte transport concernee
     * @return boolean
     */
    private boolean listeBouttonsUserCourantContientCarte(HBox listeBouttonsUserCourant, CarteTransport carteTransportCourante) {
        for (Node ancCourant :
                listeBouttonsUserCourant.getChildren()) {
            if (((AnchorPane) ancCourant).getAccessibleText().equals(carteTransportCourante.getReference())) {
                return true;
            }
        }
        return false;
    }

    private Border createBorder() {
        return new Border(new BorderStroke(Color.RED,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    }


}
