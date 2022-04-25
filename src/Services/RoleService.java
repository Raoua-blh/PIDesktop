/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Services.IService;
import utils.DB;
import entities.Role;
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
public class RoleService implements IService <Role>{
    Connection connection;
    Statement stm;
   public RoleService(){
        connection=DB.getInstance().getConnexion();
   }
   @Override
    public void ajouter(Role t) {
    try {
       /* String req = "insert into role(libille) "
                + "values('"+t.getLibille()+")";
        Statement st = connection.createStatement();
       st.executeUpdate(req);*/
        String req1="insert into role (libille) values(?)";
        PreparedStatement ps=connection.prepareStatement(req1);
        if(t.getLibille()==null)
            System.out.println("libille is empty try again");
        else{
        ps.setString(1, t.getLibille());
        ps.executeUpdate();}
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    }

    @Override
    public void modifier(Role t,int id) {
        try{
         String req = "UPDATE role set libille='" +t.getLibille()+"'  where `id`='"+ id+ "' ";
        stm = connection.createStatement();
        if(t.getLibille()==null)
            System.out.println("libille is empty try again");
        else{
        stm.executeUpdate(req);}}
        catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
    }

    @Override
    public void supprimer(int id) {
        try{
       String req = "delete from role where  `id`='"+id+"'";
        stm = connection.createStatement();
        stm.executeUpdate(req);
        }catch(SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
        }

    @Override
    public List<Role> afficher() {
        List<Role> list = new ArrayList<>();
        try{
            String req = "select * from role";
            Statement st = connection.createStatement();
    
        ResultSet rs=st.executeQuery(req);
        while(rs.next())
        {
            Role p = new Role();
            p.setId(rs.getInt("id"));
            p.setLibille(rs.getString("libille"));
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
