package sample.server.interfaces;

import sample.domain.Poll;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IListener extends Remote {
    void refreshPolls() throws RemoteException;
}
