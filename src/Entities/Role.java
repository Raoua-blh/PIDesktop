/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author EXTRA
 */
public class Role {
    private int id;
    private String libille;

    public Role(int id, String libille) {
        this.id = id;
        this.libille = libille;
    }

    public Role() {
    }

    public Role(String libille) {
        this.libille = libille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibille() {
        return libille;
    }

    public void setLibille(String libille) {
        this.libille = libille;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", libille=" + libille + '}';
    }
    
}
