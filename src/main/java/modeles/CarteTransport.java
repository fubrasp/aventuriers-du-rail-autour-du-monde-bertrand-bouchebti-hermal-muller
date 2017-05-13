package modeles;

import constantes.ConstantesJeu;
import outil.OutilES;

/**
 * Classe modelisant une carte transport
 */
public class CarteTransport extends Carte {

	//Couleur de la carte transport
	protected int couleur;

	//Capacite a etre utilisee pour construire des ports
	private boolean port;

	/*
    *
	* CONSTRUCTEUR
	*
	*/

	/**
	 * Construit une carte transport
	 * @param couleur de la carte transport
	 * @param port booleen a un port
	 */
	public CarteTransport(int couleur, boolean port) {
		super();
		this.couleur = couleur;
		this.port = port;
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public String getUrlAssociatedWithColor(){
		if(this.couleur!= ConstantesJeu.JOKER){
			return this.couleur+ ConstantesJeu.EXTENSION_IMAGES;
		}else{
			return Integer.toString(ConstantesJeu.JOKER);
		}
	}

	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	public boolean isPort() {
		return port;
	}

	public void setPort(boolean port) {
		this.port = port;
	}

	//Evite le bug lie au sur type
	//Identifiant unique d'une carte transport
	public String getReference(){
		return null;
	}


	/*
	*
	* EGALITES ET HASHCODES
	*
	*/

	//On aurait pu faire un equals sur la reference aussi
	public boolean compare(CarteTransport carteTransport){
		if(carteTransport instanceof CarteTransportBateau){
			return couleur == ((CarteTransportBateau)carteTransport).getCouleur()
					&& port == ((CarteTransportBateau)carteTransport).isPort()
					&& ((CarteTransportBateau)this).isBateauDouble() == ((CarteTransportBateau) carteTransport).isBateauDouble();
		}else if(carteTransport instanceof CarteTransportWagon){
			return couleur == ((CarteTransport)carteTransport).getCouleur() && port == ((CarteTransport)carteTransport).port;
		}else{
			return false;
		}
	}

	/*
	*
	* TO STRING ET AFFICHAGES
	*
	*/

	@Override
	public String toString() {
		return "CarteTransport{" + "couleur --> "+this.couleur+", port --> "+this.port+"}";
	}

}
