/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Guide;
import Entities.Jeux;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DB;

/**
 *
 * @author USER
 */
public class JeuxService implements IService<Jeux> {

    Connection connexion;

    public JeuxService() {
        connexion = DB.getInstance().getConnexion();
    }

    @Override
    public void ajouter(Jeux t) {
        String req1 = "insert into Jeux(nom_jeux,image_jeu) values (?,?)";
        PreparedStatement ps;
        try {

            ps = connexion.prepareStatement(req1);
            ps.setString(1, t.getNomJeux());
            ps.setString(2, t.getImage());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Jeux ADDED SUCCESFULLY !!");

        }
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Jeux> afficher() {
        List<Jeux> listJeux = new ArrayList<>();
        try {

            String req = "select * from Jeux";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Jeux p = new Jeux();
                p.setId(rs.getInt("id"));
                p.setNomJeux(rs.getString("nom_jeux"));
                p.setImage(rs.getString("image_jeu"));

                listJeux.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listJeux;
    }

    @Override
    public void modifierGuideTitre(int id, String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Jeux t, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
