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
public class Jeux extends Object {
    int id ;
    String nomJeux ;
    String Image ; 

    public Jeux(int id, String nomJeux, String Image) {
        this.id = id;
        this.nomJeux = nomJeux;
        this.Image = Image;
    }

    public Jeux() {
    }

    public Jeux(String nomJeux, String Image) {
        this.nomJeux = nomJeux;
        this.Image = Image;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomJeux() {
        return nomJeux;
    }

    public void setNomJeux(String nomJeux) {
        this.nomJeux = nomJeux;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    @Override
    public String toString() {
        return "Jeux{" + "id=" + id + ", nomJeux=" + nomJeux + ", Image=" + Image + '}';
    }
    
}
