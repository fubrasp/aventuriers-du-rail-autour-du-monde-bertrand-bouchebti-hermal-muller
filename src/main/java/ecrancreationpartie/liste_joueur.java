package ecrancreationpartie;

import modeles.Joueur;

import java.util.ArrayList;

/**
 * Created by youssef on 01/05/2017.
 */
public class liste_joueur {

    public static ArrayList<Joueur> list_joueur=new ArrayList<Joueur>();


    public static void print()
    {
        for(int i=0;i<list_joueur.size();i++)
        {
            System.out.println("Nom joueur N  " + i + ":   "+list_joueur.get(i).getPseudo());
            System.out.println("couleur joueur N  " + i + ":   "+list_joueur.get(i).getCouleur());
        }
    }
}
