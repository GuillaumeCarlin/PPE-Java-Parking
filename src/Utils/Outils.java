package Utils;

import DAO.DAOLog;
import DAO.DAOPersonne;
import DAO.DAOReservation;
import Entity.Personne;
import Entity.Reservation;


import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Outils {
    private DAOLog daol;
    private DAOPersonne daop;
    private DAOReservation daor;

    public Outils() throws SQLException {
        this.daol = new DAOLog(Singleton.getInstance().cnx);
        this.daop = new DAOPersonne(Singleton.getInstance().cnx);
        this.daor = new DAOReservation(Singleton.getInstance().cnx);
    };

    public String get_Heure(){
        Date D = new Date();
        SimpleDateFormat heureFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
        String Heure = heureFormat.format(D) + "h" + minuteFormat.format(D);
        return Heure;
    };

    public String get_Heure(Time heure){
        String Heure = String.valueOf(heure.getHours());
        String Minute = String.valueOf(heure.getMinutes());
        return Heure + "h" + Minute;
    }

    public String get_Date(){
        Date D = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String Date = dateFormat.format(D);
        return Date;
    }

    public void New_Log_Connexion(Integer IdPersonne) throws SQLException {
        String Heure = this.get_Heure();
        String Date = this.get_Date();
        Personne personne = daop.find(IdPersonne);
        String Message = "Nouvelle connexion de " + personne.getNom() + " " + personne.getPrenom();
        daol.Insert_Log_Connexion(Date, Heure, personne.getIdPersonne(), Message, daol.get_IdCategorie("Connexion"));
    }

    public void New_Log_Ajout_Personnel(Personne Personne, Reservation reservation) throws SQLException {
        String Heure = this.get_Heure();
        String Date = this.get_Date();
        String Message = Personne.getNom() + " " + Personne.getPrenom() + " a fait la réservation de la place " + reservation.getIdPlace() + " de " + this.get_Heure(reservation.getHeureDebut()) + " a " + this.get_Heure(reservation.getHeureFin());
        daol.Insert_Reservation_Personnel(Date, Heure, Personne.getIdPersonne(), Message, daol.get_IdCategorie("Reservation_Personnel"), reservation.getIdReservation());
    }

    public void New_Log_Supp_Personnel(Personne Personne, Reservation reservation) throws SQLException {
        String Heure = this.get_Heure();
        String Date = this.get_Date();
        String Message = Personne.getNom() + " " + Personne.getPrenom() + " a supprimée la réservation du " + reservation.getDate() + " de la place " + reservation.getIdPlace() + " de " + this.get_Heure(reservation.getHeureDebut()) + " a " + this.get_Heure(reservation.getHeureFin());
        daol.Insert_Reservation_Personnel(Date, Heure, Personne.getIdPersonne(), Message, daol.get_IdCategorie("Suppression_Personnel"), reservation.getIdReservation());
    }

    public void New_Log_Ajout(Personne Personne, Integer IdPersonne_Target, Reservation reservation) throws SQLException {
        String Heure= this.get_Heure();
        String Date = this.get_Date();
        Personne Personne_Target = daop.find(IdPersonne_Target);
        String Message = Personne.getNom() + " " + Personne.getPrenom() + " a crée la réservation de " + Personne_Target.getNom() + " " + Personne_Target.getPrenom() + " (Place: " + reservation.getIdPlace() + ", Date: " + reservation.getDate() + ", de " + this.get_Heure(reservation.getHeureDebut()) + " a " + this.get_Heure(reservation.getHeureFin()) + ")";
        daol.Insert_Reservation(Date, Heure, Personne.getIdPersonne(),Personne_Target.getIdPersonne(), Message, daol.get_IdCategorie("Reservation"), reservation.getIdReservation());
    }

    public void New_Log_Supp(Personne Personne, Reservation reservation) throws SQLException {
        String Heure= this.get_Heure();
        String Date = this.get_Date();
        Personne Personne_Target = daop.find(reservation.getIdPersonne());
        String Message = Personne.getNom() + " " + Personne.getPrenom() + " a supprimée la réservation de " + Personne_Target.getNom() + " " + Personne_Target.getPrenom() + " (Place: " + reservation.getIdPlace() + ", Date: " + reservation.getDate() + ", de " + this.get_Heure(reservation.getHeureDebut()) + " a " + this.get_Heure(reservation.getHeureFin()) + ")";
        daol.Insert_Reservation(Date, Heure, Personne.getIdPersonne(), Personne_Target.getIdPersonne(), Message, daol.get_IdCategorie("Suppression"), reservation.getIdReservation());
    }

    public void New_Log_Supp_Utilisateur(String Nom_Target, Integer Identifiant_Target, Personne personne) throws SQLException {
        String Heure = this.get_Heure();
        String Date = this.get_Date();
        String Message = personne.getNom() + " " + personne.getPrenom() + " a supprimé l'utilisateur " + Nom_Target + " (" + Identifiant_Target + ")";
        daol.Insert_Utilisateur(Date, Heure, Message, personne.getIdPersonne(), Identifiant_Target, daol.get_IdCategorie("Suppression_Utilisateur"));
    }

    public void New_Log_Ajout_Utilisateur(String Nom_Target, Integer Identifiant_Target, Personne personne) throws SQLException {
        String Heure = this.get_Heure();
        String Date = this.get_Date();
        String Message = personne.getNom() + " " + personne.getPrenom() + " a ajouter l'utilisateur " + Nom_Target + " (" + Identifiant_Target + ")";
        daol.Insert_Utilisateur(Date, Heure, Message, personne.getIdPersonne(), Identifiant_Target, daol.get_IdCategorie("Ajout_Utilisateur"));
    }
}
