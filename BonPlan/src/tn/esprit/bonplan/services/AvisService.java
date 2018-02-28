/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.bonplan.entities.Avis;
import tn.esprit.bonplan.util.DataSource;

/**
 *
 * @author Ali
 */
public class AvisService {
    
    
    public static void insererAvis (Avis a)
    {
    String req="INSERT INTO avis VALUES(?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1, a.getUserId());
            ste.setString(2,a.getPubText()) ; 
            ste.setInt(3,a.getEstablishmentMark());
            ste.setInt(4, a.getEstablishmentId());
            ste.setInt(5,a.getId());
            ste.setInt(6, 0);
            ste.setInt(7, 0);
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    req="SELECT * FROM avis ORDER BY review_id DESC LIMIT 1";
        try{
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req);
            ResultSet result =ste.executeQuery() ;
            result.next();
            a.setId(result.getInt("review_id"));
        } catch (SQLException ex){
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    public static void updateAvis (Avis a,String newReviewText, int newEstablishmentMark)
    {
    String req="UPDATE avis SET review_text=?,review_mark=? WHERE review_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             
            ste.setString(1,newReviewText); 
            ste.setInt(2, newEstablishmentMark);
            ste.setInt(3, a.getId());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateAvisVotePlus (Avis a)
    {
    String req="UPDATE avis SET review_vote_count=? WHERE review_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             
            ste.setInt(1,a.getVoteCount()+1); 
            ste.setInt(2,a.getId());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateAvisVoteMoins (Avis a)
    {
    String req="UPDATE avis SET review_vote_count=? WHERE review_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             
            ste.setInt(1,a.getVoteCount()-1); 
            ste.setInt(2, a.getId());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateAvisSignalements (Avis a)
    {
    String req="UPDATE avis SET signalements=? WHERE review_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             
            ste.setInt(1,a.getSignalements()+1); 
            ste.setInt(2, a.getId());
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void DeleteAvis (Avis a)
    {
    String req="DELETE  from avis where  review_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1,a.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     }
     
   
    public static List<Avis> selectAvis()
    {
        List<Avis> list =new ArrayList<>() ; 
    String req="SELECT *  FROM avis" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Avis(result.getInt("review_id"),
                                result.getString("review_text"),
                                result.getInt("review_mark"),
                                result.getInt("review_vote_count"),
                                result.getInt("user_id"),
                                result.getInt("establishment_id"),
                                result.getInt("signalements"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list ; 
    }
    
    public static List<Avis> selectAvisByEtabId(int idEtab)
    {
        List<Avis> list =new ArrayList<>() ; 
    String req="SELECT *  FROM avis where establishment_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1, idEtab);
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Avis(result.getInt("review_id"),
                                result.getString("review_text"),
                                result.getInt("review_mark"),
                                result.getInt("review_vote_count"),
                                result.getInt("user_id"),
                                result.getInt("establishment_id"),
                                result.getInt("signalements"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list ; 
    }
    
    public static List<Avis> selectAvisByUserId(int idEtab)
    {
        List<Avis> list =new ArrayList<>() ; 
    String req="SELECT *  FROM avis where user_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1, idEtab);
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Avis(result.getInt("review_id"),
                                result.getString("review_text"),
                                result.getInt("review_mark"),
                                result.getInt("review_vote_count"),
                                result.getInt("user_id"),
                                result.getInt("establishment_id"),
                                result.getInt("signalements"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list ; 
    }
    
    public static Avis selectAvisById(int idEtab)
    {
        Avis a = new Avis();
        String req="SELECT *  FROM avis where user_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1, idEtab);
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            a.setId(result.getInt("review_id"));
            a.setPubText(result.getString("review_text"));
            a.setEstablishmentMark(result.getInt("review_mark"));
            a.setVoteCount(result.getInt("review_vote_count"));
            a.setUserId(result.getInt("user_id"));
            a.setEstablishmentId(result.getInt("establishment_id"));
            a.setSignalements(result.getInt("signalements")); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ; 
    }
}

