package Entity;

public class Place {
    private String IdPlace;
    private Integer Electrique;

    public Place(){
        super();
    }

    public Place(String IdPlace, Integer Electrique){
        this.IdPlace = IdPlace;
        this.Electrique = Electrique;
    }


    public String getIdPlace() {
        return IdPlace;
    }

    public void setIdPlace(String idPlace) {
        IdPlace = idPlace;
    }

    public Integer getElectrique() {
        return Electrique;
    }

    public void setElectrique(Integer electrique) {
        Electrique = electrique;
    }
}
