package modeles;

import constantes.ConstantesJeu;
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

/**
 * Classe modelisant le plateau
 */
public class GestionnairePlateau {

    //Villes du plateau
    public static Map<String, Ville> cities = InitCities.initCities();

    /*
	*
	* FONCTIONS
	*
	*/

    /**
     * Parse plateau.fxml (construire un graphe)
     * @return Node List in plateau.fxml
     */
    public static ArrayList<Element> parsePlateau(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Element> list = new ArrayList<>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fileXML = new File(ConstantesJeu.CHEMIN_PLATEAU);
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

    /**
     * Obtenir les noeuds enfants
     * @param n noeud parent
     * @param listElement liste qui contient les noeuds enfants
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

    /**
     * Obtenir les route a partir de la liste de noud
     * @param nodeList
     * @return Map
     * key : String nodeClass format : (b_ve_3_anch_pert)
     * value : Route
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

    /**
     *
     * @param roadClass String doit etre au format (b_ve_3_anch_pert)
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
}
