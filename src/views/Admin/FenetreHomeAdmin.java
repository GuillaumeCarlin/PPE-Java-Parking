package views.Admin;

import javax.swing.*;

public class FenetreHomeAdmin extends JFrame{
    private JPanel PanelHome;
    private JButton BoutonUneReservation;
    private JButton BoutonListeReservation;
    private JLabel TextPanel;
    private JButton listeDesUtilisateursButton;
    private JButton logButton;
    private JButton QuitterBtn;


    public FenetreHomeAdmin() {
        setContentPane(PanelHome);
        setTitle("PageLogin");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public JLabel getTextPanel() {
        return TextPanel;
    }



    public JButton getListeDesUtilisateursButton() {
        return listeDesUtilisateursButton;
    }


    public JButton getQuitterBtn() {
        return QuitterBtn;
    }

    public JButton getBoutonUneReservation() {
        return BoutonUneReservation;
    }

    public JButton getBoutonListeReservation() {
        return BoutonListeReservation;
    }

    public JButton getLogButton() {
        return logButton;
    }
}
