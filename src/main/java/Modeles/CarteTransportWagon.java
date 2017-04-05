package Modeles;
import Vues.PiochesController;

public class CarteTransportWagon extends CarteTransport {

	public CarteTransportWagon(String couleur, boolean port) {
		super(couleur, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString()+"\n CT_Wagon{}";
	}

	public String getReference(){
		String str = "";
		str+="W";
		if(this.isPort()){
			str+="P";
		}
		str+=this.couleur;
		return str;
	}


}
