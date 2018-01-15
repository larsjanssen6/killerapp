package sample.serverAuthenticatie;

import sample.domain.User;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface AuthenticationManagerInterface extends Remote {
    boolean store(User user) throws RemoteException;;
    User login(String username, String password) throws SQLException, IOException, ClassNotFoundException;
}
