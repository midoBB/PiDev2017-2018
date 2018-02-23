package tn.esprit.bonplan.UI.evenements;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tn.esprit.bonplan.UI.promotions.ListePromotionsController;
import tn.esprit.bonplan.entities.Evenement;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.services.EvenementService;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class ListeEvenementController implements Initializable {

    @FXML
    private ListView<Evenement> Liste;

    static class Cell extends ListCell<Evenement> {

        protected final HBox hBox;
        protected final ImageView image;
        protected final Separator separator;
        protected final VBox vBox;
        protected final Label Nom;
        protected final Label etab;
        protected final Label duree;
        protected final Separator separator0;
        protected final VBox vBox0;
        protected final Label coupon;

        public Cell() {
            hBox = new HBox();
            image = new ImageView();
            separator = new Separator();
            vBox = new VBox();
            Nom = new Label();
            etab = new Label();
            duree = new Label();
            separator0 = new Separator();
            vBox0 = new VBox();
            coupon = new Label();
            hBox.setPrefHeight(150.0);
            hBox.setPrefWidth(550.0);
            image.setFitHeight(150.0);
            image.setFitWidth(200.0);
            image.setPickOnBounds(true);
            image.setPreserveRatio(true);
            separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
            separator.setPrefHeight(200.0);
            HBox.setHgrow(vBox, javafx.scene.layout.Priority.ALWAYS);
            vBox.setPrefHeight(200.0);
            vBox.setPrefWidth(100.0);
            Nom.setText("Nom");
            Nom.setFont(new Font(40.0));
            etab.setText("etab");
            etab.setFont(new Font(29.0));
            duree.setText("Duree");
            duree.setFont(new Font(16.0));
            duree.setTextOverrun(OverrunStyle.ELLIPSIS);
            separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
            separator0.setPrefHeight(200.0);
            vBox0.setPrefHeight(200.0);
            vBox0.setPrefWidth(100.0);
            coupon.setText("Label");
            coupon.setFont(new Font(25.0));
            VBox.setMargin(coupon, new Insets(0.0));
            hBox.getChildren().add(image);
            hBox.getChildren().add(separator);
            vBox.getChildren().add(Nom);
            vBox.getChildren().add(etab);
            vBox.getChildren().add(duree);
            hBox.getChildren().add(vBox);
            hBox.getChildren().add(separator0);
            vBox0.getChildren().add(coupon);
            hBox.getChildren().add(vBox0);
        }

        @Override
        protected void updateItem(Evenement item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null) {
                etab.setText(item.getRefEtab());
                Nom.setText(item.getNom());
                duree.setText("Du "+item.getDateDebut()+" jusqu'a "+item.getDateFin());
                coupon.setText(""+item.getDescription());
                try {
                    image.setImage(new Image(new FileInputStream(RemoteFileHandler.download(item.getIimage()))));
                } catch (Exception ex) {
                    Logger.getLogger(ListePromotionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setGraphic(hBox);
                
            }
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementService service = new EvenementService();
        ObservableList<Evenement> listeEvenement = FXCollections.observableArrayList(service.selectEvenement());
        Liste.setItems(listeEvenement);
        Liste.setCellFactory(param -> new Cell());
    }

    @FXML
    private void ModifierEv(ActionEvent event) {
       try {
            Session.setPassedParameter(Liste.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
         try {
            int id = Liste.getSelectionModel().getSelectedItem().getRef();
            
             EvenementService.DeletEvenementByID(id);
            Parent root = FXMLLoader.load(getClass().getResource("ListeEvenement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterEv(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutEvenement.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(ListeEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
