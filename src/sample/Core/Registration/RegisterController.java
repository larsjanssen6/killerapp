package sample.Core.Registration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Core.Authentication.AuthenticationController;
import sample.domain.User;
import sample.repositories.AuthenticationRepo;

import java.io.IOException;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class RegisterController {

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    private AuthenticationRepo authenticationRepo;


    public RegisterController() throws SQLException, IOException, ClassNotFoundException {
        authenticationRepo = new AuthenticationRepo();
    }

    public void setup() {}

    @FXML
    private void login() throws IOException {
        backToLogin();
    }

    @FXML
    private void register() throws IOException {
        System.out.println("registreer");
        /*
            Here we check if all fields are valid. If they
            are all valid we're going to save the user.
         */

        if (txtFirstName.getText() == null || txtFirstName.getText().trim().isEmpty()) {
            showMessageDialog(null, "Voornaam is een verplicht veld.");
            System.out.println("Username is a required field.");
        }
        else if (txtFirstName.getText() == null || txtFirstName.getText().trim().isEmpty()) {
            showMessageDialog(null, "Voornaam is een verplicht veld.");
            System.out.println("Voornaam is een verplicht veld.");
        }
        else if (txtLastName.getText() == null || txtLastName.getText().trim().isEmpty()) {
            showMessageDialog(null, "Achternaam is een verplicht veld.");
            System.out.println("Achternaam is een verplicht veld.");
        }
        else if (txtUserName.getText() == null || txtUserName.getText().trim().isEmpty()) {
            showMessageDialog(null, "Gebruikersnaam is een verplicht veld.");
            System.out.println("Gebruikersnaam is een verplicht veld.");
        }
        else if (txtPassword.getText() == null || txtPassword.getText().trim().isEmpty()) {
            showMessageDialog(null, "Wachtwoord is een verplicht veld.");
            System.out.println("Wachtwoord is een verplicht veld.");
        }
        else {

            /*
                Save user to the database.
             */

            User user = new User();

            user.setFirstName(txtFirstName.getText());
            user.setLastName(txtLastName.getText());
            user.setUserName(txtUserName.getText());
            user.setPassword((txtPassword.getText()));

            if (authenticationRepo.store(user)) {
                backToLogin();
            }
            else {
                showMessageDialog(null, "Something went wrong. Please contact the administrator.");
            }
        }
    }

    private void backToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Authentication/Login.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        AuthenticationController authenticationController = fxmlLoader.<AuthenticationController>getController();
        authenticationController.setup();
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) txtUserName.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }
}
