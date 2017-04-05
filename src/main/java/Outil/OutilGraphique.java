package Outil;

import Modeles.Carte;
import Modeles.CarteTransport;
import Vues.PiochesController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilGraphique {

    /**
     * @param carte pour laquelle on veut creer un boutton
     * @return boutton avec image et texte dessus
     */
    /*public static Button creerBoutton(CarteTransport carte) {
        //On utilise un AnchorPane
        AnchorPane anchorPaneAssocieeNouveauBoutton = new AnchorPane();
        anchorPaneAssocieeNouveauBoutton.setPrefHeight(70);
        anchorPaneAssocieeNouveauBoutton.setPrefWidth(60);

        //On prepare l'image et le texte qui doivent apparaître sur le boutton
        ImageView imageAssocieeNouveauBoutton = OutilGraphique.creerImageView(OutilES.determinerUrl(carte));
        imageAssocieeNouveauBoutton.setFitWidth(70);
        imageAssocieeNouveauBoutton.setFitHeight(60);

        Text textAssocieeNouveauBoutton = new Text("");
        textAssocieeNouveauBoutton.setLayoutX(0);
        textAssocieeNouveauBoutton.setLayoutY(55);

        //On a les ajoute a l'AnchorPane
        anchorPaneAssocieeNouveauBoutton.getChildren().add(imageAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textAssocieeNouveauBoutton);

        //On creer le boutton et joue sur sa taille
        Button nouveauBoutton = new Button(carte.getReference());
        nouveauBoutton.setMinHeight(80);
        nouveauBoutton.setMinWidth(120);

        //On associe l'AnchorPane au bouton
        nouveauBoutton.setGraphic(anchorPaneAssocieeNouveauBoutton);

        return nouveauBoutton;
    }*/

    public static Button creerBoutton(Carte carte) {
        //On utilise un AnchorPane
        AnchorPane anchorPaneAssocieeNouveauBoutton = new AnchorPane();
        anchorPaneAssocieeNouveauBoutton.setPrefHeight(70);
        anchorPaneAssocieeNouveauBoutton.setPrefWidth(60);

        //On prepare l'image et le texte qui doivent apparaître sur le boutton
        ImageView imageAssocieeNouveauBoutton = OutilGraphique.creerImageView(OutilES.determinerUrl(carte));
        imageAssocieeNouveauBoutton.setFitWidth(70);
        imageAssocieeNouveauBoutton.setFitHeight(60);

        Text textAssocieeNouveauBoutton = new Text("");
        textAssocieeNouveauBoutton.setLayoutX(0);
        textAssocieeNouveauBoutton.setLayoutY(55);

        //On a les ajoute a l'AnchorPane
        anchorPaneAssocieeNouveauBoutton.getChildren().add(imageAssocieeNouveauBoutton);
        anchorPaneAssocieeNouveauBoutton.getChildren().add(textAssocieeNouveauBoutton);

        //On creer le boutton et joue sur sa taille
        Button nouveauBoutton = new Button(carte.getReference());
        nouveauBoutton.setMinHeight(80);
        nouveauBoutton.setMinWidth(120);

        //On associe l'AnchorPane au bouton
        nouveauBoutton.setGraphic(anchorPaneAssocieeNouveauBoutton);

        return nouveauBoutton;
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
