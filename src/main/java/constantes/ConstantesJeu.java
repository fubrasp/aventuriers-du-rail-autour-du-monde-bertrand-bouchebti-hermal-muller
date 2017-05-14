package constantes;

/**
 * Created by bertran95u on 12/04/2017.
 */
/**
 * Classe regroupant toute les constantes du jeu
 */
public class ConstantesJeu {

    //Jeu

    //Carte du jeu

    //Cartes transport
    public static final int NOMBRE_CARTES_TRANSPORT_WAGON = 80;
    public static final int NOMBRE_CARTES_TRANSPORT_BATEAU = 60;
    public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE_PAR_COULEUR = 4;
    public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE_PAR_COULEUR = 6;
    public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_SIMPLE = 24;
    public static final int NOMBRE_CARTES_TRANSPORT_BATEAU_DOUBLE = 36;
    public static final int NOMBRE_CARTES_TRANSPORT_WAGON_PAR_COULEUR = 11;
    public static final int NOMBRE_CARTES_TRANSPORT_PORT_PAR_COULEUR = 4;
    public static final int NOMBRE_CARTES_TRANSPORT_JOKER_PAR_PIOCHE = 14;

    //Cartes destinations
    public final static int NOMBRES_DE_CARTES_PIOCHE_DESTINATION = 65;
    public final static int NOMBRES_DE_CARTES_DESTINATIONS_TOUR_PAR_TOUR = 4;
    public final static int NOMBRES_DE_CARTES_DESTINATIONS_INITIALISATION = 5;

    public final static int NOMBRES_DE_CARTES_MINIMUM_DESTINATIONS_INITIALISATION = 3;
    public final static int NOMBRES_DE_CARTES_DESTINATIONS_MINIMUM_APRES_INITIALISATION = 1;


    //Initialisation de la main du joueur
    public static final int NOMBRE_CARTES_BATEAU_INITIALISATION = 7;
    public static final int NOMBRE_CARTES_WAGON_INITIALISATION = 3;

    //Initialisation des pions
    public static final int NOMBRE_PIONS_WAGONS_INITIALISATION = 40;
    public static final int NOMBRE_PIONS_BATEAU_INITIALISATION = 20;
    public static final int NOMBRE_PIONS_BATEAU_RESERVE_INITIALISATION = 5;
    public static final int NOMBRE_PIONS_WAGONS_RESERVE_INITIALISATION = 10;


    //System d'actions (limitations)

    //Cartes
    public static final int VALEUR_CARTE_TRANSPORT_PIOCHEE = 1;
    public static final int VALEUR_CARTE_TRANSPORT_JOKER_VISIBLE = 2;
    public static final int VALEUR_CARTE_DESTINATIONS_PIOCHEES = 2;

    //Prise de route
    public static final int VALEUR_PRISE_DE_ROUTE = 2;

    //Echange de pions
    public static final int VALEUR_ECHANGE_PIONS = 2;


    //Reset des capacites d'action du joueur
    public static final int VALEUR_ACTIONS = 2;

    //Constantes pour les pioches
    public final static int PIOCHE_BATEAU = 1;
    public final static int PIOCHE_WAGON = 2;

    //Fin du jeu
    public final static int NOMBRE_PIONS_FIN_JEU = 6;
    public final static int NOMBRE_TOUR_PAR_JOUEUR_FIN_JEU = 2;


    //Specifique

    //Cartes transport
    public final static int JOKER=666;
    public final static int PIOCHE_REFAITE=999;
    public final static int PAS_DE_CARTE_DANS_LA_DEFAUSSE=9999;

    //Chemins de fichiers
    public final static String CHEMIN_PLATEAU = "src/main/resources/plateau.fxml";

    public static final String CHEMIN_BASE_TRANSPORT = "src/main/resources/images/cartes/transport/";
    public static final String CHEMIN_BASE_CARTE_WAGON = CHEMIN_BASE_TRANSPORT + "wagons/wagons";
    public static final String CHEMIN_BASE_CARTE_WAGON_PORT = CHEMIN_BASE_TRANSPORT + "wagons/port/wagons";
    public static final String CHEMIN_BASE_CARTE_BATEAU = CHEMIN_BASE_TRANSPORT + "bateaux/";
    public static final String CHEMIN_BASE_CARTE_BATEAU_SIMPLE = CHEMIN_BASE_CARTE_BATEAU + "bateaux";
    public static final String CHEMIN_BASE_CARTE_BATEAU_DOUBLE = CHEMIN_BASE_CARTE_BATEAU + "doubles/bateaux";
    public static final String CHEMIN_BASE_CARTE_DESTINATIONS_ITINERAIRES = "src/main/resources/images/cartes/destinations/";
    public static final String EXTENSION_IMAGES = ".png";

    public static final String CHEMIN_ROOT_LAYOUT = "/RootLayout.fxml";
    public static final String CHEMIN_VUE_JEU = "/JeuView.fxml";
    public static final String TITRE_APPLICATION = "Les aventuriers du rail autour du monde : BERTRAND - BOUCHEBTI - HERMAL - MULLER";

}
