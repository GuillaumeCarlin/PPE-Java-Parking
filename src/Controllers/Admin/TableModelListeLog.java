package Controllers.Admin;

import Entity.Log;
import Entity.Reservation;

import javax.swing.table.DefaultTableModel;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;

public class TableModelListeLog extends DefaultTableModel {
    List<Log> Logs ;

    private String[] columNames = {"Date", "Nom", "Categorie"}; // Date -> Date + Heure, Nom -> Nom + Prenom
    HashSet<Log> modified;


    public TableModelListeLog(List<Log> logs){
        super();
        this.Logs = logs;
        modified = new HashSet<Log>();
    }

    public TableModelListeLog() {
        super();
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public int getRowCount() {
        if(Logs == null){
            return 0;
        }
        return Logs.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return (column>4);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Log log = Logs.get(row);
        Object value = null;
        switch(column){
            case 0:
                value = log.getDate() + " " + log.getHeure();
                break;
            case 1:
                value = log.getNom();
                break;
            case 2:
                value = log.getCategorie();
                break;
            case 4:
                value = log.getIdLog();
                break;
            default:
                break;
        }
        return value;
    }


    //@Override
    //public void setValueAt(Object aValue, int row, int column) {
    //    Log log = Logs.get(row);
    //    switch (column) {
    //        case 1:
    //            log.setHeureDebut((Time) aValue);
    //            break;
    //        case 2:
    //            log.setHeureFin((Time) aValue);
    //            break;
    //        default:
    //            break;
    //    }
    //    modified.add(reservation);
    //    System.out.println(reservation);
    //}

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
