package views.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class FenetreListeUtilisateur extends JFrame{
    private JPanel MainPanel;
    private JTextField RechercheField;
    private JComboBox comboBoxRecherche;
    private JButton rechercherButton;
    private JTable table1;
    private JButton retourButton;
    private JScrollPane TableReservations;
    private JLabel NomText;
    private JLabel PosteText;
    private JLabel EmailText;
    private JLabel PhoneText;
    private JLabel RoleText;
    private JButton refreshButton;
    private JButton SupprimerButton;
    private JButton ajouterButton;
    private JCheckBox InactifCheck;


    public FenetreListeUtilisateur() {
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

    public JLabel getPosteText() {
        return PosteText;
    }

    public void setPosteText(JLabel posteText) {
        PosteText = posteText;
    }

    public JLabel getEmailText() {
        return EmailText;
    }

    public void setEmailText(JLabel emailText) {
        EmailText = emailText;
    }

    public JLabel getPhoneText() {
        return PhoneText;
    }

    public void setPhoneText(JLabel phoneText) {
        PhoneText = phoneText;
    }

    public JLabel getRoleText() {
        return RoleText;
    }

    public void setRoleText(JLabel roleText) {
        RoleText = roleText;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public void setRefreshButton(JButton refreshButton) {
        this.refreshButton = refreshButton;
    }

    public JButton getSupprimerButton() {
        return SupprimerButton;
    }

    public void setSupprimerButton(JButton supprimerButton) {
        SupprimerButton = supprimerButton;
    }

    public JButton getAjouterButton() {
        return ajouterButton;
    }

    public void setAjouterButton(JButton ajouterButton) {
        this.ajouterButton = ajouterButton;
    }

    public JCheckBox getActifCheck() {
        return InactifCheck;
    }

    public void setActifCheck(JCheckBox actifCheck) {
        InactifCheck = actifCheck;
    }
}
