/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Guide;
import Entities.Jeux;
import Entities.Reservation;
import Entities.UserM;
import Services.GuideService;
import Services.ServiceReservation;
import Services.UserService;
import Services.jeux_service;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AdminBackController implements Initializable {

    @FXML
    private ImageView refreshImgV;
    @FXML
    private HBox VBOXRESERVATION;
    @FXML
    private Label fenetreTitle;
    @FXML
    private TableView<Guide> GuideTab;
    @FXML
    private TableColumn<Guide, Integer> id;
    @FXML
    private TableColumn<Guide, String> titre;
    @FXML
    private TableColumn<Guide, String> descrp;
    @FXML
    private TableColumn<Guide, Jeux> jeux_id;
    @FXML
    private TableColumn<Guide, UserM> coach_id;
    @FXML
    private TableColumn<Guide, Integer> nb_heure;
    @FXML
    private TableColumn<Guide, String> date_creation;
    @FXML
    private TableColumn<Guide, Integer> prix;
    @FXML
    private TableColumn<Guide, String> action;
    @FXML
        private TableView<Reservation> ResTab;
    Guide guide=null;
     String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
  ObservableList<Guide> guides = FXCollections.observableArrayList();
  ObservableList<Reservation> reservations = FXCollections.observableArrayList();

    jeux_service js = new jeux_service();
    UserService us = new UserService();
    UserM u =us.afficher().get(2);
      Jeux j ;
    @FXML
    private Pane AffichePane;
    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, Guide> colGuide;
    @FXML
    private TableColumn<Reservation, String> coldate;
    @FXML
    private TableColumn<Reservation, String> colHeure;
    @FXML
    private TableColumn<Reservation, UserM> colCoach;
    @FXML
    private TableColumn<Reservation, UserM> colPalyer;
    @FXML
    private TableColumn<Reservation, String> SuppIcon;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox valocheckBox;
    @FXML
    private CheckBox lolCheckBox;
    @FXML
    private ImageView returnBtn;
    @FXML
    private ImageView logo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            AffichePane.getChildren().clear();

    }

    @FXML
    private void AfficherGuideTab(ActionEvent event) {
      
fenetreTitle.setText("Liste Guide");
       // GuideService ps = new GuideService();
        //guides.addAll(ps.afficherGuide());
        //System.out.println("gui.AdminBackController.AfficherGuideTab()"+ps.afficherGuide());
        id.setCellValueFactory(new PropertyValueFactory<Guide, Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Guide, String>("titre"));
        descrp.setCellValueFactory(new PropertyValueFactory<Guide, String>("Descrp"));
        nb_heure.setCellValueFactory(new PropertyValueFactory<Guide, Integer>("NbHeure"));
        date_creation.setCellValueFactory(new PropertyValueFactory<Guide, String>("dateCreation"));
        prix.setCellValueFactory(new PropertyValueFactory<Guide, Integer>("price"));
        coach_id.setCellValueFactory(new PropertyValueFactory<Guide, UserM>("user"));
        jeux_id.setCellValueFactory(new PropertyValueFactory<Guide, Jeux>("jeu"));
        Refresh(); 

    //add cell of button edit 
        Callback<TableColumn<Guide, String>, TableCell<Guide, String>> cellFoctory = (TableColumn<Guide, String> param) -> {
            // make cell containing buttons
            final TableCell<Guide, String> cell = new TableCell<Guide, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView treatIcon = new FontAwesomeIconView(FontAwesomeIcon.ARCHIVE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        treatIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#black;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText("Voulez vous vraiment supprimer cette demande ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                GuideService gs = new GuideService();
                                gs.supprimerGuide(GuideTab.getSelectionModel().getSelectedItem().getId());
                                Refresh();
                            }

                        });
