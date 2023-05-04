package views;

import Entity.Personne;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FenetreHomeAdmin extends JFrame{

    private JPanel PanelHome;
    private JButton ReserverButton;
    private JButton AllReservationButton;
    private JLabel TexteHome;

    public FenetreHomeAdmin(){
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
