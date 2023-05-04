package views.Visiteur;

import javax.swing.*;
import java.awt.*;

public class FenetreHomeVisiteur extends JFrame{
    private JPanel PanelHome;
    private JButton ReserverButton;
    private JButton AllReservationButton;
    private JLabel TexteHome;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FenetreHomeVisiteur frame = new FenetreHomeVisiteur();
                    frame.setContentPane(frame.PanelHome);
                    frame.setTitle("PageLogin");
                    frame.pack();
                    frame.setExtendedState(MAXIMIZED_BOTH);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FenetreHomeVisiteur(){
        setContentPane(PanelHome);
        setTitle("PageLogin");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel getPanelHome() {
        return PanelHome;
    }

    public void setPanelHome(JPanel panelHome) {
        PanelHome = panelHome;
    }

    public JButton getReserverButton() {
        return ReserverButton;
    }

    public void setReserverButton(JButton reserverButton) {
        ReserverButton = reserverButton;
    }

    public JButton getAllReservationButton() {
        return AllReservationButton;
    }

    public void setAllReservationButton(JButton allReservationButton) {
        AllReservationButton = allReservationButton;
    }

    public JLabel getTexteHome() {
        return TexteHome;
    }

    public void setTexteHome(JLabel texteHome) {
        TexteHome = texteHome;
    }
}
