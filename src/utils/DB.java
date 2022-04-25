/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class DB {

    private String url = "jdbc:mysql://localhost:3306/finalclutch";
    private String UserName = "root";
    private String Password = "";
    private Connection connexion;
    static DB instance;

    public DB() {
        try {
            connexion = DriverManager.getConnection(url, UserName, Password);
        } catch (SQLException ex) {
                        System.out.println("connection succ"
                                + "");

            System.out.println(ex.getMessage());
        }
    }

    //instancier la base une seuul fois grace a ce design pattern senglotan
    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return (instance);

    }
// pour executer les requette sql pour manipuler la table de base donnée
    public Connection getConnexion() {
        return connexion;
    }

}

