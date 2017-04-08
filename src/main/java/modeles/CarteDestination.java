package modeles;

import java.util.*;

public class CarteDestination extends Carte {
	private int pointsScoreAssoccies;
	private ArrayList<Ville> villes = new ArrayList<Ville>();

	public CarteDestination(int pointsScoreAssoccies, ArrayList<Ville> villes) {
		super();
		this.pointsScoreAssoccies = pointsScoreAssoccies;
		this.villes = villes;
	}

	public int getPointsScoreAssoccies() {
		return pointsScoreAssoccies;
	}

	public void setPointsScoreAssoccies(int pointsScoreAssoccies) {
		this.pointsScoreAssoccies = pointsScoreAssoccies;
	}

	public ArrayList<Ville> getVilles() {
		return villes;
	}

	public void setVilles(ArrayList<Ville> villes) {
		this.villes = villes;
	}

	@Override
	public String toString() {
		return "CarteDestination{" +
				"pointsScoreAssoccies=" + pointsScoreAssoccies +
				", villes=" + villes +
				'}';
	}

	public String getReference(){
		String str =  this.pointsScoreAssoccies+"_";
		for (Ville v:
			 this.villes) {
			str+=v.getNom();
		}
		return str;
	}

	public static ArrayList<CarteDestination> renvoyerCarteChoisies(ArrayList<CarteDestination> cartesProposees, ArrayList<String> choixUtilisateurParReferenceCarte){
		ArrayList<CarteDestination> cartesARenvoyer = new ArrayList<CarteDestination>();
		//Faire un disjoint _a_la_place_ serait pas mal mais on a pas des listes de meme type
		CarteDestination carteCourante = null;
		for (int i = 0; i<cartesProposees.size(); i++){
			carteCourante = cartesProposees.get(i);
			if (carteCourante.referenceMatch(choixUtilisateurParReferenceCarte)){
				cartesARenvoyer.add(carteCourante);
			}
		}
		return cartesARenvoyer;
	}

	private boolean referenceMatch(ArrayList<String> liste){
		for (String identifiantCarteChoisie:
			 liste) {
			if(identifiantCarteChoisie.equals(this.getReference())){
				return true;
			}
		}
		return false;
	}
}
