import modeles.GestionnairePlateau;
import modeles.InitCities;
import modeles.Ville;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitializationPlateauTest  {
    List<Ville> cities;
    ArrayList<Element> list = new ArrayList<>();
    final static int CITIES_NUMBER = 48;
    final static int ROADS_NUMBER = 130;

    @BeforeEach
    public void executedBeforeEach() {
        cities = Collections.emptyList();
        list = GestionnairePlateau.parsePlateau();
    }

    @Test
    public void citiesInitialization(){
        int numberOfCity = InitCities.initCities().size();
        assertEquals(numberOfCity,CITIES_NUMBER);
    }

    @Test
    public void roadsInitialisation(){
        int numberOfRoads = GestionnairePlateau.initRoads(list).size();
        assertEquals(numberOfRoads,ROADS_NUMBER);
    }


}
