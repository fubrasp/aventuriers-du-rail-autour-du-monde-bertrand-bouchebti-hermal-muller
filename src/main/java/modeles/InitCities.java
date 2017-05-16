package modeles;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bertran95u on 13/05/2017.
 */

/**
 * Classe modelisant l'initialisation des villes pour le plateau
 */
public class InitCities {

    //On aurait aussi pu utiliser une enumrecouper avec les cartes destinations

    /**
     * @return Map
     * key : abbreviation de la ville
     * value : Ville
     */
    public static Map<String, Ville> initCities(){
        Map<String, Ville> aMap = new HashMap<>();
        aMap.put("vanc", new Ville("vanc","Vancouver",true));
        aMap.put("losa", new Ville("losa","Los Angeles",true));
        aMap.put("winn", new Ville("winn","Winnipeg",false));
        aMap.put("newy", new Ville("newy","New York",true));
        aMap.put("miam", new Ville("miam","Miami",true));
        aMap.put("cara", new Ville("cara","Caracas",true));
        aMap.put("lima", new Ville("lima","Lima",true));
        aMap.put("valp", new Ville("valp","Valparaiso",true));
        aMap.put("buen", new Ville("buen","Buenos Aires",true));
        aMap.put("rio", new Ville("rio","Rio de Janeiro",true));
        aMap.put("cape", new Ville("cape","Cape Town",true));
        aMap.put("camb", new Ville("camb","Cambridge Bay",true));
        aMap.put("reyk", new Ville("reyk","Reykjavik",true));
        aMap.put("edin", new Ville("edin","Edinburg",true));
        aMap.put("mexi", new Ville("mexi","Mexico",false));
        aMap.put("mars", new Ville("mars","Marseille",true));
        aMap.put("hamb", new Ville("hamb","Hamburg",true));
        aMap.put("atbi", new Ville("atbi","Athina",true));
        aMap.put("murm", new Ville("murm","Murmansk",true));
        aMap.put("mosk", new Ville("mosk","Moskva",false));
        aMap.put("novo", new Ville("novo","Novosibirsk",false));
        aMap.put("tehr", new Ville("tehr","Tehran",false));
        aMap.put("alqa", new Ville("alqa","Al Qahira",true));
        aMap.put("lago", new Ville("lago","Lagos",true));
        aMap.put("djib", new Ville("djib","Djibouti",false));
        aMap.put("dar", new Ville("dar","Dar Es Salaam",true));
        aMap.put("luan", new Ville("luan","Luanda",true));
        aMap.put("cape", new Ville("cape","Cape Town",true));
        aMap.put("toam", new Ville("toam","Toamasina",true));
        aMap.put("tiks", new Ville("tiks","Tiksi",true));
        aMap.put("yaku", new Ville("yaku","Yakutsk",false));
        aMap.put("petr", new Ville("petr","Petropavlovsk",true));
        aMap.put("beij", new Ville("beij","Beijing",false));
        aMap.put("hong", new Ville("hong","Hong Kong",true));
        aMap.put("labo", new Ville("labo","Lahore",false));
        aMap.put("mumb", new Ville("mumb","Mumbai",true));
        aMap.put("bang", new Ville("bang","Bangkok",true));
        aMap.put("anch", new Ville("anch","Anchorage",true));
        aMap.put("toky", new Ville("toky","Tokyo",true));
        aMap.put("mani", new Ville("mani","Manila",true));
        aMap.put("hono", new Ville("hono","Honolulu",true));
        aMap.put("jaka", new Ville("jaka","Jakarta",true));
        aMap.put("darw", new Ville("darw","Darwin",true));
        aMap.put("port", new Ville("port","Port Moresby",true));
        aMap.put("pert", new Ville("pert","Perth",true));
        aMap.put("sydn", new Ville("sydn","Sydney",true));
        aMap.put("chri", new Ville("chri","Christchurch",true));
        aMap.put("casa", new Ville("casa","Casablanca",true));
        aMap.put("paf", new Ville("paf","Port Au Français",false));

        return aMap;
    }

    /**
     * @return cities
     * key : abreviation de la ville
     * value : Ville
     */
    public static Map<String, String> initCitiesMap(){
        Map<String, String> aMap = new HashMap<>();
        aMap.put("vanc", "Vancouver");
        aMap.put("losa", "Los Angeles");
        aMap.put("winn", "Winnipeg");
        aMap.put("newy", "New York");
        aMap.put("miam", "Miami");
        aMap.put("cara", "Caracas");
        aMap.put("lima", "Lima");
        aMap.put("valp", "Valparaiso");
        aMap.put("buen", "Buenos Aires");
        aMap.put("rio", "Rio De Janeiro");
        aMap.put("cape", "Cap Town");
        aMap.put("camb", "Cambridge Bay");
        aMap.put("reyk", "Reykjavik");
        aMap.put("edin", "Edinburg");
        aMap.put("mexi", "Mexico");
        aMap.put("mars", "Marseille");
        aMap.put("hamb", "Hamburg");
        aMap.put("atbi", "Athina");
        aMap.put("murm", "Murmansk");
        aMap.put("mosk", "Moskva");
        aMap.put("novo", "Novosibirsk");
        aMap.put("tehr", "Tehran");
        aMap.put("alqa", "Al Qahira");
        aMap.put("lago", "Lagos");
        aMap.put("djib", "Djibouti");
        aMap.put("dar", "Dar Es Salaam");
        aMap.put("luan", "Luanda");
        aMap.put("cape", "Cape Town");
        aMap.put("toam", "Toamasina");
        aMap.put("tiks", "Tiksi");
        aMap.put("yaku", "Yakutsk");
        aMap.put("petr", "Petropavlovsk");
        aMap.put("beij", "Beijing");
        aMap.put("hong", "Hong Kong");
        aMap.put("labo", "Lahore");
        aMap.put("mumb", "Mumbai");
        aMap.put("bang", "Bangkok");
        aMap.put("anch", "Anchorage");
        aMap.put("toky", "Tokyo");
        aMap.put("mani", "Manila");
        aMap.put("hono", "Honolulu");
        aMap.put("jaka", "Jakarta");
        aMap.put("darw", "Darwin");
        aMap.put("port", "Port Moresby");
        aMap.put("pert", "Perth");
        aMap.put("sydn", "Sydney");
        aMap.put("chri", "Christchurch");
        aMap.put("casa", "Casablanca");
        aMap.put("paf", "Port Au Français");

        return aMap;
    }
}
