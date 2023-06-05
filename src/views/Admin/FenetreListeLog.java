package views.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class FenetreListeLog extends JFrame{
    private JPanel MainPanel;
    private JTextField RechercheField;
    private JComboBox comboBoxRecherche;
    private JButton rechercherButton;
    private JTable table1;
    private JButton retourButton;
    private JScrollPane TableReservations;
    private JLabel CategorieText;
    private JLabel DateText;
    private JLabel PosteText;
    private JLabel NomText;
    private JLabel PrenomText;
    private JLabel ElectriqueText;
    private JButton refreshButton;
    private JLabel NomModifierText;
    private JLabel PrenomModifierText;
    private JLabel PosteModifierText;

    private JLabel HeureText;
    private JLabel MessageText;
    private JLabel TestText;


    public FenetreListeLog() {
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

    public JComboBox getComboBoxRecherche() {
        return comboBoxRecherche;
    }

    public JButton getRechercherButton() {
        return rechercherButton;
    }

    public JButton getRetourButton() {
        return retourButton;
    }

    public JScrollPane getTableReservations() {
        return TableReservations;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setComboBoxRecherche(JComboBox comboBoxRecherche) {
        this.comboBoxRecherche = comboBoxRecherche;
    }

    public void setCategorieText(JLabel categorieText) {
        CategorieText = categorieText;
    }

    public void setDateText(JLabel dateText) {
        DateText = dateText;
    }

    public void setPosteText(JLabel posteText) {
        PosteText = posteText;
    }

    public void setNomText(JLabel nomText) {
        NomText = nomText;
    }

    public void setPrenomText(JLabel prenomText) {
        PrenomText = prenomText;
    }

    public void setElectriqueText(JLabel electriqueText) {
        ElectriqueText = electriqueText;
    }

    public void setNomModifierText(JLabel nomModifierText) {
        NomModifierText = nomModifierText;
    }

    public void setPrenomModifierText(JLabel prenomModifierText) {
        PrenomModifierText = prenomModifierText;
    }

    public void setPosteModifierText(JLabel posteModifierText) {
        PosteModifierText = posteModifierText;
    }

    public JLabel getCategorieText() {
        return CategorieText;
    }

    public JLabel getDateText() {
        return DateText;
    }

    public JLabel getPosteText() {
        return PosteText;
    }

    public JLabel getNomText() {
        return NomText;
    }

    public JLabel getPrenomText() {
        return PrenomText;
    }

    public JLabel getElectriqueText() {
        return ElectriqueText;
    }

    public JLabel getNomModifierText() {
        return NomModifierText;
    }

    public JLabel getPrenomModifierText() {
        return PrenomModifierText;
    }

    public JLabel getPosteModifierText() {
        return PosteModifierText;
    }

    public JLabel getHeureText() {
        return HeureText;
    }

    public void setHeureText(JLabel heureText) {
        HeureText = heureText;
    }

    public JLabel getMessageText() {
        return MessageText;
    }

    public void setMessageText(JLabel messageText) {
        MessageText = messageText;
    }

    public JLabel getTestText() {
        return TestText;
    }

    public void setTestText(JLabel testText) {
        TestText = testText;
    }
}
