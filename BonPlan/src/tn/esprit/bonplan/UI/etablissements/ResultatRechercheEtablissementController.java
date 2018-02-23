/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ResultatRechercheEtablissementController implements Initializable {

    List<Etablissement> etablissements;
    @FXML
    private JFXListView<Etablissement> ListView;

    public ResultatRechercheEtablissementController() {
        etablissements = (List<Etablissement>) Session.getPassedParameter();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Etablissement> etabList = FXCollections.observableArrayList(etablissements);
        ListView.setItems(etabList);
        ListView.setCellFactory(param -> new AfficherEtablissementController.Cell());

    }

}
