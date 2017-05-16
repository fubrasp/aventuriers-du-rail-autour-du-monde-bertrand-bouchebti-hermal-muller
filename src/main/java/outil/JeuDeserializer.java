package outil;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import modeles.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Flo on 16/05/2017.
 */
public class JeuDeserializer extends StdDeserializer<Jeu> {

    private HashMap<String, Joueur> joueurs = new HashMap<>();

    public JeuDeserializer() {
        this(null);
    }

    public JeuDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Jeu deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        Jeu jeu = Jeu.getInstance();
        jeu.getJoueurs().clear();
        getJoueurs(node,jeu);

        jeu.setJoueurCourant(joueurs.get(node.get("id_joueur_courant").asText()));
        jeu.setIndexJeu(node.get("index_jeu").asInt());

        jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartesDefaussees().clear();
        jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartesDefaussees().clear();
        jeu.getGestionnairePioches().getPiocheCartesDestination().getCartesDefaussees().clear();
        jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes().clear();
        jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartes().clear();
        jeu.getGestionnairePioches().getPiocheCartesDestination().getCartes().clear();
        jeu.getGestionnairePioches().getCartesVisibles().clear();

        jeu.getJoueurs().addAll(joueurs.values());
        jeu.getGestionnairePioches().getCartesVisibles().addAll(
                getCartesTransports((ArrayNode)node.get("cartes_visibles")));
        jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes().addAll(
                getCartesTransports((ArrayNode)node.get("pioche_wagon")));
        jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartes().addAll(
                getCartesTransports((ArrayNode)node.get("pioche_bateau")));
        jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartesDefaussees().addAll(
                getCartesTransports((ArrayNode)node.get("defausse_bateau")));
        jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartesDefaussees().addAll(
                getCartesTransports((ArrayNode)node.get("defausse_wagon")));
        jeu.getGestionnairePioches().getPiocheCartesDestination().getCartes().addAll(
                getCartesDestinations((ArrayNode)node.get("pioche_destination")));
        jeu.getGestionnairePioches().getPiocheCartesDestination().getCartes().addAll(
                getCartesDestinations((ArrayNode)node.get("defausse_destination")));
        jeu.getGestionnairePioches().getPiocheCartesDestination().getCartes().addAll(
                getCartesDestinations((ArrayNode)node.get("cartes_precedentes")));

        return jeu;
    }

    private void getJoueurs(JsonNode jsonNode, Jeu jeu){
        ArrayNode arrayNode = (ArrayNode)jsonNode.get("joueurs");
        Joueur joueur;
        for(JsonNode joueurNode : arrayNode){
            joueur = new Joueur(joueurNode.get("pseudo").asText(),joueurNode.get("couleur").asText());
            joueur.setId(joueurNode.get("id").asText());
            joueur.setNbPionsBateau(joueurNode.get("nbPionsBateau").asInt());
            joueur.setNbPionsBateauReserve(joueurNode.get("nbPionsBateauReserve").asInt());
            joueur.setNbPionsWagons(joueurNode.get("nbPionsWagons").asInt());
            joueur.setNbPionsWagonsReserve(joueurNode.get("nbPionsWagonsReserve").asInt());
            joueur.setScore(joueurNode.get("score").asInt());
            joueur.setAEuInformationFinDuJeu(joueurNode.get("aeuInformationFinDuJeu").asBoolean());
            joueur.setaInitialiseeSesCartesDestinations(joueurNode.get("aInitialiseeSesCartesDestinations").asBoolean());

            joueur.getCartesTransport().addAll(getCartesTransports((ArrayNode)joueurNode.get("cartesTransport")));
            joueur.getCartesDestination().addAll(getCartesDestinations((ArrayNode)joueurNode.get("cartesDestination")));

            attribuerVille((ArrayNode)joueurNode.get("villesPossedees"),jeu,joueur);
            attribuerRoute((ArrayNode)joueurNode.get("routesPossedees"),jeu,joueur);

            joueurs.put(joueur.getId(),joueur);
        }
    }

    private ArrayList<CarteTransport> getCartesTransports(ArrayNode arrayNode){
        ArrayList<CarteTransport> carteTransports = new ArrayList<>();

        CarteTransport carteTransportCreer;
        for(JsonNode carteRef : arrayNode){
            carteTransportCreer = OutilCarte.createCarteTransportByReference(carteRef.asText());
            carteTransports.add(carteTransportCreer);
        }

        return carteTransports;
    }

    private ArrayList<CarteDestination> getCartesDestinations(ArrayNode arrayNode){
        ArrayList<CarteDestination> carteDestinations = new ArrayList<>();

        CarteDestination carteDestinationCree;
        for(JsonNode carteRef : arrayNode){
            carteDestinationCree = OutilCarte.createCarteDestinationByReference(carteRef.asText());
            carteDestinations.add(carteDestinationCree);
        }

        return carteDestinations;
    }

    private void attribuerRoute(ArrayNode arrayNode, Jeu jeu, Joueur joueur){
        Route route;
        for(JsonNode routeRef : arrayNode){
            route = jeu.getRoutes().get(routeRef.asText());
            route.setPossesseur(joueur);
            joueur.getRoutesPossedees().add(route);
        }
    }

    private void attribuerVille(ArrayNode arrayNode, Jeu jeu, Joueur joueur){
        Ville ville;
        for(JsonNode villeRef : arrayNode){
            ville = jeu.getVilles().get(villeRef.asText());
            ville.setPossesseur(joueur);
            joueur.getVillesPossedees().add(ville);
        }
    }
}
