/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Controller.GuideModelController;
import Entities.Guide;
import Entities.Jeux;
import Entities.Reservation;
import Entities.User;
import Entities.UserM;
import Services.GuideService;
import Services.ServiceReservation;
import Services.UserService;
import Services.jeux_service;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXDatePicker dateReservation;
    private Guide guide, g;
    private User coach, player;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterReservation(ActionEvent event) throws SQLException {
        int id = GuideModelController.idG;

        String heure = String.valueOf(heureDebut.getValue());
        String date = String.valueOf(dateReservation.getValue());

        Reservation A = new Reservation(date, heure);
        UserService us = new UserService();
        UserM uss = us.afficher().get(2);
        GuideService ps = new GuideService();
        Guide g = ps.afficherGuideBuyId(id);
        jeux_service js = new jeux_service();

        ServiceReservation RES = new ServiceReservation();

        if (date.isEmpty() || heure.isEmpty()) {   //controles de saisie non vide 

            JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir vos champs");

        } else if (date.isEmpty() && heure.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vous devez Remplir tout les champs");
        } else {

            boolean existe = true;
            int i = 0;
            while (i < RES.afficher().size()) {
                if (RES.afficher().get(i).getHeure_debut()== null ? heure == null : RES.afficher().get(i).getHeure_debut().equals(heure)) {
                    JOptionPane.showMessageDialog(null, "Ce guide est deja Reservé  dans ce temps et date ");
                    existe = true;
                    break;
                } else {
                    existe = false;
                }
                i++;
            }
            if (!existe) {
                try {
                    RES.AjouterResObject(A, g, uss, uss);

                    System.out.println("bien ajouter");

                 
                    Parent addGuide = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
                    Scene addguidescene = new Scene(addGuide);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(addguidescene);
                    stage.show();
                    
                             // twillio api sms api 
                  String accountSID = "ACfb9c0af8637bfc40f8fafd707b9ab606";
                    String authToken = "29df62b3aad5981aa168b37cdaab98f8";
                    String fromPhone = "+13853401562";
                    Twilio.init(accountSID, authToken);
                    
                    Message message = Message
                            .creator(new PhoneNumber("+21655821563"), // to
                                    new PhoneNumber(fromPhone), // from
                           "Dear Player " + uss.getNom() + " clutch.gg team wanna thank you for adding new reservation of: " +g.getTitre() +"on "+A.getDate_res()+"At "+A.getHeure_debut())
                            .create();
                    System.out.println(message.getSid());
                    
                } catch (IOException ex) {
                    Logger.getLogger(AjouterGuideController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
