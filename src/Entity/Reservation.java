package Entity;

import java.sql.Time;

public class Reservation {
    private Integer IdReservation;
    private String IdPlace;
    private Integer IdPersonne;
    private Time HeureDebut;
    private Time HeureFin;

    private String Date;
    private Integer Electrique;
    private String Nom;

    public Reservation(){
        super();
    }

    public Reservation(String IdPlace, Integer IdPersonne, Time HeureDebut, Time HeureFin, String Date, Integer Electrique){
        this.IdPlace = IdPlace;
        this.IdPersonne = IdPersonne;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
        this.Date = Date;
        this.Electrique = Electrique;
    }

    public String getIdPlace() {
        return IdPlace;
    }

    public void setIdPlace(String idPlace) {
        IdPlace = idPlace;
    }

    public Integer getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        IdPersonne = idPersonne;
    }

    public Time getHeureDebut() {
        return HeureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        HeureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return HeureFin;
    }

    public void setHeureFin(Time heureFin) {
        HeureFin = heureFin;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getElectrique() {
        return Electrique;
    }

    public void setElectrique(Integer electrique) {
        Electrique = electrique;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Integer getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(Integer idReservation) {
        IdReservation = idReservation;
    }
}
