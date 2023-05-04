package views.Visiteur;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class FenetreReservations extends JFrame{
    private JTable TableReservation;
    private JPanel MainPanel;
    private JLabel LabelReservation;
    private JLabel LabelDate;
    private JLabel LabelPlace;
    private JLabel LabelDebut;
    private JLabel LabelFin;
    private JLabel Labelelectrique;
    private JButton ButtonQRCode;
    private JButton BoutonAnnuler;
    private JButton BoutonRetour;
    private JScrollPane ListeReservations;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FenetreReservations frame = new FenetreReservations();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public FenetreReservations(){
        setContentPane(MainPanel);
        setTitle("Mes réservations");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        getTableReservation().setDefaultRenderer(String.class, centerRenderer);
        getTableReservation().setDefaultRenderer(Integer.class, centerRenderer);

        getTableReservation().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public JTable getTableReservation() {
        return TableReservation;
    }

    public void setTableReservation(JTable tableReservation) {
        TableReservation = tableReservation;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        MainPanel = mainPanel;
    }

    public JLabel getLabelReservation() {
        return LabelReservation;
    }

    public void setLabelReservation(JLabel labelReservation) {
        LabelReservation = labelReservation;
    }

    public JLabel getLabelDate() {
        return LabelDate;
    }

    public void setLabelDate(JLabel labelDate) {
        LabelDate = labelDate;
    }

    public JLabel getLabelPlace() {
        return LabelPlace;
    }

    public void setLabelPlace(JLabel labelPlace) {
        LabelPlace = labelPlace;
    }

    public JLabel getLabelDebut() {
        return LabelDebut;
    }

    public void setLabelDebut(JLabel labelDebut) {
        LabelDebut = labelDebut;
    }

    public JLabel getLabelFin() {
        return LabelFin;
    }

    public void setLabelFin(JLabel labelFin) {
        LabelFin = labelFin;
    }

    public JLabel getLabelelectrique() {
        return Labelelectrique;
    }

    public void setLabelelectrique(JLabel labelelectrique) {
        Labelelectrique = labelelectrique;
    }

    public JButton getButtonQRCode() {
        return ButtonQRCode;
    }

    public void setButtonQRCode(JButton buttonQRCode) {
        ButtonQRCode = buttonQRCode;
    }

    public JButton getBoutonAnnuler() {
        return BoutonAnnuler;
    }

    public void setBoutonAnnuler(JButton boutonAnnuler) {
        BoutonAnnuler = boutonAnnuler;
    }

    public JButton getBoutonRetour() {
        return BoutonRetour;
    }

    public void setBoutonRetour(JButton boutonRetour) {
        BoutonRetour = boutonRetour;
    }
}
