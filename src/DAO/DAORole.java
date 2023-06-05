package DAO;

import Entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAORole {
    private Connection cnx;

    public DAORole(Connection cnx) {
        this.cnx = cnx;
    }

    public String findRole(Integer IdentifiantRole) throws SQLException {
        String sql = "SELECT * FROM Reservation WHERE IdPersonne = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, IdentifiantRole);
        ResultSet rs = ps.executeQuery();
        return "Salut";
    }

    public Role findRoleById(Integer idRole) throws SQLException {
        Role role = null;
        String sql = "SELECT * FROM Role Where IdRole = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, idRole);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            role = new Role();
            role.setIdRole(rs.getInt("IdRole"));
            role.setLibelle(rs.getString("Libelle"));
            role.setPermission(rs.getInt("permission"));
        }
        return role;
    }

    public Integer FindIdbyName(String Name) throws SQLException {
        String sql = "SELECT IdRole FROM Role r WHERE r.Libelle = ?";
        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setString(1, Name);
        ResultSet rs = ps.executeQuery();
        Integer Ide;
        if(rs.next()) {
            Ide = rs.getInt("IdRole");
        }else{
            Ide = null;
        }
        return Ide;
    }
}
