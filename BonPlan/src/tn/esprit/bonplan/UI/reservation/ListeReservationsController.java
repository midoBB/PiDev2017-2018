/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.UI.reservation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import tn.esprit.bonplan.entities.Reservation;
import tn.esprit.bonplan.services.EtablissementServices;
import tn.esprit.bonplan.services.ReservationService;
import tn.esprit.bonplan.services.UserService;

/**
 * FXML Controller class
 *
 * @author mouin
 */
public class ListeReservationsController implements Initializable {

    @FXML
    private TableColumn<Reservation, String> promo;
    @FXML
    private TableColumn<Reservation, String> user;
    @FXML
    private TableColumn<Reservation, String> etab;
    @FXML
    private TableColumn<Reservation, String> cou;
    @FXML
    private TableView<Reservation> tabreserv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
             ReservationService service = new ReservationService();
        
        ObservableList<Reservation> listeReservation = FXCollections.observableArrayList(service.selectReservation());
        tabreserv.setItems(listeReservation);
        promo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getRefPromo());
            }
        });
        etab.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                try{
                return new ReadOnlyObjectWrapper(EtablissementServices.selectEtablissement(param.getValue().getRefEtab()).getNom());
                }catch(Exception e){return null;}
            }
        });
        user.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                try{
                    
                return new ReadOnlyObjectWrapper(UserService.findById(param.getValue().getRefUser()).getNom());
                }
                catch(Exception e){ return null;}
            }
        });
        cou.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getCoupon());
            }
        });
     
    }    
    
}
