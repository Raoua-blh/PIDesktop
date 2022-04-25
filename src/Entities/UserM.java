package Entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EXTRA
 */
public class UserM {
    private int id,num_telf,nom_role_id;
    private String nom,prenom,pseudo,password,email,address_loc;

    public UserM() {
    }

    public UserM( int nom_role_id, String nom, String prenom, String pseudo, String password, String email,int num_telf, String address_loc) {
        this.num_telf = num_telf;
        this.nom_role_id = nom_role_id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.address_loc = address_loc;
    }

    public UserM(int id,  int nom_role_id, String nom, String prenom, String pseudo, String password, String email,int num_telf, String address_loc) {
        this.id = id;
        this.num_telf = num_telf;
        this.nom_role_id = nom_role_id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.address_loc = address_loc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_telf() {
        return num_telf;
    }

    public void setNum_telf(int num_telf) {
        this.num_telf = num_telf;
    }

    public int getNom_role_id() {
        return nom_role_id;
    }

    public void setNom_role_id(int nom_role_id) {
        this.nom_role_id = nom_role_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress_loc() {
        return address_loc;
    }

    public void setAddress_loc(String address_loc) {
        this.address_loc = address_loc;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", num_telf=" + num_telf + ", nom_role_id=" + nom_role_id + ", nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", password=" + password + ", email=" + email + ", address_loc=" + address_loc + '}';
    }
    
}
