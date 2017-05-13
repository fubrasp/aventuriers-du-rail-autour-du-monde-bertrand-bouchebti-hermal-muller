package modeles;

/**
 * Classe modelisant une carte transport wagon
 */
public class CarteTransportWagon extends CarteTransport {

	/*
    *
	* CONSTRUCTEUR
	*
	*/

	/**
	 * Construit une carte wagon
	 */
	public CarteTransportWagon(int couleur, boolean port) {
		super(couleur, port);
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public String getReference(){
		String str = "";
		str+="W";
		if(this.isPort()){
			str+="P";
		}
		str+=this.couleur;
		return str;
	}

	/*
	*
	* TO STRING ET AFFICHAGES
	*
	*/

	@Override
	public String toString() {
		return super.toString()+"\n CT_Wagon{}";
	}

}
