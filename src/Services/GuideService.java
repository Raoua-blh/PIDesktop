/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Guide;
import Entities.Jeux;
import Entities.Reservation;
import Entities.User;
import Entities.UserM;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DB;

/**
 *
 * @author USER
 */
public class GuideService implements IGuide<Guide, Jeux, UserM> {

    Connection connexion;

    public GuideService() {
        connexion = DB.getInstance().getConnexion();
    }

    public void ajouterGuide(Guide t) {
//        String req = "insert into service_guide(titre,descrp,nb_heure,date_creation,prix)values('" + t.getTitre() + "','" + t.getDescrp() + "'," + t.getNbHeure() + "'," + t.getDateCreation() + "'," + t.getPrix() + " )";
//           Statement st =connexion.createStatement();// '' cuz nom de type varchar
//        
//                    String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);

        String req1 = "insert into service_guide(titre,descrp,nb_heure,date_creation,prix ,coach_id,jeux_id ) values (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            User u = new User();
            Jeux j = new Jeux();

            ps = connexion.prepareStatement(req1);
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getDescrp());
            ps.setInt(3, t.getNbHeure());
            ps.setString(4, t.getDateCreation());
            ps.setInt(5, t.getPrice());
            ps.setString(6, u.getNom());
            ps.setString(7, j.getNomJeux());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("GUIDE Failleddddddddd !!");

        }
    }

    @Override
    public void ajouterGuideObj(Guide t, Jeux j, UserM u) {
//        String req = "insert into service_guide(titre,descrp,nb_heure,date_creation,"
//                + "prix,coach_id,jeux_id,)values('" + t.getTitre() + "','" + t.getDescrp() +
//                "'," + t.getNbHeure() + "'," + t.getDateCreation() + "'," + t.getPrix() +"'," 
//                + t.getJeux()+ "'," + t.getCoach()+"')";
        String req1 = "insert into service_guide(titre,descrp,nb_heure,date_creation,prix,"
                + "coach_id,jeux_id) values (?,?,?,?,?,?,?)";
        PreparedStatement ps;
        try {

            ps = connexion.prepareStatement(req1);
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getDescrp());
            ps.setInt(3, t.getNbHeure());
            ps.setString(4, t.getDateCreation());
            ps.setInt(5, t.getPrice());
            ps.setObject(6, u.getId());
            ps.setObject(7, j.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("GUIDE FAILD  !!");

        }
    }

    @Override
    public void supprimerGuide(int id) {
        try {
            String requete = "DELETE FROM service_guide WHERE id = ? ";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setInt(1, id);
            int rsltUpdate = pst.executeUpdate();
            if (rsltUpdate == 0) {
                System.err.println("EXITE AUCUN ELEMENT CORRESPOND AU DONNEE SAISIE !! \n AUCUNE SUPPRESSION N'EST EFFECTUEE !!");
            } else {
                System.out.println("GUIDE DELETED SUCCESFULLY !!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Guide> afficherGuide() {
        List<Guide> listGuide = new ArrayList<>();
        try {
            String req = "select * from service_guide";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Guide p = new Guide();
                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescrp(rs.getString("descrp"));
                p.setNbHeure(rs.getInt("nb_heure"));
                System.out.println("nb" + rs.getInt("nb_heure"));
                p.setDateCreation(rs.getString("date_creation"));
                p.setPrice(rs.getInt("prix"));

//                p.setJeux(String.valueOf(rs.getObject("jeux_id")));
//                p.setCoach(String.valueOf(rs.getObject("coach_id")));
                // System.out.println(p.toString());
                String req2 = "select * from Jeux where id = " + rs.getInt("jeux_id") + "";
                PreparedStatement st2 = connexion.prepareStatement(req2);
                ResultSet rs2 = st2.executeQuery(req2);

                Jeux j = new Jeux();
                while (rs2.next()) {
                    j.setId(rs2.getInt("id"));
                    j.setNomJeux(rs2.getString("nom_jeux"));
                    j.setImage(rs2.getString("image_jeu"));

                }
                p.setJeu(j);

                String req3 = "select * from User where id = " + rs.getInt("coach_id") + "";
                PreparedStatement st3 = connexion.prepareStatement(req3);
                ResultSet rs3 = st3.executeQuery(req3);

                UserM coach = new UserM();
                while (rs3.next()) {

                    coach.setId(rs3.getInt("id"));
                    coach.setNom(rs3.getString("nom"));
                    coach.setPrenom(rs3.getString("prenom"));
                    coach.setPseudo(rs3.getString("pseudo"));
                    coach.setEmail(rs3.getString("email"));
                    coach.setPassword(rs3.getString("password"));
                    coach.setNom_role_id(rs3.getInt("nom_role_id"));
                    coach.setAddress_loc(rs3.getString("address_loc"));
                    coach.setNum_telf(rs3.getInt("num_telf"));

                }
                p.setUserM(coach);

                listGuide.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listGuide;
    }

    public Guide afficherGuideBuyId(int id) {
        Guide p = new Guide();
        try {
            String req = "select * from service_guide WHERE id = " + id;
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescrp(rs.getString("descrp"));
                p.setNbHeure(rs.getInt("nb_heure"));
                p.setDateCreation(rs.getString("date_creation"));
                p.setPrice(rs.getInt("prix"));

                p.setJeux(String.valueOf(rs.getObject("jeux_id")));
                p.setCoach(String.valueOf(rs.getObject("coach_id")));
                System.out.println(p.toString());

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }

    @Override
    public void modifierGuideTitre(int id, String titre) {
        try {
            String req = "UPDATE service_guide SET titre = ? WHERE id = ?";
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, titre);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.err.println("MISE A JOUR FAITE AVEC SUCCEE !! ");
        } catch (SQLException ex) {
            System.err.println("UN ERREUR S'EST PRODUIT LORS DE LA MODIFICATION !! ");
            System.err.println(ex.getMessage());
        }
    }

    public void modifier(Guide t, int id) {
        try {

            String req = "UPDATE service_guide set titre='" + t.getTitre() + "',descrp='" + t.getDescrp() + "',nb_heure='"
                    + t.getNbHeure() + "',date_creation='" + t.getDateCreation() + "',prix='" + t.getPrice() + "'  where `id`='" + id + "' ";
            PreparedStatement pst = connexion.prepareStatement(req);
            if ((t.getTitre() == null) || (t.getDescrp() == null) || (t.getDateCreation() == null)) {
                System.out.println("please fill out the informations");
            } else {
                pst.executeUpdate(req);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Guide> recherche(String searched) {

        List<Guide> lista = new ArrayList<>();
        try {

            String req = "select * from service_guide WHERE titre LIKE '%" + searched + "%' OR descrp LIKE '%" + searched + "%' OR prix LIKE '%" + searched + "%' OR nb_heure LIKE '%" + searched + "%' OR coach_id LIKE '%" + searched + "%' OR jeux_id LIKE '%" + searched + "%' ;";
            PreparedStatement pst = connexion.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Guide p = new Guide();
            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setTitre(rs.getString("titre"));
                p.setDescrp(rs.getString("descrp"));
                p.setNbHeure(rs.getInt("nb_heure"));
                p.setDateCreation(rs.getString("date_creation"));
                p.setPrice(rs.getInt("prix"));

                p.setJeux(String.valueOf(rs.getObject("jeux_id")));
                p.setCoach(String.valueOf(rs.getObject("coach_id")));

                lista.add(p);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Services.GuideService.recherche()" + ex.getMessage());
        }
        return null;

    }

}
