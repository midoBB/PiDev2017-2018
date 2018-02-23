/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AffichageDetailsEtablissementController implements Initializable {

    @FXML
    private Label NomLabel;
    @FXML
    private Label CategorieLabel;
    @FXML
    private Label AdresseLabel;
    @FXML
    private Label PhoneLabel;
    @FXML
    private Label DescriptionLabel;
    @FXML
    private JFXButton ConsulterMenuButton;
    @FXML
    private Text OtherField;
    private Etablissement e;
    private File f;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.getMainController().setStatus("Détails affichés");
        NomLabel.setText(e.getNom());
        CategorieLabel.setText(e.getCategorie().name());
        AdresseLabel.setText(e.getAdresse());
        PhoneLabel.setText(e.getTelephone());
        DescriptionLabel.setText(e.getDescription());
        ConsulterMenuButton.setVisible(false);
        if(e.getMenu() != null && e.getMenu().length() > 0)
        {    
            try {
                ConsulterMenuButton.setVisible(true);
                f = RemoteFileHandler.download(e.getMenu());
                if(!f.exists())
                    ConsulterMenuButton.setVisible(false);
            } catch (Exception ex) {
                Logger.getLogger(AffichageDetailsEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }    

    public AffichageDetailsEtablissementController() {
        e = (Etablissement)Session.getPassedParameter();
    }
    
    @FXML
    private void handleConsulterMenuAction(ActionEvent event) {
        try {
            Desktop.getDesktop().open(f);
        } catch (IOException ex) {
            Logger.getLogger(AffichageDetailsEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleOKButton(ActionEvent event) {
        Session.setPassedParameter(null);
        Session.getMainController().setMainContent(Session.getLastView());
    }
    
}
