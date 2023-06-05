package Controllers.Admin;

import Controllers.ControllerLogin;
import Controllers.Gestionnaire.ControllerListeReservationGestionnaire;
import Controllers.Gestionnaire.ControllerUneReservation;
import DAO.*;
import Entity.Personne;
import Entity.Role;
import Utils.Singleton;
import views.Admin.FenetreHomeAdmin;
import views.Admin.FenetreListeLog;
import views.Admin.FenetreListeUtilisateur;
import views.FenetreLogin;
import views.Gestionnaire.FenetreListeReservationGestionnaire;
import views.Gestionnaire.FenetreUneReservation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class ControllerHomeAdmin {
    FenetreHomeAdmin fenetre;
    Personne personne;
    Role role;

    public ControllerHomeAdmin(FenetreHomeAdmin fenetre, Personne personne, Role role) {
        this.fenetre = fenetre;
        this.personne = personne;
        this.role = role;
    }

    public void init(){
        fenetre.setVisible(true);
        System.out.println("Init");
        fenetre.getTextPanel().setText("Bienvenue " + personne.getNom() + " " + personne.getPrenom() + " " + role.getLibelle());

        fenetre.getBoutonUneReservation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerUneReservation(new FenetreUneReservation(), new DAOParking(Singleton.getInstance().cnx), new DAOReservation(Singleton.getInstance().cnx), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
        });

        fenetre.getBoutonListeReservation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerListeReservationGestionnaire(new FenetreListeReservationGestionnaire(), new DAOReservation(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
        });

        fenetre.getLogButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerLog(new FenetreListeLog(), new DAOLog(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
        });

        fenetre.getListeDesUtilisateursButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerListeUser(new FenetreListeUtilisateur(), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
        });

        fenetre.getQuitterBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FenetreLogin myFrame = new FenetreLogin();
                    new ControllerLogin(myFrame,  new DAOPersonne(Singleton.getInstance().cnx), new DAORole(Singleton.getInstance().cnx)).init();
                    fenetre.setVisible(false);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}
