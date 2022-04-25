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
public class User {
    int id , roleid , isverified, level , points , telf ;
    String nom , prenom, pseudo , mail , password , adressLocal;

    public User(int id, int roleid, int isverified, int level, int points, int telf, String nom, String prenom, String pseudo, String mail, String password, String adressLocal) {
        this.id = id;
        this.roleid = roleid;
        this.isverified = isverified;
        this.level = level;
        this.points = points;
        this.telf = telf;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mail = mail;
        this.password = password;
        this.adressLocal = adressLocal;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getIsverified() {
        return isverified;
    }

    public void setIsverified(int isverified) {
        this.isverified = isverified;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdressLocal() {
        return adressLocal;
    }

    public void setAdressLocal(String adressLocal) {
        this.adressLocal = adressLocal;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", roleid=" + roleid + ", isverified=" + isverified + ", level=" + level + ", points=" + points + ", telf=" + telf + ", nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", mail=" + mail + ", password=" + password + ", adressLocal=" + adressLocal + '}';
    }
    
    
}
