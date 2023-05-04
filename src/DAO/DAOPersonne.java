package DAO;
import Entity.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPersonne {
    private Connection cnx;
    public DAOPersonne(Connection cnx){
        this.cnx = cnx;
    }
    public Personne find(String Identifiant) throws SQLException {
        Personne personne = null;
        String SQL = "SELECT * FROM Gestion_Parking.Personne WHERE IdPersonne=?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, Identifiant);
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

    public Personne findByEmail(String email) throws SQLException{
        Personne personne = null;
        String SQL = "SELECT * FROM Gestion_Parking.Personne WHERE email=?";
        PreparedStatement ps = cnx.prepareStatement(SQL);
        ps.setString(1, email);
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
}
