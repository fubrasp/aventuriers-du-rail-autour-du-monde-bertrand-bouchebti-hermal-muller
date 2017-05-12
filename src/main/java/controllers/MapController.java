package controllers;

import constantes.ConstantesJeu;
import interfaces.INTJ;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import modeles.Jeu;
import modeles.Joueur;
import modeles.Route;
import modeles.Ville;
import outil.OutilDialog;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class MapController implements Initializable {
    @FXML private AnchorPane mapContainer;
    private Jeu jeu;
    private OutilDialog outilDialog = new OutilDialog();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.jeu = Jeu.getInstance();

        for (Node node : mapContainer.getChildren()) {
            if(node instanceof Circle){
                node.addEventHandler(MouseEvent.MOUSE_ENTERED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                node.setCursor(Cursor.HAND);
                                String[] className = node.getStyleClass().get(0).split("_");
                                String fullName = nameOfCity.get(className[1]);
                                Tooltip tooltip = createTooltip(fullName);
                                Tooltip.install(node,tooltip);
                            }
                        });
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) { // Click on city
                                node.setCursor(Cursor.HAND);
                                String[] className = node.getStyleClass().get(0).split("_");
                                Ville city = Jeu.getInstance().getVilles().get(className[1]);
                                createPort(node,city);
                                //System.out.println("city clicked : "+city.getNom()+" possesseur : "+city.hasPossesseur());
                            }
                        });
            }

            if (node instanceof Rectangle) {
                Route road = jeu.getRoutes().get(node.getStyleClass().get(0));
                Paint originalColor = ((Rectangle) node).getFill();
                node.addEventHandler(MouseEvent.MOUSE_ENTERED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                Paint paint = Paint.valueOf("blue");
                                ((Rectangle) node).setFill(paint);
                                node.setCursor(Cursor.HAND);
                                highlightRoad(node,paint);
                            }
                        });
                node.addEventHandler(MouseEvent.MOUSE_EXITED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent e) {
                                if(!road.isTaken()){
                                    highlightRoad(node,originalColor);
                                }else{
                                    highlightRoad(node,Paint.valueOf(road.getPossesseur().getCouleur()));
                                }
                                node.setCursor(Cursor.DEFAULT);
                            }
                        });
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                setRoadToPlayer(node,road);

                                /*System.out.println("Road clicked # " +
                                        "Maritime : "+road.isMaritime()+" " +
                                        "Color : "+road.getCouleur()+" " +
                                        "VilleDep : "+road.getVilleDepart().getNom()+" " +
                                        "VilleArr : "+road.getVilleArrivee().getNom()+" " +
                                        "Size : "+road.getNombreEtapes());*/
                            }
                        });
            }
        }
    }

    /*
        Get the first class name of the given node in param,
        and highlight all node with the same class name
        * @param  initalNode : The node used to get the class
        * @param  paint : The highlight color
     */
    public void highlightRoad(Node initialNode, Paint paint){
        if(initialNode.getStyleClass().size() >0 ){
            mapContainer.getChildren().stream()
                    .filter(node -> node.getStyleClass().size()>0)
                    .filter(node -> node.getStyleClass().get(0).equals(initialNode.getStyleClass().get(0)))
                    .filter(node -> node instanceof Rectangle)
                    .forEach(node -> ((Rectangle)node).setFill(paint));
        }

    }

    /*
        Colorize the chosen road and set the player to the road
        @param initialNode
        @param road the chosen road
     */
    private void setRoadToPlayer(Node initialNode, Route road){
        if(INTJ.verifierCapaciteJoueurPrendreRoute()){
            if(initialNode.getStyleClass().size() >0 ){
                Joueur joueur = jeu.getJoueurCourant();
                boolean roadTaken = joueur.selectRoad(road);
                if(roadTaken){
                    System.out.println("Route prise : Refresh Interface");
                    Jeu.getInstance().refreshInterface();
                    INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_PRISE_DE_ROUTE);
                }else{
                    System.out.println("Route non prise");
                }

                colorizeRoad(initialNode,Paint.valueOf(joueur.getCouleur()));
            }
        }else{
            outilDialog.montrerDialogActionNonPossible("prendre des routes");
        }
    }

    /*
        Colorize the chosen city and set the player to the city
        @param initialNode
        @param city the chosen city
     */
    private void createPort(Node initialNode, Ville ville){
        if(INTJ.verifierCapaciteJoueur()){
            if(initialNode.getStyleClass().size() > 0 ){
                Joueur joueur = jeu.getJoueurCourant();
                boolean isPortCreated = joueur.takePort(ville);
                if(isPortCreated){
                    System.out.println("Port construit pour : "+ville.getNom());
                    Jeu.getInstance().refreshInterface();
                    INTJ.diminuerCapaciteJoueur(ConstantesJeu.VALEUR_PRISE_DE_ROUTE);
                }else{
                    System.out.println("Port non construit");
                }
                ((Circle)initialNode).setFill(Paint.valueOf(joueur.getCouleur()));
            }
        }else{
            outilDialog.montrerDialogActionNonPossible("prendre des ports");
        }
    }

    /*
        Colorize the chosen road
        @param initialNode
        @param paint color of the road
     */
    private void colorizeRoad(Node initialNode, Paint paint){
        mapContainer.getChildren().stream()
                .filter(node -> node.getStyleClass().size()>0)
                .filter(node -> node.getStyleClass().get(0).equals(initialNode.getStyleClass().get(0)))
                .filter(node -> node instanceof Rectangle)
                .forEach(node -> ((Rectangle)node).setFill(paint));
    }

    private static Tooltip createTooltip(String text){
        Tooltip tooltip = new Tooltip(text);
        tooltip.setFont(new Font("Arial",16));
        hackTooltipStartTiming(tooltip);
        return tooltip;
    }

    /*
        Because tooltip delay is too slower by default, this is a hack to change his delay
        Source : http://stackoverflow.com/questions/26854301/control-javafx-tooltip-delay
     */
    private static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Map<String,String> nameOfCity = createMap();
    private static Map<String, String> createMap(){
        Map<String, String> aMap = new HashMap<>();
        aMap.put("vanc", "Vancouver");
        aMap.put("losa", "Los Angeles");
        aMap.put("winn", "Winnipeg");
        aMap.put("newy", "New York");
        aMap.put("miam", "Miami");
        aMap.put("cara", "Caracas");
        aMap.put("lima", "Lima");
        aMap.put("valp", "Valparaiso");
        aMap.put("buen", "Buenos Aires");
        aMap.put("rio", "Rio De Janeiro");
        aMap.put("cape", "Cap Town");
        aMap.put("camb", "Cambridge Bay");
        aMap.put("reyk", "Reykjavik");
        aMap.put("edin", "Edinburg");
        aMap.put("mexi", "Mexico");
        aMap.put("mars", "Marseille");
        aMap.put("hamb", "Hamburg");
        aMap.put("atbi", "Athina");
        aMap.put("murm", "Murmansk");
        aMap.put("mosk", "Moskva");
        aMap.put("novo", "Novosibirsk");
        aMap.put("tehr", "Tehran");
        aMap.put("alqa", "Al Qahira");
        aMap.put("lago", "Lagos");
        aMap.put("djib", "Djibouti");
        aMap.put("dar", "Dar Es Salaam");
        aMap.put("luan", "Luanda");
        aMap.put("cape", "Cape Town");
        aMap.put("toam", "Toamasina");
        aMap.put("tiks", "Tiksi");
        aMap.put("yaku", "Yakutsk");
        aMap.put("petr", "Petropavlovsk");
        aMap.put("beij", "Beijing");
        aMap.put("hong", "Hong Kong");
        aMap.put("labo", "Lahore");
        aMap.put("mumb", "Mumbai");
        aMap.put("bang", "Bangkok");
        aMap.put("anch", "Anchorage");
        aMap.put("toky", "Tokyo");
        aMap.put("mani", "Manila");
        aMap.put("hono", "Honolulu");
        aMap.put("jaka", "Jakarta");
        aMap.put("darw", "Darwin");
        aMap.put("port", "Port Moresby");
        aMap.put("pert", "Perth");
        aMap.put("sydn", "Sydney");
        aMap.put("chri", "Christchurch");
        aMap.put("casa", "Casablanca");
        aMap.put("paf", "Port Au Français");
        return aMap;
    }

}
