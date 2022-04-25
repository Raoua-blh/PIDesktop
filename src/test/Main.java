/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entities.Guide;
import Entities.Jeux;
import Entities.UserM;
import Services.GuideService;
import Services.UserService;
import Services.jeux_service;


/**
 *
 * @author USER
 */
public class Main {
    public static void main(String[] args) {
        GuideService ps = new GuideService();
        jeux_service js = new jeux_service();
    //     js.ajouter(new Jeux("lolll", "haha.png"));
        js.afficher();
        UserService us = new UserService();
        us.afficher();
        UserM uss = us.afficher().get(2);
        Jeux jj = js.afficher().get(3);
        
        System.out.println("user 2 " + uss.getId() + uss.getNom());
        System.out.println("jeux 2 " + jj.getId() + jj.getNomJeux());
        
        ps.ajouterGuideObj(new Guide("Newe", "test ", 24, "2022-04-20", 125), jj, uss);
         
//        ps.ajouterGuide(new Guide("GuideDesktop", "hello from javaFx",24,"12/02/2220",125));
//        System.out.println(ps.afficherGuide());
//        ps.supprimerGuide(3);
//        System.out.println("supprimer");
//        
        System.out.println(ps.afficherGuide());
//        ps.modifierGuideTitre(8, "Halima");
//         System.out.println(ps.afficherGuide());
    }

    
}
