package modeles;

import constantes.ConstantesJeu;
import outil.OutilCarte;
import java.util.*;

/**
 * Classe modelisant un joueur
 */
public class Joueur {

    //Liste des cartes de destinations ou itinéraires du joueur
    private ArrayList<CarteDestination> cartesDestination = new ArrayList<CarteDestination>();

    //Pseudo et couleur du joueur
    private String pseudo;
    private String couleur;

    //Pions en possession du joueur
    private int nbPionsBateau;
    private int nbPionsBateauReserve;
    private int nbPionsWagons;
    private int nbPionsWagonsReserve;

    //Score du joueur
    private int score;

    //Cartes selectionnees par le joueur lors d'une prise de route
    private HashMap<Integer, ArrayList<CarteTransport>> selectedCards = new HashMap<>();

    //Liste des cartes transports du joueur
    private ArrayList<CarteTransport> cartesTransport = new ArrayList<CarteTransport>();

    //Liste utilisee pour les ports construits par les joueurs
    private ArrayList<Ville> villesPossedees = new ArrayList<Ville>();

    //Liste des routes conquises par les joueurs
    private ArrayList<Route> routesPossedees = new ArrayList<Route>();

    //Attribut limitant les actions(prendre une route, piocher, echanger des pions) du joueur
    private int capaciteJeu = 2;

    //Attribut permettant d'effectuer l'initialisation des cartes destinations pour la prmeiere fois
    private boolean aInitialiseeSesCartesDestinations = false;

    //Attribut permettant d'eviter la double apparition d'une popup informative de fin de jeu
    private boolean aEuInformationFinDuJeu = false;


	/*
    *
	* CONSTRUCTEUR
	*
	*/

    /**
     * Construit un joueur
     * @param pseudo identifiant
     * @param couleur couleur associee au joueur utilisee pour la prise de route
     */
    public Joueur(String pseudo, String couleur) {
        this.pseudo = pseudo;
        this.score = 0;
        this.cartesDestination = new ArrayList<CarteDestination>();
        this.nbPionsBateau = ConstantesJeu.NOMBRE_PIONS_BATEAU_INITIALISATION;
        this.nbPionsBateauReserve = ConstantesJeu.NOMBRE_PIONS_BATEAU_RESERVE_INITIALISATION;
        this.nbPionsWagons = ConstantesJeu.NOMBRE_PIONS_WAGONS_INITIALISATION;
        this.nbPionsWagonsReserve = ConstantesJeu.NOMBRE_PIONS_WAGONS_RESERVE_INITIALISATION;
        this.cartesTransport = new ArrayList<CarteTransport>();
        this.couleur = couleur;
    }

	/*
	*
	* FONCTIONS
	*
	*/

    /**
     * Methode ajoutant une carte transport aux carte selectionnees du joueurs
     * utilisee pour la prise de route
     * @param carteTransport
     */
    public void addSelectCard(CarteTransport carteTransport) {
        if (selectedCards.containsKey(carteTransport.getCouleur())) {
            selectedCards.get(carteTransport.getCouleur()).add(carteTransport);
        } else {
            ArrayList<CarteTransport> cards = new ArrayList<>();
            cards.add(carteTransport);
            selectedCards.put(carteTransport.getCouleur(), cards);
        }
    }

    /**
     * Methode retirant une carte transport aux carte selectionnees du joueurs
     * utilisee pour la prise de route
     * @param carteTransport
     */
    public void removeSelectCard(CarteTransport carteTransport) {
        if (selectedCards.containsKey(carteTransport.getCouleur())) {
            selectedCards.get(carteTransport.getCouleur()).remove(carteTransport);
        }
    }

