/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class ModifierEtablissementController implements Initializable  {
    private Etablissement e;
    @FXML
    private Label TitleLabel;
    @FXML
    private JFXTextField NomField;
    @FXML
    private JFXTextField AdresseField;
    @FXML
    private JFXTextField TelephoneField;
    @FXML
    private JFXTextField FacebookField;
    @FXML
    private JFXTextField SiteField;
    @FXML
    private JFXTextField HoraireField;
    @FXML
    private JFXTextArea DescriptionField;
    @FXML
    private JFXButton AjouterButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NomField.setText(e.getNom());
        AdresseField.setText(e.getAdresse());
        TelephoneField.setText(e.getTelephone());
        FacebookField.setText(e.getFacebook());
        SiteField.setText(e.getSite());
        HoraireField.setText(e.getHoraire());
        DescriptionField.setText(e.getDescription());
    }    

    public ModifierEtablissementController() {
        e = (Etablissement)Session.getPassedParameter();
    }

    
    @FXML
    private void handleModifierButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Modification");
        alert.setContentText("Etes vous sur de modifier l'etablissement?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                e.setNom(NomField.getText());
                e.setAdresse(AdresseField.getText());
                e.setTelephone(TelephoneField.getText());
                e.setFacebook(FacebookField.getText());
                e.setSite(SiteField.getText());
                e.setHoraire(HoraireField.getText());
                e.setDescription(DescriptionField.getText());
                EtablissementServices.updateEtablissement(e);
                Parent root = FXMLLoader.load(getClass().getResource("AfficherEtablissement.fxml"));
                Session.getMainController().setMainContent(root);
                Session.setPassedParameter(null);
                Session.getMainController().setStatus("Modification effectue avec success");
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(ModifierEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ModifierEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleAnnulerAction(ActionEvent event) {
        Session.getMainController().setMainContent(Session.getLastView());
        Session.setPassedParameter(null);
    }

}
