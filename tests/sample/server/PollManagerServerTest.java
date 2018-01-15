package sample.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.Core.Dashboard.DashboardController;
import sample.domain.Option;
import sample.domain.Poll;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class PollManagerServerTest {
    private PollManagerServer server;

    @Before
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        server = new PollManagerServer();
    }

    @Test
    public void get_all() throws SQLException, IOException, ClassNotFoundException {
        Assert.assertNotNull(server.getAll());
    }

    @Test
    public void get_options() throws SQLException, IOException, ClassNotFoundException {
        Poll poll = new Poll();
        poll.setId(3);
        Assert.assertNotNull(server.getOptions(poll).size());
    }

    @Test
    public void get_votes() throws SQLException, IOException, ClassNotFoundException {
        Option option = new Option();
        option.setId(12);

        Assert.assertNotNull(server.getVotes(option));
    }

    @Test
    public void get_total_votes() throws SQLException, IOException, ClassNotFoundException {
        Poll poll = new Poll();
        poll.setId(34);
        Assert.assertEquals(server.getTotalVotes(poll), 7);
    }

    @Test
    public void vote() throws SQLException, IOException, ClassNotFoundException {

        Assert.assertEquals(server.vote(9, 1), true);
    }

    @Test
    public void store_poll() throws SQLException, IOException, ClassNotFoundException {
        Poll poll = new Poll();
        poll.setQuestion("test");
        poll.setName("test");

        Option optionOne = new Option();
        optionOne.setPollId(34);
        optionOne.setName("one");

        Option optionTwo = new Option();
        optionTwo.setPollId(34);
        optionTwo.setName("two");

        Option optionThree = new Option();
        optionThree.setPollId(34);
        optionThree.setName("three");

        Assert.assertEquals(server.store(poll, optionOne, optionTwo, optionThree), true);
    }

    @Test
    public void can_add_listener() throws RemoteException {
        this.server.addListener(new DashboardController());

        Assert.assertEquals(this.server.getListeners().size(), 1);
    }

    @Test
    public void can_remove_listener() throws RemoteException {
        DashboardController controller = new DashboardController();

        this.server.addListener(controller);
        this.server.removeListener(controller);

        Assert.assertEquals(this.server.getListeners().size(), 0);
    }
}
