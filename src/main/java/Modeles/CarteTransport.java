package Modeles;

import Outil.OutilES;
import Vues.PiochesController;

import java.util.ArrayList;

public class CarteTransport extends Carte {
	protected String couleur;
	private boolean port;

	public final static String JOKER="joker";
	public final static String JAUNE="Jaune";
	public final static String VERT="Vert";
	public final static String ROUGE="Rouge";
	public final static String NOIR="Noir";
	public final static String BLANC="Blanc";
	public final static String VIOLET="Violet";

	public CarteTransport(String couleur, boolean port) {
		super();
		this.couleur = couleur;
		this.port = port;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public boolean isPort() {
		return port;
	}

	public String getUrlAssociatedWithColor(){
		if(!this.couleur.equals(CarteTransport.JOKER)){
			return this.couleur+ OutilES.EXTENSION_IMAGES;
		}else{
			return CarteTransport.JOKER;
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
