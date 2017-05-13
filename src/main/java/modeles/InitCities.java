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
        aMap.put("vanc", new Ville("Vancouver",true));
        aMap.put("losa", new Ville("Los Angeles",true));
        aMap.put("winn", new Ville("Winnipeg",false));
        aMap.put("newy", new Ville("New York",true));
        aMap.put("miam", new Ville("Miami",true));
        aMap.put("cara", new Ville("Caracas",true));
        aMap.put("lima", new Ville("Lima",true));
        aMap.put("valp", new Ville("Valparaiso",true));
        aMap.put("buen", new Ville("Buenos Aires",true));
        aMap.put("rio", new Ville("Rio de Janeiro",true));
        aMap.put("cape", new Ville("Cape Town",true));
        aMap.put("camb", new Ville("Cambridge Bay",true));
        aMap.put("reyk", new Ville("Reykjavik",true));
        aMap.put("edin", new Ville("Edinburg",true));
        aMap.put("mexi", new Ville("Mexico",false));
        aMap.put("mars", new Ville("Marseille",true));
        aMap.put("hamb", new Ville("Hamburg",true));
        aMap.put("atbi", new Ville("Athina",true));
        aMap.put("murm", new Ville("Murmansk",true));
        aMap.put("mosk", new Ville("Moskva",false));
        aMap.put("novo", new Ville("Novosibirsk",false));
        aMap.put("tehr", new Ville("Tehran",false));
        aMap.put("alqa", new Ville("Al Qahira",true));
        aMap.put("lago", new Ville("Lagos",true));
        aMap.put("djib", new Ville("Djibouti",false));
        aMap.put("dar", new Ville("Dar Es Salaam",true));
        aMap.put("luan", new Ville("Luanda",true));
        aMap.put("cape", new Ville("Cape Town",true));
        aMap.put("toam", new Ville("Toamasina",true));
        aMap.put("tiks", new Ville("Tiksi",true));
        aMap.put("yaku", new Ville("Yakutsk",false));
        aMap.put("petr", new Ville("Petropavlovsk",true));
        aMap.put("beij", new Ville("Beijing",false));
        aMap.put("hong", new Ville("Hong Kong",true));
        aMap.put("labo", new Ville("Lahore",false));
        aMap.put("mumb", new Ville("Mumbai",true));
        aMap.put("bang", new Ville("Bangkok",true));
        aMap.put("anch", new Ville("Anchorage",true));
        aMap.put("toky", new Ville("Tokyo",true));
        aMap.put("mani", new Ville("Manila",true));
        aMap.put("hono", new Ville("Honolulu",true));
        aMap.put("jaka", new Ville("Jakarta",true));
        aMap.put("darw", new Ville("Darwin",true));
        aMap.put("port", new Ville("Port Moresby",true));
        aMap.put("pert", new Ville("Perth",true));
        aMap.put("sydn", new Ville("Sydney",true));
        aMap.put("chri", new Ville("Christchurch",true));
        aMap.put("casa", new Ville("Casablanca",true));
        aMap.put("paf", new Ville("Port Au Français",false));

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
