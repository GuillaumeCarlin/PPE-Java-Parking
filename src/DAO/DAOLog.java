package DAO;

import Entity.Log;
import Entity.Personne;
import Entity.Reservation;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOLog {
    private Connection cnx;
    public DAOLog(Connection cnx){
        this.cnx = cnx;
    }

    public void Insert_Log_Connexion(String Date, String Heure, Integer IdPersonne, String Message, Integer IdCategorie) throws SQLException {
        String SQL = "INSERT INTO Log(DateLog, Heure, IdPersonne, Message, Id_Categorie) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, Date);
        ps.setString(2, Heure);
        ps.setInt(3, IdPersonne);
        ps.setString(4, Message);
        ps.setInt(5, IdCategorie);
        ResultSet rs =ps.executeQuery();
    }

    public void Insert_Reservation_Personnel(String Date, String Heure, Integer IdPersonne, String Message, Integer IdCategorie, Integer IdReservation) throws SQLException {
        String SQL = "INSERT INTO Log(DateLog, Heure, IdPersonne, Message, Id_Categorie, Id_Reservation) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, Date);
        ps.setString(2, Heure);
        ps.setInt(3, IdPersonne);
        ps.setString(4, Message);
        ps.setInt(5, IdCategorie);
        ps.setInt(6, IdReservation);
        ResultSet rs = ps.executeQuery();
    }

    public void Insert_Reservation(String Date, String Heure, Integer IdPersonne,Integer IdPersonneModifier, String Message, Integer IdCategorie, Integer IdReservation) throws SQLException {
        String SQL = "INSERT INTO Log(DateLog, Heure, IdPersonne, IdPersonneModifier, Message, Id_Categorie, Id_Reservation) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, Date);
        ps.setString(2, Heure);
        ps.setInt(3, IdPersonne);
        ps.setInt(4, IdPersonneModifier);
        ps.setString(5, Message);
        ps.setInt(6, IdCategorie);
        ps.setInt(7, IdReservation);
        ResultSet rs = ps.executeQuery();
    }

    public void Insert_Utilisateur(String Date, String Heure, String Message, Integer IdPersonne, Integer IdPersonne_Target, Integer IdCategorie) throws SQLException {
        String SQL = "INSERT INTO Log(DateLog, Heure, IdPersonne, IdPersonneModifier, Message, Id_Categorie) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, Date);
        ps.setString(2, Heure);
        ps.setInt(3, IdPersonne);
        ps.setInt(4, IdPersonne_Target);
        ps.setString(5, Message);
        ps.setInt(6, IdCategorie);
        ResultSet rs = ps.executeQuery();
    }

    public List<Log> FindAll() throws SQLException {
        Log log;
        List<Log> Logs = new ArrayList<Log>();
        String sql = "SELECT l.IdLog, l.DateLog, l.Heure, l.IdPersonne, l.IdPersonneModifier, l.Message, c.Libelle, l.Id_Reservation, concat(p.Nom, \" \", p.Prenom) AS Nom FROM Log l JOIN Log_Categorie c ON l.Id_Categorie = c.IdLog_Categorie JOIN Personne p ON l.IdPersonne = p.IdPersonne ORDER BY IdLog DESC" ;
        PreparedStatement ps = cnx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            log = new Log();
            log.setIdLog(rs.getInt("IdLog"));
            log.setDate(rs.getString("DateLog"));
            log.setHeure(rs.getString("Heure"));
            log.setIdPersonne(rs.getInt("IdPersonne"));
            log.setIdPersonneModifier(rs.getInt("IdPersonneModifier"));
            log.setMessage(rs.getString("Message"));
            log.setCategorie(rs.getString("Libelle"));
            log.setIdReservation(rs.getInt("Id_Reservation"));
            log.setNom(rs.getString("Nom"));
            Logs.add(log);
        }
        return Logs;
    }

    public Integer get_IdCategorie(String Libelle) throws SQLException {
        String SQL = "SELECT IdLog_Categorie FROM Gestion_Parking.Log_Categorie LC WHERE LC.Libelle = ?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, Libelle);
        ResultSet rs = ps.executeQuery();
        Integer IdCategorie = 7;
        if (rs.next()) {
            IdCategorie = rs.getInt("IdLog_Categorie");
        }
        return IdCategorie;
    }


    public Log FindbyId(Integer IdLog) throws SQLException {
        Log log = new Log();
        String SQL = "SELECT l.IdLog, l.DateLog, l.Heure, l.IdPersonne, l.IdPersonneModifier, l.Message, c.Libelle, l.Id_Reservation FROM Log l JOIN Log_Categorie c ON l.Id_Categorie = c.IdLog_Categorie WHERE l.IdLog = ?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setInt(1, IdLog);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            log.setIdLog(rs.getInt("IdLog"));
            log.setDate(rs.getString("DateLog"));
            log.setHeure(rs.getString("Heure"));
            log.setIdPersonne(rs.getInt("IdPersonne"));
            log.setIdPersonneModifier(rs.getInt("IdPersonneModifier"));
            log.setMessage(rs.getString("Message"));
            log.setCategorie(rs.getString("Libelle"));
            log.setIdReservation(rs.getInt("Id_Reservation"));
        }
        return log;
    }

    public List<Log> Recherche(String Categorie, String Valeur) throws SQLException {
        Log log;
        List<Log> Logs = new ArrayList<Log>();
        String sql = "";
        switch(Categorie){
            case "Catégorie":
                Valeur = Valeur.replace(" ", "_");
                sql = "SELECT l.IdLog, l.DateLog, l.Heure, l.IdPersonne, l.IdPersonneModifier, l.Message, c.Libelle, l.Id_Reservation, concat(p.Nom, \" \", p.Prenom) AS Nom FROM Log l JOIN Log_Categorie c ON l.Id_Categorie = c.IdLog_Categorie JOIN Personne p ON l.IdPersonne = p.IdPersonne WHERE c.Libelle = ?";
                break;
            case "Date":
                sql = "SELECT l.IdLog, l.DateLog, l.Heure, l.IdPersonne, l.IdPersonneModifier, l.Message, c.Libelle, l.Id_Reservation, concat(p.Nom, \" \", p.Prenom) AS Nom FROM Log l JOIN Log_Categorie c ON l.Id_Categorie = c.IdLog_Categorie JOIN Personne p ON l.IdPersonne = p.IdPersonne WHERE l.DateLog = ?";
                break;
            case "Nom":
                Valeur = Valeur.split(" ")[0];
                sql = "SELECT l.IdLog, l.DateLog, l.Heure, l.IdPersonne, l.IdPersonneModifier, l.Message, c.Libelle, l.Id_Reservation, concat(p.Nom, \" \", p.Prenom) AS Nom FROM Log l JOIN Log_Categorie c ON l.Id_Categorie = c.IdLog_Categorie JOIN Personne p ON l.IdPersonne = p.IdPersonne WHERE p.Nom = ?";
                break;
            default:
        }
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, Valeur);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            log = new Log();
            log.setIdLog(rs.getInt("IdLog"));
            log.setDate(rs.getString("DateLog"));
            log.setHeure(rs.getString("Heure"));
            log.setIdPersonne(rs.getInt("IdPersonne"));
            log.setIdPersonneModifier(rs.getInt("IdPersonneModifier"));
            log.setMessage(rs.getString("Message"));
            log.setCategorie(rs.getString("Libelle"));
            log.setIdReservation(rs.getInt("Id_Reservation"));
            log.setNom(rs.getString("Nom"));
            Logs.add(log);
        }
        return Logs;
    }
}
