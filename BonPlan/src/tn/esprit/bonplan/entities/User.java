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
public class User {
    
    private int ref ;
    private String Nom;
    private String Num;
    private String Mail;

    public User(int ref, String Nom, String Num, String Mail) {
        this.ref = ref;
        this.Nom = Nom;
        this.Num = Num;
        this.Mail = Mail;
    }
    
    
    
    

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }
    

  

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String Num) {
        this.Num = Num;
    }

    @Override
    public String toString() {
        return "User{" + "ref=" + ref + ", Nom=" + Nom + ", Num=" + Num + ", Mail=" + Mail + '}';
    }

  
    
    
    
}
