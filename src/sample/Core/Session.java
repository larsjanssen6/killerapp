package sample.Core;

import sample.domain.User;
import sample.server.interfaces.PollManagerServerInterface;
import sample.serverAuthenticatie.AuthenticationManagerInterface;
import sample.serverGrafieken.interfaces.GraphManagerServerInterface;

public class Session {
    private PollManagerServerInterface pollManagerServer;
    private GraphManagerServerInterface graphManagerServer;
    private AuthenticationManagerInterface authenticationManagerServer;

    private User user;

    public Session() {

    }

    public PollManagerServerInterface getPollServer() {
        return pollManagerServer;
    }

    public GraphManagerServerInterface getGraphManagerServer() {
        return graphManagerServer;
    }

    public void setPollServer(PollManagerServerInterface pollManagerServer) {
        this.pollManagerServer = pollManagerServer;
    }

    public AuthenticationManagerInterface getAuthenticationManagerServer() {
        return authenticationManagerServer;
    }

    public void setAuthenticationManagerServer(AuthenticationManagerInterface authenticationManagerServer) {
        this.authenticationManagerServer = authenticationManagerServer;
    }


    public void setGraphManagerServer(GraphManagerServerInterface graphManagerServer) {
        this.graphManagerServer = graphManagerServer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}