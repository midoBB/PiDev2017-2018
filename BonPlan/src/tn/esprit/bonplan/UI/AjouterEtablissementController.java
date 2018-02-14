/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.enumerations.CategorieEtablissement;
import tn.esprit.bonplan.services.EtablissementServices;

/**
 * FXML Controller class
 *
 * @author mohamed
 */
public class AjouterEtablissementController implements Initializable {

    private StringProperty titleText;
    @FXML
    private Label TitleLabel;
    @FXML
    private JFXTextField NomField;
    @FXML
    private JFXComboBox<String> CategorieField;
    @FXML
    private JFXTextField AdresseField;
    @FXML
    private JFXTextField TelephoneField;
    @FXML
    private JFXTextField FacebookField;
    @FXML
    private JFXTextField HoraireField;
    @FXML
    private BorderPane MenuPane;
    @FXML
    private JFXButton AjouterMenuButton;
    @FXML
    private JFXTextArea DescriptionField;
    @FXML
    private JFXButton AjouterButton;
    @FXML
    private AnchorPane Parent;
    @FXML
    private Label FileLabel;
    @FXML
    private JFXTextField SiteField;
    SimpleObjectProperty<File> SelectedFileProperty = new SimpleObjectProperty<>();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieField.getItems().add("Restaurent");
        CategorieField.getItems().add("Cafe");
        CategorieField.getItems().add("Bar");
        CategorieField.getItems().add("Hotel");
        FadeTransition ft = new FadeTransition(Duration.millis(1000), MenuPane);
        CategorieField.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if ("Hotel".equals(oldValue)) {
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                ft.setCycleCount(1);
                ft.play();
                System.out.println("fadeout");
            }
            if ("Hotel".equals(newValue)) {
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                ft.setCycleCount(1);
                ft.play();
                System.out.println("fadein");
            }
        });
    }

    @FXML
    private void handleCategorieChangeEvent(ActionEvent event) {
        if (!TitleLabel.textProperty().isBound()) {
            TitleLabel.textProperty().bind(Bindings.format("Ajout d'un nouvel %s", CategorieField.valueProperty()));
        }
    }

    @FXML
    private void handleAjouterMenuButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("DOCX", "*.docx"),
                new FileChooser.ExtensionFilter("DOC", "*.doc")
        );
        fileChooser.setTitle("Choisir le fichier contant le menu de l'établissement");
        File file = fileChooser.showOpenDialog(Parent.getScene().getWindow());
        if (file != null) {
                FileLabel.textProperty().bind(Bindings.format("%s", file.getName()));
                SelectedFileProperty.set(file);
        }
    }

    @FXML
    private void handleAjouterButtonAction(ActionEvent event) {
        if(isInputValid()){
            try {
                Etablissement newEtablissement = new Etablissement(NomField.getText(),  CategorieEtablissement.valueOf(CategorieField.getSelectionModel().getSelectedIndex()),
                        AdresseField.getText(), DescriptionField.getText()
                        , TelephoneField.getText(), FacebookField.getText(), SiteField.getText(), HoraireField.getText(), SelectedFileProperty.get(), true);
                EtablissementServices.insertEtablissement(newEtablissement);
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(AjouterEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (NomField.getText() == null || NomField.getText().length() == 0) {
            errorMessage += "Entrer un nom d'établissement valide\n"; 
        }
        if (CategorieField.getSelectionModel().getSelectedItem() == null || CategorieField.getSelectionModel().getSelectedItem().length() == 0) {
            errorMessage += "Sellectionner une catégorie d'établissement!\n"; 
        }
        if (AdresseField.getText() == null || AdresseField.getText().length() == 0) {
            errorMessage += "Entrer une adresse valide\n"; 
        }

        if (DescriptionField.getText() == null || DescriptionField.getText().length() == 0) {
            errorMessage += "Decrivez l'etablissement en quelques mots!\n"; 
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(Parent.getScene().getWindow());
            alert.setTitle("Champs manquants");
            alert.setHeaderText("SVP de remplir les champs necessaires");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
