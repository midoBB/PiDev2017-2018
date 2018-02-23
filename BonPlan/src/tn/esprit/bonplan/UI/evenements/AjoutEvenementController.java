/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.evenements;

import java.io.File;
import tn.esprit.bonplan.UI.evenements.ListeEvenementController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.bonplan.API.sendSMS;
import tn.esprit.bonplan.entities.Evenement;
import tn.esprit.bonplan.services.EvenementService;
import tn.esprit.bonplan.services.UserService;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class AjoutEvenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField etab;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    @FXML
    private TextField decs;
    @FXML
    private Button image;
    
    private File f;
    SimpleObjectProperty<File> SelectedFileProperty = new SimpleObjectProperty<>();
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          if("a".equals(nom.getText())){
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le type doit etre inséré");
            alert.show();
        }
    }    
    @FXML
    private void AjouterEvenement(ActionEvent event) throws Exception {
         Evenement v =new Evenement(nom.getText(),etab.getText(),debut.getValue().toString(),fin.getValue().toString(),decs.getText(),SelectedFileProperty.get().getName());
                
                EvenementService sv=new EvenementService();
                sv.insererEvenement(v);
                RemoteFileHandler.upload(SelectedFileProperty.get());
                
             sendSMS.sendSms(UserService.selectUser(), v);
            Session.getMainController().setStatus("Evenement Ajouté");
        Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        
    }
    

    @FXML
    private void ajouterimg(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg","*.jpg","*.png")
        );
        fileChooser.setTitle("Choisir le fichier contant le menu de l'établissement");
        File file = fileChooser.showOpenDialog(nom.getScene().getWindow());
        if (file != null) {
                SelectedFileProperty.set(file);
        }
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
    }

   

    }
    

