/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import javax.xml.bind.DatatypeConverter;
import tn.esprit.bonplan.entities.Promotion;
import tn.esprit.bonplan.util.DataSource;



/**
 *
 * @author Guideinfo
 */
public class PromotionService {

    static DataSource ds =DataSource.getInstance(); 
    
    
    public static void insererPromotion (Promotion p)
    {
    String req="INSERT INTO promotion (produit,ref_etab,date_debut,date_fin,description,image,prix,cota,prix_promo,coupon,coupon_dispo) VALUES(?,?,?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,p.getProduit()) ; 
            ste.setString(2,p.getRefEtab()) ; 
            ste.setString(3,p.getDateDebut());
            ste.setString(4,p.getDateFin());
            ste.setString(5,p.getDescription());
            ste.setString(6,p.getImage());
            ste.setFloat(7,p.getPrix()) ;
            ste.setInt(8,p.getCota()) ;
           ste.setFloat(9,p.getPrix() - (p.getPrix()*p.getCota()/100));
            ste.setInt(10,p.getCoupon()) ;
             ste.setInt(11, p.getCoupon());
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(
            ex
        );
        }
    
    }
       public static void insererArPromotion (Promotion p)
    {
    String req="INSERT INTO archivepr (produit,ref_etab,date_debut,date_fin,description,image,prix,cota,prix_promo,coupon,coupon_dispo) VALUES(?,?,?,?,?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,p.getProduit()) ; 
            ste.setString(2,p.getRefEtab()) ; 
            ste.setString(3,p.getDateDebut());
            ste.setString(4,p.getDateFin());
            ste.setString(5,p.getDescription());
            ste.setString(6,p.getImage());
            ste.setFloat(7,p.getPrix()) ;
            ste.setInt(8,p.getCota()) ;
           ste.setFloat(9,p.getPrix() - (p.getPrix()*p.getCota()/100));
            ste.setInt(10,p.getCoupon()) ;
             ste.setInt(11, p.getCoupon());
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(
            ex
        );
        }
    
    }
     
    public static void updatePromotion (Promotion p )
    {
    String req="UPDATE promotion SET produit=?,ref_etab=?,date_debut=?,date_fin=?,description=?,image=?,prix=?,cota=?,coupon=?,coupon_dispo=?,prix_promo=? WHERE ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,p.getProduit()) ; 
            ste.setString(2,p.getRefEtab()) ; 
            ste.setString(3,p.getDateDebut());
            ste.setString(4,p.getDateFin());
            ste.setString(5,p.getDescription());
            ste.setString(6,p.getImage());
               ste.setFloat(7,p.getPrix()) ; 
            ste.setInt(8,p.getCota()) ; 
            ste.setInt(9,p.getCoupon()) ; 
             ste.setInt(10,p.getCouponDispo()) ; 
            ste.setFloat(11,p.getPrix() - (p.getPrix()*p.getCota()/100));
            
            ste.setInt(12,p.getRef()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     public static void DeletPromotion (Promotion p)
    {
    String req="DELETE  from promotion where  ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,p.getRef()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
      public static void DeletPromotionByID (int e)
    {
    String req="DELETE  from promotion where  ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,e) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
      }
     
    public static List<Promotion> selectPromotion ()
    {
        List<Promotion> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM promotion";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Promotion(
                                  
                                    result.getInt("ref"),
                                    result.getString("produit"),
                                    result.getString("ref_etab"),
                                    result.getString("date_debut"),
                                    result.getString("date_fin"),
                                    result.getString("description"),
                                    result.getString("image"),
                                    result.getFloat("prix"),
                                    result.getInt("cota"),
                                    result.getFloat("prix_promo"),
                                    result.getInt("coupon"),
                                    result.getInt("coupon_dispo")
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
  
     
    
    public static void  participer (Promotion p){
        if(p.getCouponDispo() > 2){
            p.setCouponDispo(p.getCouponDispo()-1);
            updatePromotion(p);
        }
        else
            DeletPromotion(p);
    }
    public static String genererHash() throws NoSuchAlgorithmException{
      MessageDigest instance = MessageDigest.getInstance("MD5");
byte[] messageDigest = instance.digest(String.valueOf(System.nanoTime()).getBytes());
StringBuilder hexString = new StringBuilder();
for (int i = 0; i < messageDigest.length; i++) {
    String hex = Integer.toHexString(0xFF & messageDigest[i]);
    if (hex.length() == 1) {
        // could use a for loop, but we're only dealing with a single
        // byte
        hexString.append('0');
    }
    hexString.append(hex);
}
     
return hexString.toString();

    }
       public static void Archive(Promotion p){
    
    if ( LocalDate.parse(p.getDateFin()).compareTo(LocalDate.now())<0){
        insererArPromotion(p);
        
        DeletPromotion(p);
        
    }
    
        
    
    } 
    
}
