package views.Admin;

import javax.swing.*;

public class FenetreHomeAdmin extends JFrame{
    private JPanel PanelMain;
    private JButton BoutonPropreReservation;
    private JButton BoutonListePropreReservation;
    private JButton BoutonReservation;
    private JButton BoutonListeReservation;
    private JLabel TextPanel;


    public FenetreHomeAdmin() {
        setContentPane(PanelMain);
        setTitle("PageLogin");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public JLabel getTextPanel() {
        return TextPanel;
    }

    public void setTextPanel(JLabel textPanel) {
        TextPanel = textPanel;
    }

    public JButton getBoutonPropreReservation() {
        return BoutonPropreReservation;
    }

    public void setBoutonPropreReservation(JButton boutonPropreReservation) {
        BoutonPropreReservation = boutonPropreReservation;
    }

    public JButton getBoutonListePropreReservation() {
        return BoutonListePropreReservation;
    }

    public void setBoutonListePropreReservation(JButton boutonListePropreReservation) {
        BoutonListePropreReservation = boutonListePropreReservation;
    }

    public JButton getBoutonReservation() {
        return BoutonReservation;
    }

    public void setBoutonReservation(JButton boutonReservation) {
        BoutonReservation = boutonReservation;
    }

    public JButton getBoutonListeReservation() {
        return BoutonListeReservation;
    }

    public void setBoutonListeReservation(JButton boutonListeReservation) {
        BoutonListeReservation = boutonListeReservation;
    }
}
