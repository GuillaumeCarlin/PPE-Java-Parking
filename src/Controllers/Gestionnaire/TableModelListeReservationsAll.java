package Controllers.Gestionnaire;

import Entity.Reservation;

import javax.swing.table.DefaultTableModel;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;

public class TableModelListeReservationsAll extends DefaultTableModel {
    List<Reservation> reservations ;
    private String[] columNames = {"Date", "Place", "Nom", "HeureDebut", "HeureFin"};
    HashSet<Reservation> modified;


    public TableModelListeReservationsAll(List<Reservation> reservations){
        super();
        this.reservations = reservations;
        modified = new HashSet<Reservation>();
    }

    public TableModelListeReservationsAll() {
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
        return 5;
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
                value = reservation.getDate();
                break;
            case 1:
                value = reservation.getIdPlace();
                break;
            case 2:
                value = reservation.getNom();
                break;
            case 3:
                value = reservation.getHeureDebut();
                break;
            case 4:
                value = reservation.getHeureFin();
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
    }
*/
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> type = null;
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                type = String.class;
                break;
            default:
                break;
        }
        return type;
    }
}
