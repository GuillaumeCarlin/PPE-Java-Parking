package views.Gestionnaire;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class FenetreListeReservationGestionnaire extends JFrame{
    private JPanel MainPanel;
    private JTextField RechercheField;
    private JComboBox comboBoxRecherche;
    private JButton rechercherButton;
    private JTable table1;
    private JButton retourButton;
    private JScrollPane TableReservations;
    private JLabel NomText;
    private JLabel PlaceText;
    private JLabel DateText;
    private JLabel HeureDebutText;
    private JLabel HeureFinText;
    private JLabel ElectriqueText;
    private JButton refreshButton;


    public FenetreListeReservationGestionnaire() {
        setContentPane(MainPanel);
        setTitle("PageLogin");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        getTable1().setDefaultRenderer(String.class, centerRenderer);
        getTable1().setDefaultRenderer(Integer.class, centerRenderer);

        getTable1().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public void setRefreshButton(JButton refreshButton) {
        this.refreshButton = refreshButton;
    }

    public JLabel getPlaceText() {
        return PlaceText;
    }

    public void setPlaceText(JLabel placeText) {
        PlaceText = placeText;
    }

    public JTextField getRechercheField() {
        return RechercheField;
    }

    public void setRechercheField(JTextField rechercheField) {
        RechercheField = rechercheField;
    }

    public JComboBox getComboBoxRecherche() {
        return comboBoxRecherche;
    }

    public void setComboBoxRecherche(JComboBox comboBoxRecherche) {
        this.comboBoxRecherche = comboBoxRecherche;
    }

    public JButton getRechercherButton() {
        return rechercherButton;
    }

    public void setRechercherButton(JButton rechercherButton) {
        this.rechercherButton = rechercherButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public void setRetourButton(JButton retourButton) {
        this.retourButton = retourButton;
    }

    public JScrollPane getTableReservations() {
        return TableReservations;
    }

    public void setTableReservations(JScrollPane tableReservations) {
        TableReservations = tableReservations;
    }

    public JLabel getNomText() {
        return NomText;
    }

    public void setNomText(JLabel nomText) {
        NomText = nomText;
    }

    public JLabel getIdReservationText() {
        return PlaceText;
    }

    public void setIdReservationText(JLabel idReservationText) {
        PlaceText = idReservationText;
    }

    public JLabel getDateText() {
        return DateText;
    }

    public void setDateText(JLabel dateText) {
        DateText = dateText;
    }

    public JLabel getHeureDebutText() {
        return HeureDebutText;
    }

    public void setHeureDebutText(JLabel heureDebutText) {
        HeureDebutText = heureDebutText;
    }

    public JLabel getHeureFinText() {
        return HeureFinText;
    }

    public void setHeureFinText(JLabel heureFinText) {
        HeureFinText = heureFinText;
    }

    public JLabel getElectriqueText() {
        return ElectriqueText;
    }

    public void setElectriqueText(JLabel electriqueText) {
        ElectriqueText = electriqueText;
    }
}
