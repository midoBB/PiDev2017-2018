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
public class Reservation {

    private int _Id;
    private int _RefEtab;
    private int _RefUser;
    private int _RefPromo;
    private String coupon ;

    public Reservation(int _Id, int _RefEtab, int _RefUser, int _RefPromo, String coupon) {
        this._Id = _Id;
        this._RefEtab = _RefEtab;
        this._RefUser = _RefUser;
        this._RefPromo = _RefPromo;
        this.coupon = coupon;
    }

    public Reservation(int _RefEtab, int _RefUser, int _RefPromo, String coupon) {
        this._RefEtab = _RefEtab;
        this._RefUser = _RefUser;
        this._RefPromo = _RefPromo;
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "Reservation{" + "_Id=" + _Id + ", _RefEtab=" + _RefEtab + ", _RefUser=" + _RefUser + ", _RefPromo=" + _RefPromo + ", coupon=" + coupon + '}';
    }
    
    
    
    
    
    
    
    

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

   
    
    
    

    public int getId() {
        return _Id;
    }

    public void setId(int value) {
        _Id = value;
    }

    public int getRefEtab() {
        return _RefEtab;
    }

    public void setRefEtab(int value) {
        _RefEtab = value;
    }

    public int getRefUser() {
        return _RefUser;
    }

    public void setRefUser(int value) {
        _RefUser = value;
    }

    public int getRefPromo() {
        return _RefPromo;
    }

    public void setRefPromo(int value) {
        _RefPromo = value;
    }
}
