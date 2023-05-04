package DAO;

import Entity.Place;
import Entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOParking {
    private Connection cnx;
    public DAOParking(Connection cnx){
        this.cnx = cnx;
    }

    public List<Place> FindAllParking(Integer Bool) throws SQLException {
        List<Place> Places = new ArrayList<Place>();
        String sql = "SELECT * FROM Parking WHERE Electrique = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, Bool);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Place place = new Place();
            place.setIdPlace(rs.getString("IdPlace"));
            place.setElectrique(rs.getInt("Electrique"));
            Places.add(place);
        }
        return Places;
    }

    public List<Place> FindParkingLibrebyDate(String Date) throws SQLException {
        List<Place> Places = new ArrayList<Place>();
        String sql = "SELECT * FROM Parking WHERE IdPlace IN (SELECT IdPlace FROM Reservation WHERE Date = ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, Date);
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            Place place = new Place();
            place.setIdPlace(rs.getString("IdPlace"));
            place.setElectrique(rs.getInt("Electrique"));
            Places.add(place);
        }
        return Places;
    }
}
