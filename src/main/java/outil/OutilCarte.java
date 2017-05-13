package outil;

import constantes.ConstantesJeu;
import modeles.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Flo on 10/05/2017.
 */

/**
 * Classe utilisee pour la prise de route
 */
public class OutilCarte {

    /*
	*
	* FONCTIONS
	*
	*/

    /**
	 * Verifie que le joueur à les cartes requises pour sélectionner une route
	 * Si oui, return true et supprime les cartes de la main
	 * @param routeSelectionnee : route selectionnee
	 * @param cartesSelectionnees : liste de cartes selectionnees
     * @return boolean , true si les cartes selectionnes permettent de prendre la route
	 */
    public static boolean hasCarteRequiseDansLaMain(Route routeSelectionnee,
                                                    ArrayList<CarteTransport> cartesUtilisees,
                                                    HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        boolean hasCarteRequiseDansLaMain = false;
        int nbBonneCarteDansLaMain = 0;
        int nbCardMissed = 0;

        switch (routeSelectionnee.getCouleur()){
            case Couleur.SPE :{
                nbBonneCarteDansLaMain = prendreRoutePaire(routeSelectionnee,cartesUtilisees,cartesSelectionnees);
                nbCardMissed = routeSelectionnee.getNombreEtapes()-nbBonneCarteDansLaMain;
                nbCardMissed = nbCardMissed*2;
                break;
            }
            case Couleur.GRIS:{
                nbBonneCarteDansLaMain = prendreRouteGrise(routeSelectionnee,cartesUtilisees,cartesSelectionnees);
                nbCardMissed = routeSelectionnee.getNombreEtapes()-nbBonneCarteDansLaMain;
                break;
            }
            default:{
                nbBonneCarteDansLaMain = prendreRouteCouleur(routeSelectionnee,cartesUtilisees,cartesSelectionnees);
                nbCardMissed = routeSelectionnee.getNombreEtapes()-nbBonneCarteDansLaMain;
                break;
            }
        }

        if(nbCardMissed > 0){
            int jokerNeeded = nbJokerBesoin(nbCardMissed);
            hasCarteRequiseDansLaMain = utiliserJoker(jokerNeeded,cartesUtilisees,cartesSelectionnees);
        }else{
            hasCarteRequiseDansLaMain = true;
        }

        return hasCarteRequiseDansLaMain;
    }

    /**
	 *	Prends le nombre de carte nécessaire pour prendre une route parmis les cartes sélectionnées
	 *	@param routeSelectionnee route selectionnee par le joueur
	 *	@param cartesSelectionnees cartes selectionnees par le joueur
	 *	@param cartesUtilisees : Cartes utilisees pour prendre la route
	 *	@return integer, le nombre de case que peut prendre un joueur (maximum taille de la route)
	 */
    //Enlever la duplication de code dans cette classe conduit a des bugs..
    public static int prendreRouteCouleur(Route routeSelectionnee,
                                          ArrayList<CarteTransport> cartesUtilisees,
                                          HashMap<Integer, ArrayList<CarteTransport>> cartesSelectionnees){
        int nbOccurence = 0;

        if(cartesSelectionnees.containsKey(routeSelectionnee.getCouleur())){
            ArrayList<CarteTransport> carteTransports = cartesSelectionnees.get(routeSelectionnee.getCouleur());

            if(routeSelectionnee.isMaritime()){
                int i=0;
                while(nbOccurence<routeSelectionnee.getNombreEtapes() && i<carteTransports.size() ){
                    if(carteTransports.get(i) instanceof CarteTransportBateau){
                        if(((CarteTransportBateau) carteTransports.get(i)).isBateauDouble()){
                            nbOccurence += 2;
                        }else{
                            nbOccurence += 1;
                        }
                        cartesUtilisees.add(carteTransports.get(i));
                    }
                    i++;
                }
            }else{
                int i=0;
                while(nbOccurence<routeSelectionnee.getNombreEtapes() && i<carteTransports.size() ){
                    if(carteTransports.get(i) instanceof CarteTransportWagon){
                        nbOccurence += 1;
                        cartesUtilisees.add(carteTransports.get(i));
                    }
                    i++;
                }
            }
        }
        return nbOccurence;
    }

