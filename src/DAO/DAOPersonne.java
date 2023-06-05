package DAO;
import Entity.Log;
import Entity.Personne;
import Entity.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOPersonne {
    private Connection cnx;
    public DAOPersonne(Connection cnx){
        this.cnx = cnx;
    }
    public Personne find(Integer Identifiant) throws SQLException {
        Personne personne = null;
        String SQL = "SELECT p.IdPersonne, p.Nom, p.Prenom, p.Poste, p.Telephone, p.email, p.IdRole, r.Libelle FROM Personne p JOIN Role r ON p.IdRole = r.IdRole WHERE IdPersonne=?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setInt(1, Identifiant);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            personne = new Personne();
            personne.setIdPersonne(rs.getInt("IdPersonne"));
            personne.setNom(rs.getString("Nom"));
            personne.setPrenom(rs.getString("Prenom"));
            personne.setPoste(rs.getString("Poste"));
            personne.setTelephone(rs.getString("Telephone"));
            personne.setEmail(rs.getString("email"));
            personne.setIdRole(rs.getInt("IdRole"));
            personne.setRoleName(rs.getString("Libelle"));
        }
        return personne;
    }

    public List<Personne> FindAll(Boolean estInactif) throws SQLException {
        Personne personne;
        List<Personne> Personnes = new ArrayList<Personne>();
        String SQL = "SELECT p.IdPersonne, p.Nom, p.Prenom, p.Poste, p.Telephone, p.email, p.IdRole, r.Libelle FROM Personne p JOIN Role r ON p.IdRole = r.IdRole WHERE p.estInactif = ?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        if (estInactif){
            ps.setInt(1, 1);
        }else{
            ps.setInt(1, 0);
        }
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            personne = new Personne();
            personne.setIdPersonne(rs.getInt("IdPersonne"));
            personne.setNom(rs.getString("Nom"));
            personne.setPrenom(rs.getString("Prenom"));
            personne.setPoste(rs.getString("Poste"));
            personne.setTelephone(rs.getString("Telephone"));
            personne.setEmail(rs.getString("email"));
            personne.setIdRole(rs.getInt("IdRole"));
            personne.setRoleName(rs.getString("Libelle"));
            Personnes.add(personne);
        }
        return Personnes;
    }

    public Personne findByEmail(String email) throws SQLException{
        Personne personne = null;
        String SQL = "SELECT * FROM Gestion_Parking.Personne p WHERE p.email = ? and p.estInactif = ?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, email);
        ps.setInt(2, 0);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            personne = new Personne();
            personne.setIdPersonne(rs.getInt("IdPersonne"));
            personne.setNom(rs.getString("Nom"));
            personne.setPrenom(rs.getString("Prenom"));
            personne.setPoste(rs.getString("Poste"));
            personne.setTelephone(rs.getString("Telephone"));
            personne.setMdp(rs.getString("Mdp"));
            personne.setEmail(rs.getString("email"));
            personne.setIdRole(rs.getInt("IdRole"));
        }
        return personne;
    }

    public Map<String, Integer> FindAllPersonnes() throws SQLException {
        Map<String, Integer> Personnes = new HashMap<>();
        String sql = "SELECT concat(p.Nom, \" \", p.Prenom) AS Nom, p.IdPersonne FROM Personne p WHERE p.estInactif = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1,0);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Personnes.put(rs.getString("Nom"), rs.getInt(("IdPersonne")));
        }
        return Personnes;
    }

    public List<Personne> Recherche(String Categorie, String Valeur, Boolean estInactif) throws SQLException {
        Personne personne;
        List<Personne> Personnes = new ArrayList<Personne>();
        String sql = "";
        System.out.println(Categorie);
        System.out.println(Valeur);
        switch(Categorie){
            case "Email":
                sql = "SELECT p.IdPersonne, p.Nom, p.Prenom, p.Poste, p.Telephone, p.email, p.IdRole, r.Libelle FROM Personne p JOIN Role r ON p.IdRole = r.IdRole WHERE p.estInactif = ? AND Email = ?";
                break;
            case "Poste":
                sql = "SELECT p.IdPersonne, p.Nom, p.Prenom, p.Poste, p.Telephone, p.email, p.IdRole, r.Libelle FROM Personne p JOIN Role r ON p.IdRole = r.IdRole WHERE p.estInactif = ? AND Poste = ?";
            break;
            case "Nom":
                Valeur = Valeur.split(" ")[0];
                sql =  "SELECT p.IdPersonne, p.Nom, p.Prenom, p.Poste, p.Telephone, p.email, p.IdRole, r.Libelle FROM Personne p JOIN Role r ON p.IdRole = r.IdRole WHERE p.estInactif = ? AND Nom = ?";
                break;
            default:
        }
        PreparedStatement ps = cnx.prepareStatement(sql);
        if(estInactif){
            ps.setInt(1, 1);
        }
        else {
            ps.setInt(1, 0);
        }
        ps.setString(2, Valeur);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            personne = new Personne();
            personne.setIdPersonne(rs.getInt("IdPersonne"));
            personne.setNom(rs.getString("Nom"));
            personne.setPrenom(rs.getString("Prenom"));
            personne.setPoste(rs.getString("Poste"));
            personne.setTelephone(rs.getString("Telephone"));
            personne.setEmail(rs.getString("email"));
            personne.setIdRole(rs.getInt("IdRole"));
            personne.setRoleName(rs.getString("Libelle"));
            Personnes.add(personne);
        }
        return Personnes;
    }

    public String DeleteUser(Integer Identifiant) throws SQLException {
        Personne personne;
        String sql = "UPDATE Personne SET estInactif = ? WHERE IdPersonne = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, 1);
        ps.setInt(2, Identifiant);
        int nbLigne = ps.executeUpdate();
        if (nbLigne > 0){
            String Message = "L'Utilisateur à bien été supprimer";
            return Message;
        }else{
            String Message = "Une erreur est survenue lors de la suppression";
            return Message;
        }

    }

    public String NewUser(String Nom, String Prenom, String Poste, String Telephone, String Mdp, String Email, Integer IdRole) throws SQLException {
        String sql = "INSERT INTO Personne(Nom, Prenom, Poste, Telephone, Mdp, Email, IdRole, estInactif) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, Nom);
        ps.setString(2, Prenom);
        ps.setString(3, Poste);
        ps.setString(4, Telephone);
        ps.setString(5, Mdp);
        ps.setString(6, Email);
        ps.setInt(7, IdRole);
        ps.setInt(8, 0);
        int nbLine = ps.executeUpdate();
        if (nbLine > 0){
            String Message = "L'utilisateur à bien été crée";
            return Message;
        }else{
            String Message = "Une erreur est survenue lors de la création";
            return Message;
        }
    }

    public List<String> AllEmail() throws SQLException {
        List<String> Emails = new ArrayList<>();
        String sql = "SELECT Email FROM Personne p WHERE p.estInactif = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1,0);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String email = rs.getString("Email");
            Emails.add(email);
        }
        return Emails;
    }
}
