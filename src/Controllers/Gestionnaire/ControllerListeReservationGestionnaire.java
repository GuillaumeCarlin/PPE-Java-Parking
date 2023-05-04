package Controllers.Gestionnaire;

import Controllers.Visiteur.ControllerHomeVisiteur;
import Controllers.Visiteur.TableModelListeReservations;
import Controllers.Visiteur.TableModelNewReservation;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import Entity.Personne;
import Entity.Reservation;
import Entity.Role;
import Utils.Singleton;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.Gestionnaire.FenetreListeReservationGestionnaire;
import views.Visiteur.FenetreHomeVisiteur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class ControllerListeReservationGestionnaire {
    private FenetreListeReservationGestionnaire fenetre;

    private DAOReservation daor;
    private List<Reservation> reservations;
    private Personne personne;
    TableModelListeReservationsAll mDTM;
    private DAOPersonne daop;
    private Role Role;


    public ControllerListeReservationGestionnaire(FenetreListeReservationGestionnaire fenetre, DAOReservation daor, Personne personne, Role role) {
        this.fenetre = fenetre;
        this.daor = daor;
        this.personne = personne;
        this.Role = role;
    }

    public void init() throws SQLException {
        fenetre.setVisible(true);
        reservations = daor.FindAll();
        mDTM = new TableModelListeReservationsAll(reservations);
        fenetre.getTable1().setModel(mDTM);

        fenetre.getComboBoxRecherche().addItem("Nom");
        fenetre.getComboBoxRecherche().addItem("Place");
        fenetre.getComboBoxRecherche().addItem("Date");

        fenetre.getRechercherButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Value = fenetre.getRechercheField().getText();
                System.out.println(Value);
                switch (fenetre.getComboBoxRecherche().getSelectedItem().toString()){
                    case "Place":
                        try {
                            reservations = daor.FindByPlace(Value);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Date" :
                        break;
                    case "Nom" :
                        try {
                            reservations = daor.FindByNom(Value);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                }
                mDTM = new TableModelListeReservationsAll(reservations);
                fenetre.getTable1().setModel(mDTM);
            }
        });

        fenetre.getRefreshButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        fenetre.getTable1().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(fenetre.getTable1().getSelectedRow() > -1) {
                    JTable table = fenetre.getTable1();
                    int ligne = table.getSelectedRow();
                    String Place = (String) table.getModel().getValueAt(ligne, 1);
                    String Date = (String) table.getModel().getValueAt(ligne, 0);
                    Time HeureDebut = (Time) table.getModel().getValueAt(ligne, 3);


                    try {
                        Reservation reservation = daor.FindbyDatePlaceHeure(Place, Date, HeureDebut);
                        fenetre.getDateText().setText("Date : " + reservation.getDate());
                        fenetre.getHeureDebutText().setText("Heure de début : " + reservation.getHeureDebut().toString());
                        fenetre.getHeureFinText().setText("Heure de fin : " + reservation.getHeureFin().toString());
                        fenetre.getPlaceText().setText("Place : " + reservation.getIdPlace());
                        fenetre.getElectriqueText().setText("Électrique : " + reservation.getElectrique().toString());
                        fenetre.getNomText().setText("Nom : " + table.getModel().getValueAt(ligne, 2));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        fenetre.getRetourButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Role.getLibelle()){
                    case "Gestionnaire":
                        System.out.println("Gestionnaire");
                        try {
                            new ControllerHomeGestionnaire(new FenetreHomeGestionnaire(), new DAOPersonne(Singleton.getInstance().cnx), personne, Role).init();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Visiteur":
                        try {
                            new ControllerHomeVisiteur(new FenetreHomeVisiteur(), new DAOPersonne(Singleton.getInstance().cnx), personne, Role).init();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Admin":
                        break;
                }
                fenetre.setVisible(false);
            }
        });
    }

    private void refreshTable() throws SQLException {
        reservations = daor.FindAll();
        mDTM = new TableModelListeReservationsAll(reservations);
        fenetre.getTable1().setModel(mDTM);

        fenetre.getNomText().setText("Nom : ");
        fenetre.getDateText().setText("Date : ");
        fenetre.getElectriqueText().setText("Électrique : ");
        fenetre.getPlaceText().setText("Place : ");
        fenetre.getHeureFinText().setText("Heure de Fin : ");
        fenetre.getHeureDebutText().setText("Heure de début : ");
    }

}
