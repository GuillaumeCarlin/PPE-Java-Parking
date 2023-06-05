package Entity;

public class Log {
    private String Date;
    private Integer IdPersonne;
    private Integer IdPersonneModifier;
    private String Message;
    private Integer IdCategorie;
    private String Categorie;
    private Integer IdReservation;
    private String Heure;
    private Integer IdLog;
    private String Nom;

    public Log(){
        super();
    }

    //Ajout + Suppression
    public Log(Integer IdPersonne,String Date ,Integer IdReservation, String Categorie ,String Heure){
        this.Date = Date;
        this.IdPersonne = IdPersonne;
        this.IdReservation = IdReservation;
        this.Categorie = Categorie;
        this.Heure = Heure;
    }

    //Ajout + Suppression  Personnel
    public Log(Integer IdPersonne,Integer IdPersonnerModifier,String Date ,Integer IdReservation, String Categorie,String Heure){
        this.Date = Date;
        this.IdPersonne = IdPersonne;
        this.IdReservation = IdReservation;
        this.Categorie = Categorie;
        this.IdPersonneModifier = IdPersonnerModifier;
        this.Heure = Heure;
    }

    //Ajout + Suppression Utilisateur
    public Log(Integer IdPersonne,Integer IdPersonneCreer,String Date , String Categorie,String Heure){
        this.Date = Date;
        this.IdPersonne = IdPersonne;
        this.Categorie = Categorie;
        this.IdPersonneModifier = IdPersonneCreer;
        this.Heure = Heure;
    }

    public Log(Integer IdPersonne,String Date,String Heure){
        this.Date = Date;
        this.IdPersonne = IdPersonne;
        this.Heure = Heure;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        IdPersonne = idPersonne;
    }

    public Integer getIdPersonneModifier() {
        return IdPersonneModifier;
    }

    public void setIdPersonneModifier(Integer idPersonneModifier) {
        IdPersonneModifier = idPersonneModifier;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Integer getIdCategorie() {
        return IdCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        IdCategorie = idCategorie;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public Integer getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(Integer idReservation) {
        IdReservation = idReservation;
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String heure) {
        Heure = heure;
    }

    public Integer getIdLog() {
        return IdLog;
    }

    public void setIdLog(Integer idLog) {
        IdLog = idLog;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }
}
