package sample.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.domain.User;
import sample.serverAuthenticatie.AuthenticationManagerServer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class AuthenticationManagerServerTest {
    private AuthenticationManagerServer server;

    @Before
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        server = new AuthenticationManagerServer();
    }

    @Test
    public void store_user() {

        Random rand = new Random();

        int  n = rand.nextInt(50) + 1;

        User user = new User();
        user.setLastName("test12345");
        user.setPassword("test12345");
        user.setFirstName("test12345");
        user.setUserName(String.valueOf(n));

        Assert.assertEquals(server.store(user), true);
    }

    @Test
    public void login_successful() throws SQLException, IOException, ClassNotFoundException {
        Assert.assertEquals(server.login("lars", "fontystilburg").getId(), 1);
    }
}
