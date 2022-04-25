/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Guide;
import Entities.Jeux;
import Entities.UserM;
import Services.GuideService;
import Services.UserService;
import Services.jeux_service;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterGuideController implements Initializable {

    @FXML
    private Label titreLabel;
    @FXML
    private TextField titleTF;
    @FXML
    private TextField DescrpTF;
    @FXML
    private TextField nbrhTF;
    @FXML
    private TextField gameTF;
    private TextField CoachLab;
    @FXML
    private TextField PrixLab;
    @FXML
    private Label DATELabel;
    @FXML
    private Button ajouterBTn;
    @FXML
    private ChoiceBox GameChoiceBox;

    /**
     * Initializes the controller class.
     */
    jeux_service js = new jeux_service();
    UserService us = new UserService();
    @FXML
    private Hyperlink CoachLink;
    String CoachName;
    String GameName ;
    Jeux jeu  ;
     UserM user ;
     int idJeux ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String dateguide = java.time.LocalDate.now().toString();
        DATELabel.setText(dateguide);
        for (Jeux jeu : js.afficher()) {
            GameChoiceBox.getItems().add(jeu.getNomJeux());
           idJeux = jeu.getId();
            
        }
        System.out.println("gui.AjouterGuideController.initialize()"+idJeux);
        GameChoiceBox.setOnAction((event) -> {
            int selectedIndex = GameChoiceBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = GameChoiceBox.getSelectionModel().getSelectedItem();
            
            System.out.println("ffffs"+selectedIndex); 
            Jeux j = new Jeux();
            
         //    j  = (Jeux) GameChoiceBox.getValue();
//            j.getId();
            GameName = GameChoiceBox.getValue().toString();
            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + GameChoiceBox.getValue());
                   System.out.println("gui.AjouterGuideController.initialize()"+idJeux+GameChoiceBox.getValue());

        });

//          for (UserM uss : us.afficher()) {
//        CoachComboBox.getItems().add(uss.getNom());
//         }
        CoachName = us.afficher().get(1).getNom();
        CoachLink.setText(CoachName);
        user = us.afficher().get(1);
//        
    }

    @FXML
    private void AjoutGuide(ActionEvent event) {
        String titreGuide = titleTF.getText();
        String Descrp = DescrpTF.getText();
        int nbrH = Integer.parseInt(nbrhTF.getText());
        int Prix = Integer.parseInt(PrixLab.getText());
        String dateguide = java.time.LocalDate.now().toString();
        DATELabel.setText(dateguide);
        System.out.println("datelabel" + DATELabel);

        Guide A = new Guide(titreGuide, Descrp, nbrH, dateguide, Prix);
        A.setJeuxId(idJeux);
        GuideService B = new GuideService();

        if (titreGuide.isEmpty() || Descrp.isEmpty() || dateguide.isEmpty() ||  nbrhTF.getText().isEmpty() || PrixLab.getText().isEmpty()) {   //controles de saisie non vide 

            JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir vos champs");

        } else if (titreGuide.isEmpty() && Descrp.isEmpty() && dateguide.isEmpty() && GameName.isEmpty() && CoachName.isEmpty() && nbrhTF.getText().isEmpty() && PrixLab.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vous devez Remplir tout les champs");
        } else {

            boolean existe = true;
            int i = 0;
            while (i < B.afficherGuide().size()) {
                if (B.afficherGuide().get(i).getTitre() == null ? titreGuide == null : B.afficherGuide().get(i).getTitre().equals(titreGuide)) {
                    JOptionPane.showMessageDialog(null, "Ce titre deja existe");
                    existe = true;
                    break;
                } else {
                    existe = false;
                }
                i++;
            }
            if (!existe) {
                try {
                    B.ajouterGuideObj(A,js.afficherJeuxBuyId(idJeux),user);

                    System.out.println("bien ajouter");

// SendGrid Mail api 
                        Email from = new Email("blhyosri@gmail.com");
                    String subject = "Clutch.gg ";
                    Email to = new Email("Leoanas10@gmail.com");
                    Content content = new Content("text/plain", "Hello Dear Player we added a new Guide take a look you may find something you like");
                    Mail mail = new Mail(from, subject, to, content);

                    SendGrid sg = new SendGrid(("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"));
                    
                    Request request = new Request();
                    try {
                        request.setMethod(Method.POST);
                        request.setEndpoint("mail/send");
                        request.setBody(mail.build());
                        Response response = sg.api(request);
                        System.out.println(response.getStatusCode());
                        System.out.println(response.getBody());
                        System.out.println(response.getHeaders());
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                    // end api 
                    Parent addGuide = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
                    Scene addguidescene = new Scene(addGuide);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(addguidescene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AjouterGuideController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
