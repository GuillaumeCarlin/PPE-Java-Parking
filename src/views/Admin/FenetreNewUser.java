package views.Admin;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class FenetreNewUser extends JFrame{
    private JPanel panel1;
    private JButton BoutonRetour;

    private JButton Valider;
    private JLabel NomText;
    private JLabel PrenomText;
    private JLabel EmailText;
    private JComboBox RoleBox;
    private JTextField PosteField;
    private JTextField MdpField;
    private JTextField TelField;
    private JTextField EmailField;
    private JTextField PrenomField;
    private JTextField NomField;



    public FenetreNewUser(){
        setContentPane(panel1);
        setTitle("Page New User");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[]args){
        new FenetreNewUser().setVisible(true);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JButton getBoutonRetour() {
        return BoutonRetour;
    }

    public void setBoutonRetour(JButton boutonRetour) {
        BoutonRetour = boutonRetour;
    }

    public JButton getValider() {
        return Valider;
    }

    public void setValider(JButton valider) {
        Valider = valider;
    }

    public JLabel getNomText() {
        return NomText;
    }

    public void setNomText(JLabel nomText) {
        NomText = nomText;
    }

    public JLabel getPrenomText() {
        return PrenomText;
    }

    public void setPrenomText(JLabel prenomText) {
        PrenomText = prenomText;
    }

    public JLabel getEmailText() {
        return EmailText;
    }

    public void setEmailText(JLabel emailText) {
        EmailText = emailText;
    }

    public JComboBox getRoleBox() {
        return RoleBox;
    }

    public void setRoleBox(JComboBox roleBox) {
        RoleBox = roleBox;
    }

    public JTextField getPosteField() {
        return PosteField;
    }

    public void setPosteField(JTextField posteField) {
        PosteField = posteField;
    }

    public JTextField getMdpField() {
        return MdpField;
    }

    public void setMdpField(JTextField mdpField) {
        MdpField = mdpField;
    }

    public JTextField getTelField() {
        return TelField;
    }

    public void setTelField(JTextField telField) {
        TelField = telField;
    }

    public JTextField getEmailField() {
        return EmailField;
    }

    public void setEmailField(JTextField emailField) {
        EmailField = emailField;
    }

    public JTextField getPrenomField() {
        return PrenomField;
    }

    public void setPrenomField(JTextField prenomField) {
        PrenomField = prenomField;
    }

    public JTextField getNomField() {
        return NomField;
    }

    public void setNomField(JTextField nomField) {
        NomField = nomField;
    }
}
