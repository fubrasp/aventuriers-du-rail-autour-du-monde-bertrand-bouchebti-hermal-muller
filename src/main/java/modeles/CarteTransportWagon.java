package modeles;

public class CarteTransportWagon extends CarteTransport {

	public CarteTransportWagon(int couleur, boolean port) {
		super(couleur, port);
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
