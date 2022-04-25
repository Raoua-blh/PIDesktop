/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Guide;
import Entities.UserM;
import Services.GuideService;
import Services.UserService;
import Services.jeux_service;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gui.MyListener;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GuideModelController implements Initializable {

    @FXML
    private Label nameLabel;


    private Guide guide;
    @FXML
    private Label descpL;
    @FXML
    private Label DescrpLabel;

    @FXML
    private Label DurationLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label priceLabelnew;
    @FXML
    private Label coachnameLabel;
    @FXML
    private Label gamenameLabel;

    @FXML
    private VBox cardGuide;

    MyListener myListener;
    private ArrayList<Guide> guides = new ArrayList<>();
    @FXML
    private Label descpL1;
    @FXML
    private Label descpL11;
    @FXML
    private Label descpL121;
    public static int idG;
    @FXML
    private ImageView img;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private Label descpL12;
    @FXML
    private Label descpL1211;
    @FXML
    private Button btnReserver;
    UserService us = new UserService();
    UserM u = us.afficher().get(3);
    jeux_service js = new jeux_service();
        GuideService ps = new GuideService();

    
        String jeunom = ps.afficherGuide().get(idG).getJeu().getNomJeux();
       String coachnom = ps.afficherGuide().get(idG).getUser().getNom();

    public void setData(Guide guide, MyListener mylistener) {
        this.guide = guide;
        this.myListener = myListener;
        nameLabel.setText(guide.getTitre());
        DescrpLabel.setText(guide.getDescrp());
        DurationLabel.setText(String.valueOf(guide.getNbHeure()));
        DateLabel.setText(guide.getDateCreation());
        String Prix = String.valueOf(guide.getPrice());
        System.out.println(Prix);
        priceLabelnew.setText(Prix);

        coachnameLabel.setText(coachnom);
        gamenameLabel.setText(jeunom);
//        Image image = new Image(getClass().getResourceAsStream(guide.getJeuxImage()));
//        img.setImage(image);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void Reserver(ActionEvent event) {
        System.out.println("Reserver  CLICKEDD"+guide.getId());
        idG=guide.getId();
               try {
            System.out.println("You clicked me!");
            Parent reservation = FXMLLoader.load(getClass().getResource("../gui/AjouterReservation.fxml"));
            Scene scene1 = new Scene(reservation);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene1); 
            app_stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage()); 
        }
    }

    @FXML
    private void DeleteGuide(MouseEvent event) {

        System.out.println("delete pressed and this is the id of the card" + guide.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Voulez vous vraiment supprimer ce Service ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            GuideService gs = new GuideService();
            gs.supprimerGuide(guide.getId());
//            getData();
        }
                System.out.println("deleted Succefully " + guide.getId());

    }

    private ArrayList<Guide> getData() {
        ArrayList<Guide> guides = new ArrayList<>();
        Guide guide;
        //ObservableList<Guide> guides = FXCollections.observableArrayList();
        GuideService ps = new GuideService();
        ps.afficherGuide().get(idG).getJeu().getNomJeux();
        ps.afficherGuide().get(idG).getUser().getNom();
        guides.addAll(ps.afficherGuide());
       // System.out.println(ps.afficherGuide().get(2));

        return guides;

    }

    @FXML
    private void ModifierGuide(ActionEvent event) {
              System.out.println("Modifier CLICKEDD"+guide.getId());
        idG=guide.getId();
               try {
            System.out.println("You clicked me!");
            Parent modifier = FXMLLoader.load(getClass().getResource("../gui/ModifierGuide.fxml"));
            System.out.println("after parent!");
            Scene scene1 = new Scene(modifier);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene1); 
            app_stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage()); 
        }
    }


}
