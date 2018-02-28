/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplan.entities;

/**
 *
 * @author Ali
 */
public class Commentaire {
    private int id;
    private String text;
    private int reviewId;
    private int userId;
    private int signalements;
    
    
    public Commentaire(Avis a, User u, String text){
        this.text=text;
        this.reviewId=a.getId();
        this.userId=u.getId();
        this.signalements=0;
    }
    
    public Commentaire(int id, String text, int reviewId, int userId, int signalements){
        this.id=id;
        this.text=text;
        this.reviewId=reviewId;
        this.userId=userId;
        this.signalements=signalements;
}
    
    public int getId(){
        return id;
    }
    
    public String getText(){
        return text;
    }
    
    public int getReviewId(){
        return reviewId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public int getSignalements(){
        return this.signalements;
    }

    public void setSignalements(int sig){
        this.signalements=sig;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public void setText(String text){
        this.text=text;
    }
    
    public void setReviewId(int reviewId){
        this.reviewId=reviewId;
    }
    
    public void setUserId(int userId){
        this.userId=userId;
    }
    
    
    @Override public String toString(){
        return "Id : "+id+"\n"
                +"User ID: "+userId+"\n"
                +"Review ID : "+reviewId+"\n"
                +"Text : "+text+"\n";
    }
}
