package sample.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PollTest {
    private Poll poll;

    @Before
    public void setup() throws IOException {
        poll = new Poll();
    }

    @Test
    public void get_and_set_name()
    {
        poll.setName("test");
        Assert.assertEquals(poll.getName(), "test");
    }

    @Test
    public void get_and_set_id()
    {
        poll.setId(1);
        Assert.assertEquals(poll.getId(), 1);
    }

    @Test
    public void get_and_set_question()
    {
        poll.setQuestion("test");
        Assert.assertEquals(poll.getQuestion(), "test");
    }
}
