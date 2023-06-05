package Controllers.Gestionnaire;

import Controllers.Admin.ControllerHomeAdmin;
import Controllers.Admin.TableModelListeLog;
import Controllers.Visiteur.ControllerHomeVisiteur;
import Controllers.Visiteur.TableModelListeReservations;
import Controllers.Visiteur.TableModelNewReservation;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import Entity.Personne;
import Entity.Reservation;
import Entity.Role;
import Utils.Outils;
import Utils.Singleton;
import views.Admin.FenetreHomeAdmin;
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
                Reset_Label();
                try {
                    reservations = daor.Recherche(fenetre.getComboBoxRecherche().getSelectedItem().toString(), fenetre.getRechercheField().getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                mDTM = new TableModelListeReservationsAll(reservations);
                fenetre.getTable1().setModel(mDTM);
            }
        });

        fenetre.getAnnulerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer Bool = JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir annuler le reservation de la place : " + fenetre.getTable1().getModel().getValueAt(fenetre.getTable1().getSelectedRow(), 0));
                JTable table = fenetre.getTable1();
                int ligne = table.getSelectedRow();
                if(Bool.equals(0)){
                    try {
                        new Outils().New_Log_Supp(personne, daor.FindbyDatePlaceHeure((String) table.getModel().getValueAt(ligne, 1), (String) table.getModel().getValueAt(ligne, 0),(Time) table.getModel().getValueAt(ligne, 3)));
                        String Message = daor.DeleteReservation((String) table.getModel().getValueAt(ligne, 1),(String) table.getModel().getValueAt(ligne, 0),  (Time) table.getModel().getValueAt(ligne, 3));
                        JOptionPane.showMessageDialog(null, Message);
                        refreshTable();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
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

                        new ControllerHomeAdmin(new FenetreHomeAdmin(), personne, Role).init();
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
        Reset_Label();
    }

    private void Reset_Label(){
        fenetre.getNomText().setText("");
        fenetre.getDateText().setText("");
        fenetre.getElectriqueText().setText("");
        fenetre.getPlaceText().setText("");
        fenetre.getHeureFinText().setText("");
        fenetre.getHeureDebutText().setText("");
    }

}
