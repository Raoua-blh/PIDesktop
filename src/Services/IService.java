/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author USER
 */
public interface IService<T> {
//T placeholder type de l'entite    

public void ajouter(T t);
public void supprimer(int id);
public List<T> afficher();
public void modifier(T t , int id);
public void modifierGuideTitre(int id , String titre);

}
