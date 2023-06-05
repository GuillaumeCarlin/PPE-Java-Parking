package Controllers.Visiteur;

import Controllers.Gestionnaire.ControllerHomeGestionnaire;
import DAO.DAOReservation;

import DAO.DAOParking;
import DAO.DAOPersonne;
import Entity.Personne;
import Entity.Role;
import Utils.Outils;
import Utils.Singleton;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.Visiteur.FenetreHomeVisiteur;
import views.Visiteur.FenetreReservation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ControllerNewReservation extends TableModelListeReservations {
    private FenetreReservation fenetre;
    private DAOParking daoParking;
    private Personne personne;
    private DAOReservation daoReservation;
    private TableModelNewReservation mDTM;
    private Role Role;

    public ControllerNewReservation(FenetreReservation fenetre, DAOParking daoParking, DAOReservation daoReservation, Personne personne, Role role){
        super();
        this.fenetre = fenetre;
        this.daoParking = daoParking;
        this.personne = personne;
        this.daoReservation = daoReservation;
        this.Role = role;

    }

    public void init() throws ParseException, SQLException {
        fenetre.setVisible(true);
        mDTM = new TableModelNewReservation(daoParking.FindAllParking(0));
        fenetre.getTableReservation().setModel(mDTM);
        fenetre.getValider().setEnabled(false);

        //On initialise le composent Datechos par la date d'aujourd'hui
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fenetre.getDatechos().setDate(dateFormat.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));


        fenetre.getElectriqueCheck().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.getValider().setEnabled(false);
                if(fenetre.getElectriqueCheck().isSelected()){
                    try {
                        updateView(1);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    try {
                        updateView(0);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        fenetre.getDatechos().getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date selectedDate = fenetre.getDatechos().getDate();
                    String Date = dateFormat.format(selectedDate);
                }
            }
        });

    fenetre.getTableReservation().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(fenetre.getTableReservation().getSelectedRow() > -1) {
                fenetre.getValider().setEnabled(true);
            }
        }
    });

    fenetre.getValider().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date date = fenetre.getDatechos().getDate();
            String place = (String) fenetre.getTableReservation().getModel().getValueAt(fenetre.getTableReservation().getSelectedRow(), 0);;
            Date heureDebut = (Date) fenetre.getSpinnerD().getValue();
            Date heureFin = (Date) fenetre.getSpinnerF().getValue();

            if(ValidationAjout(date, place, heureDebut, heureFin)){
                Time Debut = new Time(heureDebut.getTime());
                Time Fin = new Time(heureFin.getTime());
                SimpleDateFormat form = new SimpleDateFormat("dd/MM/YYYY");
                String Date = form.format(date);

                try {
                    if(daoReservation.VerifHeures(Debut, Fin, Date, place) == true) {
                        String Message = daoReservation.AjoutReservation(place, personne.getIdPersonne(), Debut, Fin, Date);
                        new Outils().New_Log_Ajout_Personnel(personne, daoReservation.FindbyDatePlaceHeure(place ,Date, Debut));
                        JOptionPane.showMessageDialog(null, Message);
                    }
                    else{
                        String Message = "Réservation impossible: une autre réservation existe déjà à cette heure.";
                        JOptionPane.showMessageDialog(null, Message);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    });

        fenetre.getBoutonRetour().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (Role.getLibelle()){
                    case "Gestionnaire":
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

    public void updateView(Integer BoolElectrique) throws SQLException {
        mDTM = new TableModelNewReservation(daoParking.FindAllParking(BoolElectrique));
        fenetre.getTableReservation().setModel(mDTM);
    }

    public Boolean ValidationAjout(Date date, String place , Date heureDebut, Date heureFin){
        if(!(date == null) && heureDebut.before(heureFin)){
            return true;
        }
        else{
            return false;
        }
    }
}
