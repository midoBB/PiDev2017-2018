/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.StatusBar;
import tn.esprit.bonplan.util.Session;

/**
 *
 * @author mohamed
 */
public class BonPlanController implements Initializable {

    @FXML
    private AnchorPane ContentPane;
    @FXML
    private StatusBar Status;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.setMainController(this);
    }
    public void setStatus(String statusText){
        Status.setText(statusText);
    }
    public void setMainContent(Node content){
         ContentPane.getChildren().removeAll(ContentPane.getChildren().sorted());
         ContentPane.getChildren().add(content);
    }
    public Node getMainContent(){
        return ContentPane.getChildren().get(0);
    }
    @FXML
    private void handleQuitterAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Quitter");
        alert.setContentText("Etes vous sur de quitter l'application?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           System.exit(0);
        }
    }

    @FXML
    private void handleAjouterEtablissementAction(ActionEvent event) {
        Parent root;
        try {
            Session.setPassedParameter(null);
            root = FXMLLoader.load(getClass().getResource("etablissements/AjouterEtablissement.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void handleAfficherEtablissementAction(ActionEvent event) {
        Parent root;
        try {
            Session.setPassedParameter(null);
            root = FXMLLoader.load(getClass().getResource("etablissements/AfficherEtablissement.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
        }
    }
    @FXML
    void handleAjouterAPIAction(ActionEvent event) {
        Parent root;
        try {
            Session.setPassedParameter(null);
            root = FXMLLoader.load(getClass().getResource("etablissements/AjoutEtablissementAPI.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void handleRechercherAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("etablissements/RechercheEtablissement.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleEtabSuggereAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("etablissements/AfficherSugesstionEtablissement.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleListePromotionsAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("promotions/ListePromotions.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleListeEvenementsAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("evenements/ListeEvenement.fxml"));
            setMainContent(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
