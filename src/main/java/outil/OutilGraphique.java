package outil;

import modeles.Carte;

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

import java.io.*;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilGraphique {

    /**
     * @param carte pour laquelle on veut creer un boutton
     * @return boutton avec image et texte dessus
     */
    public static Button creerBoutton(Carte carte) {
        //On utilise un AnchorPane
        AnchorPane anchorPaneAssocieeNouveauBoutton = new AnchorPane();
        anchorPaneAssocieeNouveauBoutton.setPrefHeight(60);
        anchorPaneAssocieeNouveauBoutton.setPrefWidth(80);

        //On prepare l'image et le texte qui doivent apparaître sur le boutton
        ImageView imageAssocieeNouveauBoutton = OutilGraphique.creerImageView(OutilES.determinerUrl(carte));
        imageAssocieeNouveauBoutton.setFitWidth(80);
        imageAssocieeNouveauBoutton.setFitHeight(60);

        Text textAssocieeNouveauBoutton = new Text("");
        textAssocieeNouveauBoutton.setLayoutX(0);
        textAssocieeNouveauBoutton.setLayoutY(55);

        //On a les ajoute a l'AnchorPane
        anchorPaneAssocieeNouveauBoutton.getChildren().add(imageAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textAssocieeNouveauBoutton);

        //On creer le boutton et joue sur sa taille
        Button nouveauBoutton = new Button(carte.getReference());
        nouveauBoutton.setMinHeight(60);
        nouveauBoutton.setMinWidth(80);

        //On associe l'AnchorPane au bouton
        nouveauBoutton.setGraphic(anchorPaneAssocieeNouveauBoutton);

        return nouveauBoutton;
    }

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
}
