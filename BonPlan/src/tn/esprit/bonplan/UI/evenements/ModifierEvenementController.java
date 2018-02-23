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
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.bonplan.entities.Evenement;
import tn.esprit.bonplan.services.EvenementService;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField etab;
    @FXML
    private TextField disc;
    @FXML
    private Button image;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
      private Evenement a;
      private File f;
    SimpleObjectProperty<File> SelectedFileProperty = new SimpleObjectProperty<>();
    
    public ModifierEvenementController() {
        this.a = (Evenement)Session.getPassedParameter();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              nom.setText(a.getNom());
              etab.setText(a.getRefEtab());
              debut.setValue(LocalDate.parse(a.getDateDebut()));
              fin.setValue(LocalDate.parse(a.getDateFin()));
              disc.setText(a.getDescription());
              image.setText(a.getIimage());
       

    }    

    @FXML
    private void ModifierEvenement(ActionEvent event) throws Exception {
                a.setNom(nom.getText());
                a.setRefEtab(etab.getText());
                a.setDateDebut(debut.getValue().toString());
                a.setDateFin(fin.getValue().toString());
                a.setDescription(disc.getText());
                try{
                a.setImage(SelectedFileProperty.get().getName());
                RemoteFileHandler.upload(SelectedFileProperty.get());
                }catch(java.lang.NullPointerException error)
                {
                    a.setImage(a.getIimage());
                }
                
                EvenementService.updateEvenement(a);
                Session.getMainController().setStatus("Modification effectue avec success");
                Session.setPassedParameter(null);
                Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
                Session.getMainController().setMainContent(root);

                
          
        
    }

    @FXML
    private void getImage(ActionEvent event) {
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
 }
