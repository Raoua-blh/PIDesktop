/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Reservation;
import Services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane btnn;
    @FXML
    private Button btnReservation;
    @FXML
    private Button btnReservation1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void AdminBack(ActionEvent event) {
         try {
            System.out.println("You clicked me!");
            Parent helllo = FXMLLoader.load(getClass().getResource("AdminBack.fxml"));
            Scene scene1 = new Scene(helllo);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene1); 
            app_stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage()); 
        }
    }

    @FXML
    private void Player(ActionEvent event) {
         try {
            System.out.println("You clicked me!");
            Parent helllo = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
            Scene scene1 = new Scene(helllo);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene1); 
            app_stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage()); 
        }
    }

    @FXML
    private void Coach(ActionEvent event) {
         try {
            System.out.println("You clicked me!");
            Parent helllo = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
            Scene scene1 = new Scene(helllo);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene1); 
            app_stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage()); 
        }
    }
    
}
