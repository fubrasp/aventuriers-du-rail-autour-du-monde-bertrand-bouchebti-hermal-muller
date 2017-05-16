package outil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import modeles.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Flo on 15/05/2017.
 */
public class JeuSerializer extends StdSerializer<Jeu> {

    public JeuSerializer() {
        this(null);
    }

    public JeuSerializer(Class<Jeu> t) {
        super(t);
    }

    @Override
    public void serialize(
            Jeu jeu, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeStringField("id_joueur_courant",jeu.getJoueurCourant().getId());
        jgen.writeNumberField("index_jeu",jeu.getIndexJeu());

        serializeJoueur(jeu, jgen);

        serializeCarteTransports(jgen,"cartes_visibles",jeu.getGestionnairePioches().getCartesVisibles());
        serializeCarte(jgen,"pioche_wagon",jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartes());
        serializeCarte(jgen,"pioche_bateau",jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartes());
        serializePiocheDestination(jgen,"pioche_destination",jeu.getGestionnairePioches().getPiocheCartesDestination().getCartes());
        serializeCarte(jgen,"defausse_wagon",jeu.getGestionnairePioches().getPiocheCartesTransportWagon().getCartesDefaussees());
        serializeCarte(jgen,"defausse_bateau",jeu.getGestionnairePioches().getPiocheCartesTransportBateau().getCartesDefaussees());
        serializePiocheDestination(jgen,"defausse_destination",jeu.getGestionnairePioches().getPiocheCartesDestination().getCartesDefaussees());
        serializeCarteDestination(jgen,"cartes_precedentes",jeu.getGestionnairePioches().getPiocheCartesDestination().getCartesPrecedentes());

        jgen.writeEndObject();
    }

    private void serializeJoueur(Jeu jeu, JsonGenerator jgen) throws IOException, JsonProcessingException{
        // Serialize Joueur
        jgen.writeArrayFieldStart("joueurs");
        for(Joueur joueur : jeu.getJoueurs()){
            jgen.writeStartObject();

            jgen.writeStringField("id",joueur.getId());
            jgen.writeStringField("pseudo",joueur.getPseudo());
            jgen.writeStringField("couleur",joueur.getCouleur());

            jgen.writeNumberField("nbPionsBateau",joueur.getNbPionsBateau());
            jgen.writeNumberField("nbPionsBateauReserve",joueur.getNbPionsBateauReserve());
            jgen.writeNumberField("nbPionsWagons",joueur.getNbPionsWagons());
            jgen.writeNumberField("nbPionsWagonsReserve",joueur.getNbPionsWagonsReserve());
            jgen.writeNumberField("score",joueur.getScore());

            jgen.writeBooleanField("aInitialiseeSesCartesDestinations",joueur.isaInitialiseeSesCartesDestinations());
            jgen.writeBooleanField("aeuInformationFinDuJeu",joueur.isAEuInformationFinDuJeu());

            serializeCarteDestination(jgen,"cartesDestination",joueur.getCartesDestination());
            serializeCarteTransports(jgen,"cartesTransport",joueur.getCartesTransport());
            serializeVille(jgen,"villesPossedees",joueur.getVillesPossedees());
            serializeRoute(jgen,"routesPossedees",joueur.getRoutesPossedees());

            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

    private void serializeCarte(JsonGenerator jgen,String key, ArrayList<Carte>
                               carteTransports) throws IOException, JsonProcessingException{

        jgen.writeArrayFieldStart(key);
        String carteRef = null;
        for(Carte carteTransport : carteTransports){
            carteRef = carteTransport.getReference();
            jgen.writeString(carteRef);
        }
        jgen.writeEndArray();
    }

    private void serializeCarteTransports(JsonGenerator jgen,String key, ArrayList<CarteTransport>
            carteTransports) throws IOException, JsonProcessingException{

        jgen.writeArrayFieldStart(key);
        String carteRef = null;
        for(Carte carteTransport : carteTransports){
            carteRef = carteTransport.getReference();
            jgen.writeString(carteRef);
        }
        jgen.writeEndArray();
    }

    private void serializeCarteDestination(JsonGenerator jgen,String key, ArrayList<CarteDestination>
            carteDestinations) throws IOException, JsonProcessingException{

        jgen.writeArrayFieldStart(key);
        String carteRef = null;
        for(CarteDestination carteDestination : carteDestinations){
            carteRef = carteDestination.getId();
            jgen.writeString(carteRef);
        }
        jgen.writeEndArray();
    }

    private void serializePiocheDestination(JsonGenerator jgen,String key, ArrayList<Carte>
            carteDestinations) throws IOException, JsonProcessingException{

        jgen.writeArrayFieldStart(key);
        String carteRef = null;
        for(Carte carte : carteDestinations){
            if(carte instanceof CarteDestination){
                carteRef = ((CarteDestination) carte).getId();
                jgen.writeString(carteRef);
            }
        }
        jgen.writeEndArray();
    }

    private void serializeVille(JsonGenerator jgen,String key, ArrayList<Ville>
            villes) throws IOException, JsonProcessingException{

        jgen.writeArrayFieldStart(key);
        for(Ville ville : villes){
            jgen.writeString(ville.getId());
        }
        jgen.writeEndArray();
    }

    private void serializeRoute(JsonGenerator jgen,String key, ArrayList<Route>
            routes) throws IOException, JsonProcessingException{

        jgen.writeArrayFieldStart(key);
        for(Route route : routes){
            jgen.writeString(route.getId());
        }
        jgen.writeEndArray();
    }
}
