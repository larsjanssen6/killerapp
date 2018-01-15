package sample.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sample.Core.Dashboard.DashboardController;
import sample.Core.Poll.PollController;
import sample.domain.Poll;
import sample.domain.User;
import sample.serverAuthenticatie.AuthenticationManagerServer;
import sample.serverGrafieken.GraphManagerServer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Random;

public class GraphManagerServerTest {
    private GraphManagerServer server;

    @Before
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        server = new GraphManagerServer();
    }

    @Test
    public void can_add_listener() throws RemoteException {
        this.server.addListener(new PollController());

        Assert.assertEquals(this.server.getListeners().size(), 1);
    }

    @Test
    public void can_remove_listener() throws RemoteException {
        PollController controller = new PollController();

        this.server.addListener(controller);
        this.server.removeListener(controller);

        Assert.assertEquals(this.server.getListeners().size(), 0);
    }
}
