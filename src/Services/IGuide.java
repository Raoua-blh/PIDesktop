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
public interface IGuide<T ,J ,U> {
    public void ajouterGuideObj(T t,J j ,U u );
public void supprimerGuide(int id);
public List<T> afficherGuide();
public void modifierGuideTitre(int id , String titre);

}
