import Controllers.ControllerLogin;
import DAO.DAOPersonne;
import DAO.DAORole;
import Utils.Singleton;
import views.FenetreLogin;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAOPersonne daop = new DAOPersonne(Singleton.getInstance().cnx);
        DAORole daor = new DAORole(Singleton.getInstance().cnx);
        FenetreLogin myFrame = new FenetreLogin();
        new ControllerLogin(myFrame, daop, daor).init();
    }

    //TODO: Faire la fonction FindByDate du DAOReservation pour pouvoir faire une recherche en fonction de la date
    //TODO: Faire la génération de QRCode
    //TODO: Mettre une vérifiaction pourqu'une personne ne puisse pas réserver deux place en même temps


    //Ajout de fonctionnalité Idée
    //TODO: Rajouter un champs pour changer son mot de passe
    //TODO: Faire un bouton pour générer un mot de passe
    //TODO: Faire une pop-up à la connexion quand le mot de passe n'est pas bon














































































































}