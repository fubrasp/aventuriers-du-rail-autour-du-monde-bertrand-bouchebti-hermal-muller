package application;

import java.io.*;

import constantes.ConstantesJeu;
import modeles.Jeu;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * Classe principale chargant le jeu
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

	private Jeu jeu;

    public static int nbr_joueur=0;

    public MainApp(){
	    this.jeu = Jeu.getInstance();
	    this.jeu.initialiserJeu();
        this.jeu.getGestionnairePioches().preparerPioches();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(ConstantesJeu.TITRE_APPLICATION);

        //Charge le canvas de la fenetre
        initRootLayout();

        //Charge l'ecran de creation des joueurs
        showGameStartView();
    }

    /**
     * Initialise le layout root
     */
    public void initRootLayout() {
        try {
            //Charge le fichier FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(ConstantesJeu.CHEMIN_ROOT_LAYOUT));
            rootLayout = (BorderPane) loader.load();

            // Montre la scene liee au leayout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Montre l'ecran start integree au layout root
     */
    public void showGameStartView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/sample.fxml"));
            
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
	*
	* GETTER & SETTER
	*
	*/

    public Jeu getJeu() {
        return jeu;
    }


    /*
	*
	* MAIN
	*
	*/

    public static void main(String[] args) {
        launch(args);
    }
}
