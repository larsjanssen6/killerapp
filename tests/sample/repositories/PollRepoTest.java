package sample.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.domain.Option;
import sample.domain.Poll;

import java.io.IOException;
import java.sql.SQLException;

public class PollRepoTest {
    private PollRepo repo;

    @Before
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        repo = new PollRepo();
    }

    @Test
    public void get_all() throws SQLException, IOException, ClassNotFoundException {
        Assert.assertNotNull(repo.getAll().size());
    }

    @Test
    public void find_options() throws SQLException, IOException, ClassNotFoundException {
        Assert.assertNotNull(repo.findOptions(3).size());
    }

    @Test
    public void find_votes() throws SQLException, IOException, ClassNotFoundException {
        Option option = new Option();
        option.setId(12);
        Assert.assertEquals(repo.findVotes(option), 3);
    }

    @Test
    public void find_total_votes() throws SQLException, IOException, ClassNotFoundException {
        Poll poll = new Poll();
        poll.setId(34);
        Assert.assertEquals(repo.findTotalVotes(poll), 7);
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

        Assert.assertEquals(repo.store(poll, optionOne, optionTwo, optionThree), true);
    }

    @Test
    public void vote() throws SQLException, IOException, ClassNotFoundException {

        Assert.assertEquals(repo.vote(9, 1), true);
    }
}
