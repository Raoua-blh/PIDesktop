/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Guide;
import Entities.Reservation;
import Services.GuideService;
import Services.ServiceReservation;
import com.itextpdf.text.DocumentException;
import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import static com.itextpdf.text.pdf.PdfName.rb;
import gui.MyListener;
import gui.PDF;
import gui.ReservationModelController;
import java.awt.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GestionGuideController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid = new GridPane();
    @FXML
    private Button AjouterGuide;
    @FXML
    private VBox GuideBox;
    @FXML
    private ImageView ServiceGuideBox;
    @FXML
    private VBox ReservationBox;
    @FXML
    private HBox VboxGuide;
    @FXML
    private HBox VBOXRESERVATION;
    private MyListener myListener;
    @FXML
    private TextField searchedTXT;
    /**
     * Initializes the controller class.
     */
    int column = 0;
    int row = 0;
    @FXML
    private Button AjouterGuide11;
    @FXML
    private Button Reservationlist;
    @FXML
    private ImageView refreshImgV;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    private ArrayList<Guide> guides = new ArrayList<>();
    private ArrayList<Guide> guideRecher = new ArrayList<>();

    private ArrayList<Reservation> reservations = new ArrayList<>();

    private ArrayList<Guide> getData() {
        ArrayList<Guide> guides = new ArrayList<>();
        Guide guide;
        // ObservableList<Guide> guides = FXCollections.observableArrayList();
        GuideService ps = new GuideService();
        guides.addAll(ps.afficherGuide());
        System.out.println(ps.afficherGuide().get(2));
//        for (int i = 0; i < 20; i++) {
//            guide = new Guide();
//            guide.setTitre("Valorant");
//            guide.setPrice(2);
        //guide.getJeuxImage("/resources/valo.jpg");
////            guide.setColor("6A7324");
//            guidess.add(guide);

        return guides;

    }

    private ArrayList<Guide> getsearchedData() {
        ArrayList<Guide> guideRecher = new ArrayList<>();
        Guide res;
        GuideService ps = new GuideService();
        guideRecher.addAll(ps.recherche(searchedTXT.getText()));
        //    System.out.println(ps.recherche(searchedTXT.getText());

        return guideRecher;

    }

    private ArrayList<Reservation> getResData() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Reservation res;
        ServiceReservation ps = new ServiceReservation();
        reservations.addAll(ps.AfficherResObj());
        System.out.println(ps.AfficherResObj().get(2));

        return reservations;

    }

    @FXML
    private void AjouteGuidee(ActionEvent event) {
        try {
            System.out.println("You clicked me!");
            Parent helllo = FXMLLoader.load(getClass().getResource("../gui/AjouterGuide.fxml"));
            Scene scene1 = new Scene(helllo);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene1);
            app_stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void AfficherGuide(MouseEvent event) {
        System.err.println("clickedd  Reservationn  mousee ");
        grid.getChildren().clear();
        guides.clear();
        guides.addAll(getData());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < guides.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/GuideModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GuideModelController itemController = fxmlLoader.getController();
                itemController.setData(guides.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void AfficherReservation(MouseEvent event) {
        System.out.println("Reservation Box Clicked");
        grid.getChildren().clear();
        
        //  initialize(url, rb);
reservations.clear();
        reservations.addAll(getResData());
        int column = 0;
        int row = 1;

        for (int i = 0; i < reservations.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/ReservationModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ReservationModelController itemController = fxmlLoader.getController();
                itemController.setResData(reservations.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(GestionGuideController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void ListeGuide(MouseEvent event) {
        System.out.println("Liste Guide  Clicked");
        System.err.println("clickedd  Reservationn  mousee ");
                grid.getChildren().clear();
             guides.clear();
        guides.addAll(getData());

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < guides.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/GuideModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GuideModelController itemController = fxmlLoader.getController();
                itemController.setData(guides.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ListeReservation(MouseEvent event) {
        System.out.println("Liste Reservationnn  Clicked");
    }

    public void clear() {
    }

    @FXML
    private void DownloadPDF(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez vous exporter la liste des Reservation en un fichier PDF ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                PDF pdff = new PDF();
                try {
                    pdff.liste_Reservation();
                } catch (DocumentException ex) {
                    System.out.println("Controller.GestionGuideController.DownloadPDF()" + ex.getMessage());
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Controller.GestionGuideController.DownloadPDF()" + ex.getMessage());
            }
        }
    }

    @FXML
    private void RechercheGuide(ActionEvent event) {
        GuideService gs = new GuideService();
        System.out.println("liste des guide qui sont " + searchedTXT.getText() + ":" + gs.recherche(searchedTXT.getText()));
        System.err.println("*******************************************************************");

        for (int i = 0; i < guides.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/GuideModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GuideModelController itemController = fxmlLoader.getController();
                itemController.setData(guides.get(i), myListener);
                if (column == 1) {
                    column = 0;
                    row++;

                }

                grid.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
                // setGrid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                // setGrid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void ListeReservation(ActionEvent event) {
    }

    @FXML
    private void ClearAll(MouseEvent event) {
        grid.getChildren().clear();
        //            System.out.println("You clicked me!");
//            Parent modifier = FXMLLoader.load(getClass().getResource("../gui/GestionGuide.fxml"));
        System.out.println("after parent!");
//            Scene scene1 = new Scene(modifier);
//            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            app_stage.setScene(scene1); 
//            app_stage.show();
    }

    @FXML
    private void searchedAvance(KeyEvent event) {
        System.out.println("test");
        grid.getChildren().clear();

        GuideService gs = new GuideService();
        java.util.List<Guide> mylist = new ArrayList<>();

        mylist = gs.recherche(searchedTXT.getText());

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < mylist.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../gui/GuideModel.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GuideModelController itemController = fxmlLoader.getController();
                itemController.setData(mylist.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
