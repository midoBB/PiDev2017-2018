package tn.esprit.bonplan.entities;

import java.io.File;
import tn.esprit.bonplan.enumerations.CategorieEtablissement;


public class Etablissement {

    private int _Ref;
    private String _Nom;
    private CategorieEtablissement _Categorie;
    private String _Adresse;
    private String _Description;
    private String _Telephone;
    private String _Facebook;
    private String _Site;
    private String _Horaire;
    private String _Menu;
    private boolean _Verified;

    public int getRef() {
        return _Ref;
    }

    public void setRef(int value) {
        _Ref = value;
    }

    public String getNom() {
        return _Nom;
    }

    public void setNom(String value) {
        _Nom = value;
    }

    public CategorieEtablissement getCategorie() {
        return _Categorie;
    }

    public void setCategorie(CategorieEtablissement value) {
        _Categorie = value;
    }

    public Etablissement(String _Nom, CategorieEtablissement _Categorie, String _Adresse, String _Description, String _Telephone, String _Facebook, String _Site, String _Horaire, String _Menu, boolean _Verified) {
        this._Nom = _Nom;
        this._Categorie = _Categorie;
        this._Adresse = _Adresse;
        this._Description = _Description;
        this._Telephone = _Telephone;
        this._Facebook = _Facebook;
        this._Site = _Site;
        this._Horaire = _Horaire;
        this._Menu = _Menu;
        this._Verified = _Verified;
    }

    public Etablissement(int _Ref, String _Nom, CategorieEtablissement _Categorie, String _Adresse, String _Description, String _Telephone, 
            String _Facebook, String _Site, String _Horaire, String _Menu, boolean _Verified) {
        this._Ref = _Ref;
        this._Nom = _Nom;
        this._Categorie = _Categorie;
        this._Adresse = _Adresse;
        this._Description = _Description;
        this._Telephone = _Telephone;
        this._Facebook = _Facebook;
        this._Site = _Site;
        this._Horaire = _Horaire;
        this._Menu = _Menu;
        this._Verified = _Verified;
    }

    public Etablissement() {
    }

    public String getAdresse() {
        return _Adresse;
    }

    public void setAdresse(String value) {
        _Adresse = value;
    }

    public String getDescription() {
        return _Description;
    }

    public void setDescription(String value) {
        _Description = value;
    }

    public String getTelephone() {
        return _Telephone;
    }

    public void setTelephone(String value) {
        _Telephone = value;
    }

    public String getFacebook() {
        return _Facebook;
    }

    public void setFacebook(String value) {
        _Facebook = value;
    }

    public String getSite() {
        return _Site;
    }

    public void setSite(String value) {
        _Site = value;
    }

    public String getHoraire() {
        return _Horaire;
    }

    public void setHoraire(String value) {
        _Horaire = value;
    }

    public String getMenu() {
        return _Menu;
    }

    public void setMenu(String value) {
        _Menu = value;
    }

    public boolean getVerified() {
        return _Verified;
    }

    public void setVerified(boolean value) {
        _Verified = value;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "_Ref=" + _Ref + ", _Nom=" + _Nom + ", _Categorie=" + _Categorie + ", _Adresse=" + _Adresse + ", _Description=" + _Description + ", _Telephone=" + _Telephone + ", _Facebook=" + _Facebook + ", _Site=" + _Site + ", _Horaire=" + _Horaire + ", _Verified=" + _Verified + '}';
    }
    
}
