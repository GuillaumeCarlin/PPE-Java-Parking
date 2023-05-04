package Controllers.Visiteur;
import Controllers.Gestionnaire.ControllerHomeGestionnaire;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import Entity.Personne;
import Entity.Reservation;
import Entity.Role;
import Utils.Singleton;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.Visiteur.FenetreHomeVisiteur;
import views.Visiteur.FenetreReservations;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class ControllerListeReservations extends DefaultTableModel {
    FenetreReservations fenetre;
    DAOReservation daor;
    List<Reservation> reservations;
    Personne personne;
    TableModelListeReservations mDTM;
    Role role;

    public ControllerListeReservations(FenetreReservations fenetre, DAOReservation daor, Personne personne, Role role){
        super();
        this.fenetre = fenetre;
        this.daor = daor;
        this.personne = personne;
        this.role = role;
    }

    public void init(){
        try {
            fenetre.setVisible(true);
            reservations = daor.FindbyIdPersonne(personne.getIdPersonne());
            fenetre.getBoutonAnnuler().setEnabled(false);
            fenetre.getButtonQRCode().setEnabled(false);
            mDTM = new TableModelListeReservations(reservations);
            fenetre.getTableReservation().setModel(mDTM);


            fenetre.getBoutonRetour().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (role.getLibelle()){
                        case "Gestionnaire":
                            System.out.println("Gestionnaire");
                            try {
                                new ControllerHomeGestionnaire(new FenetreHomeGestionnaire(), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Visiteur":
                            System.out.println("Visiteur");
                            try {
                                new ControllerHomeVisiteur(new FenetreHomeVisiteur(), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case "Admin":
                            System.out.println("Admin");
                            break;
                    }
                    fenetre.setVisible(false);
                }
            });

            fenetre.getBoutonAnnuler().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer Bool = JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir annuler le reservation de la place : " + fenetre.getTableReservation().getModel().getValueAt(fenetre.getTableReservation().getSelectedRow(), 0));
                    System.out.println(Bool);
                    JTable table = fenetre.getTableReservation();
                    int ligne = table.getSelectedRow();
                    if(Bool.equals(0)){
                        try {
                            String Message = daor.DeleteReservation((String) table.getModel().getValueAt(ligne, 0),(String) table.getModel().getValueAt(ligne, 3),  (Time) table.getModel().getValueAt(ligne, 1));
                            JOptionPane.showMessageDialog(null, Message);
                            refreshTable();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

            fenetre.getTableReservation().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(fenetre.getTableReservation().getSelectedRow() > -1) {
                        fenetre.getBoutonAnnuler().setEnabled(true);
                        fenetre.getButtonQRCode().setEnabled(true);
                        JTable table = fenetre.getTableReservation();
                        int ligne = table.getSelectedRow();
                        String Place = (String) table.getModel().getValueAt(ligne, 0);
                        String Date = (String) table.getModel().getValueAt(ligne, 3);
                        Time HeureDebut = (Time) table.getModel().getValueAt(ligne, 1);

                        try {
                            Reservation reservation = daor.FindbyDatePlaceHeure(Place, Date, HeureDebut);
                            System.out.println(reservation);
                            fenetre.getLabelDate().setText("Date : " + reservation.getDate());
                            fenetre.getLabelPlace().setText("Place : " + reservation.getIdPlace());
                            fenetre.getLabelDebut().setText("Heure début : " + reservation.getHeureDebut().toString());
                            fenetre.getLabelFin().setText("Heure fin : " + reservation.getHeureFin().toString());
                            fenetre.getLabelelectrique().setText("Électrique : " + reservation.getElectrique().toString());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshTable() throws SQLException {
        reservations = daor.FindbyIdPersonne(personne.getIdPersonne());
        fenetre.getBoutonAnnuler().setEnabled(false);
        fenetre.getButtonQRCode().setEnabled(false);
        mDTM = new TableModelListeReservations(reservations);
        fenetre.getTableReservation().setModel(mDTM);

        // Remise à zero des labels
        fenetre.getLabelDate().setText("Date");
        fenetre.getLabelPlace().setText("Place");
        fenetre.getLabelDebut().setText("Heure début");
        fenetre.getLabelFin().setText("Heure fin");
        fenetre.getLabelelectrique().setText("Électrique");
    }
}
