/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.enumerations.CategorieEtablissement;
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

        protected final HBox hBox;
    protected final FontAwesomeIconView  Icon;
    protected final Separator separator;
    protected final VBox vBox;
    protected final Label Nom;
    protected final Separator separator0;
    protected final Label Adresse;
    protected final Separator separator1;
    protected final Label Telephone;

        public Cell() {
            super();
        hBox = new HBox();
        Icon = new FontAwesomeIconView();
        separator = new Separator();
        vBox = new VBox();
        Nom = new Label();
        separator0 = new Separator();
        Adresse = new Label();
        separator1 = new Separator();
        Telephone = new Label();

        setId("AnchorPane");
        setPrefHeight(150.0);
        setPrefWidth(550.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        Icon.setSize("100px");
        Icon.setTextAlignment(TextAlignment.CENTER);
        Icon.setWrappingWidth(150.0);
        Icon.setPickOnBounds(true);

        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(148.0);
        separator.setPrefWidth(16.0);

        HBox.setHgrow(vBox, javafx.scene.layout.Priority.ALWAYS);
        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);

        Nom.setText("Label");
        Nom.setFont(new Font(36.0));

        separator0.setPrefWidth(200.0);

        Adresse.setText("Label");
        Adresse.setWrapText(true);
        Adresse.setFont(new Font(20.0));

        separator1.setPrefWidth(200.0);

        Telephone.setText("Label");
        Telephone.setFont(new Font(20.0));

        hBox.getChildren().add(Icon);
        hBox.getChildren().add(separator);
        vBox.getChildren().add(Nom);
        vBox.getChildren().add(separator0);
        vBox.getChildren().add(Adresse);
        vBox.getChildren().add(separator1);
        vBox.getChildren().add(Telephone);
        hBox.getChildren().add(vBox);
        
        }

        @Override
        protected void updateItem(Etablissement item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null) {
                Nom.setText(item.getNom());
                Adresse.setText(item.getAdresse());
                if(item.getCategorie() == CategorieEtablissement.Bar)
                    Icon.setIcon(FontAwesomeIcon.BEER);
                if(item.getCategorie() == CategorieEtablissement.Cafe)
                    Icon.setIcon(FontAwesomeIcon.COFFEE);
                if(item.getCategorie() == CategorieEtablissement.Hotel)
                    Icon.setIcon(FontAwesomeIcon.HOTEL);
                if(item.getCategorie() == CategorieEtablissement.Restaurent)
                    Icon.setIcon(FontAwesomeIcon.SPOON);
                Telephone.setText(item.getTelephone());
                setGraphic(hBox);
            }
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ObservableList<Etablissement> etabList;
            if (Session.getPassedParameter() instanceof List) {
                etabList = FXCollections.observableArrayList((List<Etablissement>) Session.getPassedParameter());
            } else {
                etabList = FXCollections.observableArrayList(EtablissementServices.selectEtablissements());
            }
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
            Session.setPassedParameter(ListView.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEtablissement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleDetailsButtonAction(ActionEvent event) {
        try {
            Session.setPassedParameter(ListView.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("AffichageDetailsEtablissement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
