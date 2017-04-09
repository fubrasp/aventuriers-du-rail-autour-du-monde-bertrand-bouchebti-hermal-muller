package modeles;

import outil.OutilES;

public class CarteTransport extends Carte {
	protected int couleur;
	private boolean port;

	public final static int JOKER=666;
	public final static int PIOCHE_REFAITE=999;
	public final static int PAS_DE_CARTE_DANS_LA_DEFAUSSE=9999;


	public CarteTransport(int couleur, boolean port) {
		super();
		this.couleur = couleur;
		this.port = port;
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

	public String getUrlAssociatedWithColor(){
		if(this.couleur!=CarteTransport.JOKER){
			return this.couleur+ OutilES.EXTENSION_IMAGES;
		}else{
			return Integer.toString(CarteTransport.JOKER);
		}
	}

	public void setPort(boolean port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "CarteTransport{" + "couleur --> "+this.couleur+", port --> "+this.port+"}";
	}

	public String getReference(){
		return null;
	}
}
