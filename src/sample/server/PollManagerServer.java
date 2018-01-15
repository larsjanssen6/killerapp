package sample.server;

import sample.domain.Option;
import sample.domain.Poll;
import sample.repositories.PollRepo;
import sample.server.interfaces.IListener;
import sample.server.interfaces.PollManagerServerInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PollManagerServer extends UnicastRemoteObject implements PollManagerServerInterface {

    public PollRepo pollRepo;

    private List<IListener> listeners = new ArrayList<>();

    public PollManagerServer() throws RemoteException {
        pollRepo = new PollRepo();
    }


    @Override
    public List<Poll> getAll() throws SQLException, IOException, ClassNotFoundException {
        return pollRepo.getAll();
    }

    @Override
    public List<Option> getOptions(Poll poll) throws SQLException, IOException, ClassNotFoundException {
        return pollRepo.findOptions(poll.getId());
    }

    @Override
    public int getVotes(Option option) throws SQLException, IOException, ClassNotFoundException {
        return pollRepo.findVotes(option);
    }

    @Override
    public int getTotalVotes(Poll poll) throws SQLException, IOException, ClassNotFoundException {
        return pollRepo.findTotalVotes(poll);
    }

    @Override
    public boolean vote(int optionId, int userId) throws SQLException, IOException, ClassNotFoundException {
        return pollRepo.vote(optionId, userId);
    }

    @Override
    public boolean store(Poll poll, Option optionOne, Option optionTwo, Option optionThree) throws RemoteException {
        this.pollRepo.store(poll, optionOne, optionTwo, optionThree);

        for (IListener client:listeners)
                client.refreshPolls();

        return true;
    }

    @Override
    public void addListener(IListener listener) throws RemoteException {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) throws RemoteException {
        listeners.remove(listener);
    }

    public List<IListener> getListeners()
    {
        return listeners;
    }
}
