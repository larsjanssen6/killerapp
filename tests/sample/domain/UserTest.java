package sample.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class UserTest {
    private User user;

    @Before
    public void setup() throws IOException {
        user = new User();
    }

    @Test
    public void get_and_set_id()
    {
        user.setId(10);
        Assert.assertEquals(user.getId(), 10);
    }

    @Test
    public void get_and_set_username()
    {
        user.setUserName("test");
        Assert.assertEquals(user.getUsername(), "test");
    }

    @Test
    public void get_and_set_first_name()
    {
        user.setFirstName("test");
        Assert.assertEquals(user.getFirstName(), "test");
    }

    @Test
    public void get_and_set_last_name()
    {
        user.setLastName("test");
        Assert.assertEquals(user.getLastName(), "test");
    }

    @Test
    public void get_and_set_password()
    {
        user.setPassword("test");
        Assert.assertEquals(user.getPassword(), "test");
    }
}
