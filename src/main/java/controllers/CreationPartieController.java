package controllers;

import application.MainApp;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import ecrancreationpartie.liste_joueur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modeles.Jeu;
import modeles.Joueur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreationPartieController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        combo_remplissage();// fonction qui ajouter les couleurs dans -> combobox

        if(MainApp.nbr_joueur<2)
            Start.setDisable(true);
        else
            Start.setDisable(false);
    }

    @FXML
    private JFXTextField username;
    @FXML
    private JFXButton login;

    @FXML
    private JFXComboBox combo = new JFXComboBox() ;

    @FXML
    private JFXButton Start;

    @FXML
    private JFXButton sign;

    @FXML
    private AnchorPane root = new AnchorPane() ;

    public  void LoadMenu(ActionEvent event)
    {

        Parent pere = null;
        try {
            pere = FXMLLoader.load(MainApp.class.getResource("/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scene welcome= new Scene(pere);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(new Scene(pere, 520, 332));
        app_stage.show();

    }

    @FXML
    void makelogin(ActionEvent event) {

        String uname=username.getText();
        String couleur;
        boolean indice = verify_name(uname);
        try
        {

            couleur=combo.getValue().toString();
            System.out.print("COULEUR :"+couleur);
            boolean indice2=verify_color(couleur);
            System.out.println("BOOLEEN COULEUR : " + indice);

            if(indice==true)// verification si le nom joueur existe deja !
            {
                if(indice2==true)// verification de la couleur
                {
                    MainApp.nbr_joueur++;
                    Joueur j= new Joueur(uname,couleur);
                    liste_joueur.list_joueur.add(j);

                    if(MainApp.nbr_joueur<5)// il faut 2 joueurs min
                    {
                        LoadMenu(event);
                    }
                }
                else// la couleur n'est pas correcte
                {
                    alert_function("Changer la couleur SVP");
                }

            }
            else
            {
                alert_function("Nom existe déja ou erroné, merci de choisir un autre");
                username.setText("");
            }

        }
        catch(Exception e)
        {
            alert_function("Choisir une couleur SVP");
        }




    }

    @FXML
    void start(ActionEvent event) {

        ArrayList<Joueur> joueursChoisisPourLaPartieLocale = liste_joueur.list_joueur;
        Jeu.getInstance().setJoueurs(joueursChoisisPourLaPartieLocale);
        Jeu.getInstance().setJoueurCourant(liste_joueur.list_joueur.get(0));

        Parent pere = null;
        try {
            //pere = FXMLLoader.load(MainApp.class.getResource("/sample.fxml"));

            pere = FXMLLoader.load(MainApp.class.getResource(MainApp.CHEMIN_VUE_JEU));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scene welcome= new Scene(pere);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(new Scene(pere, 1200, 800));
        app_stage.show();

    }

    public boolean verify_name(String name)
    {
        boolean indice= true;

        if(name.contains(" ") || name.equalsIgnoreCase("")) // Eviter les noms vides et les espaces
            return false;

        for(int i=0;i<liste_joueur.list_joueur.size();i++)
        {
            String nom=liste_joueur.list_joueur.get(i).getPseudo().toString();
            if(nom.equalsIgnoreCase(name))
            {
                indice=false;
                return indice;
            }
        }
        return indice;
    }
    public void alert_function(String s)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(s);

        alert.showAndWait();

    }

    public void combo_remplissage()
    {

       combo.getItems().add("green");
       combo.getItems().add("red");
       combo.getItems().add("blue");
       combo.getItems().add("yellow");
       combo.getItems().add("purple");

    }
    public boolean verify_color(String col)
    {
        boolean indice= true;

        for(int i=0;i<liste_joueur.list_joueur.size();i++)
        {
            String couleur=liste_joueur.list_joueur.get(i).getCouleur().toString();
            if(couleur.equalsIgnoreCase(col))
            {
                indice=false;
                return indice;
            }
        }
        return indice;
    }

}





