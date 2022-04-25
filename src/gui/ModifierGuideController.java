/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.GuideModelController;
import Entities.Guide;
import Entities.Jeux;
import Entities.UserM;
import Services.GuideService;
import Services.UserService;
import Services.jeux_service;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.text.DateFormatter;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierGuideController implements Initializable {

    @FXML
    private Label addGuideTitle;
    @FXML
    private Label NGL;
    @FXML
    private Label DescrpGL;
    @FXML
    private Label nbrhL;
    @FXML
    private Label DateLab;
    @FXML
    private Label GameL;
    @FXML
    private Label coachNameLabel;
    @FXML
    private TextField nomGuide;
    @FXML
    private TextField DescrpGTm;

    @FXML
    private Button btnmodifier;
    @FXML
    private Label prixlb;
    @FXML
    private TextField PrixGuide;
    @FXML
    private TextField gamenom;
    @FXML
    private TextField coachnom;
  
    @FXML
    private TextField HeureGT;
    /**
     * Initializes the controller class.
     */
    int id = GuideModelController.idG;
    GuideService gs = new GuideService();
    Guide CurrentGuide = gs.afficherGuideBuyId(id);
    jeux_service js = new jeux_service();
    UserService us = new UserService();
    // ObservableList<Jeux> jeuxx = js.afficher();
    @FXML
    private TextField DATE;
    @FXML
    private ChoiceBox  gameliste ;

    ObservableList<String> ListJeux = FXCollections.observableArrayList();
    String[] jeuxx;
    @FXML
    private ChoiceBox coachListe;
      int idJeux ; 
      int idUser;
         String CoachName;
    String GameName ;
    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomGuide.setText(CurrentGuide.getTitre());
        DescrpGTm.setText(CurrentGuide.getDescrp());
        HeureGT.setText(String.valueOf(CurrentGuide.getNbHeure()));
        coachnom.setText(CurrentGuide.getCoach());
        gamenom.setText(CurrentGuide.getJeux());
        PrixGuide.setText(String.valueOf(CurrentGuide.getPrice()));
        DATE.setText(CurrentGuide.getDateCreation());
      //  ListJeux .add(js.afficherTitre());
//        for (int i=0 ; i <=  js.afficher().size()  ;i++)
//        {  
//            jeuxx [i]= js.afficher().get(i).getNomJeux();
//        }
//       
        for (Jeux jeu : js.afficher()) {
        gameliste.getItems().add(jeu.getNomJeux());
         }
        for (UserM userr : us.afficher()) {
        coachListe.getItems().add(userr.getNom());
         }
        
                   gameliste.setOnAction((event) -> {
            int selectedIndex = gameliste.getSelectionModel().getSelectedIndex();
            Object selectedItem = gameliste.getSelectionModel().getSelectedItem();
            
            System.out.println("ffffs"+selectedIndex); 
            Jeux j = new Jeux();
            
         //    j  = (Jeux) gameliste.getValue();
//            j.getId();
            GameName = gameliste.getValue().toString();
            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + gameliste.getValue());
                   System.out.println("gui.AjouterGuideController.initialize()"+idJeux+gameliste.getValue());

        });
                   
                       CoachName = us.afficher().get(1).getNom();
         coachListe.setOnAction((event) -> {
            int selectedIndex = coachListe.getSelectionModel().getSelectedIndex();
            Object selectedItem = coachListe.getSelectionModel().getSelectedItem();
            
            System.out.println("ffffs"+selectedIndex); 
            Jeux j = new Jeux();
            
         //    j  = (Jeux) gameliste.getValue();
//            j.getId();
            CoachName = coachListe.getValue().toString();
            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + coachListe.getValue());
                   System.out.println("gui.AjouterGuideController.initialize()"+idUser+coachListe.getValue());

        });
                   
                       
//            js.afficherTitre();
//            gameliste.setItems(FXCollections.observableArrayList(js.afficherTitre()));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//    LocalDate localDate = LocalDate.parse(CurrentGuide.getDateCreation(), formatter);
//
//         dateGuide.setValue(localDate);
       // dateGuide.set(CurrentGuide.getDateCreation());

    }


    @FXML
    private void modifierGuideBtn(ActionEvent event) throws IOException {

        String titreGuide = nomGuide.getText();
        String Descrp = DescrpGTm.getText();
        int nbrH = Integer.parseInt(HeureGT.getText());
        String CoachName = coachnom.getText();
        String GameName = gamenom.getText();
        int Prix = Integer.parseInt(PrixGuide.getText());
       String date = DATE.getText();
//        LocalDate dateguide = dateGuide.getValue();
//        dateGuide.setValue(dateguide);

        CurrentGuide.setTitre(titreGuide);
        CurrentGuide.setDescrp(Descrp);
        CurrentGuide.setNbHeure(nbrH);
        CurrentGuide.setCoach(titreGuide);
        CurrentGuide.setJeux(titreGuide);
        CurrentGuide.setPrice(Prix);

        //String date = dateGuide.getValue().format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("yyyy-MM-dd"));
        CurrentGuide.setDateCreation(date);
        
        System.out.println("cuurentt guide" + CurrentGuide);
        gs.modifier(CurrentGuide, id);

        System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void cancelModif(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

}
