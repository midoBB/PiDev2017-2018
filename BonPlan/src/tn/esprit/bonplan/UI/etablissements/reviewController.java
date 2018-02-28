/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import tn.esprit.bonplan.entities.Commentaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import Emoji.Emoji;
import Emoji.EmojiOne;
import Emoji.ImageCache;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.Rating;
import tn.esprit.bonplan.entities.Avis;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.entities.User;
import tn.esprit.bonplan.services.AvisService;
import tn.esprit.bonplan.services.CommentaireService;
import tn.esprit.bonplan.util.Session;


/**
 *
 * @author Ali
 */
public class reviewController implements Initializable {
    
    User u = Session.getLoggedInUser();
    Etablissement e = (Etablissement)Session.getPassedParameter();
    @FXML
    private Label etabname;
    @FXML
    private JFXTextArea etabdesc;
    @FXML
    private JFXButton addReview;
    @FXML
    private StackPane stackPane;
    @FXML
    private Rating noteEtab;
    @FXML
    private JFXListView<Avis> listReview;
    
@Override
    public void initialize(URL url, ResourceBundle rb) {
        etabname.setText(e.getNom());
        etabdesc.setText(e.getDescription());
        noteEtab.setDisable(true);
        addReview.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                loadDialog(event);
            }
        });
        List<Avis> list = AvisService.selectAvisByEtabId(e.getRef());
        int totalNotes=0;
        int nbRev = list.size();
        for(Avis a : list){
            totalNotes+=a.getEstablishmentMark();
        }
        double moyenneEtab = (double)totalNotes/nbRev;
        noteEtab.setRating(moyenneEtab);
        listReview.setItems(FXCollections.observableArrayList(list));
        listReview.setCellFactory(e-> new affichageReviewBase());
        
    }
    @FXML
    private void loadDialog(ActionEvent event){
        JFXDialogLayout content = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane,content,JFXDialog.DialogTransition.CENTER);
        content.setHeading(new Text("Ajouter un avis"));
        JFXTextField pubTextField = new JFXTextField();
        VBox vBox = new VBox(25, new Label("Entrez votre avis"), pubTextField);
        Rating mark = new Rating();
        VBox vBox1 = new VBox(25, new Label("Choisissez une note "), mark);
        HBox box = new HBox(25, vBox, vBox1);
        mark.setMax(5);
        mark.setPartialRating(false);
        content.setBody(box);
        JFXButton add = new JFXButton("Ajouter");
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(!pubTextField.getText().isEmpty()){
                    Avis a = new Avis(pubTextField.getText(), (int)mark.getRating(), (Etablissement)Session.getPassedParameter(), Session.getLoggedInUser());
                    AvisService.insererAvis(a);
                }
                dialog.close();
            }
        });
        JFXButton cancel = new JFXButton("Annuler");
        cancel.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                dialog.close();
            }
        });
        content.setActions(add,cancel);
        stackPane.toFront();
        dialog.toFront();
        dialog.show();
    }
    
    static class affichageReviewBase extends ListCell<Avis> {

    protected final VBox vBox;
    protected final Pane pane;
    protected final StackPane sPane;
    protected final Label username;
    protected final Label vote_count;
    protected final Rating rating;
    protected final ImageView vote_down;
    protected final ImageView vote_up;
    protected final ImageView addComment;
    protected final TextFlow pubText;
    protected final JFXListView listComment;

    public affichageReviewBase() {

        vBox = new VBox();
        pane = new Pane();
        username = new Label();
        vote_count = new Label();
        rating = new Rating();
        vote_down = new ImageView();
        vote_up = new ImageView();
        addComment = new ImageView();
        pubText = new TextFlow();
        listComment = new JFXListView();
        sPane=new StackPane();
        
        vBox.setSpacing(10.0);
        
        setId("AnchorPane");
        setPrefHeight(500.0);
        setPrefWidth(530.0);

        vBox.setPrefHeight(288.0);
        vBox.setPrefWidth(530.0);

        pane.setPrefHeight(80.0);
        pane.setPrefWidth(930.0);

        username.setLayoutX(69.0);
        username.setLayoutY(28.0);
        username.setPrefHeight(32.0);
        username.setPrefWidth(176.0);
        username.setFont(new Font("Century Gothic Bold", 28.0));

        vote_count.setLayoutX(35.0);
        vote_count.setLayoutY(24.0);
        vote_count.setPrefHeight(43.0);
        vote_count.setPrefWidth(28.0);
        vote_count.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);
        vote_count.setFont(new Font(24.0));

        rating.setLayoutX(245.0);
        rating.setLayoutY(27.0);

        vote_down.setFitHeight(14.0);
        vote_down.setFitWidth(12.0);
        vote_down.setLayoutX(14.0);
        vote_down.setLayoutY(43.0);
        vote_down.setPickOnBounds(true);
        vote_down.setPreserveRatio(true);
        vote_down.setImage(new Image(getClass().getResource("..\\..\\..\\..\\..\\Resources\\001-down-arrow.png").toExternalForm()));
        vote_down.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                vote_count.setText(""+(Integer.parseInt(vote_count.getText())-1));
                AvisService.updateAvisVoteMoins(getItem());
                getItem().setVoteCount(getItem().getVoteCount()-1);
            }
    });
        
        
        vote_up.setFitHeight(19.0);
        vote_up.setFitWidth(12.0);
        vote_up.setLayoutX(14.0);
        vote_up.setLayoutY(33.0);
        vote_up.setPickOnBounds(true);
        vote_up.setPreserveRatio(true);
        vote_up.setRotate(180.0);
        vote_up.setImage(new Image(getClass().getResource("..\\..\\..\\..\\..\\Resources\\001-down-arrow.png").toExternalForm()));
        vote_up.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                vote_count.setText(""+(Integer.parseInt(vote_count.getText())+1));
                AvisService.updateAvisVotePlus(getItem());
                getItem().setVoteCount(getItem().getVoteCount()+1);
            }
    });
        
        sPane.setLayoutX(441.0);
        sPane.setLayoutY(28.0);
        addComment.setFitHeight(30.0);
        addComment.setFitWidth(30.0);
        addComment.setPickOnBounds(true);
        addComment.setPreserveRatio(true);
        addComment.setImage(new Image(getClass().getResource("../../../../../Resources/004-arrows.png").toExternalForm()));
        sPane.getChildren().add(addComment);
        addComment.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                JFXDialogLayout content = new JFXDialogLayout();
                JFXDialog dialog = new JFXDialog(sPane,content,JFXDialog.DialogTransition.CENTER);
                content.setHeading(new Text("Ajouter un commentaire"));
                JFXTextField commentTextField = new JFXTextField();
                JFXButton add = new JFXButton("Ajouter");
                add.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        if(!commentTextField.getText().isEmpty()){
                            Commentaire c = new Commentaire(getItem(), Session.getLoggedInUser() ,commentTextField.getText());
                            CommentaireService.insererCommentaire(c);
                        }
                        dialog.close();
                        pubText.setVisible(true);
                    }
                });
                JFXButton cancel = new JFXButton("Annuler");
                cancel.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        dialog.close();
                        pubText.setVisible(true);
                    }
            });
            content.setBody(commentTextField);
            content.setActions(add,cancel);
            dialog.setTranslateX(-400.0);
            pubText.setVisible(false);
            sPane.toFront();
            dialog.show();
            dialog.toFront();
            }
    });
        pubText.setPrefHeight(145.0);
        pubText.setPrefWidth(464.0);
        pubText.setTextAlignment(javafx.scene.text.TextAlignment.JUSTIFY);

        pane.getChildren().add(username);
        pane.getChildren().add(vote_count);
        pane.getChildren().add(rating);
        pane.getChildren().add(vote_down);
        pane.getChildren().add(vote_up);
        pane.getChildren().add(sPane);
        vBox.getChildren().add(pane);
        vBox.getChildren().add(pubText);
        vBox.getChildren().add(listComment);
        getChildren().add(vBox);

    }
    @Override
        protected void updateItem(Avis item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null && item.getSignalements()<20) {
                username.setText("username");
                vote_count.setText(""+item.getVoteCount());
                rating.setRating((double)item.getEstablishmentMark());
                
                Queue<Object> obs = EmojiOne.getInstance().toEmojiAndText(item.getPubText());
                    while(!obs.isEmpty()) {
			Object ob = obs.poll();
			if(ob instanceof String) {
                        	addText((String)ob);
			}
			else if(ob instanceof Emoji) {
				Emoji emoji = (Emoji) ob;
				pubText.getChildren().add(createEmojiNode(emoji));
			}
                    }
                List<Commentaire> listc = CommentaireService.selectCommentaireByRevId(item.getId());
                listComment.setItems(FXCollections.observableArrayList(listc));
                listComment.setCellFactory(e-> new affichageCommentBase());
                rating.setDisable(true);
                setGraphic(vBox);
            }
        }
        
    private Node createEmojiNode(Emoji emoji) {
		StackPane stackPane = new StackPane();
		stackPane.setPadding(new Insets(3));
		ImageView imageView = new ImageView();
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
		imageView.setImage(ImageCache.getInstance().getImage(getEmojiImagePath(emoji.getHex())));
		stackPane.getChildren().add(imageView);

		Tooltip tooltip = new Tooltip(emoji.getShortname());
		Tooltip.install(stackPane, tooltip);
		stackPane.setCursor(Cursor.HAND);
		stackPane.setOnMouseEntered(e-> {
			stackPane.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 3;");
		});
		stackPane.setOnMouseExited(e-> {
			stackPane.setStyle("");
		});
		return stackPane;
	}
    
    private String getEmojiImagePath(String hexStr) {
		return getClass().getResource("..\\..\\..\\..\\..\\Resources\\" + hexStr + ".png").toExternalForm();
	}

	private void addText(String text) {
		Text textNode = new Text(text);
		textNode.setFont(Font.font(24));
		pubText.getChildren().add(textNode);
	}
    }
    
   static class affichageCommentBase extends ListCell<Commentaire> {

    protected final VBox vBox;
    protected final Pane pane;
    protected final Label username;
    protected final Separator separator;
    protected final Pane pane0;
    protected final TextFlow commentText;

    public affichageCommentBase() {

        vBox = new VBox();
        pane = new Pane();
        username = new Label();
        separator = new Separator();
        pane0 = new Pane();
        commentText = new TextFlow();

        setId("AnchorPane");
        setPrefHeight(250.0);
        setPrefWidth(400.0);

        vBox.setPrefHeight(358.0);
        vBox.setPrefWidth(600.0);

        pane.setPrefHeight(68.0);
        pane.setPrefWidth(600.0);

        username.setLayoutX(57.0);
        username.setLayoutY(24.0);
        username.setFont(new Font("Century Gothic Bold", 20.0));

        separator.setPrefHeight(0.0);
        separator.setPrefWidth(600.0);

        pane0.setPrefHeight(132.0);
        pane0.setPrefWidth(600.0);

        commentText.setLayoutX(14.0);
        commentText.setLayoutY(14.0);
        commentText.setPrefHeight(107.0);
        commentText.setPrefWidth(570.0);

        pane.getChildren().add(username);
        vBox.getChildren().add(pane);
        vBox.getChildren().add(separator);
        pane0.getChildren().add(commentText);
        vBox.getChildren().add(pane0);
        getChildren().add(vBox);

    }
    
        @Override
        protected void updateItem(Commentaire item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);
            if (item != null && item.getSignalements()<20) {
                username.setText("username");
                Queue<Object> obs = EmojiOne.getInstance().toEmojiAndText(item.getText());
                    while(!obs.isEmpty()) {
			Object ob = obs.poll();
			if(ob instanceof String) {
                        	addText((String)ob);
			}
			else if(ob instanceof Emoji) {
				Emoji emoji = (Emoji) ob;
				commentText.getChildren().add(createEmojiNode(emoji));
			}
		}                
            setGraphic(vBox);
            }
        }
    private Node createEmojiNode(Emoji emoji) {
		StackPane stackPane = new StackPane();
		stackPane.setPadding(new Insets(3));
		ImageView imageView = new ImageView();
		imageView.setFitWidth(32);
		imageView.setFitHeight(32);
		imageView.setImage(ImageCache.getInstance().getImage(getEmojiImagePath(emoji.getHex())));
		stackPane.getChildren().add(imageView);

		Tooltip tooltip = new Tooltip(emoji.getShortname());
		Tooltip.install(stackPane, tooltip);
		stackPane.setCursor(Cursor.HAND);
		stackPane.setOnMouseEntered(e-> {
			stackPane.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 3;");
		});
		stackPane.setOnMouseExited(e-> {
			stackPane.setStyle("");
		});
		return stackPane;
	}
    
    private String getEmojiImagePath(String hexStr) {
		return getClass().getResource("..\\..\\..\\..\\..\\Resources\\" + hexStr + ".png").toExternalForm();
	}

	private void addText(String text) {
		Text textNode = new Text(text);
		textNode.setFont(Font.font(24));
		commentText.getChildren().add(textNode);
	}
    }
}