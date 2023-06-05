package Controllers.Admin;

import Controllers.Gestionnaire.ControllerHomeGestionnaire;
import Controllers.Gestionnaire.TableModelListeReservationsAll;
import Controllers.Visiteur.ControllerHomeVisiteur;
import Controllers.Visiteur.TableModelListeReservations;
import DAO.DAOLog;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import Entity.Log;
import Entity.Personne;
import Entity.Reservation;
import Entity.Role;
import Utils.Singleton;
import views.Admin.FenetreHomeAdmin;
import views.Admin.FenetreListeLog;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.Visiteur.FenetreHomeVisiteur;
import views.Visiteur.FenetreReservations;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ControllerLog extends DefaultTableModel {

    private FenetreListeLog fenetre;
    private List<Log> Logs;
    private Personne personne;
    private TableModelListeLog mDTM;

    private DAOLog daol;
    private Role role;

    public ControllerLog(FenetreListeLog fenetre, DAOLog daol, Personne personne, Role role) {
        this.fenetre = fenetre;
        this.daol = daol;
        this.personne = personne;
        this.role = role;
    }

    public void init() throws SQLException {
        fenetre.setVisible(true);

        refreshTable();

        fenetre.getComboBoxRecherche().addItem("Nom");
        fenetre.getComboBoxRecherche().addItem("Catégorie");
        fenetre.getComboBoxRecherche().addItem("Date");

        fenetre.getTable1().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(fenetre.getTable1().getSelectedRow() > -1) {
                    JTable table = fenetre.getTable1();
                    Integer IdLog = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 4);
                    try {
                        Log log = daol.FindbyId(IdLog);
                        Personne personne = new DAOPersonne(Singleton.getInstance().cnx).find(log.getIdPersonne());
                        fenetre.getDateText().setText("Date : " + log.getDate());
                        fenetre.getHeureText().setText("Heure : " + log.getHeure());
                        fenetre.getCategorieText().setText("Catégorie : " + log.getCategorie());
                        fenetre.getMessageText().setText("<html>" + "Message : " + log.getMessage().substring(0, log.getMessage().length() / 2) + "</html>");
                        fenetre.getTestText().setText(log.getMessage().substring(log.getMessage().length() / 2));

                        fenetre.getNomText().setText("Nom : " + personne.getNom());
                        fenetre.getPrenomText().setText("Prenom : " + personne.getPrenom());
                        fenetre.getPosteText().setText("Poste : " + personne.getPoste());

                        if (log.getIdPersonneModifier() != 0){
                            Personne personneModifier = new DAOPersonne(Singleton.getInstance().cnx).find(log.getIdPersonneModifier());
                            fenetre.getNomModifierText().setText("Nom : " + personneModifier.getNom());
                            fenetre.getPrenomModifierText().setText("Prenom : " + personneModifier.getPrenom());
                            fenetre.getPosteModifierText().setText("Poste : " + personneModifier.getPoste());
                        }else {
                            fenetre.getNomModifierText().setText("");
                            fenetre.getPrenomModifierText().setText("");
                            fenetre.getPosteModifierText().setText("");
                        }


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        fenetre.getRechercherButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Value = fenetre.getRechercheField().getText();
                try {
                    Logs = daol.Recherche(fenetre.getComboBoxRecherche().getSelectedItem().toString(), fenetre.getRechercheField().getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                mDTM = new TableModelListeLog(Logs);
                fenetre.getTable1().setModel(mDTM);
                Reset_Label();
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

        fenetre.getRetourButton().addActionListener(new ActionListener() {
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
                        try {
                            new ControllerHomeVisiteur(new FenetreHomeVisiteur(), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case "Admin":

                        new ControllerHomeAdmin(new FenetreHomeAdmin(), personne, role).init();
                        break;
                }
                fenetre.setVisible(false);
            }

        });
    }

    private void refreshTable() throws SQLException {
        Logs = daol.FindAll();
        mDTM = new TableModelListeLog(Logs);
        fenetre.getTable1().setModel(mDTM);
        Reset_Label();
    }

    private void Reset_Label(){
        fenetre.getDateText().setText("");
        fenetre.getHeureText().setText("");
        fenetre.getCategorieText().setText("");
        fenetre.getMessageText().setText("");
        fenetre.getTestText().setText("");

        fenetre.getNomText().setText("");
        fenetre.getPrenomText().setText("");
        fenetre.getPosteText().setText("");

        fenetre.getNomModifierText().setText("");
        fenetre.getPrenomModifierText().setText("");
        fenetre.getPosteModifierText().setText("");
    }
}
