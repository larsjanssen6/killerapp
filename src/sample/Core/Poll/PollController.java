package sample.Core.Poll;

import javafx.fxml.FXML;

import javafx.scene.text.Text;

import sample.Core.Session;
import sample.domain.Option;
import sample.domain.Poll;
import javafx.scene.control.CheckBox;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PollController {
    @FXML
    private Text txtQuestion;

    @FXML
    private CheckBox cbOne;

    @FXML
    private CheckBox cbTwo;

    @FXML
    private CheckBox cbThree;

    private Session session;

    private Poll poll;
    private List<Option> options;

    public void setup(Session session, Poll poll) throws SQLException, IOException, ClassNotFoundException {
        this.session = session;
        this.poll = poll;
        txtQuestion.setText(poll.getQuestion());
        this.loadOptions(poll);
    }

    @FXML
    public void btnHome() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Dashboard/Dashboard.fxml"));
//        Parent root = (Parent)fxmlLoader.load();
//        DashboardController dashboardController = fxmlLoader.<DashboardController>getController();
//        dashboardController.setup(session);
//        Scene registerScreen = new Scene(root);
//        Stage stage;
//        stage = (Stage) home.getScene().getWindow();
//        stage.setScene(registerScreen);
//        stage.show();
    }

    public void loadOptions(Poll poll) throws SQLException, IOException, ClassNotFoundException {
        this.options = session.getServer().getOptions(poll);

        cbOne.setText(options.get(0).getName());
        cbTwo.setText(options.get(1).getName());
        cbThree.setText(options.get(2).getName());
    }

    public void btnVote()
    {

    }
}
