package Controllers.Visiteur;

import DAO.DAOParking;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import Entity.Personne;
import Entity.Role;
import views.Visiteur.FenetreHomeVisiteur;
import views.Visiteur.FenetreReservation;
import views.Visiteur.FenetreReservations;
import  Utils.Singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

public class ControllerHomeVisiteur {
    FenetreHomeVisiteur fenetre;
    DAOPersonne daop;
    Personne personne;
    Role Role;

    public ControllerHomeVisiteur(FenetreHomeVisiteur fenetre, DAOPersonne daop, Personne personne, Role role){
        super();
        this.fenetre = fenetre;
        this.daop = daop;
        this.personne = personne;
        this.Role = role;
    }

    public void init() {
        fenetre.setVisible(true);
        System.out.println(personne.getNom());
        fenetre.getTexteHome().setText("Bienvenue " + personne.getNom() + " " + personne.getPrenom() + " " +Role.getLibelle());
        fenetre.getAllReservationButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerListeReservations(new FenetreReservations(), new DAOReservation(Singleton.getInstance().cnx), personne, Role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
            });

            fenetre.getReserverButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new ControllerNewReservation(new FenetreReservation(), new DAOParking(Singleton.getInstance().cnx), new DAOReservation(Singleton.getInstance().cnx), personne, Role).init();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    fenetre.setVisible(false);
                }
            });
    }
}