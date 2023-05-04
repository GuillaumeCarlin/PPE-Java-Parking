package DAO;

import Entity.Personne;
import Entity.Reservation;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOReservation {
    private Connection cnx;
    public DAOReservation(Connection cnx){
        this.cnx = cnx;
    }

    public String DeleteReservation(String Place, String Date, Time HeureDebut) throws SQLException {
        String sql = "UPDATE Reservation SET estEnCour = ? WHERE IdPlace = ? AND Date = ? AND HeureDebut = ? ";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, 0);
        ps.setString(2, Place);
        ps.setString(3, Date);
        ps.setString(4, HeureDebut.toString());
        int nbLigne = ps.executeUpdate();

        if (nbLigne > 0){
            String Message = "La réservation à bien été annuler";
            return Message;
        }else{
            String Message = "Une erreur est survenue lors de l'annulation";
            return Message;
        }
    }

    public String AjoutReservation(String place, Integer Idpersonne, Time debut, Time fin, String date) throws SQLException {
        String sql = "INSERT INTO Reservation (IdPlace, IdPersonne, HeureDebut, HeureFin, Date, estEnCour) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps =cnx.prepareStatement(sql);
        ps.setString(1, place);
        ps.setInt(2, Idpersonne);
        ps.setTime(3, debut);
        ps.setTime(4, fin);
        ps.setString(5, date);
        ps.setInt(6, 1);
        int nbLigne = ps.executeUpdate();
        if (nbLigne > 0){
            String Message = "La réservation à bien été ajouter";
            return Message;
        }else{
            String Message = "Une erreur est survenue lors de l'ajout";
            return Message;
        }
    }


    public Reservation FindbyDatePlaceHeure(String Place, String Date, Time HeureDebut) throws SQLException {
        Reservation reservation = new Reservation();
        String sql = "SELECT res.Date, res.IdPlace, res.IdPersonne, res.HeureDebut, res.HeureFin, park.Electrique FROM Reservation AS res JOIN Parking AS park ON res.IdPlace = park.IdPlace WHERE res.IdPlace = ? AND res.Date = ? AND res.HeureDebut = ? AND res.estEnCour = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, Place);
        ps.setString(2, Date);
        ps.setString(3, HeureDebut.toString());
        ps.setInt(4, 1);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            reservation.setDate(rs.getString("Date"));
            reservation.setIdPlace(rs.getString("IdPlace"));
            reservation.setIdPersonne(rs.getInt("IdPersonne"));
            reservation.setHeureDebut(rs.getTime("HeureDebut"));
            reservation.setHeureFin(rs.getTime("HeureFin"));
            reservation.setElectrique(rs.getInt("Electrique"));
        }
        return reservation;
    }

    public List<Reservation> FindByPlace(String Place) throws SQLException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT res.Date, res.IdPlace, res.IdPersonne, res.HeureDebut, res.HeureFin, park.Electrique, concat(pers.Nom, \" \", pers.Prenom) AS Nom FROM Reservation AS res JOIN Parking AS park ON res.IdPlace = park.IdPlace JOIN Personne AS pers ON res.IdPersonne = pers.IdPersonne WHERE res.estEnCour = ? AND res.IdPlace = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setString(2, Place);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setIdPersonne(rs.getInt("IdPersonne"));
            reservation.setIdPlace(rs.getString("IdPlace"));
            reservation.setHeureDebut(rs.getTime("HeureDebut"));
            reservation.setHeureFin(rs.getTime("HeureFin"));
            reservation.setDate(rs.getString("Date"));
            reservation.setElectrique(rs.getInt("Electrique"));
            reservation.setNom(rs.getString("Nom"));
            reservations.add(reservation);
        }
        return reservations;
    }

    public List<Reservation> FindByNom(String Nom) throws SQLException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT res.Date, res.IdPlace, res.IdPersonne, res.HeureDebut, res.HeureFin, park.Electrique, concat(pers.Nom, \" \", pers.Prenom) AS Nom FROM Reservation AS res JOIN Parking AS park ON res.IdPlace = park.IdPlace JOIN Personne AS pers ON res.IdPersonne = pers.IdPersonne WHERE res.estEnCour = ? AND pers.Nom = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(2, Nom);
        ps.setInt(1, 1);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Reservation reservation = new Reservation();
            reservation.setIdPersonne(rs.getInt("IdPersonne"));
            reservation.setIdPlace(rs.getString("IdPlace"));
            reservation.setHeureDebut(rs.getTime("HeureDebut"));
            reservation.setHeureFin(rs.getTime("HeureFin"));
            reservation.setDate(rs.getString("Date"));
            reservation.setElectrique(rs.getInt("Electrique"));
            reservation.setNom(rs.getString("Nom"));
            reservations.add(reservation);
        }
        return reservations;
    }


    public List<Reservation> FindbyIdPersonne(Integer Identifiant) throws SQLException {
        Reservation reservation;
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT res.Date, res.IdPlace, res.IdPersonne, res.HeureDebut, res.HeureFin, park.Electrique FROM Reservation AS res JOIN Parking AS park ON res.IdPlace = park.IdPlace WHERE res.IdPersonne = ? AND res.estEnCour = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, Identifiant);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            reservation = new Reservation();
            reservation.setIdPersonne(rs.getInt("IdPersonne"));
            reservation.setIdPlace(rs.getString("IdPlace"));
            reservation.setHeureDebut(rs.getTime("HeureDebut"));
            reservation.setHeureFin(rs.getTime("HeureFin"));
            reservation.setDate(rs.getString("Date"));
            reservation.setElectrique(rs.getInt("Electrique"));
            reservations.add(reservation);
        }
        return reservations;
    }

    public List<Reservation> FindAll() throws SQLException {
        Reservation reservation;
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "select r.IdPersonne, r.IdPlace, r.HeureDebut, r.HeureFin, p2.Electrique  ,r.Date, concat(p.Nom, \" \", p.Prenom) AS Nom FROM Reservation r JOIN Personne p on r.IdPersonne = p.IdPersonne JOIN Parking p2 ON p2.IdPlace = r.IdPlace  where r.estEnCour = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1,1);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            reservation = new Reservation();
            reservation.setIdPersonne(rs.getInt(("IdPersonne")));
            reservation.setIdPlace(rs.getString("IdPlace"));
            reservation.setHeureDebut(rs.getTime("HeureDebut"));
            reservation.setHeureFin(rs.getTime("HeureFin"));
            reservation.setDate(rs.getString("Date"));
            reservation.setElectrique(rs.getInt("Electrique"));
            reservation.setNom(rs.getString("Nom"));
            reservations.add(reservation);
        }
        return reservations;
    }

    public List<Reservation> findByDatePlace(String Date, String IdPlace) throws SQLException {
        Reservation reservation;
        List<Reservation> reservations = new ArrayList<Reservation>();
        String sql = "SELECT * FROM Reservation WHERE Date = ? AND IdPlace = ? AND estEnCour = 1";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, Date);
        ps.setString(2, IdPlace);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            reservation = new Reservation();
            reservation.setIdPersonne(rs.getInt("IdPersonne"));
            reservation.setIdPlace(rs.getString("IdPlace"));
            reservation.setHeureDebut(rs.getTime("HeureDebut"));
            reservation.setHeureFin(rs.getTime("HeureFin"));
            reservation.setDate(rs.getString("Date"));
            reservation.setElectrique(rs.getInt("Electrique"));
            reservations.add(reservation);
        }

        return  reservations;
    }
/*
    public Boolean VerifHeures(Time HeureD, Time HeureF){
        String sql = "SELECT * FROM reservation WHERE time_column BETWEEN '09:00:00' AND '17:00:00'";
        return true;
    }
 */

}
