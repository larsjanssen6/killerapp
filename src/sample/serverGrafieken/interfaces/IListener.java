package sample.serverGrafieken.interfaces;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IListener extends Remote {
     void loadOptions() throws RemoteException, SQLException, IOException, ClassNotFoundException;
}


