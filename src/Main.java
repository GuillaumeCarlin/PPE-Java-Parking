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

    //TODO: Faire la fonction de vérification qui empêche de réserver une place à un horraire déjà réserver
    //TODO: Faire la fonction FindByDate du DAOReservation pour pouvoir faire une recherche en fonction de la date
    //TODO: Faire la génération de QRCode















































































































}