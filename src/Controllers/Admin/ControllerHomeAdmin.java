package Controllers.Admin;

import Entity.Personne;
import Entity.Role;
import views.FenetreHomeAdmin;

public class ControllerHomeAdmin {
    FenetreHomeAdmin fenetre;
    Personne personne;
    Role role;

    public ControllerHomeAdmin(FenetreHomeAdmin fenetre, Personne personne, Role role) {
        this.fenetre = fenetre;
        this.personne = personne;
        this.role = role;
    }

    public void init(){
        fenetre.setVisible(true);
        fenetre.getTexteHome().setText("Bienvenue " + personne.getNom() + " " + personne.getPrenom() + " " + role.getLibelle());
    }
}
