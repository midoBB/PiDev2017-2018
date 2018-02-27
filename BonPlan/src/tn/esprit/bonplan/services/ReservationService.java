/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.entities.User;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.entities.Reservation;
import static tn.esprit.bonplan.services.PromotionService.ds;
import tn.esprit.bonplan.util.DataSource;

/**
 *
 * @author mouin
 */
public class ReservationService {
    
    static DataSource ds =DataSource.getInstance(); 
    
    
    public static void insererReservation (Reservation r)
    {
    String req="INSERT INTO reservation (ref_etab,ref_user,ref_promo,coupon) VALUES(?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setInt(1,r.getRefEtab()) ; 
            ste.setInt(2,r.getRefUser()) ; 
            ste.setInt(3,r.getRefPromo());
            ste.setString(4,r.getCoupon());
          
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(
            ex
        );
        }
        
    
    }
    
        public static List<Reservation> selectReservation ()
    {
        List<Reservation> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT * FROM reservation ORDER BY ref_etab";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Reservation(
                                  
                                    result.getInt("id"),
                                     result.getInt("ref_etab"),
                                      result.getInt("ref_user"),

                                     result.getInt("ref_promo"),
                                    result.getString("coupon")
                                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    
    
}
