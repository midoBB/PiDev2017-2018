/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.promotions;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.services.PromotionService;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class ModifierPromotionController implements Initializable {

    @FXML
    private TextField Produit;
    @FXML
    private Button image;
    @FXML
    private TextField decs;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    @FXML
    private TextField Prix;
    @FXML
    private TextField cou;
    @FXML
    private TextField cota;
    private Promotion p;
    private File f;
    SimpleObjectProperty<File> SelectedFileProperty = new SimpleObjectProperty<>();
    @FXML
    private ComboBox<Etablissement> etab;
    public ModifierPromotionController() {
        this.p = (Promotion)Session.getPassedParameter(); 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Produit.setText(p.getProduit());
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
            
            debut.setValue(LocalDate.parse(p.getDateDebut()));
            fin.setValue(LocalDate.parse(p.getDateFin()));
            decs.setText(p.getDescription());
            image.setText(p.getImage());
            cou.setText(""+p.getCoupon());
            Prix.setText(""+p.getPrix());
            cota.setText(""+p.getCota());
        } catch (SQLException ex) {
            Logger.getLogger(ModifierPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModifierPromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ModifeirPromotion(ActionEvent event) throws IOException, Exception {
                p.setProduit(Produit.getText());
                p.setRefEtab(etab.getSelectionModel().getSelectedItem().getRef());
                p.setDateDebut(debut.getValue().toString());
                p.setDateFin(fin.getValue().toString());
                p.setDescription(decs.getText());
                try{
                p.setImage(SelectedFileProperty.get().getName());
                
                RemoteFileHandler.upload(SelectedFileProperty.get());
                }catch(NullPointerException error){
                    p.setImage(p.getImage());
                }
                p.setPrix(Float.parseFloat(Prix.getText()));
                p.setCota(Integer.parseInt(cota.getText()));
                p.setCoupon(Integer.parseInt(cou.getText()));
                PromotionService.updatePromotion(p);
                Session.getMainController().setStatus("Modification effectue avec success");
                Session.setPassedParameter(null);
                Parent root = FXMLLoader.load(getClass().getResource("ListePromotions.fxml"));
                Session.getMainController().setMainContent(root);
    }

    @FXML
    private void SetImage(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.jpeg","*.jpg","*.png")
        );
        fileChooser.setTitle("Choisir le fichier contant le menu de l'établissement");
        File file = fileChooser.showOpenDialog(Prix.getScene().getWindow());
        if (file != null) {
                SelectedFileProperty.set(file);
        }
    }
    
}
