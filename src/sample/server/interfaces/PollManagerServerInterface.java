package sample.server.interfaces;

import sample.domain.Option;
import sample.domain.Poll;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.rmi.Remote;

public interface PollManagerServerInterface extends IRemotePublisher, Remote {
    void broadCastNewVote() throws RemoteException;

    List<Poll> getAll() throws SQLException, IOException, ClassNotFoundException;

    void store(Poll poll, Option optionOne, Option optionTwo, Option optionThree) throws RemoteException;

    List<Option> getOptions(Poll poll) throws SQLException, IOException, ClassNotFoundException;
}