    /**
	 *	Utilise des jokers si nombre suffisant de joker selectionees
	 *	@param nbJokerBesoin : nombre de joker besoin
	 *	@param cartesUtilisees : cartes utilisées parmis les cartes selectionnées
	 *	@param cartesSelectionnees : cartes selectionnées par le joueur
	 *	@return boolean , true si les jokers ont été utilisé, false si pas assez de joker selectionné
	 */
    public static boolean utiliserJoker(int nbJokerBesoin,
                                   ArrayList<CarteTransport> cartesUtilisees,
                                   HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        boolean asEnoughJoker = false;
        if(cartesSelectionnees.containsKey(ConstantesJeu.JOKER)){
            ArrayList<CarteTransport> jokerSelected = cartesSelectionnees.get(ConstantesJeu.JOKER);
            if(jokerSelected.size() >= nbJokerBesoin){
                int i=0;
                while (i<nbJokerBesoin){
                    cartesUtilisees.add(jokerSelected.get(i));
                    i++;
                }
                asEnoughJoker=true;
            }
        }

        return asEnoughJoker;
    }

    /**
	 *	Retourne le nombre de joker besoin en fonction du nombre de carte manquante
	 *	@param nbCarteManquante nombre de carte manquante
	 *	@return nombre de joker besoin
	 */
    public static int nbJokerBesoin(int nbCarteManquante){
        int jokerNeeded = 0;
        if(nbCarteManquante>0){
            // Division par 2 car il s'agit d'un joker qui correspond a l'equivalent de 2 cartes simples
            jokerNeeded = (int)Math.ceil((double)nbCarteManquante/2);
        }
        return jokerNeeded;
    }

    /**
     *  Une route grise ne peut être prise qu'avec des cartes d'une même couleur
     *  Parmis les cartes sélectionné on :
     *  Sélectionne la couleur dont le score est le plus élevé
	 *	Dans cette couleur on les cartes nécessaires pour prendre la route sélectionnée
	 *	@param routeSelectionnee route sélectionnée
	 *	@param cartesUtilisees cartes utilisées pour prendre la route
	 *	@return integer nombre de case que peut prendre un joueur parmis ses cartes sélectionnées
	 */
    public static int prendreRouteGrise(Route routeSelectionnee,
                                   ArrayList<CarteTransport> cartesUtilisees,
                                   HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        int result = 0;
        Integer colorWhichHasMaxOccurence = -1;
        int maxOccurence = 0;
        for(Integer couleur : cartesSelectionnees.keySet()){
            if(!couleur.equals(ConstantesJeu.JOKER)){
                int colorOccurence = getScorePourUneCouleur(routeSelectionnee,couleur,cartesUtilisees,cartesSelectionnees);
                if( colorOccurence > maxOccurence){
                    maxOccurence = colorOccurence;
                    colorWhichHasMaxOccurence = couleur;
                }
            }
        }

        cartesUtilisees.clear();
        if(cartesSelectionnees.containsKey(colorWhichHasMaxOccurence)){
            result = getScorePourUneCouleur(routeSelectionnee,colorWhichHasMaxOccurence,cartesUtilisees,cartesSelectionnees);
        }

        return result;
    }

