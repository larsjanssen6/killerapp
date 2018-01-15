package sample.Core.Authentication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Core.Dashboard.DashboardController;
import sample.Core.Registration.RegisterController;
import sample.Core.Session;
import sample.domain.User;
import sample.server.interfaces.PollManagerServerInterface;
import sample.serverAuthenticatie.AuthenticationManagerInterface;
import sample.serverGrafieken.interfaces.GraphManagerServerInterface;

import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class AuthenticationController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private Registry pollServerRegistry;

    private Registry graphServerRegistry;

    private Registry authenticationManager;

    private Session session;


    public AuthenticationController() throws SQLException, IOException, ClassNotFoundException {
    }

    public void setup() {}

    @FXML
    public void login() throws SQLException, IOException, ClassNotFoundException, NotBoundException {

        /*
            Create the session
         */

        this.session = new Session();
        this.authenticationManager = locateRegistryAuthenticationManager();

        session.setAuthenticationManagerServer((AuthenticationManagerInterface) authenticationManager.lookup("AuthenticationManager"));

        /*
            Check if user has given correct credentials. Return
            proper response.
         */

        if (!txtUsername.getText().trim().isEmpty() && !txtPassword.getText().trim().isEmpty()) {
            User user = session.getAuthenticationManagerServer().login(txtUsername.getText(), txtPassword.getText());

            if (user.getUsername() != null){
                System.out.println("success");

                this.pollServerRegistry = locateRegistryPollServer();
                this.graphServerRegistry = locateRegistryGraphServer();

                session.setUser(user);
                session.setPollServer((PollManagerServerInterface) pollServerRegistry.lookup("PollManager"));
                session.setGraphManagerServer((GraphManagerServerInterface) graphServerRegistry.lookup("GraphManager"));
                openDashboard();
            }
            else
            {
                System.out.println("failed");
            }
        }
    }

    @FXML
    public void register() throws IOException {

        /*
            Open registration window.
         */

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Registration/Register.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        RegisterController registerController = fxmlLoader.<RegisterController>getController();
        registerController.setup();
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) txtUsername.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }

    private void openDashboard() throws IOException {

         /*
            Open dashboard window.
         */

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Dashboard/Dashboard.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        DashboardController dashboardController = fxmlLoader.<DashboardController>getController();
        dashboardController.setup(session);
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) txtUsername.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }

    private Registry locateRegistryPollServer() throws SQLException, IOException, ClassNotFoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");

        try
        {
            return LocateRegistry.getRegistry("127.0.0.1", 1099);
        }
        catch (RemoteException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No connection to Server");
            alert.setContentText("The server is unavailable at this time, try again later.");
            return null;
        }
    }

    private Registry locateRegistryGraphServer() throws SQLException, IOException, ClassNotFoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");

        try
        {
            return LocateRegistry.getRegistry("127.0.0.1", 1088);
        }
        catch (RemoteException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No connection to Server");
            alert.setContentText("The server is unavailable at this time, try again later.");
            return null;
        }
    }

    private Registry locateRegistryAuthenticationManager() throws SQLException, IOException, ClassNotFoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");

        try
        {
            return LocateRegistry.getRegistry("127.0.0.1", 1077);
        }
        catch (RemoteException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No connection to Server");
            alert.setContentText("The server is unavailable at this time, try again later.");
            return null;
        }
    }
}
