package Entity;

public class Role {
    private Integer idRole;
    private String Libelle;
    private Integer Permission;

    public Role() {
        super();
    }

    public Role(Integer idRole, String libelle, Integer permission) {
        this.idRole = idRole;
        Libelle = libelle;
        Permission = permission;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public Integer getPermission() {
        return Permission;
    }

    public void setPermission(Integer permission) {
        Permission = permission;
    }
}
