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
public class Promotion {

    private int _Ref;
    private String _Produit;
    private String Etab;
    private String _DateDebut;
    private String _DateFin;
    private String _Description;
    private String _Image;
    private float prix;
    private int _Cota;
    private float prix_promo;
    private int coupon;
    private int _CouponDispo;

    public Promotion(int _Ref, String _Produit, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image, float prix,int _Cota, float prix_promo ,int coupon ,int _CouponDispo) {
        this._Ref = _Ref;
        this._Produit = _Produit;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
        this.prix=prix;
        this._Cota = _Cota;
        this.prix_promo =prix_promo;
        this.coupon = coupon;
        this._CouponDispo = _CouponDispo;
        
    }
       public Promotion( String _Produit, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image, int prix,int _Cota, int prix_promo ,int coupon ,int _CouponDispo) {
       
           
        this._Produit = _Produit;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
        this.prix=prix;
        this._Cota = _Cota;
        this.prix_promo =prix_promo;
        this.coupon = coupon;
        this._CouponDispo = _CouponDispo;
        
    }
    
    

    public int getRef() {
        return _Ref;
    }

    public void setRef(int value) {
        _Ref = value;
    }

    public String getProduit() {
        return _Produit;
    }

    public void setProduit(String value) {
        _Produit = value;
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

    public String getImage() {
        return _Image;
    }

    public void setImage(String value) {
        _Image = value;
    }

    public int getCota() {
        return _Cota;
    }

    public void setCota(int value) {
        _Cota = value;
    }

    public int getCouponDispo() {
        return _CouponDispo;
    }

    public void setCouponDispo(int value) {
        _CouponDispo = value;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(float prix_promo) {
        this.prix_promo = prix_promo;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public Promotion(String _Produit, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image, float prix, int _Cota, float prix_promo, int _CouponDispo) {
        this._Produit = _Produit;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
        this.prix = prix;
        this._Cota = _Cota;
        this.prix_promo = prix_promo;
        this._CouponDispo = _CouponDispo;
    }

    public Promotion(String _Produit, String Etab, String _DateDebut, String _DateFin, String _Description, String _Image, float prix, int _Cota, int coupon) {
        this._Produit = _Produit;
        this.Etab = Etab;
        this._DateDebut = _DateDebut;
        this._DateFin = _DateFin;
        this._Description = _Description;
        this._Image = _Image;
        this.prix = prix;
        this._Cota = _Cota;
        this.coupon = coupon;
    }
   
    @Override
    public String toString() {
        return "Promotion{" + "_Produit=" + _Produit + ", Etab=" + Etab + ", _DateDebut=" + _DateDebut + ", _DateFin=" + _DateFin + ", _Description=" + _Description + ", _Image=" + _Image + ", prix=" + prix + ", _Cota=" + _Cota + ", prix_promo=" + prix_promo + ", coupon=" + coupon + ", _CouponDispo=" + _CouponDispo + '}';
    }

    
    
    
}
