package Controllers;
import Controllers.Admin.ControllerHomeAdmin;
import Controllers.Gestionnaire.ControllerHomeGestionnaire;
import Controllers.Visiteur.ControllerHomeVisiteur;
import DAO.DAOPersonne;
import DAO.DAORole;
import Entity.Log;
import Entity.Personne;
import Entity.Role;
import Utils.Outils;
import Utils.Singleton;
import views.Admin.FenetreHomeAdmin;
import views.Visiteur.FenetreHomeVisiteur;
import views.Gestionnaire.FenetreHomeGestionnaire;
import views.FenetreLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Base64;

public class ControllerLogin {
    private FenetreLogin fenetre;
    private DAOPersonne daop;
    private DAORole daor;

    public ControllerLogin(FenetreLogin fenetre, DAOPersonne daop, DAORole daor){
        super();
        this.fenetre = fenetre;
        this.daop = daop;
        this.daor = daor;
    }

    public void init() {
        fenetre.setVisible(true);
        try {
            fenetre.getLoginField().setText("admin.admin@admin.fr");
            fenetre.getMdpField().setText("123+aze");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        fenetre.getValiderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(LoginValidation(fenetre.getMdpField().getText(), fenetre.getLoginField().getText()) == true){
                    try {
                        Personne personne = daop.findByEmail(fenetre.getLoginField().getText());
                        Role Role = daor.findRoleById(personne.getIdRole());
                        Outils Log = new Outils();
                        Log.New_Log_Connexion(personne.getIdPersonne());
                        if(Role.getLibelle().equals("Gestionnaire")){
                            System.out.println("Gestionnaire");
                            new ControllerHomeGestionnaire(new FenetreHomeGestionnaire(), new DAOPersonne(Singleton.getInstance().cnx), personne, Role).init();
                        }
                        else if (Role.getLibelle().equals("Visiteur")){
                            System.out.println("Visiteur");
                            new ControllerHomeVisiteur(new FenetreHomeVisiteur(),daop ,personne, Role).init();
                        }
                        else if(Role.getLibelle().equals("Admin")) {
                            System.out.println("Admin");
                            new ControllerHomeAdmin(new FenetreHomeAdmin(), personne, Role).init();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    fenetre.setVisible(false);
                }
            }
        });
    }

    public static String chiffrement(String Message) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(Message.getBytes("UTF-8"));
        String encodedHash = Base64.getEncoder().encodeToString(hash);
        return encodedHash;
    }

    public boolean LoginValidation(String MotdePasse, String Identfiant){
        try {
            Personne personne = daop.findByEmail(Identfiant);
            if (chiffrement(MotdePasse).equals(personne.getMdp())){
                return true;
            }
            else{
                fenetre.getMdpTexte().setText("Mot de Passe Incorrecte");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
