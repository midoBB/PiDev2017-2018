/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class RechercheEtablissementController implements Initializable {

    @FXML
    private JFXTextField NameField;
    @FXML
    private JFXTextField TagField;
    @FXML
    private JFXComboBox<String> CategorieCombo;
    @FXML
    private JFXTextField VilleField;
    @FXML
    private AnchorPane ContentPane;
    @FXML
    private AnchorPane ResultPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieCombo.getItems().add("Restaurent");
        CategorieCombo.getItems().add("Bar");
        CategorieCombo.getItems().add("Hotel");
        CategorieCombo.getItems().add("Cafe");
        CategorieCombo.getSelectionModel().selectFirst();
        Session.getMainController().setStatus("nbbbbb");
    }    

    @FXML
    private void handleRechercheAction(ActionEvent event) throws SQLException, IOException {
        Session.getMainController().setStatus("aaaa");
        boolean recherchenom =true, rechercheville =true , recherchetag = true;
        if(NameField.getText() == null || NameField.getText().length() == 0){
            recherchenom = false;
        }
        if(VilleField.getText() == null || VilleField.getText().length() == 0){
            rechercheville = false;
        }
        List<Etablissement> etabList = EtablissementServices.selectEtablissements();
        if(recherchenom)
            etabList = etabList.stream().filter(e->e.getNom().toLowerCase().matches(".*"+NameField.getText().toLowerCase()+".*")).collect(Collectors.toList());
        if(rechercheville)
            etabList = etabList.stream().filter(e->e.getAdresse().toLowerCase().matches(".*"+VilleField.getText().toLowerCase()+".*")).collect(Collectors.toList());
        etabList = etabList.stream().filter(e->e.getCategorie().getValue() == CategorieCombo.getSelectionModel().getSelectedIndex()).collect(Collectors.toList());
        Session.setPassedParameter(etabList);
        HBox root = FXMLLoader.load(getClass().getResource("AfficherEtablissement.fxml"));
        root.setMaxHeight(380);
        ResultPane.getChildren().removeAll(ResultPane.getChildren().sorted());
        ResultPane.getChildren().add(root);
        Session.getMainController().setStatus(etabList.size()+" Etablissement trouvé(s)");
    }
    
}
