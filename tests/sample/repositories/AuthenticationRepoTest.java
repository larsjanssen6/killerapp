package sample.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.domain.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class AuthenticationRepoTest {
    private AuthenticationRepo repo;

    @Before
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        repo = new AuthenticationRepo();
    }

    @Test
    public void login_successful() throws SQLException, IOException, ClassNotFoundException {
        Assert.assertEquals(repo.login("lars", "fontystilburg").getId(), 1);
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

        Assert.assertEquals(repo.store(user), true);
    }
}
