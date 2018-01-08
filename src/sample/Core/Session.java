package sample.Core;

import sample.domain.User;
import sample.server.interfaces.PollManagerServerInterface;

public class Session {
    private PollManagerServerInterface server;
    private User user;

    public Session(PollManagerServerInterface server) {
        this.server = server;
    }

    public PollManagerServerInterface getServer() {
        return server;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}