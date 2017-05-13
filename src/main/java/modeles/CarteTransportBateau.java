package modeles;

/**
 * Classe modelisant une carte transport wagon
 */
public class CarteTransportBateau extends CarteTransport {

	//Nous aurions pu supprimmer cette attribut car une carte bateau qui n'a pas de port est forcement double... il s'agit seulement d'un soucis de clarte
	private Boolean bateauDouble;

	/*
    *
	* CONSTRUCTEUR
	*
	*/

	/**
	 * Construit une carte wagon
	 * @param bateauDouble
	 */
	public CarteTransportBateau(int couleur, boolean port, Boolean bateauDouble) {
		super(couleur, port);
		this.bateauDouble = bateauDouble;
	}

	/*
	*
	* GETTER & SETTER
	*
	*/

	public boolean isBateauDouble(){
		return this.bateauDouble;
	}

	public String getReference(){
		String str = "";
		str+="B";
		if(!this.isBateauDouble()){
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
		return super.toString()+"\n CT_Bateau{" +
				"bateauDouble=" + bateauDouble +
				'}';
	}
}
