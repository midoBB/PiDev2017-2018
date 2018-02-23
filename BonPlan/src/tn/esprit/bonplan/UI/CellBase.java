package tn.esprit.bonplan.UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public abstract class CellBase extends AnchorPane {

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

    public CellBase() {

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
        duree.setFont(new Font(19.0));

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
        getChildren().add(hBox);

    }
}
