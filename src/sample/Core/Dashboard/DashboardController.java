package sample.Core.Dashboard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Core.Poll.PollController;
import sample.Core.Poll.PollMakeController;
import sample.Core.Session;
import sample.domain.Poll;
import sample.server.interfaces.IListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class DashboardController extends UnicastRemoteObject implements IListener {
    @FXML
    private TableView<Poll> tablePolls;

    @FXML
    private TableColumn id;

    @FXML
    private TableColumn question;

    private Session session;

    public DashboardController() throws RemoteException {
    }

    public void setup(Session session) throws RemoteException {
        this.session = session;

        try {
            session.getServer().addListener(this);
        } catch (RemoteException e) {
            System.out.println("Could not register listener");
        }

        tablePolls.setRowFactory( tv -> {
            TableRow<Poll> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Poll poll = row.getItem();
                    try {
                        openPoll(poll);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

    @FXML
    private void loadPolls()
    {
        tablePolls.getItems().clear();
        question.setCellValueFactory(new PropertyValueFactory<Poll,String>("question"));
        id.setCellValueFactory(new PropertyValueFactory<Poll,String>("id"));

        try {
            List<Poll> polls = session.getServer().getAll();
            if (!polls.isEmpty()) {
                for (Poll poll : polls) {
                    tablePolls.getItems().add(poll);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void signOut()
    {

    }

    @FXML
    private void newPoll() throws IOException {
        session.getServer().removeListener(this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Poll/NewPoll.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        PollMakeController pollController = fxmlLoader.<PollMakeController>getController();
        pollController.setup(session);
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) tablePolls.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }

    public void openPoll(Poll poll) throws IOException, SQLException, ClassNotFoundException {
        session.getServer().removeListener(this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Poll/Poll.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        PollController pollController = fxmlLoader.<PollController>getController();
        pollController.setup(session, poll);
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) tablePolls.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }

    @Override
    public void refreshPolls() throws RemoteException {
        loadPolls();
    }
}
