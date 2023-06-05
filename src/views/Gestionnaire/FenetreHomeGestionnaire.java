package views.Gestionnaire;

import javax.swing.*;

public class FenetreHomeGestionnaire extends JFrame{
    private JPanel PanelHome;
    private JButton BoutonPropreReservation;
    private JButton BoutonListePropreReservation;
    private JButton BoutonReservation;
    private JButton BoutonListeReservation;
    private JLabel TextPanel;
    private JButton BoutonQuitter;
    private JButton Quitterbtn;


    public FenetreHomeGestionnaire() {
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

    public JButton getBoutonQuitter() {
        return BoutonQuitter;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
