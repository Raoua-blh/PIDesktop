/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Guide;
import Entities.Reservation;
import Services.GuideService;
import Services.ServiceReservation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReservationModelController implements Initializable {

    @FXML
    private AnchorPane reserPane;
    @FXML
    private VBox ReservationCard;
    @FXML
    private ImageView ResImg;
    @FXML
    private Label Reservation;
    @FXML
    private ImageView deleteIconRes;
    @FXML
    private Label titreResGuide;
    @FXML
    private Label dateReser;
    @FXML
    private Label dateResLabel;
    @FXML
    private Label heure;
    @FXML
    private Label HeureDeLabel;
    @FXML
    private Label coach;
    @FXML
    private Label coachResLabel;
    private Reservation res;
    MyListener myListener;
    private ArrayList<Reservation> reservaations = new ArrayList<>();

    public void setResData(Reservation reservat, MyListener mylistener) {
        this.res = reservat;
        this.myListener = myListener;
        titreResGuide.setText(reservat.getGuide().getTitre());
        dateReser.setText(reservat.getDate_res());
        HeureDeLabel.setText((reservat.getHeure_debut()));
        coachResLabel.setText(reservat.getCoach().getNom());
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
    private void DeleteReservation(MouseEvent event) {
     System.out.println("delete pressed and this is the id of the card" + res.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Voulez vous vraiment supprimer cette Reservation ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceReservation gs = new ServiceReservation();
            gs.supprimer(res.getId());
//            getData();
        }
                System.out.println("deleted Succefully " + res.getId());

    }

    @FXML
    private void modifierRes(ActionEvent event) {
    }
    
    
 private ArrayList<Reservation> getResData() {
         ArrayList<Reservation> reservations = new ArrayList<>();
         Reservation res;
         ServiceReservation ps = new ServiceReservation();
         reservations.addAll(ps.AfficherResObj());
        System.out.println(ps.AfficherResObj().get(2));
       
        return reservations;

        }
}
