package modeles;

public class Couleur {
    public final static int ROUGE = 0x1;
    public final static int JAUNE = 0x2;
    public final static int VERT = 0x3;
    public final static int VIOLET = 0x4;
    public final static int BLANC = 0x5;
    public final static int NOIR = 0x6;
    public final static int GRIS = 0x7;
    public final static int SPE = 0x8;


    public static int getColor(String pattern){
        int color;
        switch (pattern){
            case "b":{
                color = BLANC;
                break;
            }
            case "ve":{
                color = VERT;
                break;
            }
            case "vi":{
                color = VIOLET;
                break;
            }
            case "n":{
                color = NOIR;
                break;
            }
            case "r":{
                color = ROUGE;
                break;
            }
            case "j":{
                color = JAUNE;
                break;
            }
            case "g":{
                color = GRIS;
                break;
            }
            case "spe":{
                color = SPE;
                break;
            }
            default:{
                throw new IllegalArgumentException("Pattern : "+pattern+" doesn't exist");
            }
        }
        return color;
    }
}
