package sample.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class OptionTest {

    private Option option;

    @Before
    public void setup() throws IOException {
        option = new Option();
    }

    @Test
    public void get_and_set_id()
    {
        option.setId(10);
        Assert.assertEquals(option.getId(), 10);
    }

    @Test
    public void get_and_set_name()
    {
        option.setName("test");
        Assert.assertEquals(option.getName(), "test");
    }

    @Test
    public void get_and_set_poll_id()
    {
        option.setPollId(10);
        Assert.assertEquals(option.getPollId(), 10);
    }
}
