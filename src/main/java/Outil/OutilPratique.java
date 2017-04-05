package Outil;

/**
 * Created by bertran95u on 03/04/2017.
 */
public class OutilPratique {
    public static int nbAleat(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