    //Eponyme
    public int nbOccurenceCarteSelectionnee(CarteTransport carteSelectionnee) {
        int nbOccurence = 0;
        if (selectedCards.containsKey(carteSelectionnee.getCouleur())) {
            ArrayList<CarteTransport> cards = selectedCards.get(carteSelectionnee.getCouleur());
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) == carteSelectionnee) {
                    nbOccurence = nbOccurence + 1;
                }
            }
        }

        return nbOccurence;
    }

    public void ajouterCarteTransport(CarteTransport cartePioche) {
        this.cartesTransport.add(cartePioche);
    }

    public void ajouterCartesDestination(ArrayList<CarteDestination> carteDestinations) {
        this.cartesDestination.addAll(carteDestinations);
    }

    /**
     * Methode donnant le nombre de carte d'un type donnee dans la main du joueur
     * utilisee pour l'ajout dynamique des cartes destinations
     * @param ct carte a comparer
     * @return nombre de carte du meme type que la carte donnee
     */
    public int dejaDansLaMainDuJoueur(CarteTransport ct) {
        int compteur = 0;

        for (CarteTransport ctt :
                this.cartesTransport) {
            if (ctt.getReference().equals(ct.getReference())) {
                compteur++;
            }
        }
        return compteur;
    }



    /**
     * Methode realisant la mise à jour du score en cas d'échange de pions
     * @param nbWInit nombre de pions wagons
     * @param nbBInit nombre de pions bateaux
     */
    public void majScoreEchange(int nbWInit, int nbBInit) {

        //Nombre de pions dans la main à la fin de l'échange
        int WMFin = this.nbPionsWagons;
        int BMFin = this.nbPionsBateau;

        //Calcul du malus à appliquer au score
        int malus = (WMFin - nbWInit) + (BMFin - nbBInit);

        //Modification du score
        this.score = (this.score - malus);

        //Eviter un score négatif
        if (this.score < 0) {
            this.score = 0;
        }

    }

    public void initialiserMainJoueur() {
        // aucune verification de carte est faite car nous savons pertinament que les pioches ont un nombre de carte suffisant
        CarteTransport carteAAjouter;
        for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_BATEAU_INITIALISATION; i++) {
            carteAAjouter = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportBateau().piocherCarte();
            this.cartesTransport.add(carteAAjouter);
        }

        for (int i = 0; i < ConstantesJeu.NOMBRE_CARTES_WAGON_INITIALISATION; i++) {
            carteAAjouter = (CarteTransport) Jeu.getInstance().getGestionnairePioches().getPiocheCartesTransportWagon().piocherCarte();
            this.cartesTransport.add(carteAAjouter);
        }
    }

    //<reference card, number of occurence>
    public HashMap<String, Integer> compterOccurencesCartes() {
        HashMap<String, Integer> comptes = new HashMap<String, Integer>();
        int compteurCourant = 0;
        for (CarteTransport carteCourante :
                this.cartesTransport) {
            compteurCourant = 0;
            for (CarteTransport carteCourantedeux :
                    this.cartesTransport) {
                if (carteCourante.getReference().equals(carteCourantedeux.getReference()))
                    compteurCourant++;
            }
            comptes.put(carteCourante.getReference(), compteurCourant);
        }

        return comptes;
    }

    /**
     * Selectionne une route, verifie que :
     * - la route n'est pas prise
     * - le joueur a les cartes requises dans la main
     * - le joueur a un nombre de pions suffisants
     * @param routeSelectionnee route selectionnee
     * @return true si le joueur à pris la route en ayant passé tous les test avec succès, false sinon
     */
    public boolean selectRoad(Route routeSelectionnee) {
        boolean result = false;
        if (routeSelectionnee.isTaken()) {
            System.out.println("Impossible d'attribuer la route : Route déjà prise");
        } else {
            ArrayList<CarteTransport> cartesUtilisees = new ArrayList<>(); // Cartes utilisees pour prendre la route
            if (OutilCarte.hasCarteRequiseDansLaMain(routeSelectionnee, cartesUtilisees, selectedCards)) {

                if (retirerPions(routeSelectionnee)) {
                    routeSelectionnee.setPossesseur(this);
                    defausserCartesUtilisees(cartesUtilisees);
                    this.majScoreRoadTaken(routeSelectionnee.getNombreEtapes());
                    routesPossedees.add(routeSelectionnee);
                    System.out.println("Road size : " + routeSelectionnee.getNombreEtapes());
                    result = true;
                } else {
                    System.out.println("Impossible d'attribuer la route : Pas assez de pions");
                }

            } else {
                System.out.println("Impossible d'attribuer la route : Pas de carte requise");
            }
        }
        return result;
    }

    /**
     * Retire les pions en fonctions du type de route (pion bateau pour route maritime, pion wagon pour route terrestre)
     * Le nombre de pion retiré est égale à la taille de la route
     * @param routeSelectionnee Route selectionnée
     * @return true si pion retiré avec succès, false si pas assez de pions
     */
    public boolean retirerPions(Route routeSelectionnee) {
        int nombreCaseRoute = routeSelectionnee.getNombreEtapes();
        if (routeSelectionnee.isMaritime()) {
            if (nbPionsBateau >= nombreCaseRoute) {
                nbPionsBateau -= nombreCaseRoute;
                return true;
            }
        } else {
            if (nbPionsWagons >= nombreCaseRoute) {
                nbPionsWagons -= nombreCaseRoute;
                return true;
            }
        }
        return false;
    }

    /**
     * Supprime les cartes utilisées de la main et les met dans la défausse
     * @param cartesUtilisees cartes utilisées pour prendre une route
     */
    public void defausserCartesUtilisees(ArrayList<CarteTransport> cartesUtilisees) {
        ArrayList<CarteTransport> carteTransportsASupprimer = new ArrayList<>();
        for (CarteTransport carteUtilisee : cartesUtilisees) {
            for (CarteTransport carteTransport : cartesTransport) {
                if (carteTransport.compare(carteUtilisee)) {
                    cartesTransport.remove(carteTransport);
                    break;
                }
            }
        }
        selectedCards.clear();
        Jeu.getInstance().getGestionnairePioches().dispatcherCartesDefausses(cartesUtilisees);
    }


    //Mise à jour du score en cas de prise de possession d'une route
    public void majScoreRoadTaken(int nbCases) {
        switch (nbCases) {
            case 1:
                this.score += 1;
                break;
            case 2:
                this.score += 2;
                break;
            case 3:
                this.score += 4;
                break;
            case 4:
                this.score += 7;
                break;
            case 5:
                this.score += 10;
                break;
            case 6:
                this.score += 15;
                break;
            case 7:
                this.score += 18;
                break;
            case 8:
                this.score += 21;
                break;
        }

        //Eviter un score négatif
        if (this.getScore() < 0) {
            this.score = 0;
        }
    }

    /**
     * Methode permettant de prendre un port
     * @param ville ville pour laquelle on veut construire le port
     * @return booleen notifiant si la prise de route est possible ou non
     */
    public boolean takePort(Ville ville) {
        boolean hasRequiredCard = false;
        ArrayList<CarteTransport> carteUtilisees = new ArrayList<>();
        if (ville.isCapacitePort()) {
            if (ville.hasPossesseur()) {
                System.out.println("Vous ne pouvez pas construire de port sur " + ville.getNom() + " car la ville " +
                        "à déjà un possesseur");
            } else {
                if (isRoadConstructed(ville)) {
                    if (getSelectedCardToTakePort(carteUtilisees)) {
                        hasRequiredCard = true;
                        villesPossedees.add(ville);
                    } else {
                        System.out.println("Vous ne pouvez pas construire de port sur " + ville.getNom() + " car vous n'avez " +
                                "pas les cartes requises");
                    }
                } else {
                    System.out.println("Vous ne pouvez pas construire de port sur " + ville.getNom() + " car vous ne " +
                            "possédez pas de route menant à cette ville");
                }
            }
        } else {
            System.out.println("Ville " + ville.getNom() + " non portuaire ne peut pas avoir de port");
        }

        if (hasRequiredCard) {
            defausserCartesUtilisees(carteUtilisees);
        }

        return hasRequiredCard;
    }

    /**
     * Vérifie si le joueur à une route construite menant à une ville donnée
     * @param ville
     * @return boolean, true si le joueur à une route construire menant à une ville, false sinon
     */
    public boolean isRoadConstructed(Ville ville) {
        boolean canBeTake = false;
        int i = 0;
        while (i < routesPossedees.size() && !canBeTake) {
            if (routesPossedees.get(i).getVilleArrivee() == ville ||
                    routesPossedees.get(i).getVilleDepart() == ville) {
                canBeTake = true;
            }
            i++;
        }

        return canBeTake;
    }

    /**
     * Methode permettant de determiner si la prise de port est possible
     * @param cartesUtilisees cartes fournies pour la prise du port
     * @return
     */
    public boolean getSelectedCardToTakePort(ArrayList<CarteTransport> cartesUtilisees) {
        Integer bestColor = -1;
        int bestScoreByColor = 0;
        int scoreByColor = 0;

        for (Integer key : selectedCards.keySet()) {
            if (!key.equals(ConstantesJeu.JOKER)) {
                scoreByColor = checkIfTwoShipTwoRailByColor(key, cartesUtilisees);
                if (scoreByColor > bestScoreByColor) {
                    bestScoreByColor = scoreByColor;
                    bestColor = key;
                }
            }
        }

        checkIfTwoShipTwoRailByColor(bestColor, cartesUtilisees);
        if (bestScoreByColor < 4) {
            int nbJokerNeeded = OutilCarte.nbJokerBesoin(4 - bestScoreByColor);
            return OutilCarte.utiliserJoker(nbJokerNeeded, cartesUtilisees, selectedCards);
        } else {
            return true;
        }
    }

    /**
     * Methode permettant de verifier si nous avons les paires de cartes adequates ou non pour la prise d'un port
     * @param color
     * @param usedCard
     * @return
     */
    public int checkIfTwoShipTwoRailByColor(Integer color, ArrayList<CarteTransport> usedCard) {
        usedCard.clear();
        if (selectedCards.containsKey(color)) {
            ArrayList<CarteTransport> carteTransports = selectedCards.get(color);
            int nbCarteBateauSimple = 0;
            int nbCarteWagon = 0;
            int i = 0;
            CarteTransport carteTransport;
            while (i < carteTransports.size() && nbCarteBateauSimple < 2) {
                carteTransport = carteTransports.get(i);
                if (carteTransport.isPort()) {
                    if (carteTransport instanceof CarteTransportBateau) {
                        nbCarteBateauSimple = nbCarteBateauSimple + 1;
                        usedCard.add(carteTransport);
                    }
                }
                i++;
            }
            i = 0;
            while (i < carteTransports.size() && nbCarteWagon < 2) {
                carteTransport = carteTransports.get(i);
                if (carteTransport.isPort()) {
                    if (carteTransport instanceof CarteTransportWagon) {
                        nbCarteWagon = nbCarteWagon + 1;
                        usedCard.add(carteTransport);
                    }
                }
                i++;
            }
        }

        return usedCard.size();
    }

    public ArrayList<ArrayList<Route>> getChemin() {
        //le chemin est une structure intermediaire facilitant le calcul des scores
        ArrayList<ArrayList<Route>> chemins = new ArrayList<>();

        for (Route routeCourante :
                this.routesPossedees) {
            constructChemin(chemins, routeCourante);
        }

        return chemins;
    }

    //Fonction qui calcule le score final d'un joueur à la fin de la partie
    public void calculerScoreFinal() {
        ArrayList<CarteDestination> destinationsReussies = new ArrayList<CarteDestination>();
        for (CarteDestination destination : this.getCartesDestination()) {
            boolean reussi = this.majScoreDestinations(this.getChemin(), destination);
            if (reussi) {
                destinationsReussies.add(destination);
            }
        }
        this.majScorePort(this.villesPossedees, destinationsReussies);
        this.majScorePortsNonUtilises();
    }

    public void majScorePort(ArrayList<Ville> ports, ArrayList<CarteDestination> destinations) {
        for (Ville port : ports) {
            int nbCartes = 0;
            for (CarteDestination destination : destinations) {
                for (Ville ville : destination.getVilles()) {
                    if (port.getNom().equals(ville.getNom())) {
                        nbCartes++;
                    }
                }

            }
            switch (nbCartes) {
                case 0:
                    break;
                case 1:
                    this.score+=20;
                    break;
                case 2:
                    this.score+=30;
                    break;
                default:
                    this.score+=40;
                    break;
            }
        }
    }

    public void majScorePortsNonUtilises() {
        int malus = (3 - villesPossedees.size()) * 4;
        this.score -= malus;
    }

    //Fonction qui met à jour le score à la fin de la partie en fonction d'une carte destination (réussie ou non)
    public boolean majScoreDestinations(ArrayList<ArrayList<Route>> chemins, CarteDestination destination) {
        int index = 1;
        boolean depart;
        boolean arrivee;
        boolean reussi = false;
        for (ArrayList<Route> chemin : chemins) {
            depart = false;
            arrivee = false;
            index++;
            for (Route route : chemin) {
                if (destination.getVilles().get(0).getNom().equals(route.getVilleArrivee().getNom()) || destination.getVilles().get(0).getNom().equals(route.getVilleDepart().getNom())) {
                    arrivee = true;
                }
                if (destination.getVilles().get(1).getNom().equals(route.getVilleArrivee().getNom()) || destination.getVilles().get(1).getNom().equals(route.getVilleDepart().getNom())) {
                    depart = true;
                }
            }
            if (depart && arrivee) {
                reussi = true;
            }

        }
        if (reussi) {
            this.setScore(this.getScore() + destination.getPointsScoreAssoccies());
        } else {
            this.setScore(this.getScore() - destination.getPointsScoreAssoccies());
        }
        return reussi;
    }

    public void constructChemin(ArrayList<ArrayList<Route>> chemins, Route currentRoute) {
        int i = chemins.size() - 1;

        ArrayList<Route> chemin;
        Route route;

        ArrayList<Integer> indiceChoisi = new ArrayList<>();
        ArrayList<Route> routeChoisi = null;
        while (i >= 0) {
            chemin = chemins.get(i);
            int j = 0;
            while (j < chemin.size()) {
                route = chemin.get(j);

                if (route.containsCity(currentRoute.getVilleDepart()) || route.containsCity(currentRoute.getVilleArrivee())) {
                    if (routeChoisi != null) {
                        chemin.addAll(routeChoisi);
                        routeChoisi = chemin;
                    } else {
                        chemin.add(currentRoute);
                        routeChoisi = chemin;
                    }
                    indiceChoisi.add(i);

                    break;
                }
                j++;
            }

            i--;
        }

        if (indiceChoisi.size() == 0) {
            ArrayList<Route> newChemin = new ArrayList<>();
            newChemin.add(currentRoute);
            chemins.add(newChemin);
        } else {
            i = 0;
            while (i < indiceChoisi.size() - 1) {
                int indice = indiceChoisi.get(i);
                chemins.remove(indice);
                i++;
            }
        }
    }

    public void diminuerCapaciteJoueur(int value) {
        this.capaciteJeu -= value;
    }

    public void augmenterCapaciteJoueur(int value) {
        this.capaciteJeu += value;
    }

    public void reseterCapaciteJoueur() {
        this.capaciteJeu = ConstantesJeu.VALEUR_ACTIONS;
    }

    public boolean peutPiocherJokerCartesVisibles() {
        return this.capaciteJeu >= 2;
    }


    /*
	*
	* GETTER & SETTER
	*
	*/

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNbPionsBateau() {
        return nbPionsBateau;
    }

    public void setNbPionsBateau(int nbPionsBateau) {
        this.nbPionsBateau = nbPionsBateau;
    }

    public int getNbPionsBateauReserve() {
        return nbPionsBateauReserve;
    }

    public void setNbPionsBateauReserve(int nbPionsBateauReserve) {
        this.nbPionsBateauReserve = nbPionsBateauReserve;
    }

    public int getNbPionsWagons() {
        return nbPionsWagons;
    }

    public void setNbPionsWagons(int nbPionsWagons) {
        this.nbPionsWagons = nbPionsWagons;
    }

    public int getNbPionsWagonsReserve() {
        return nbPionsWagonsReserve;
    }

    public void setNbPionsWagonsReserve(int nbPionsWagonsReserve) {
        this.nbPionsWagonsReserve = nbPionsWagonsReserve;
    }

    public Joueur(String pseudo) {
        this.pseudo = pseudo;
    }

    public ArrayList<CarteDestination> getCartesDestination() {
        return cartesDestination;
    }

    public ArrayList<CarteTransport> getCartesTransport() {
        return cartesTransport;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public HashMap<Integer, ArrayList<CarteTransport>> getSelectedCards() {
        return selectedCards;
    }

    public ArrayList<Ville> getVillesPossedees() {
        return villesPossedees;
    }

    public ArrayList<Route> getRoutesPossedees() {
        return routesPossedees;
    }

    public void ajouterRoute(Route route) {
        this.routesPossedees.add(route);
    }

    public void setCapaciteJeu(int capaciteJeu) {
        this.capaciteJeu = capaciteJeu;
    }

    public boolean aLaCapaciteDeJouer() {
        return this.capaciteJeu > 0;
    }

    public boolean aLaCapaciteDePiocherDesCartesDestinations() {
        return this.capaciteJeu >= 2;
    }

    public boolean aLaCapaciteDePrendreRoute() {
        return this.capaciteJeu >= 2;
    }

    public int getNbBateauxEtWagonsConfondus() {
        return this.nbPionsWagons + this.nbPionsBateau;
    }

    public boolean isAEuInformationFinDuJeu() {
        return aEuInformationFinDuJeu;
    }

    public void setAEuInformationFinDuJeu(boolean aEuInformationFinDuJeu) {
        this.aEuInformationFinDuJeu = aEuInformationFinDuJeu;
    }

    public boolean isaInitialiseeSesCartesDestinations() {
        return aInitialiseeSesCartesDestinations;
    }

    public void setaInitialiseeSesCartesDestinations(boolean aInitialiserSesCartesDestinations) {
        this.aInitialiseeSesCartesDestinations = aInitialiserSesCartesDestinations;
    }
}
