/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MOUEFEK
 */
public class Articles {
    private int id,nom_jeux_id ; 
    private String titre , description , date , image ;

    public Articles(int id, int nom_jeux_id, String titre, String description, String date, String image) {
        this.id = id;
        this.nom_jeux_id = nom_jeux_id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public Articles(int nom_jeux_id, String titre, String description, String date, String image) {
        this.nom_jeux_id = nom_jeux_id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public Articles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNom_jeux_id() {
        return nom_jeux_id;
    }

    public void setNom_jeux_id(int nom_jeux_id) {
        this.nom_jeux_id = nom_jeux_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Articles{" + "id=" + id + ", nom_jeux_id=" + nom_jeux_id + ", titre=" + titre + ", description=" + description + ", date=" + date + ", image=" + image + '}';
    }
    
      
}
