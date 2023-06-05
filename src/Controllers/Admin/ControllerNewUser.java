package Controllers.Admin;

import DAO.*;
import Entity.Personne;
import Entity.Role;
import Utils.Outils;
import Utils.Singleton;
import views.Admin.FenetreListeUtilisateur;
import views.Admin.FenetreNewUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

public class ControllerNewUser {

    private FenetreNewUser fenetre;
    private Personne personne;
    private Role role;
    private DAOPersonne daop;

    public ControllerNewUser(FenetreNewUser fenetre, Personne personne, Role role, DAOPersonne daop) {
        this.fenetre = fenetre;
        this.personne = personne;
        this.role = role;
        this.daop = daop;

        //TODO: Faire une pop-up à la connexion quand le mot de passe n'est pas bon
    }

    public void init(){
        fenetre.setVisible(true);
        fenetre.getRoleBox().addItem("Visiteur");
        fenetre.getRoleBox().addItem("Gestionnaire");
        fenetre.getRoleBox().addItem("Admin");


        fenetre.getBoutonRetour().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ControllerListeUser(new FenetreListeUtilisateur(), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                fenetre.setVisible(false);
            }
        });

        fenetre.getValider().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nom = fenetre.getNomField().getText();
                String Prenom = fenetre.getPrenomField().getText();
                String Poste = fenetre.getPosteField().getText();
                String Telephone = fenetre.getTelField().getText();
                String Mdp = fenetre.getMdpField().getText();
                String Email = fenetre.getEmailField().getText();
                String Role = fenetre.getRoleBox().getSelectedItem().toString();
                if (VerifString(Nom) && VerifString(Prenom) && VerifString(Poste)){
                    if(VerifTelephone(Telephone)){
                        try {
                            if(VerifEmail(Email)){
                                if(VerifMdp(Mdp)) {
                                    try {
                                        Integer Id = new DAORole(Singleton.getInstance().cnx).FindIdbyName(Role);
                                        String Message = daop.NewUser(Nom, Prenom, Poste, Telephone, chiffrement(Mdp), Email, Id);
                                        Personne personne_target = daop.findByEmail(Email);
                                        new Outils().New_Log_Ajout_Utilisateur(Nom + " " + Prenom, personne_target.getIdPersonne(),personne);
                                        JOptionPane.showMessageDialog(null, Message);
                                    } catch (Exception ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    try {
                                        new ControllerListeUser(new FenetreListeUtilisateur(), new DAOPersonne(Singleton.getInstance().cnx), personne, role).init();
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    fenetre.setVisible(false);
                                }else{
                                    JOptionPane.showMessageDialog(null, "Le champ Mot de passe n'est pas valide");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Le champ Email n'est pas valide");
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Le champ Téléphone n'est pas valide");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Le champ Nom, Prenom ou Poste n'est pas valide");
                }
            }
        });
    }

    public boolean VerifString(String Texte){
        if (Texte.length() == 0){
            return false;
        }
        for (char c : Texte.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println(c);
                return false;
            }
        }
        return true;
    }

    public boolean VerifTelephone(String Texte){
        if (Texte.length() != 10){
            return false;
        }
        for (char c : Texte.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean VerifEmail(String email) throws SQLException {
        List<String> Emails = daop.AllEmail();
        for (String Email : Emails) {
            if (Email.equals(email)) {
                return false;
            }
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern EmailPattern = Pattern.compile(regex);
        return EmailPattern.matcher(email).matches();
    }
    public boolean VerifMdp(String mdp){
        return !(mdp.length() == 0);
    }
    public static String chiffrement(String Message) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(Message.getBytes("UTF-8"));
        String encodedHash = Base64.getEncoder().encodeToString(hash);
        return encodedHash;
    }

}
