package modeles;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestionnairePlateau {
    public static Map<String, Ville> cities = initCities();

    /*
        Parse plateau.fxml
        @Return Node List in plateau.fxml
     */
    public static ArrayList<Element> parsePlateau(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Element> list = new ArrayList<>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File("src/main/resources/plateau.fxml");
            Document xml = builder.parse(fileXML);

            Element root = xml.getDocumentElement();
            getNodes(root, list);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /*
        Get childNodes for a given node
        @param n parentNode
        @param listElement that will contains childNodes
        @Return Node List in plateau.fxml
     */
    public static void getNodes(Node n, ArrayList<Element> listElement) {
        String str = new String();
        if (n instanceof Element) {
            Element element = (Element) n;
            listElement.add(element);

            //Nous allons maintenant traiter les nœuds enfants du nœud en cours de traitement
            int nbChild = n.getChildNodes().getLength();
            //Nous récupérons la liste des nœuds enfants
            NodeList list = n.getChildNodes();

            //nous parcourons la liste des nœuds
            for (int i = 0; i < nbChild; i++) {
                Node n2 = list.item(i);

                //si le nœud enfant est un Element, nous le traitons
                if (n2 instanceof Element) {
                    //appel récursif à la méthode pour le traitement du nœud et de ses enfants
                    getNodes(n2, listElement);
                }
            }
        }
    }

    /*
        Get roads from a nodeList
        @Return Map
            key : String nodeClass format : (b_ve_3_anch_pert)
            value : Route
     */
    public static Map<String, Route> initRoads(ArrayList<Element> nodeList){
        Map<String, Route> roads = new HashMap<>();
        nodeList.stream()
                .filter(element -> element.getNodeName().equals("Rectangle"))
                .forEach(element -> {
                    Route road = new Route();
                    parseRoad(element.getAttribute("styleClass"),road);
                    roads.put(element.getAttribute("styleClass"),road);
                });
        return roads;
    }

    /*
        Parse nodeClass
        * @param roadClass , String that should be in format (b_ve_3_anch_pert)
        * @param road
     */
    private static void parseRoad(String roadClass, Route road){
        String[] roadMetaData = roadClass.split("_");
        String roadType = roadMetaData[0];
        int roadColor = Couleur.getColor(roadMetaData[1]);
        int roadSize = new Integer(roadMetaData[2]);
        Ville city1 = cities.get(roadMetaData[3]);
        Ville city2 = cities.get(roadMetaData[4]);

        road.setCouleur(roadColor);
        road.setNombreEtapes(roadSize);
        road.setPossesseur(null);
        road.setVilleArrivee(city1);
        road.setVilleDepart(city2);
        road.setMaritime(roadType.equals("b"));
    }

    /*
        @Return Map
            key : String city abbreviation
            value : Ville
     */
    public static Map<String, Ville> initCities(){
        Map<String, Ville> aMap = new HashMap<>();
        aMap.put("vanc", new Ville("Vancouver",true));
        aMap.put("losa", new Ville("Los Angeles",true));
        aMap.put("winn", new Ville("Winnipeg",false));
        aMap.put("newy", new Ville("New York",true));
        aMap.put("miam", new Ville("Miami",true));
        aMap.put("cara", new Ville("Caracas",true));
        aMap.put("lima", new Ville("Lima",true));
        aMap.put("valp", new Ville("Valparaiso",true));
        aMap.put("buen", new Ville("Buenos Aires",true));
        aMap.put("rio", new Ville("Rio de Janeiro",true));
        aMap.put("cape", new Ville("Cape Town",true));
        aMap.put("camb", new Ville("Cambridge Bay",true));
        aMap.put("reyk", new Ville("Reykjavik",true));
        aMap.put("edin", new Ville("Edinburg",true));
        aMap.put("mexi", new Ville("Mexico",false));
        aMap.put("mars", new Ville("Marseille",true));
        aMap.put("hamb", new Ville("Hamburg",true));
        aMap.put("atbi", new Ville("Athina",true));
        aMap.put("murm", new Ville("Murmansk",true));
        aMap.put("mosk", new Ville("Moskva",false));
        aMap.put("novo", new Ville("Novosibirsk",false));
        aMap.put("tehr", new Ville("Tehran",false));
        aMap.put("alqa", new Ville("Al Qahira",true));
        aMap.put("lago", new Ville("Lagos",true));
        aMap.put("djib", new Ville("Djibouti",false));
        aMap.put("dar", new Ville("Dar Es Salaam",true));
        aMap.put("luan", new Ville("Luanda",true));
        aMap.put("cape", new Ville("Cape Town",true));
        aMap.put("toam", new Ville("Toamasina",true));
        aMap.put("tiks", new Ville("Tiksi",true));
        aMap.put("yaku", new Ville("Yakutsk",false));
        aMap.put("petr", new Ville("Petropavlovsk",true));
        aMap.put("beij", new Ville("Beijing",false));
        aMap.put("hong", new Ville("Hong Kong",true));
        aMap.put("labo", new Ville("Lahore",false));
        aMap.put("mumb", new Ville("Mumbai",true));
        aMap.put("bang", new Ville("Bangkok",true));
        aMap.put("anch", new Ville("Anchorage",true));
        aMap.put("toky", new Ville("Tokyo",true));
        aMap.put("mani", new Ville("Manila",true));
        aMap.put("hono", new Ville("Honolulu",true));
        aMap.put("jaka", new Ville("Jakarta",true));
        aMap.put("darw", new Ville("Darwin",true));
        aMap.put("port", new Ville("Port Moresby",true));
        aMap.put("pert", new Ville("Perth",true));
        aMap.put("sydn", new Ville("Sydney",true));
        aMap.put("chri", new Ville("Christchurch",true));
        aMap.put("casa", new Ville("Casablanca",true));
        aMap.put("paf", new Ville("Port Au Français",false));

        return aMap;
    }
}
