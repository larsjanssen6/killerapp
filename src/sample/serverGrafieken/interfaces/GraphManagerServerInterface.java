package sample.serverGrafieken.interfaces;

import sample.serverGrafieken.IRemotePublisher;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface GraphManagerServerInterface extends IRemotePublisher, Remote {
    void broadCastVoteChanged() throws IOException, SQLException, ClassNotFoundException;
}
