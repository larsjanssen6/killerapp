package sample.server.interfaces;

import sample.domain.Option;
import sample.domain.Poll;
import sample.server.IRemotePublisher;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.rmi.Remote;

public interface PollManagerServerInterface extends IRemotePublisher, Remote {
    List<Poll> getAll() throws SQLException, IOException, ClassNotFoundException;

    boolean store(Poll poll, Option optionOne, Option optionTwo, Option optionThree) throws RemoteException;

    List<Option> getOptions(Poll poll) throws SQLException, IOException, ClassNotFoundException;

    int getVotes(Option option) throws SQLException, IOException, ClassNotFoundException;

    int getTotalVotes(Poll poll) throws SQLException, IOException, ClassNotFoundException;

    boolean vote(int pollId, int userId) throws SQLException, IOException, ClassNotFoundException;
}
