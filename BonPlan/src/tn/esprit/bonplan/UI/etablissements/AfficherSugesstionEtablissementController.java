/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.services.EtablissementSuggeresServices;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class AfficherSugesstionEtablissementController implements Initializable {

    @FXML
    private JFXListView<Etablissement> ListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Etablissement> etabList;

            etabList = FXCollections.observableArrayList(EtablissementSuggeresServices.selectEtablissements());
            ListView.setItems(etabList);
            ListView.setCellFactory(param -> new AfficherEtablissementController.Cell());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleSupprimerButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Quitter");
        alert.setContentText("Etes vous sur de supprimer l'element selectionne?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                EtablissementSuggeresServices.RefuserEtablissement(ListView.getSelectionModel().getSelectedItem());
                ObservableList<Etablissement> etabList = FXCollections.observableArrayList(EtablissementSuggeresServices.selectEtablissements());
                ListView.setItems(etabList);
                ListView.setCellFactory(param -> new AfficherEtablissementController.Cell());
                Session.getMainController().setStatus("Etablissement non accepté");
            } catch (SQLException ex) {
                Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleDetailsButtonAction(ActionEvent event) {
        try {
            Session.setPassedParameter(ListView.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("AffichageDetailsEtablissement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleAccepterButtonAction(ActionEvent event) {
        try {
            EtablissementSuggeresServices.AcceptEtablissement(ListView.getSelectionModel().getSelectedItem());
            ObservableList<Etablissement> etabList = FXCollections.observableArrayList(EtablissementSuggeresServices.selectEtablissements());
            ListView.setItems(etabList);
            ListView.setCellFactory(param -> new AfficherEtablissementController.Cell());
            Session.getMainController().setStatus("Etablissement ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSugesstionEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherSugesstionEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherSugesstionEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
