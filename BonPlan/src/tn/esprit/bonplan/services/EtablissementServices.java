/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import tn.esprit.bonplan.entities.Etablissement;
import tn.esprit.bonplan.enumerations.CategorieEtablissement;
import tn.esprit.bonplan.util.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamed
 */
public class EtablissementServices {

    static DataSource ds = DataSource.getInstance();

    public static void insertEtablissement(Etablissement e) throws SQLException, FileNotFoundException {
        String sql = "INSERT INTO etablissement (nom , categorie, adresse, description , telephone , facebook , site , horaire , menu , verified) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = ds.getConnection().prepareStatement(sql);
        statement.setString(1, e.getNom());
        statement.setInt(2, e.getCategorie().ordinal());
        statement.setString(3, e.getAdresse());
        statement.setString(4, e.getDescription());
        statement.setString(5, e.getTelephone());
        statement.setString(6, e.getFacebook());
        statement.setString(7, e.getSite());
        statement.setString(8, e.getHoraire());
        statement.setString(9,e.getMenu());
        statement.setBoolean(10, e.getVerified());
        statement.executeUpdate();
    }

    public static void updateEtablissement(Etablissement e) throws SQLException, FileNotFoundException {
        String sql = "UPDATE etablissement SET nom=? , categorie=? , adresse=? , description=? , telephone=? , facebook=? , site=? , horaire=? , menu=? , verified=? WHERE ref=?";

        PreparedStatement statement = ds.getConnection().prepareStatement(sql);
        statement.setString(1, e.getNom());
        statement.setInt(2, e.getCategorie().ordinal());
        statement.setString(3, e.getAdresse());
        statement.setString(4, e.getDescription());
        statement.setString(5, e.getTelephone());
        statement.setString(6, e.getFacebook());
        statement.setString(7, e.getSite());
        statement.setString(8, e.getHoraire());
        statement.setString(9, e.getMenu());
        statement.setBoolean(10, e.getVerified());
        statement.setInt(11, e.getRef());
        statement.executeUpdate();
    }

    public static void deleteEtablissement(Etablissement e) throws SQLException {
        String req = "DELETE  from etablissement where  ref =?";
        PreparedStatement ste = ds.getConnection().prepareStatement(req);
        ste.setInt(1, e.getRef());
        ste.executeUpdate();

    }

    public static List<Etablissement> selectEtablissements() throws SQLException, FileNotFoundException, IOException {
        List<Etablissement> list = new ArrayList<>();
        String req = "SELECT *  FROM etablissement";
        PreparedStatement ste = ds.getConnection().prepareStatement(req);
        ResultSet result = ste.executeQuery();
        while (result.next()) {
            list.add(new Etablissement(result.getInt("ref"), result.getString("nom"), CategorieEtablissement.valueOf(result.getInt("categorie")),
                    result.getString("adresse"), result.getString("description"), result.getString("telephone"), result.getString("facebook"),
                    result.getString("site"), result.getString("horaire"), result.getString("menu"), result.getBoolean("verified")));
        }
        return list;
    }
    public static Etablissement selectEtablissement(int id) throws SQLException, FileNotFoundException, IOException {
        List<Etablissement> list = new ArrayList<>();
        String req = "SELECT *  FROM etablissement where ref=?";
        PreparedStatement ste = ds.getConnection().prepareStatement(req);
        ste.setInt(1, id);
        ResultSet result = ste.executeQuery();
        while (result.next()) {
            list.add(new Etablissement(result.getInt("ref"), result.getString("nom"), CategorieEtablissement.valueOf(result.getInt("categorie")),
                    result.getString("adresse"), result.getString("description"), result.getString("telephone"), result.getString("facebook"),
                    result.getString("site"), result.getString("horaire"), result.getString("menu"), result.getBoolean("telephone")));
        }
        return list.get(0);
    }
}
