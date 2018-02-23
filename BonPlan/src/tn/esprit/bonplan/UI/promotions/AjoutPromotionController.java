/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.promotions;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import tn.esprit.bonplan.UI.evenements.ListeEvenementController;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.bonplan.API.EnvoyerEmail;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.services.PromotionService;
import tn.esprit.bonplan.services.UserService;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class AjoutPromotionController implements Initializable {

    @FXML
    private TextField Produit;
    @FXML
    private TextField etab;
    @FXML
    private JFXButton image;
    @FXML
    private TextField decs;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    @FXML
    private TextField Prix;
    @FXML
    private TextField cota;
    @FXML
    private TextField cou;
    private File f;
    SimpleObjectProperty<File> SelectedFileProperty = new SimpleObjectProperty<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ("a".equals(Produit.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le type doit etre inséré");
            alert.show();
        }

    }

    @FXML
    private void AjouterPromotion(ActionEvent event) throws NoSuchAlgorithmException, Exception {
        Promotion v = new Promotion(Produit.getText(), etab.getText(), debut.getValue().toString(), fin.getValue().toString(), decs.getText(), SelectedFileProperty.get().getName(), Float.parseFloat(Prix.getText()), Integer.parseInt(cota.getText()), Integer.parseInt(cou.getText()));
        PromotionService.insererPromotion(v);
        RemoteFileHandler.upload(SelectedFileProperty.get());
        EnvoyerEmail em = new EnvoyerEmail();
        em.EnvoyerMail(UserService.selectUser(), v);
        Session.getMainController().setStatus("Promo ajouté");
        Parent root = FXMLLoader.load(getClass().getResource("ListePromotions.fxml"));
        Session.setLastView(Session.getMainController().getMainContent());
        Session.getMainController().setMainContent(root);

    }

    @FXML
    private void handleAddFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.jpg", "*.png")
        );
        fileChooser.setTitle("Choisir le fichier contant le menu de l'établissement");
        File file = fileChooser.showOpenDialog(Prix.getScene().getWindow());
        if (file != null) {
            SelectedFileProperty.set(file);
        }

    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ListePromotions.fxml"));
        Session.setLastView(Session.getMainController().getMainContent());
        Session.getMainController().setMainContent(root);
    }

}
