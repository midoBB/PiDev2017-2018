/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.promotions;

import com.jfoenix.controls.JFXButton;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import tn.esprit.bonplan.API.EnvoyerEmail;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.services.EtablissementServices;
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
    private ComboBox<Etablissement> etab;
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
    ValidationSupport support = new ValidationSupport();

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
            Prix.setText("0");
            cou.setText("0");
            cota.setText("0");
            //TODO ajouter apache math pour valider promo
            support.registerValidator(Produit, Validator.createEmptyValidator("VEuiller entrer le nom du produit"));
            support.registerValidator(debut, true, (Control c, LocalDate newValue)
                    -> ValidationResult.fromErrorIf(debut, "La date de debut doit etre antérieure à ajourd'hui", (LocalDate.now().compareTo(newValue) > 0)));
            support.registerValidator(fin, true, (Control c, LocalDate newValue)
                    -> ValidationResult.fromErrorIf(fin, "La date de fin doit etre antérieure à ajourd'hui et à celle du début de l'evenement", (LocalDate.now().compareTo(newValue) > 0) && (newValue.compareTo(debut.getValue()) < 0)));
            support.registerValidator(decs, Validator.createEmptyValidator("Veuiller remplir la description"));
            support.registerValidator(Prix, true, (Control c , String text)-> ValidationResult.fromErrorIf(Prix, "Veuiller entrer un prix", Float.parseFloat(text)<0));
            support.registerValidator(cota, true, (Control c, String t) -> ValidationResult.fromErrorIf(cota, "Le taux doit etre > 0 et < 100", Float.parseFloat(t) < 0 && Float.parseFloat(t) > 100));

        } catch (SQLException ex) {
            Logger.getLogger(AjoutPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AjoutPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AjouterPromotion(ActionEvent event) throws NoSuchAlgorithmException, Exception {
        if (support.invalidProperty().get() == true) {
            Session.getMainController().setStatus("Veuillez faire les corrections necessaires");
            Alert aleert = new Alert(Alert.AlertType.WARNING);
            String get = support.getValidationResult().getMessages().stream().map(a -> a.getText()).reduce((A, B) -> A + "\n" + B).get();
            aleert.setContentText(get);
            aleert.show();
        } else {
            try {
                Promotion v = new Promotion(Produit.getText(), etab.getSelectionModel().getSelectedItem().getRef(), debut.getValue().toString(), fin.getValue().toString(), decs.getText(), SelectedFileProperty.get().getName(), Float.parseFloat(Prix.getText()), Integer.parseInt(cota.getText()), Integer.parseInt(cou.getText()));
                PromotionService.insererPromotion(v);
                RemoteFileHandler.upload(SelectedFileProperty.get());
                EnvoyerEmail em = new EnvoyerEmail();
                em.EnvoyerMail(UserService.getAll(), v);
                Session.getMainController().setStatus("Promo ajouté");
                Parent root = FXMLLoader.load(getClass().getResource("ListePromotions.fxml"));
                Session.setLastView(Session.getMainController().getMainContent());
                Session.getMainController().setMainContent(root);
            }catch(java.lang.NullPointerException erreur){
                Alert aleert = new Alert(Alert.AlertType.WARNING);

                aleert.setContentText("Veuiller choisir une image de l'evenement");
                aleert.show();
            }
        }

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
