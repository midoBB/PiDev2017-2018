/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.evenements;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import tn.esprit.bonplan.API.sendSMS;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.entities.Evenement;
import tn.esprit.bonplan.services.EtablissementServices;
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

    ValidationSupport support = new ValidationSupport();
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<Etablissement> etab;
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
        try {
            etab.setItems(FXCollections.observableArrayList(EtablissementServices.selectEtablissements()));
            etab.setConverter(new StringConverter<Etablissement>() {
                @Override
                public String toString(Etablissement object) {
                    return object.getNom();
                }

                @Override
                public Etablissement fromString(String string) {
                    return null;
                }
            });
            etab.getSelectionModel().select(0);
            debut.setValue(LocalDate.now());
            fin.setValue(LocalDate.now().plusDays(1));
            support.registerValidator(nom, Validator.createEmptyValidator("Veuiller remplir le champs de nom d'evenement"));
            support.registerValidator(debut, true, (Control c, LocalDate newValue)
                    -> ValidationResult.fromErrorIf(debut, "La date de debut doit etre antérieure à ajourd'hui", (LocalDate.now().compareTo(newValue) > 0)));
            support.registerValidator(fin, true, (Control c, LocalDate newValue)
                    -> ValidationResult.fromErrorIf(fin, "La date de fin doit etre antérieure à ajourd'hui et à celle du début de l'evenement", (LocalDate.now().compareTo(newValue) > 0) && (newValue.compareTo(debut.getValue()) < 0)));
            support.registerValidator(decs, Validator.createEmptyValidator("Veuiller remplir la description"));

        } catch (SQLException ex) {
            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterEvenement(ActionEvent event) throws Exception {
        if (support.invalidProperty().get() == true) {
            Session.getMainController().setStatus("Veuillez faire les corrections necessaires");
            Alert aleert = new Alert(AlertType.WARNING);
            String get = support.getValidationResult().getMessages().stream().map(a -> a.getText()).reduce((A, B) -> A + "\n" + B).get();
            aleert.setContentText(get);
            aleert.show();
            
        } else {
            try {
                Evenement v = new Evenement(nom.getText(), etab.getSelectionModel().getSelectedItem().getRef(), debut.getValue().toString(), fin.getValue().toString(), decs.getText(), SelectedFileProperty.get().getName());
                EvenementService.insererEvenement(v);
                RemoteFileHandler.upload(SelectedFileProperty.get());
                sendSMS.sendSms(UserService.getAll(), v);
                Session.getMainController().setStatus("Evenement Ajouté");
                Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
                Session.setLastView(Session.getMainController().getMainContent());
                Session.getMainController().setMainContent(root);
            } catch (java.lang.NullPointerException erreur) {
                Alert aleert = new Alert(AlertType.WARNING);

                aleert.setContentText("Veuiller choisir une image de l'evenement");
                aleert.show();
            }
        }

    }

    @FXML
    private void ajouterimg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg", "*.jpg", "*.png")
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
