package Entity;

public class Personne {
    private Integer IdPersonne;
    private String Nom;
    private String Prenom;
    private String Poste;
    private String Telephone;
    private String Mdp;
    private String Email;
    private Integer IdRole;
    private String RoleName;

    public Personne(){
        super();
    }

    public Personne(Integer idPersonne, String nom, String prenom, String poste, String telephone, String mdp, String email, Integer idrole) {
        super();
        IdPersonne = idPersonne;
        Nom = nom;
        Prenom = prenom;
        Poste = poste;
        Telephone = telephone;
        Mdp = mdp;
        Email = email;
        IdRole = idrole;
    }

    public Integer getIdPersonne() {
        return IdPersonne;
    }

    public void setIdPersonne(Integer idPersonne) {
        IdPersonne = idPersonne;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getPoste() {
        return Poste;
    }

    public void setPoste(String poste) {
        Poste = poste;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String mdp) {
        Mdp = mdp;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getIdRole() {
        return IdRole;
    }

    public void setIdRole(Integer idRole) {
        IdRole = idRole;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}
