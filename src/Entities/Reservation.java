/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author USER
 */
public class Reservation {
    private int id , guideId , idJoueur , idCoach;
    private String date_res ;
    private String heure_debut , Joueur , Coach , Guide ;
    private Guide guide ; 
    private UserM coach , player ;

    public Reservation(int id, String dateRes, String HeureDebut) {
        this.id = id;
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
    }

    public Reservation( String dateRes, String HeureDebut) {
       
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
    }

    public Reservation(int id, Guide guide, String dateRes, String HeureDebut, UserM coach, UserM player) {
        this.id = id;
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
        this.guide = guide;
        this.coach = coach;
        this.player = player;
    }
 public Reservation( Guide guide, String dateRes, String HeureDebut, UserM coach, UserM player) {
       
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
        this.guide = guide;
        this.coach = coach;
        this.player = player;
    }

    public Reservation(int id,String Guide,String dateRes,String HeureDebut,String Coach, String Joueur) {
        this.id = id;
        this.Guide = Guide;
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
        this.Joueur = Joueur;
        this.Coach = Coach;

    }
     public Reservation(int id,int Guide,String dateRes,String HeureDebut,String Coach, String Joueur) {
        this.id = id;
        this.guideId = Guide;
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
        this.Joueur = Joueur;
        this.Coach = Coach;

    }
 public Reservation(int id,int Guide,String dateRes,String HeureDebut,int Coach, int Joueur) {
        this.id = id;
        this.guideId = Guide;
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
        this.idJoueur = Joueur;
        this.idCoach = Coach;

    }
    public Reservation(String Guide, String dateRes, String HeureDebut) {
        this.date_res = dateRes;
        this.heure_debut = HeureDebut;
        this.Guide = Guide;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public Reservation() {
    }

  

  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_res() {
        return date_res;
    }

    public void setDate_res(String date_res) {
        this.date_res = date_res;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

  

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public UserM getCoach() {
        return coach;
    }

    public void setCoach(UserM coach) {
        this.coach = coach;
    }

    public UserM getPlayer() {
        return player;
    }

    public void setPlayer(UserM player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", guideId=" + guideId + ", idJoueur=" + idJoueur + ", idCoach=" + idCoach + ", date_res=" + date_res + ", heure_debut=" + heure_debut + ", Joueur=" + Joueur + ", Coach=" + Coach + ", Guide=" + Guide + ", guide=" + guide + ", coach=" + coach + ", player=" + player + '}';
    }


    

    
}
