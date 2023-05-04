package views.Visiteur;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class FenetreReservation extends JFrame{
    private JPanel panel1;
    private JPanel TestPanel;
    private JComboBox PlaceComboBox;
    private JButton BoutonRetour;
    private JLabel TexteHeureDebut;
    private JLabel TexteHeureFin;

    private JSpinner SpinnerD;
    private JSpinner SpinnerF;
    private JPanel SpinnerDebutPanel;
    private JPanel SpinnerFinPanel;
    private JTable TableReservation;
    private JButton Valider;
    private JCheckBox electriqueCheck;
    private JPanel Testpannel;

    private JDateChooser datechos = new JDateChooser();


    public FenetreReservation(){
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Page de Réservation");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        datechos.setDateFormatString("dd/MM/yyyy");
        TestPanel.add(datechos);

        // Centrer les valeurs dans les cellules
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        getTableReservation().setDefaultRenderer(String.class, centerRenderer);
        getTableReservation().setDefaultRenderer(Integer.class, centerRenderer);

        // Permet de sélectionner qu'une seul ligne
        getTableReservation().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

/*
        JSpinner.DateEditor editorD = new JSpinner.DateEditor(SpinnerDebut, "HH:mm");
        editorD.getTextField().setEditable(false);
        SpinnerDebut.setEditor(editorD);
        SpinnerDebutPanel.add(SpinnerDebut);


        JSpinner.DateEditor editorF = new JSpinner.DateEditor(SpinnerFin, "HH:mm");
        editorF.getTextField().setEditable(false);
        SpinnerFin.setEditor(editorF);
        SpinnerFinPanel.add(SpinnerFin);
    */

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date dateDebut = new Date();
        Date dateFin = calendar.getTime();

        SpinnerDateModel modelD = new SpinnerDateModel(dateDebut, null, null, Calendar.MINUTE);
        JSpinner spinnerD = new JSpinner(modelD);
        JSpinner.DateEditor editorD = new JSpinner.DateEditor(spinnerD, "HH:mm");
        spinnerD.setEditor(editorD);

        SpinnerDateModel modelF = new SpinnerDateModel(dateFin, null, null, Calendar.MINUTE);
        JSpinner spinnerF = new JSpinner(modelF);
        JSpinner.DateEditor editorF = new JSpinner.DateEditor(spinnerF, "HH:mm");
        spinnerF.setEditor(editorF);

        this.SpinnerD = spinnerD;
        this.SpinnerF = spinnerF;


        SpinnerDebutPanel.add(spinnerD);
        SpinnerFinPanel.add(spinnerF);

    }

    public static void main(String[]args){
        new FenetreReservation().setVisible(true);
    }


    public JSpinner getSpinnerD() {
        return SpinnerD;
    }

    public void setSpinnerD(JSpinner spinnerD) {
        SpinnerD = spinnerD;
    }

    public JSpinner getSpinnerF() {
        return SpinnerF;
    }

    public void setSpinnerF(JSpinner spinnerF) {
        SpinnerF = spinnerF;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JPanel getTestPanel() {
        return TestPanel;
    }

    public void setTestPanel(JPanel testPanel) {
        TestPanel = testPanel;
    }

    public JComboBox getPlaceComboBox() {
        return PlaceComboBox;
    }

    public void setPlaceComboBox(JComboBox placeComboBox) {
        PlaceComboBox = placeComboBox;
    }


    public JButton getBoutonRetour() {
        return BoutonRetour;
    }

    public void setBoutonRetour(JButton boutonRetour) {
        BoutonRetour = boutonRetour;
    }

    public JLabel getTexteHeureDebut() {
        return TexteHeureDebut;
    }

    public void setTexteHeureDebut(JLabel texteHeureDebut) {
        TexteHeureDebut = texteHeureDebut;
    }

    public JLabel getTexteHeureFin() {
        return TexteHeureFin;
    }

    public void setTexteHeureFin(JLabel texteHeureFin) {
        TexteHeureFin = texteHeureFin;
    }


    public JTable getTableReservation() {
        return TableReservation;
    }

    public void setTableReservation(JTable tableReservations) {
        TableReservation = tableReservations;
    }


    public JDateChooser getDatechos() {
        return datechos;
    }

    public void setDatechos(JDateChooser datechos) {
        this.datechos = datechos;
    }


    public JButton getValider() {
        return Valider;
    }

    public void setValider(JButton valider) {
        Valider = valider;
    }

    public JPanel getSpinnerDebutPanel() {
        return SpinnerDebutPanel;
    }

    public void setSpinnerDebutPanel(JPanel spinnerDebutPanel) {
        SpinnerDebutPanel = spinnerDebutPanel;
    }

    public JPanel getSpinnerFinPanel() {
        return SpinnerFinPanel;
    }

    public void setSpinnerFinPanel(JPanel spinnerFinPanel) {
        SpinnerFinPanel = spinnerFinPanel;
    }

    public JCheckBox getElectriqueCheck() {
        return electriqueCheck;
    }

    public void setElectriqueCheck(JCheckBox electriqueCheck) {
        this.electriqueCheck = electriqueCheck;
    }

    public JPanel getTestpannel() {
        return Testpannel;
    }

    public void setTestpannel(JPanel testpannel) {
        Testpannel = testpannel;
    }
}
