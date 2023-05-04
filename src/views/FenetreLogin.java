package views;
import javax.swing.*;
import java.awt.*;

public class FenetreLogin extends JFrame{
    private JPanel PanelLogin;
    private JTextField LoginField;
    private JPasswordField MdpField;
    private JButton validerButton;

    public JLabel getMdpTexte() {return MdpTexte;}

    private JLabel MdpTexte;

    public JPanel getPanelLogin() {
        return PanelLogin;
    }

    public void setPanelLogin(JPanel panelLogin) {
        PanelLogin = panelLogin;
    }

    public JTextField getLoginField() {
        return LoginField;
    }

    public void setLoginField(JTextField loginField) {
        LoginField = loginField;
    }

    public JPasswordField getMdpField() {
        return MdpField;
    }

    public void setMdpField(JPasswordField mdpField) {
        MdpField = mdpField;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public void setValiderButton(JButton validerButton) {
        this.validerButton = validerButton;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FenetreLogin frame = new FenetreLogin();
                    frame.setContentPane(frame.PanelLogin);
                    frame.setTitle("PageLogin");
                    frame.pack();
                    frame.setExtendedState(frame.MAXIMIZED_BOTH);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FenetreLogin(){
        setContentPane(PanelLogin);
        setTitle("PageLogin");
        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
