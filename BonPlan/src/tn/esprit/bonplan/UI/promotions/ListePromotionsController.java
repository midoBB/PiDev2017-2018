/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.promotions;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.enumerations.CategorieEtablissement;
import tn.esprit.bonplan.services.PromotionService;
import tn.esprit.bonplan.util.RemoteFileHandler;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class ListePromotionsController implements Initializable {

    @FXML
    private JFXListView<Promotion> List;

    static class Cell extends ListCell<Promotion> {

        protected final ImageView image;
        protected final HBox hBox;
        protected final Separator separator;
        protected final VBox vBox;
        protected final Label Etab;
        protected final Label produit;
        protected final Label duree;
        protected final Separator separator0;
        protected final VBox vBox0;
        protected final Label prix;
        protected final Label cota;
        protected final Label prixred;
        protected final Label coupon;

        public Cell() {
            super();
            hBox = new HBox();
            image = new ImageView();
            separator = new Separator();
            vBox = new VBox();
            Etab = new Label();
            produit = new Label();
            duree = new Label();
            separator0 = new Separator();
            vBox0 = new VBox();
            prix = new Label();
            cota = new Label();
            prixred = new Label();
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

            Etab.setText("Etab");
            Etab.setFont(new Font(35.0));

            produit.setText("Produit");
            produit.setFont(new Font(20.0));

            duree.setText("Duree");
            duree.setFont(new Font(19.0));

            separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
            separator0.setPrefHeight(200.0);

            vBox0.setPrefHeight(200.0);
            vBox0.setPrefWidth(100.0);

            prix.setText("Label");

            cota.setText("Label");
            cota.setFont(new Font(25.0));

            prixred.setText("Label");
            prixred.setFont(new Font(26.0));

            coupon.setText("Label");
            coupon.setFont(new Font(25.0));

            hBox.getChildren().add(image);
            hBox.getChildren().add(separator);
            vBox.getChildren().add(Etab);
            vBox.getChildren().add(produit);
            vBox.getChildren().add(duree);
            hBox.getChildren().add(vBox);
            hBox.getChildren().add(separator0);
            vBox0.getChildren().add(prix);
            vBox0.getChildren().add(cota);
            vBox0.getChildren().add(prixred);
            vBox0.getChildren().add(coupon);
            hBox.getChildren().add(vBox0);
            getChildren().add(hBox);
        }

        @Override
        protected void updateItem(Promotion item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null) {
                Etab.setText(item.getRefEtab());
                produit.setText(item.getProduit());
                prix.setText("" + item.getPrix() + " TND");
                prixred.setText("" + item.getPrix_promo() + " TND");
                prixred.setEffect(new Glow());
                duree.setText("Du " + item.getDateDebut() + " jusqu'a " + item.getDateFin());
                coupon.setText("" + item.getCouponDispo());
                cota.setText("" + item.getCota() + "%");
                try {
                    image.setImage(new Image(new FileInputStream(RemoteFileHandler.download(item.getImage()))));
                } catch (Exception ex) {
                    Logger.getLogger(ListePromotionsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setGraphic(hBox);

            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Promotion> listePromotion = FXCollections.observableArrayList(PromotionService.selectPromotion());
        List.setItems(listePromotion);
        List.setCellFactory(param -> new Cell());

    }

    @FXML
    private void ModifierPr(ActionEvent event) {
        try {
            Session.setPassedParameter(List.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("ModifierPromotion.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(ListePromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void SupprimerPr(ActionEvent event) {
        try {
            int id = List.getSelectionModel().getSelectedItem().getRef();
            Parent root = FXMLLoader.load(getClass().getResource("ListePromotions.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(ListePromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterPr(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjoutPromotion.fxml"));
            Session.setLastView(Session.getMainController().getMainContent());
            Session.getMainController().setMainContent(root);
        } catch (IOException ex) {
            Logger.getLogger(ListePromotionsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void participerPr(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        PromotionService.participer(List.getSelectionModel().getSelectedItem());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = PromotionService.genererHash();
        int width = 300;
        int height = 300;
        String fileType = "png";
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            // Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
        Dialog d = new Dialog();
        d.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = d.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        d.getDialogPane().setContent(root);
        d.showAndWait();
        Parent root2 = FXMLLoader.load(getClass().getResource("ListePromotions.fxml"));
        Session.setLastView(Session.getMainController().getMainContent());
        Session.getMainController().setMainContent(root2);
    }

}
