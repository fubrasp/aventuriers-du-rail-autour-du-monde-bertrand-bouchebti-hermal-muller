package outil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import modeles.Jeu;

import java.io.File;
import java.io.IOException;

/**
 * Created by Flo on 15/05/2017.
 */

public class ChargerPartie {
    public static File file = new File ("C:/Users/Flo/testSave/user.txt");
    public static File fileSave = new File ("C:/Users/Flo/testSave/save.txt");

    public static void sauvegarderPartie(Jeu jeu, File fileSelected){
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Jeu.class, new JeuSerializer());
        mapper.registerModule(module); // Add custom serializer

        if(fileSelected != null){
            try {
                mapper.writeValue(fileSelected, jeu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Jeu chargerPartie(File fileSelected){
        try {
            ObjectMapper mapper = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addDeserializer(Jeu.class,new JeuDeserializer());
            mapper.registerModule(module); // Add custom serializer

            if(fileSelected != null){
                return mapper.readValue(fileSelected, Jeu.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
