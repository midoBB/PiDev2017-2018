/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.etablissements;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResult;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.enumerations.CategorieEtablissement;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.services.EtablissementSuggeresServices;
import tn.esprit.bonplan.util.Session;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjoutEtablissementAPIController implements Initializable {

    @FXML
    private JFXTextField EtabField;
    @FXML
    private Label TitleLabel;
    @FXML
    private JFXComboBox<String> ComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboBox.getItems().add("Tunis");
        ComboBox.getItems().add("Hammamet");
        ComboBox.getItems().add("Sousse");
        ComboBox.getItems().add("Sfax");
        ComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void AddApi(ActionEvent event) {
        try {
            GeoApiContext.Builder b = new GeoApiContext.Builder();
            b.apiKey("AIzaSyB2mdcSMjO3CWf3D5B5bkY_aUQXRlZprxw");
            
            GeoApiContext con = b.build();
            GeocodingApiRequest geocodingApiRequest =  GeocodingApi.geocode(con , ComboBox.getSelectionModel().getSelectedItem());
            NearbySearchRequest r = PlacesApi.nearbySearchQuery(con, geocodingApiRequest.await()[0].geometry.location);
            r.radius(50000);
            r.keyword(EtabField.getText());
            PlacesSearchResult[] res = r.await().results;
            if (res[0] != null) {
                PlacesSearchResult e = res[0];
                System.out.println(e.placeId);
                PlaceDetailsRequest request = new PlaceDetailsRequest(con);
                request.placeId(e.placeId);
                PlaceDetails details = request.await();
                if (details != null) {
                    Etablissement newEtab = new Etablissement();
                    if (details.formattedAddress != null) {
                        newEtab.setAdresse(details.formattedAddress);
                    }
                    if (details.formattedPhoneNumber != null) {
                        newEtab.setTelephone(details.formattedPhoneNumber);
                    }else
                        newEtab.setTelephone("");
                    if (details.website != null) {
                        newEtab.setSite(details.website.toString());
                    }
                    if (details.name != null) {
                        newEtab.setNom(details.name);
                    }
                    if (details.openingHours != null) {
                        newEtab.setHoraire(String.join(" , ", details.openingHours.weekdayText));
                    }
                    for(String s : details.types){
                        System.out.println(s);
                       if(s.equalsIgnoreCase("lodging")){
                           newEtab.setCategorie(CategorieEtablissement.Hotel);
                           break;
                       }
                       if(s.equalsIgnoreCase("cafe")){
                           newEtab.setCategorie(CategorieEtablissement.Cafe);
                           break;
                       }
                       if(s.equalsIgnoreCase("bar")){
                           newEtab.setCategorie(CategorieEtablissement.Bar);
                           break;
                       }
                       if(s.equalsIgnoreCase("food")){
                           newEtab.setCategorie(CategorieEtablissement.Restaurent);
                           break;
                       }
                    }
                    newEtab.setDescription("");
                    newEtab.setFacebook("");
                    newEtab.setSite("");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Ajout");
                    alert.setContentText("Etes vous sur d'ajouter l'etablissement " + newEtab.getNom() + "?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        EtablissementSuggeresServices.insertEtablissement(newEtab);
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Ajout effectue avec succees");
                        alert.showAndWait();
                    }
                }
            }
        } catch (ApiException ex) {
            System.out.println(ex);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ArrayIndexOutOfBoundsException ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Pas de resultat trouvé");
            a.show();
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }

}
