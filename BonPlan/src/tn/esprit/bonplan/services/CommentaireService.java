/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.services;

import tn.esprit.bonplan.entities.Commentaire;
import tn.esprit.bonplan.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ali
 */
public class CommentaireService {
    
    
    public static void insererCommentaire (Commentaire c)
    {
    String req="INSERT INTO commentaires VALUES(?,?,?,?)" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1, c.getId());
            ste.setInt(2, c.getUserId());
            ste.setInt(3,c.getReviewId()) ; 
            ste.setString(4,c.getText());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    req="SELECT comment_id FROM commentaires ORDER BY comment_id DESC LIMIT 1";
        try{
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req);
            ResultSet result =ste.executeQuery() ;
            result.next();
            c.setId(result.getInt(1));
        } catch (SQLException ex){
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    public static void updateCommentaire (Commentaire c,String newCommentText)
    {
    String req="UPDATE commentaires SET comment_text=? WHERE comment_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             
            ste.setString(1,newCommentText); 
            ste.setInt(2,c.getId());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void deleteCommentaire (Commentaire c)
    {
    String req="DELETE  from commentaires where  comment_id =?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1,c.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
     }
     
   
    public static List<Commentaire> selectCommentaire()
    {
        List<Commentaire> list =new ArrayList<>() ; 
    String req="SELECT *  FROM commentaires" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Commentaire(result.getInt("comment_id"),
                                result.getString("comment_text"),
                                result.getInt("review_id"),
                                result.getInt("user_id"),
                                result.getInt("signalements"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list ; 
    }
    
    public static List<Commentaire> selectCommentaireByRevId(int idRev)
    {
        List<Commentaire> list =new ArrayList<>() ; 
    String req="SELECT *  FROM commentaires WHERE review_id=?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1,idRev) ;
            ResultSet result =ste.executeQuery(); 
            while (result.next()){
            list.add(new Commentaire(result.getInt("comment_id"),
                                result.getString("comment_text"),
                                result.getInt("review_id"),
                                result.getInt("user_id"),
                                result.getInt("signalements"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list ; 
    }
    
    public static List<Commentaire> selectCommentaireByUserId(int idRev)
    {
        List<Commentaire> list =new ArrayList<>() ; 
    String req="SELECT *  FROM commentaires WHERE user_id=?" ; 
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
            ste.setInt(1,idRev) ;
            ResultSet result =ste.executeQuery(); 
            while (result.next()){
            list.add(new Commentaire(result.getInt("comment_id"),
                                result.getString("comment_text"),
                                result.getInt("review_id"),
                                result.getInt("user_id"),
                                result.getInt("signalements"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list ; 
    }
}
