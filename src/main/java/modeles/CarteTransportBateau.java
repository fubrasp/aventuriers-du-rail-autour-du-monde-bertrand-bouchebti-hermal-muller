package modeles;

public class CarteTransportBateau extends CarteTransport {
	private Boolean bateauDouble;

	public CarteTransportBateau(String couleur, boolean port, Boolean bateauDouble) {
		super(couleur, port);
		this.bateauDouble = bateauDouble;
	}

	public boolean isBateauDouble(){
		return this.bateauDouble;
	}

	@Override
	public String toString() {
		return super.toString()+"\n CT_Bateau{" +
				"bateauDouble=" + bateauDouble +
				'}';
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
}
