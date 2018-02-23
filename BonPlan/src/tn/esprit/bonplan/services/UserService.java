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
import tn.esprit.bonplan.entities.User;
import tn.esprit.bonplan.util.DataSource;

/**
 *
 * @author mouin
 */
public class UserService {
    public static List<User> selectUser ()
    {
        List<User> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM user";
        try { 
            PreparedStatement ste = DataSource.getInstance().getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new User(
                                  
                                    result.getInt("Ref"),
                                    result.getString("Nom"),
                                    result.getString("Num"),
                                    result.getString("Mail")));
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
}
