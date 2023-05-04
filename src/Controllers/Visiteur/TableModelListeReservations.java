package Controllers.Visiteur;

import Entity.Reservation;

import javax.swing.table.DefaultTableModel;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;

public class TableModelListeReservations extends DefaultTableModel {
    List<Reservation> reservations ;
    private String[] columNames = {"Place", "HeureDebut", "HeureFin", "Date"};
    HashSet<Reservation> modified;


    public TableModelListeReservations(List<Reservation> reservations){
        super();
        this.reservations = reservations;
        modified = new HashSet<Reservation>();
    }

    public TableModelListeReservations() {
        super();
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public int getRowCount() {
        if(reservations == null){
            return 0;
        }
        return reservations.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return (column>5);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Reservation reservation = reservations.get(row);
        Object value = null;
        switch(column){
            case 0:
                value = reservation.getIdPlace();
                break;
            case 1:
                value = reservation.getHeureDebut();
                break;
            case 2:
                value = reservation.getHeureFin();
                break;
            case 3:
                value = reservation.getDate();
            default:
                break;
        }
        return value;
        }


    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Reservation reservation = reservations.get(row);
        switch (column) {
            case 1:
                reservation.setHeureDebut((Time) aValue);
                break;
            case 2:
                reservation.setHeureFin((Time) aValue);
                break;
            default:
                break;
        }
        modified.add(reservation);
        System.out.println(reservation);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> type = null;
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                type = String.class;
                break;
            default:
                break;
        }
        return type;
    }
}
