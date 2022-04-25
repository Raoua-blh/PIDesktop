/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.UserM;
import Services.IService;
import utils.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EXTRA
 */
public class UserService implements IService<UserM> {
    Connection connection;
    Statement stm;
    public UserService(){
     connection=DB.getInstance().getConnexion();
}

    @Override
    public void ajouter(UserM t) {
       try {
       /* String req = "insert into role(libille) "
                + "values('"+t.getLibille()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
        String req1="insert into user (nom_role_id,nom,prenom,pseudo,password,email,num_telf,address_loc) values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps=connection.prepareStatement(req1);
        if((t.getNom()==null)||(t.getPrenom()==null)||(t.getEmail()==null)||(t.getPassword()==null)||(t.getAddress_loc()==null)||(t.getPseudo()==null))
                  System.out.println("please fill out the informations");
        else {
        ps.setString(2, t.getNom());
        ps.setString(3, t.getPrenom());
        ps.setString(4, t.getPseudo());
        ps.setString(5, t.getPassword());
        ps.setString(6, t.getEmail());
        ps.setString(8, t.getAddress_loc());
        ps.setInt(7, t.getNum_telf());
        ps.setInt(1, t.getNom_role_id());
        ps.executeUpdate();
    }} catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    }
    @Override
    public void modifier(UserM t,int id) {
                try{
         String req = "UPDATE user set nom='" +t.getNom()+"',prenom='"+t.getPrenom()+"',pseudo='"+t.getPseudo()+"',email='"+t.getEmail()+"',password='"+t.getPassword()+"',address_loc='"+t.getAddress_loc()+"',num_telf='"+t.getNum_telf()+"'  where `id`='"+ id+ "' ";
        stm = connection.createStatement();
        if((t.getNom()==null)||(t.getPrenom()==null)||(t.getEmail()==null)||(t.getPassword()==null)||(t.getAddress_loc()==null)||(t.getPseudo()==null))
                  System.out.println("please fill out the informations");
        else {
        stm.executeUpdate(req);}}
        catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @Override
    public void supprimer(int id) {
         try{
       String req = "delete from user where  `id`='"+id+"'";
        stm = connection.createStatement();
        
        stm.executeUpdate(req);
        }catch(SQLException ex) {
        System.out.println(ex.getMessage());
    }
    }

    @Override
    public List<UserM> afficher() {
              List<UserM> list = new ArrayList<>();
        try{
            String req = "select * from user";
            Statement st = connection.createStatement();
    
        ResultSet rs=st.executeQuery(req);
        while(rs.next())
        {
            UserM p = new UserM();
            p.setId(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setPseudo(rs.getString("pseudo"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setNom_role_id(rs.getInt("nom_role_id"));
            p.setAddress_loc(rs.getString("address_loc"));
            p.setNum_telf(rs.getInt("num_telf"));
            list.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        return list;
    }

  

    @Override
    public void modifierGuideTitre(int id, String titre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