//                       
//                        editIcon.setOnMouseClicked((MouseEvent event) -> {
//
//                            try {
//                                GuideService gs = new GuideService();
//                                
//                                // reclamation = tableRec.getSelectionModel().getSelectedItem();
//                               
//                                
//                                Parent modifierCours = FXMLLoader.load(getClass().getResource("../gui/ModifierGuide.fxml"));
//                                Scene modifierCours_scene = new Scene(modifierCours);
//                                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                                app_stage.setScene(modifierCours_scene);
//                                app_stage.show();   
//                            
//                            } catch (IOException ex) {
//                                Logger.getLogger(AdminBackController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        });

                        HBox managebtn = new HBox( deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 2, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        action.setCellFactory(cellFoctory);
        GuideTab.setItems(guides);
        GuideTab.getItems();

    }

    @FXML
    private void AfficherReservationTab(ActionEvent event) {
      
            
          fenetreTitle.setText("Liste Reservations");

//        ServiceReservation res = new ServiceReservation();
        colid.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
        colGuide.setCellValueFactory(new PropertyValueFactory<Reservation, Guide>("guide"));
        coldate.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date_res"));
        colHeure.setCellValueFactory(new PropertyValueFactory<Reservation, String>("heure_debut"));
        colCoach.setCellValueFactory(new PropertyValueFactory<Reservation, UserM>("coach"));
        colPalyer.setCellValueFactory(new PropertyValueFactory<Reservation, UserM>("player"));
     
        RefreshRes();
        //add cell of button edit 
        Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>> cellFoctory = (TableColumn<Reservation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reservation, String> cell = new TableCell<Reservation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView treatIcon = new FontAwesomeIconView(FontAwesomeIcon.ARCHIVE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        treatIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#black;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText("Voulez vous vraiment supprimer cette Reservation ?");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                               ServiceReservation gs = new ServiceReservation();
                                gs.supprimer(ResTab.getSelectionModel().getSelectedItem().getId());
                                RefreshRes();
                            }

                        });
//                       
//                        editIcon.setOnMouseClicked((MouseEvent event) -> {
//
//                            try {
//                                GuideService gs = new GuideService();
//                                
//                                // reclamation = tableRec.getSelectionModel().getSelectedItem();
//                               
//                                
//                                Parent modifierCours = FXMLLoader.load(getClass().getResource("../gui/ModifierGuide.fxml"));
//                                Scene modifierCours_scene = new Scene(modifierCours);
//                                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                                app_stage.setScene(modifierCours_scene);
//                                app_stage.show();   
//                            
//                            } catch (IOException ex) {
//                                Logger.getLogger(AdminBackController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        });

                        HBox managebtn = new HBox( deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 2, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        SuppIcon.setCellFactory(cellFoctory);
        ResTab.setItems(reservations);
        ResTab.getItems();
       
    }

    @FXML
    private void ClearAll(MouseEvent event) {
        AffichePane.getChildren().clear();
    }

    @FXML
    private void AfficherReservation(MouseEvent event) {
    }

   private void Refresh() {
        try {
            
           
            guides.clear();
                    AffichePane.getChildren().clear();

            AffichePane.getChildren().add(GuideTab);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalclutch", "root", "");

            query = "SELECT * FROM service_guide";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id=resultSet.getInt("jeux_id");
                System.out.println(id);
                Jeux j = js.afficherJeuxBuyId(id);
                System.out.println("gui.AdminBackController.Refresh()"+ j.getNomJeux());
                guides.add(new Guide(resultSet.getInt("id"), resultSet.getString("titre"),
                        resultSet.getString("descrp"), resultSet.getInt("nb_heure"),
                        resultSet.getString("date_creation"), resultSet.getInt("prix"),j,u));
                GuideTab.setItems(guides);

            }

           

        } catch (SQLException ex) {
            System.out.println("gui.AdminBackController.Refresh()"+ex.getMessage());
        }
    }
private void RefreshRes() {
        try {
            reservations.clear();
                    AffichePane.getChildren().clear();

            AffichePane.getChildren().add(ResTab);

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finalclutch", "root", "");

            query = "SELECT * FROM reservation";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reservations.add(new Reservation(resultSet.getInt("id"),new Guide(),resultSet.getString("date_res"),
                        resultSet.getString("heure_debut"),u,u));
                System.out.println("refresh res"+reservations);
                ResTab.setItems(reservations);

            }

           

        } catch (SQLException ex) {
            System.out.println("gui.AdminBackController.Refresh()"+ex.getMessage());
        }
    }

    @FXML
    private void filtreTitre(ActionEvent event) {
        
    }

    @FXML
    private void filtrerValo(ActionEvent event) {
    }

    @FXML
    private void filterLol(ActionEvent event) {
    }

    @FXML
    private void ReturnHome(MouseEvent event) throws IOException {
          System.out.println("You clicked me!");
        Parent modifierCours = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }

    @FXML
    private void ReturnHomme(MouseEvent event) {
        try {
            System.out.println("You clicked mehommmee!");
            Parent modifierCours = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene modifierCours_scene = new Scene(modifierCours);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(modifierCours_scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
