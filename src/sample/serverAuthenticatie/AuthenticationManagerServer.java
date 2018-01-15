package sample.serverAuthenticatie;


import sample.domain.User;
import sample.repositories.AuthenticationRepo;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class AuthenticationManagerServer extends UnicastRemoteObject implements AuthenticationManagerInterface {

    AuthenticationRepo authenticationRepo;

    public AuthenticationManagerServer() throws IOException, SQLException, ClassNotFoundException {
        this.authenticationRepo = new AuthenticationRepo();
    }

    public boolean store(User user)
    {
        return this.authenticationRepo.store(user);
    }

    public User login(String username, String password) throws SQLException, IOException, ClassNotFoundException {
        return this.authenticationRepo.login(username, password);
    }
}
