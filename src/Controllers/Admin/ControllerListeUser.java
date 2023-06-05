package Controllers.Admin;

import Controllers.Gestionnaire.ControllerHomeGestionnaire;
import Controllers.Visiteur.ControllerHomeVisiteur;
import DAO.DAOLog;
import DAO.DAOPersonne;
import Entity.Log;
import Entity.Personne;
import Entity.Role;
import Utils.Outils;
import Utils.Singleton;
import views.Admin.FenetreHomeAdmin;
import views.Admin.FenetreListeLog;
import views.Admin.FenetreListeUtilisateur;
import views.Admin.FenetreNewUser;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.Visiteur.FenetreHomeVisiteur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class ControllerListeUser extends DefaultTableModel {

    private FenetreListeUtilisateur fenetre;
    private List<Personne> Personnes;
    private Personne personne;
    private TableModelListeUser mDTM;

    private DAOPersonne daop;
    private Role role;

    public ControllerListeUser(FenetreListeUtilisateur fenetre, DAOPersonne daop, Personne personne, Role role) {
        this.fenetre = fenetre;
        this.daop = daop;
        this.personne = personne;
        this.role = role;
    }

    public void init() throws SQLException {
        fenetre.setVisible(true);

        fenetre.getSupprimerButton().setEnabled(false);

        refreshTable();

        fenetre.getComboBoxRecherche().addItem("Nom");
        fenetre.getComboBoxRecherche().addItem("Poste");
        fenetre.getComboBoxRecherche().addItem("Email");

        fenetre.getTable1().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(fenetre.getTable1().getSelectedRow() > -1) {
                    JTable table = fenetre.getTable1();
                    Integer IdPersonne = (Integer) table.getModel().getValueAt(table.getSelectedRow(), 4);
                    try {
                        Personne personne = daop.find(IdPersonne);
                        fenetre.getRoleText().setText("Role : " + personne.getRoleName());
                        fenetre.getNomText().setText("Nom : " + personne.getNom() + " " + personne.getPrenom());
                        fenetre.getEmailText().setText("Email : " + personne.getEmail());
                        fenetre.getPhoneText().setText("Téléphone : " + personne.getTelephone());
                        fenetre.getPosteText().setText("Poste :" + personne.getPoste());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(fenetre.getActifCheck().isSelected()){
                        fenetre.getSupprimerButton().setEnabled(false);
                    }else {
                        fenetre.getSupprimerButton().setEnabled(true);
                    }
                }
            }
        });

        fenetre.getSupprimerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer Bool = JOptionPane.showConfirmDialog(null, "Êtes-vous sur de vouloir supprimer l'utilisateur " + fenetre.getTable1().getModel().getValueAt(fenetre.getTable1().getSelectedRow(), 0));
                if(Bool.equals(0)){
                    try {
                        new Outils().New_Log_Supp_Utilisateur((String) fenetre.getTable1().getModel().getValueAt(fenetre.getTable1().getSelectedRow(), 0), (Integer) fenetre.getTable1().getModel().getValueAt(fenetre.getTable1().getSelectedRow(), 4), personne);
                        String Message = daop.DeleteUser((Integer) fenetre.getTable1().getModel().getValueAt(fenetre.getTable1().getSelectedRow(), 4));
                        JOptionPane.showMessageDialog(null, Message);
                        refreshTable();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        fenetre.getAjouterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ControllerNewUser(new FenetreNewUser(), personne, role, daop).init();
                fenetre.setVisible(false);
            }
        });

        fenetre.getRechercherButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Personnes = daop.Recherche(fenetre.getComboBoxRecherche().getSelectedItem().toString(), fenetre.getRechercheField().getText(), fenetre.getActifCheck().isSelected());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                mDTM = new TableModelListeUser(Personnes);
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

        fenetre.getActifCheck().addActionListener(new ActionListener() {
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
        Personnes = daop.FindAll(fenetre.getActifCheck().isSelected());
        mDTM = new TableModelListeUser(Personnes);
        fenetre.getTable1().setModel(mDTM);
        Reset_Label();
        fenetre.getSupprimerButton().setEnabled(false);
    }

    private void Reset_Label(){
        fenetre.getEmailText().setText("");
        fenetre.getPhoneText().setText("");
        fenetre.getRoleText().setText("");
        fenetre.getNomText().setText("");
        fenetre.getPosteText().setText("");
    }
}
