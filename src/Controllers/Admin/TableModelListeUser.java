package Controllers.Admin;

import Entity.Log;
import Entity.Personne;

import javax.swing.table.DefaultTableModel;
import java.util.HashSet;
import java.util.List;

public class TableModelListeUser extends DefaultTableModel {
    List<Personne> Personnes ;

    private String[] columNames = {"Nom", "Poste", "Email"};
    HashSet<Personne> modified;


    public TableModelListeUser(List<Personne> personnes){
        super();
        this.Personnes = personnes;
        modified = new HashSet<Personne>();
    }

    public TableModelListeUser() {
        super();
    }

    @Override
    public void removeRow(int row) {
        super.removeRow(row);
    }

    @Override
    public int getRowCount() {
        if(Personnes == null){
            return 0;
        }
        return Personnes.size();
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
        Personne personne = Personnes.get(row);
        Object value = null;
        switch(column){
            case 0:
                value = personne.getNom() + " " + personne.getPrenom();
                break;
            case 1:
                value = personne.getPoste();
                break;
            case 2:
                value = personne.getEmail();
                break;
            case 4:
                value = personne.getIdPersonne();
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
