package controllers;

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

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class MapController implements Initializable {
    @FXML private AnchorPane mapContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            }

            if (node instanceof Rectangle) {
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
                                ((Rectangle) node).setFill(originalColor);
                                node.setCursor(Cursor.DEFAULT);
                                highlightRoad(node,originalColor);
                            }
                        });
                node.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if(node.getStyleClass().size()>0){
                                    String[] nodeClass = node.getStyleClass().get(0).split("_");
                                    String roadType = nodeClass[0];
                                    String roadColor = nodeClass[1];
                                    int roadSize = new Integer(nodeClass[2]);
                                    String city1 = nodeClass[3];
                                    String city2 = nodeClass[4];

                                    System.out.println("Road clicked # " +
                                            "Type : "+roadType+" " +
                                            "Color : "+roadColor+" " +
                                            "Size : "+roadSize);

                                }
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

    public static Tooltip createTooltip(String text){
        Tooltip tooltip = new Tooltip(text);
        tooltip.setFont(new Font("Arial",16));
        hackTooltipStartTiming(tooltip);
        return tooltip;
    }

    /*
        Because tooltip delay is too slower by default, this is a hack to change his delay
        Source : http://stackoverflow.com/questions/26854301/control-javafx-tooltip-delay
     */
    public static void hackTooltipStartTiming(Tooltip tooltip) {
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

    public static final Map<String,String> nameOfCity = createMap();
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
        aMap.put("rio", "Rio de Janeiro");
        aMap.put("cape", "Cape Town");
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
