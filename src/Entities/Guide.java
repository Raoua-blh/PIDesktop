/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author USER
 */
public class Guide {

    private int NbHeure, id, jeuxId, coachId;
    private Float prix;
    private String titre, Descrp;
    private String dateCreation, jeux, coach;
    private Image jeuxImg;
    private Jeux jeu;
    private UserM user ;
    private int price;

    public Guide() {
    }

    public Jeux getJeu() {
        return jeu;
    }

    public Guide(int id, String titre, String Descrp, String dateCreation, int price, String jeux, String coach) {
        this.id = id;
        this.titre = titre;
        this.Descrp = Descrp;
        this.dateCreation = dateCreation;
        this.jeux = jeux;
        this.coach = coach;
        this.price = price;
    }

    public Guide(String titre, String Descrp, int NbHeure, String dateCreation, int price, String jeux, String coach) {
        this.NbHeure = NbHeure;

        this.titre = titre;
        this.Descrp = Descrp;
        this.dateCreation = dateCreation;
        this.jeux = jeux;
        this.coach = coach;
        this.price = price;
    }

    public Guide(String titre, String Descrp, int NbHeure, String dateCreation, int price, int jeux, int coach) {
        this.NbHeure = NbHeure;

        this.titre = titre;
        this.Descrp = Descrp;
        this.dateCreation = dateCreation;
        this.jeuxId = jeux;
        this.coachId = coach;
        this.price = price;
    }

    public void setJeu(Jeux jeu) {
        this.jeu = jeu;
    }

    public UserM getUser() {
        return user;
    }

    public void setUserM(UserM user) {
        this.user = user;
    }

 

    public Guide(String titre, String Descrp, int NbHeure, String dateCreation, int price, Jeux jeu, UserM user) {

        this.titre = titre;
        this.Descrp = Descrp;
        this.NbHeure = NbHeure;
        this.dateCreation = dateCreation;
        this.price = price;
        this.jeu = jeu;
        this.user = user;
    }
     public Guide(int id , String titre, String Descrp, int NbHeure, String dateCreation, int price, Jeux jeu, UserM user) {
         this.id=id;
        this.titre = titre;
        this.Descrp = Descrp;
        this.NbHeure = NbHeure;
        this.dateCreation = dateCreation;
        this.price = price;
        this.jeu = jeu;
        this.user = user;
    }
       public Guide(int id , String titre, String Descrp, int NbHeure, String dateCreation, int price, String jeux, String coach) {
         this.id=id;
        this.titre = titre;
        this.Descrp = Descrp;
        this.NbHeure = NbHeure;
        this.dateCreation = dateCreation;
        this.price = price;
        this.jeu = jeu;
        this.user = user;
    }
    
    
    public Guide(String titre, String Descrp, int NbHeure, String dateCreation, int price) {

        this.titre = titre;
        this.Descrp = Descrp;
        this.NbHeure = NbHeure;
        this.dateCreation = dateCreation;
        this.price = price;
       
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbHeure() {
        return NbHeure;
    }

    public void setNbHeure(int NbHeure) {
        this.NbHeure = NbHeure;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescrp() {
        return Descrp;
    }

    public void setDescrp(String Descrp) {
        this.Descrp = Descrp;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getJeuxId() {
        return jeuxId;
    }

    public void setJeuxId(int jeuxId) {
        this.jeuxId = jeuxId;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getJeux() {
        return jeux;
    }

    public void setJeux(String jeux) {
        this.jeux = jeux;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Image getJeuxImg() {
        return jeuxImg;
    }

    public void setJeuxImg(Image jeuxImg) {
        this.jeuxImg = jeuxImg;
    }

    @Override
    public String toString() {
        return "ServiceGuide{" + "titre=" + titre + ", Descrp=" + Descrp + ", dateCreation=" + dateCreation + ", jeux=" + jeux + ", coach=" + coach + ", jeuxImg=" + jeuxImg + '}';
    }

}
