/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class AfficherEtablissementController implements Initializable {

    @FXML
    private JFXListView<Etablissement> ListView;
    @FXML
    private JFXButton handleModifierButtonAction;

    static class Cell extends ListCell<Etablissement> {

        HBox h = new HBox();
        Label nom = new Label();
        Label adresse = new Label();
        Label separateur = new Label();

        public Cell() {
            super();
            h.getChildren().add(nom);
            h.getChildren().add(separateur);
            h.getChildren().add(adresse);
        }

        @Override
        protected void updateItem(Etablissement item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null) {
                nom.setText(item.getNom());
                separateur.setText("    |    ");
                adresse.setText(item.getAdresse());
                setGraphic(h);
            }
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Etablissement> etabList = FXCollections.observableArrayList(EtablissementServices.selectEtablissements());
            List<String> l = etabList.stream().map(e -> e.getNom()).collect(Collectors.toList());
            ObservableList<String> s = FXCollections.observableList(l);
            ListView.setItems(etabList);
            ListView.setCellFactory(param -> new Cell());
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
                EtablissementServices.deleteEtablissement(ListView.getSelectionModel().getSelectedItem());
                ObservableList<Etablissement> etabList = FXCollections.observableArrayList(EtablissementServices.selectEtablissements());
                List<String> l = etabList.stream().map(e -> e.getNom()).collect(Collectors.toList());
                ObservableList<String> s = FXCollections.observableList(l);
                ListView.setItems(etabList);
                ListView.setCellFactory(param -> new Cell());
            } catch (SQLException ex) {
                Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleModifierButtonAction(ActionEvent event) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEtablissement.fxml"));
            loader.setController(new ModifierEtablissementController(ListView.getSelectionModel().getSelectedItem()));
            final Parent root = loader.load();
            final Scene scene = new Scene(root, 600, 600);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.initOwner(ListView.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
