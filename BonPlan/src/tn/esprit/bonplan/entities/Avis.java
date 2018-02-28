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
public class Avis extends Publication {
    private int signalements;
    private int voteCount;
    private int reviewMark;
    private int establishmentId;
    
    public Avis(){
        
    }
    
    public Avis(String reviewText, int reviewMark, Etablissement e, User u){
        super(reviewText,u);
        this.establishmentId=e.getRef();
        this.voteCount=0;
        this.reviewMark=reviewMark;
        this.signalements=0;
    }

    public Avis(int id, String reviewText, int reviewMark, int voteCount, int userId, int establishmentId, int signalements){
        super(reviewText);
        this.id=id;
        this.reviewMark=reviewMark;
        this.voteCount=voteCount;
        this.establishmentId=establishmentId;
        this.userId=userId;
        this.signalements=signalements;
    }
    
    public int getSignalements(){
        return signalements;
    }
    public int getVoteCount(){
        return voteCount;
    }
    
    public int getEstablishmentId(){
        return establishmentId;
    }
    
    public int getEstablishmentMark(){
        return reviewMark;
    }
    
    public void setVoteCount(int newVoteCount){
        this.voteCount=newVoteCount;
    }
    public void setSignalements(int signalements){
        this.signalements=signalements;
    }
    
    public void setEstablishmentMark(int newEstablishmentMark){
        this.reviewMark=newEstablishmentMark;
    }
    
    public void setReviewMark(int reviewMark) {
        this.reviewMark = reviewMark;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Override public String toString(){
       return "Id : "+this.id+"\n"
               + "Note : "+this.reviewMark+"\n"
               + "Texte : "+this.pubText+"\n"
               + "Votes : "+this.voteCount+"\n";   
    }
    
}
