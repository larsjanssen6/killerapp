package sample.serverGrafieken;

import sample.serverGrafieken.interfaces.GraphManagerServerInterface;
import sample.serverGrafieken.interfaces.IListener;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GraphManagerServer extends UnicastRemoteObject implements GraphManagerServerInterface {


    private List<IListener> listeners = new ArrayList<>();

    public GraphManagerServer() throws RemoteException {
    }

    @Override
    public void broadCastVoteChanged() throws IOException, SQLException, ClassNotFoundException {
        for (IListener client:listeners)
                client.loadOptions();
    }

    @Override
    public void addListener(IListener listener) throws RemoteException {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) throws RemoteException {
        this.listeners.remove(listener);
    }

    public List<IListener> getListeners()
    {
        return this.listeners;
    }
}
