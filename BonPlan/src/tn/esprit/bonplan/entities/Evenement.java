/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.entities;



/**
 *
 * @author mouin
 */
public class Evenement {

    private int _Ref;
    private String _Nom;
    private String Etab;
    private String _DateDebut;
    private String _DateFin;
    private String _Description;
    private String _Image;

    public Evenement(int _Ref, String _Nom, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image) {
        this._Ref = _Ref;
        this._Nom = _Nom;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
    }
     public Evenement( String _Nom, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image) {
        this._Nom = _Nom;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
    }

    public Evenement(String text, String text0, String toString, String toString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Evenement(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    public String getRefEtab() {
        return Etab;
    }

    public void setRefEtab(String value) {
        Etab = value;
    }

    public String getDateDebut() {
        return _DateDebut;
    }

    public void setDateDebut(String value) {
        _DateDebut = value;
    }

    public String getDateFin() {
        return _DateFin;
    }

    public void setDateFin(String value) {
        _DateFin = value;
    }

    public String getDescription() {
        return _Description;
    }

    public void setDescription(String value) {
        _Description = value;
    }

    public String getIimage() {
        return _Image;
    }

    public void setImage(String value) {
        _Image = value;
    }

    @Override
    public String toString() {
        return "Evenement{" + "_Ref=" + _Ref + ", _Nom=" + _Nom + ", Etab=" + Etab + ", _DateDebut=" + _DateDebut + ", _DateFin=" + _DateFin + ", _Description=" + _Description + ", _Image=" + _Image + '}';
    }
    
}
