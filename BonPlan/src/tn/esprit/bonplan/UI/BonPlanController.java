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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mohamed
 */
public class BonPlanController implements Initializable {

    @FXML
    private AnchorPane ContentPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            root = FXMLLoader.load(getClass().getResource("AjouterEtablissement.fxml"));
            ContentPane.getChildren().removeAll(ContentPane.getChildren().sorted());
            ContentPane.getChildren().add(root);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void handleAfficherEtablissementAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherEtablissement.fxml"));
            ContentPane.getChildren().removeAll(ContentPane.getChildren().sorted());
            ContentPane.getChildren().add(root);
        } catch (IOException ex) {
        }
    }

}
