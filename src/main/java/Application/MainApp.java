package Application;

import java.io.IOException;

import Modeles.Jeu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private static final String CHEMIN_ROOT_LAYOUT = "/RootLayout.fxml";
	private static final String CHEMIN_VUE_JEU = "/JeuView.fxml";
	private static final String TITRE_APPLICATION = "Les aventuriers du rail autour du monde : BERTRAND - BOUCHEBTI - HERMAL - MULLER";

	private Jeu jeu;

	public MainApp(){
	    this.jeu = new Jeu();
	    this.jeu.initialiserJeu();
	    this.jeu.preparerLesPioches();
    }

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(TITRE_APPLICATION);

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(CHEMIN_ROOT_LAYOUT));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(MainApp.CHEMIN_VUE_JEU));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
