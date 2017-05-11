package modeles;

import java.util.*;

/**
 * Created by bertran95u on 07/04/2017.
 */
public class GestionnairePioches {
    private PiocheTransport piocheCartesTransportWagon;
    private PiocheTransport piocheCartesTransportBateau;
    private PiocheDestination piocheCartesDestination;
    private ArrayList<CarteTransport> cartesVisibles = new ArrayList<CarteTransport>();

    public GestionnairePioches(PiocheTransport piocheCartesTransportWagon, PiocheTransport piocheCartesTransportBateau, PiocheDestination piocheCartesDestination, ArrayList<CarteTransport> cartesVisibles) {
        this.piocheCartesTransportWagon = piocheCartesTransportWagon;
        this.piocheCartesTransportBateau = piocheCartesTransportBateau;
        this.piocheCartesDestination = piocheCartesDestination;
        this.cartesVisibles = cartesVisibles;
    }

    public GestionnairePioches(){
        this.piocheCartesTransportBateau = new PiocheTransport();
        this.piocheCartesTransportWagon = new PiocheTransport();
        this.piocheCartesDestination = new PiocheDestination();
    }

    public void initialiserPioches() {
        this.piocheCartesTransportBateau.initialiserPiocheBateaux();
        this.piocheCartesTransportWagon.initialiserPiocheWagons();
        this.piocheCartesDestination.initialiserPiocheDestinations();
    }

    private void initialiserCarteVisibles(){
        initialisationCarteVisiblesPioche(this.piocheCartesTransportBateau);
        initialisationCarteVisiblesPioche(this.piocheCartesTransportWagon);
    }

    private void initialisationCarteVisiblesPioche(PiocheTransport piocheTransport){
        if(!piocheTransport.getCartes().isEmpty()){
            this.ajouterCartesVisibles(piocheTransport.renvoyerTroisDernieresCarteVisibles());
        }else {
            boolean b = piocheTransport.reconstruirePiocheAvecDefausse();
            if(b)
                this.ajouterCartesVisibles(piocheTransport.renvoyerTroisDernieresCarteVisibles());
        }
    }

    public void ajouterCartesVisibles(ArrayList<CarteTransport> cartesAAjouter){
        this.cartesVisibles.addAll(cartesAAjouter);
    }

    public void reseterCartesVisibles(){
        this.dispatcherCartesDefausses(this.cartesVisibles);
        this.cartesVisibles.clear();
        this.initialiserCarteVisibles();
    }

    public void dispatcherCartesDefausses(ArrayList<CarteTransport> cartesADefausser){
        for (CarteTransport carteVisibleADefausser:
                cartesADefausser) {
            if(carteVisibleADefausser instanceof CarteTransportBateau){
                this.piocheCartesTransportBateau.ajouterCarteDefausse(carteVisibleADefausser);
            }else{
                if (carteVisibleADefausser instanceof CarteTransportWagon){
                    this.piocheCartesTransportWagon.ajouterCarteDefausse(carteVisibleADefausser);
                }
            }
        }
    }

    public void preparerPioches(){
        this.piocheCartesTransportWagon.melanger();
        this.piocheCartesTransportBateau.melanger();
        this.piocheCartesDestination.melanger();

        this.initialiserCarteVisibles();
    }

    public int detecterTropJokersVisibles() {
        int compteur = 0;
        for (CarteTransport carteTransport : this.cartesVisibles) {
            if (carteTransport.getCouleur()==CarteTransport.JOKER) {
                compteur++;
            }
            // eviter des parcours inutiles
            if (compteur >= 3) {
                return 3;
            }
        }
        return compteur;
    }

    public PiocheTransport getPiocheCartesTransportWagon() {
        return piocheCartesTransportWagon;
    }

    public PiocheTransport getPiocheCartesTransportBateau() {
        return piocheCartesTransportBateau;
    }

    public PiocheDestination getPiocheCartesDestination() {
        return this.piocheCartesDestination;
    }

    public ArrayList<CarteTransport> getCartesVisibles() {
        return cartesVisibles;
    }

    /*public void setPiocheCartesTransportWagon(PiocheTransport piocheCartesTransportWagon) {
        this.piocheCartesTransportWagon = piocheCartesTransportWagon;
    }

    public void setPiocheCartesTransportBateau(PiocheTransport piocheCartesTransportBateau) {
        this.piocheCartesTransportBateau = piocheCartesTransportBateau;
    }

    public void setPiochesDestinations(PiocheDestination piochesDestinations) {
        this.piocheCartesDestination = piochesDestinations;
    }*/

    public void setCartesVisibles(ArrayList<CarteTransport> cartesVisibles) {
        this.cartesVisibles = cartesVisibles;
    }
}
