/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Guide;
import utils.DB;
import Entities.Jeux;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

/**
 *
 * @author MOUEFEK
 */
public class jeux_service implements IService<Jeux> {

    Connection connection;
    private Statement stm;

    public jeux_service() {
        connection = DB.getInstance().getConnexion();
    }

    @Override
    public void ajouter(Jeux t) {
        try {
            /*  String req = "insert into personne(nom,prenom,age) "
                + "values('"+t.getNom()+"','"+t.getPrenom()+"',"+t.getAge()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
            String req1 = "insert into jeux (nom_jeux,image_jeu) values(?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, t.getNomJeux());
            ps.setString(2, t.getImage());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Jeux t, int id) {
        try {
            String req = "UPDATE jeux set nom_jeux = '" + t.getNomJeux() + "' , image_jeu = '" + t.getImage() + "' where `id`='" + id + "' ";
            stm = connection.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from jeux  where `id`='" + id + "' ";
            stm = connection.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Jeux> afficher() {
        List<Jeux> list = new ArrayList<Jeux>();
        try {
            String req = "select * from jeux";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Jeux j = new Jeux();
                j.setId(rs.getInt("id"));
                j.setNomJeux(rs.getString("nom_jeux"));
                j.setImage(rs.getString("image_jeu"));
                list.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public Jeux afficherTitre() {
        List<Jeux> list = new ArrayList<Jeux>();
        Jeux j = new Jeux();
        try {
            String req = "select nom_jeux from jeux";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                j.setId(rs.getInt("id"));
                j.setNomJeux(rs.getString("nom_jeux"));
                j.setImage(rs.getString("image_jeu"));
                list.add(j);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return j;
    }

    @Override
    public void modifierGuideTitre(int id, String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Jeux afficherJeuxBuyId(int id) {
        Jeux j = new Jeux();
        try {
            String req = "select * from jeux WHERE id = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                j.setId(rs.getInt("id"));
                j.setNomJeux(rs.getString("nom_jeux"));
                j.setImage(rs.getString("image_jeu"));

                System.out.println(j.toString());

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return j;
    }

}
