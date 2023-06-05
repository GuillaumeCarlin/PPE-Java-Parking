package Controllers.Gestionnaire;

import Controllers.ControllerLogin;
import Controllers.Visiteur.ControllerListeReservations;
import Controllers.Visiteur.ControllerNewReservation;
import DAO.DAOParking;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import DAO.DAORole;
import Entity.Personne;
import Entity.Role;
import Utils.Singleton;
import views.FenetreLogin;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.Gestionnaire.FenetreListeReservationGestionnaire;
import views.Gestionnaire.FenetreUneReservation;
import views.Visiteur.FenetreReservation;
import views.Visiteur.FenetreReservations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class ControllerHomeGestionnaire {
    FenetreHomeGestionnaire fenetre;
    Personne personne;
    Role role;

    public ControllerHomeGestionnaire(FenetreHomeGestionnaire fenetre, DAOPersonne daoPersonne, Personne personne, Role role) {
        this.fenetre = fenetre;
        this.personne = personne;
        this.role = role;
    }

    public void init(){
        fenetre.setVisible(true);
        fenetre.getTextPanel().setText("Bienvenue " + personne.getNom() + " " + personne.getPrenom() + " " + role.getLibelle());


        fenetre.getBoutonPropreReservation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerNewReservation(new FenetreReservation(), new DAOParking(Singleton.getInstance().cnx),new DAOReservation(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
        });

        fenetre.getBoutonReservation().addActionListener(new ActionListener() {
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

        fenetre.getBoutonListePropreReservation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerListeReservations(new FenetreReservations(), new DAOReservation(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
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


        fenetre.getBoutonQuitter().addActionListener(new ActionListener() {
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