    /**
     *  Retourne le score pour une couleur de carte donnée
     *  Le score indique le nombre de case que le joueur peut prendre avec ses cartes
	 *	Les bateaux doubles incrémente le score de 2
	 *	@param routeSelectionnee route selectionnée
	 *	@param couleurCarte couleur de la carte sur lequel on veut établir le score
	 *	@param cartesUtilisees cartes utilisee pour prendre la route
	 *	@param cartesSelectionnees cartes selectionnées par le joueur
	 *	@return score pour une couleur de carte donnée
	 */
    public static int getScorePourUneCouleur(Route routeSelectionnee,
                               Integer couleurCarte,
                               ArrayList<CarteTransport> cartesUtilisees,
                               HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        int nbOccurence = 0;
        int nombreCaseRoute = routeSelectionnee.getNombreEtapes();

        if(cartesSelectionnees.containsKey(couleurCarte)){
            ArrayList<CarteTransport> carteTransports = cartesSelectionnees.get(couleurCarte);

            if(routeSelectionnee.isMaritime()){ // Si route maritime utilise carte BATEAU
                int i=0;
                while(nbOccurence<nombreCaseRoute && i<carteTransports.size() ){
                    if(carteTransports.get(i) instanceof CarteTransportBateau){
                        if(((CarteTransportBateau) carteTransports.get(i)).isBateauDouble()){
                            nbOccurence += 2;
                        }else{
                            nbOccurence += 1;
                        }
                        cartesUtilisees.add(carteTransports.get(i));
                    }
                    i++;
                }
            }else{ // Si route terrestre, utilise carte WAGON
                int i=0;
                while(nbOccurence<nombreCaseRoute && i<carteTransports.size() ){
                    if(carteTransports.get(i) instanceof CarteTransportWagon){
                        nbOccurence += 1;
                        cartesUtilisees.add(carteTransports.get(i));
                    }
                    i++;
                }
            }
        }
        return nbOccurence;
    }


    /**
     *  Retourne le nombre de carte pouvant être placé parmis les cartes sélectionnées
     *  Une route paire de 2 cases nécessite 4 cartes wagons (une couleur par case)
     *  @param routeSelectionnee route sélectionnée par le joueur
     *  @param cartesUtilisees carte utilisé pour prendre la route
     *  @param cartesSelectionnees carte sélectionnée par le joueur
     *  @return nombre de carte pouvant être placé
	 */
    public static int prendreRoutePaire(Route routeSelectionnee,
                                     ArrayList<CarteTransport> cartesUtilisees,
                                     HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        int nbWagonByColor;
        int nbCarteCanBePlaced = 0;

        for(Integer key : cartesSelectionnees.keySet()){
            nbWagonByColor = getNbWagonByCouleur(key,cartesSelectionnees);
            if (nbWagonByColor != 0 && nbCarteCanBePlaced < (routeSelectionnee.getNombreEtapes()*2)) {
                nbCarteCanBePlaced = nbCarteCanBePlaced + (nbWagonByColor/2);
                utiliseNbWagonByCouleur(key,((nbWagonByColor/2)*2),cartesUtilisees,cartesSelectionnees);
            }
        }

        return nbCarteCanBePlaced;
    }

    /**
     *  Retourne le nombre de wagon pour une couleur donnée parmis les cartes sélectionnées
     *  @param color couleur pour laquelle on veut un nombre de wagon
     *  @param cartesSelectionnees carte sélectionnée par le joueur
     *  @return nombre de wagon pour une couleur donnée
     */
    public static int getNbWagonByCouleur(Integer color,HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        int nbWagon = 0;
        if(cartesSelectionnees.containsKey(color)){
            ArrayList<CarteTransport> transports = cartesSelectionnees.get(color);
            for(CarteTransport carteTransport : transports){
                if(carteTransport instanceof CarteTransportWagon){
                    nbWagon = nbWagon+1;
                }
            }
        }
        return nbWagon;
    }

    /**
     *  Utilise des wagons selon leur couleur et le nombre donée
     *  @param color couleur pour laquelle on veut un nombre de wagon
     *  @p
     *  @param cartesSelectionnees carte sélectionnée par le joueur
     */
    public static void utiliseNbWagonByCouleur(Integer color,
                                      int nbWagonAUtiliser,
                                      ArrayList<CarteTransport> cartesUtilisees,
                                      HashMap<Integer,ArrayList<CarteTransport>> cartesSelectionnees){
        int nbWagon = 0;
        if(cartesSelectionnees.containsKey(color)){
            ArrayList<CarteTransport> transports = cartesSelectionnees.get(color);
            for(CarteTransport carteTransport : transports){
                if(carteTransport instanceof CarteTransportWagon && nbWagonAUtiliser > nbWagon){
                    nbWagon = nbWagon+1;
                    cartesUtilisees.add(carteTransport);
                }
            }
        }
    }


}
