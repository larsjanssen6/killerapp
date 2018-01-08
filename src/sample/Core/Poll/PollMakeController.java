package sample.Core.Poll;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Core.Dashboard.DashboardController;
import sample.domain.Option;
import sample.Core.Session;
import sample.domain.Poll;

import java.io.IOException;
import java.sql.SQLException;

public class PollMakeController {

    @FXML
    private TextField txtQuestion;

    @FXML
    private TextField txtOptionOne;

    @FXML
    private TextField txtOptionTwo;

    @FXML
    private TextField txtOptionThree;

    private Session session;

    public void setup(Session session) {
        this.session = session;
    }

    @FXML
    public void btnHome() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Dashboard/Dashboard.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        DashboardController dashboardController = fxmlLoader.<DashboardController>getController();
        dashboardController.setup(session);
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) txtOptionOne.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }

    @FXML
    public void btnMake() throws IOException, SQLException, ClassNotFoundException {
        System.out.println("make poll");

        Poll poll = new Poll();
        poll.setQuestion(this.txtQuestion.getText());

        Option optionOne = new Option();
        optionOne.setName(txtOptionOne.getText());

        Option optionTwo = new Option();
        optionTwo.setName(txtOptionTwo.getText());

        Option optionThree = new Option();
        optionThree.setName(txtOptionThree.getText());

        System.out.println(optionOne.getName());
        this.session.getServer().store(poll, optionOne, optionTwo, optionThree);
    }
}
