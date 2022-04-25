/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Guide;
import Entities.Reservation;
import Entities.UserM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DB;

/**
 *
 * @author USER
 */
public class ServiceReservation implements IService<Reservation> {

    Connection connexion;

    public ServiceReservation() {
        connexion = DB.getInstance().getConnexion();
    }

    @Override
    public void ajouter(Reservation t) {
        //  String req = "insert into reservation(guide_id,date_res,heure_debut,id_coach_id,idjoueur_id)values('" +t.getGuide()+ "','" + t.getDate_res() + "'," + t.getHeure_debut() + "'," + t.getCoach()+"'," + t.getJoueur()+" )";
        String req1 = "insert into reservation(guide_id,date_res,heure_debut,id_coach_id,idjoueur_id) values (?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = connexion.prepareStatement(req1);
            GuideService ps1 = new GuideService();
            for (Guide guide : ps1.afficherGuide()) {
                System.out.println((guide.getId()));
            }
            //ps.setInt(1, guide.get);
            //  System.out.println("guide id " +G.getId());

            ps.setInt(1, 2);
            ps.setString(2, t.getDate_res());
            ps.setString(3, t.getHeure_debut());
            ps.setInt(4, 2);
            ps.setInt(5, 1);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Reservation ADDED SUCCESFULLY !!");

            System.out.println(ex.getMessage());
        }
    }

    public void AjouterResObject(Reservation R, Guide g, UserM u , UserM J) {
        String req1 = "insert into reservation(guide_id,date_res,heure_debut,id_coach_id,idjoueur_id) values (?,?,?,?,?)";
        PreparedStatement ps;

        try {
            ps = connexion.prepareStatement(req1);
            ps.setInt(1, g.getId());
            ps.setString(2, R.getDate_res());
            ps.setString(3, R.getHeure_debut());
            ps.setInt(4, u.getId());
            ps.setInt(5, u.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Reservation> AfficherResObj() {
        List<Reservation> listRes = new ArrayList<>();
        try {
            String req = "select * from reservation";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reservation res = new Reservation();
                res.setId(rs.getInt("id"));
                // res.setGuideId(rs.getInt("guide_id"));
                res.setDate_res(rs.getString("date_res"));
                res.setHeure_debut(rs.getString("heure_debut"));
//                res.setIdJoueur(rs.getInt("idjoueur_id"));
//                res.setIdCoach(rs.getInt("id_coach_id"));

                String req2 = "select * from service_guide where id = " + rs.getInt("guide_id") + "";
                PreparedStatement st2 = connexion.prepareStatement(req2);

                ResultSet rs2 = st2.executeQuery(req2);

                Guide g = new Guide();
                while (rs2.next()) {
                    
                    g.setId(rs2.getInt("id"));
                    g.setTitre(rs2.getString("titre"));
                    g.setDescrp(rs2.getString("descrp"));
                    g.setNbHeure(rs2.getInt("nb_heure"));
                    g.setDateCreation(rs2.getString("date_creation"));
                    g.setPrice(rs2.getInt("prix"));
                    g.setJeux(String.valueOf(rs2.getObject("jeux_id")));
                    g.setCoach(String.valueOf(rs2.getObject("coach_id")));
                }

                res.setGuide(g);

                String req3 = "SELECT *  from user u  WHERE u.nom_role_id=3 AND u.id = " + rs.getInt("id_coach_id") + "";
                PreparedStatement st3 = connexion.prepareStatement(req3);
                //st3.setInt(1, rs.getInt("idRep"));
                ResultSet rs3 = st3.executeQuery(req3);
                UserM u = new UserM();
                while (rs3.next()) {

                    u.setId(rs3.getInt("id"));
                    u.setNom(rs3.getString("nom"));
                    u.setPrenom(rs3.getString("prenom"));
                    u.setPseudo(rs3.getString("pseudo"));
                    u.setEmail(rs3.getString("email"));
                    u.setPassword(rs3.getString("password"));
                    u.setNom_role_id(rs3.getInt("nom_role_id"));
                    u.setAddress_loc(rs3.getString("address_loc"));
                    u.setNum_telf(rs3.getInt("num_telf"));
                }
                res.setCoach(u);
                
                
                 String req4 = "SELECT *  from user u  WHERE u.nom_role_id=2 AND u.id = " + rs.getInt("idjoueur_id") + "";
                PreparedStatement st4 = connexion.prepareStatement(req3);
                //st3.setInt(1, rs.getInt("idRep"));
                ResultSet rs4 = st4.executeQuery(req3);
                UserM j = new UserM();
                while (rs4.next()) {

                    j.setId(rs4.getInt("id"));
                    j.setNom(rs4.getString("nom"));
                    j.setPrenom(rs4.getString("prenom"));
                    j.setPseudo(rs4.getString("pseudo"));
                    j.setEmail(rs4.getString("email"));
                    j.setPassword(rs4.getString("password"));
                    j.setNom_role_id(rs4.getInt("nom_role_id"));
                    j.setAddress_loc(rs4.getString("address_loc"));
                    j.setNum_telf(rs4.getInt("num_telf"));
                }
                res.setCoach(j);

                listRes.add(res);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listRes;
    }

    public ObservableList<Reservation> AfficherResObjj() {
  ObservableList<Reservation> listRes = FXCollections.observableArrayList();

      try {
            String req = "select * from reservation";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reservation res = new Reservation();
                res.setId(rs.getInt("id"));
                res.setDate_res(rs.getString("date_res"));
                res.setHeure_debut(rs.getString("heure_debut"));
                res.setIdJoueur(rs.getInt("idjoueur_id"));
                res.setIdCoach(rs.getInt("id_coach_id"));

                listRes.add(res);


            }
    } catch (SQLException ex) {
        }
    return listRes;
    }


    @Override
    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM reservation WHERE id = ? ";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setInt(1, id);
            int rsltUpdate = pst.executeUpdate();
            if (rsltUpdate == 0) {
                System.err.println("EXITE AUCUN ELEMENT CORRESPOND AU DONNEE SAISIE !! \n AUCUNE SUPPRESSION N'EST EFFECTUEE !!");
            } else {
                System.out.println("RESERVATION DELETED SUCCESFULLY !!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> afficher() {
        List<Reservation> listRes = new ArrayList<>();
        try {
            String req = "select * from reservation";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Reservation res = new Reservation();
                res.setId(rs.getInt("id"));
               // res.setGuideId(rs.getInt("guide_id"));
                res.setDate_res(rs.getString("date_res"));
                res.setHeure_debut(rs.getString("heure_debut"));
                res.setIdJoueur(rs.getInt("idjoueur_id"));
                res.setIdCoach(rs.getInt("id_coach_id"));

                listRes.add(res);
//                listRes.add(new Reservation((rs.getInt("id")),
//                (rs.getInt("guide_id")),
//                (rs.getString("date_res")),
//                (rs.getString("heure_debut")),
//               (rs.getString("idjoueur_id")),
//                (rs.getString("id_coach_id"))));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return listRes;
    }

    @Override
    public void modifierGuideTitre(int id, String date) {
        try {
            String req = "UPDATE reservation SET date_res = ? WHERE id = ?";
            PreparedStatement pst = connexion.prepareStatement(req);
            pst.setString(1, date);
            pst.setInt(2, id);
            pst.executeUpdate();
            System.err.println("MISE A JOUR FAITE AVEC SUCCEE !! ");
        } catch (SQLException ex) {
            System.err.println("UN ERREUR S'EST PRODUIT LORS DE LA MODIFICATION !! ");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation t, int id) {
      try{
       
          String req = "UPDATE reservation set date_res='" +t.getDate_res()+"',heure_debut='" +t.getHeure_debut()+"'  where `id`='"+ id+ "' ";
           PreparedStatement pst = connexion.prepareStatement(req);
        if((t.getDate_res()==null)||(t.getHeure_debut()==null))
        System.out.println("please fill out the informations");
        else {
        pst.executeUpdate(req);}}
        catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }    }

}
