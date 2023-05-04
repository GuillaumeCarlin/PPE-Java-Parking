package Controllers.Visiteur;

import Entity.Place;
import Entity.Reservation;

import javax.swing.table.DefaultTableModel;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;

public class TableModelNewReservation extends DefaultTableModel {

    List<Place> Places ;
    private String[] columNames = {"IdPlace"};
    HashSet<Place> modified;


    public TableModelNewReservation(List<Place> Places){
        super();
        this.Places = Places;
        modified = new HashSet<Place>();
    }

    public TableModelNewReservation() {
        super();
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public int getRowCount() {
        if(Places == null){
            return 0;
        }
        return Places.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return (column>1);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Place place = Places.get(row);
        Object value = null;
        switch(column){
            case 0:
                value = place.getIdPlace();
                break;
            default:
                break;
        }
        return value;
    }

/*
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Reservation reservation = reservations.get(row);
        switch (column) {
            case 2:
                reservation.setHeureDebut((Time) aValue);
                break;
            case 3:
                reservation.setHeureFin((Time) aValue);
                break;
            default:
                break;
        }
        modified.add(reservation);
        System.out.println(reservation);
    }
*/
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> type = null;
        switch (columnIndex) {
            case 0:
                type = String.class;
                break;
            default:
                break;
        }
        return type;
    }
}