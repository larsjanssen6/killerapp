package sample.serverGrafieken;

import sample.serverGrafieken.interfaces.IListener;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemotePublisher extends Remote {
    void addListener(IListener listener)  throws RemoteException;
    void removeListener(IListener listener)  throws RemoteException;
}