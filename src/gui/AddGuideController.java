/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Guide;
import Entities.Jeux;
import Services.GuideService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class AddGuideController implements Initializable {

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
    private TextField GnomTF;
    @FXML
    private TextField DescrpGTF;
    @FXML
    private TextField dateGTF;
    @FXML
    private TextField NbhTF;
    @FXML
    private ChoiceBox<Jeux> GameGuiCB;
    @FXML
    private Hyperlink CoachNameLink;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label prixlb;
    @FXML
    private TextField PrixTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterGuideBtn(ActionEvent event) throws IOException {
        String titreGuide = GnomTF.getText() ;
        String Descrp =DescrpGTF.getText();
        int nbrH = Integer.parseInt(NbhTF.getText());
        String CoachName = coachNameLabel.getText(); 
        String GameName =GameGuiCB.getSelectionModel().toString();
         int  Prix =Integer.parseInt(PrixTF.getText());

         
         
         String dateguide =java.time.LocalDate.now().toString();
        
   
        Guide A = new Guide(titreGuide,Descrp,nbrH,dateguide,Prix ,GameName,CoachName); 
        GuideService B = new GuideService(); 
        
        if (titreGuide.isEmpty()||Descrp.isEmpty()){   //controles de saisie non vide 
            
             JOptionPane.showMessageDialog(null, "Vous avez oublié de remplir vos champs");
            
            
        }
        
        else {
        
        
         boolean existe=true;
            int i =0;
           while (i<B.afficherGuide().size()){
               if (B.afficherGuide().get(i).getTitre()== null ? titreGuide == null : B.afficherGuide().get(i).getTitre().equals(titreGuide)){
                    JOptionPane.showMessageDialog(null, "Ce titre deja existe");
                    existe=true;
                   break;
               }
               else 
                   existe=false;
                   i++;
        
        
           }
        
         if (!existe){
        B.ajouterGuide(A);
       
        
        
        System.out.println("bien ajouter");
        
             Parent addGuide = FXMLLoader.load(getClass().getResource("GestionGuide.fxml"));
        Scene addguidescene = new Scene(addGuide);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(addguidescene);
        stage.show(); 
         }
    }
    }
    
    
}
