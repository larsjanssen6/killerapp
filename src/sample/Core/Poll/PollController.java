package sample.Core.Poll;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import javafx.stage.Stage;
import sample.Core.Dashboard.DashboardController;
import sample.Core.Session;
import sample.domain.Option;
import sample.domain.Poll;
import javafx.scene.control.CheckBox;
import sample.serverGrafieken.interfaces.IListener;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class PollController extends UnicastRemoteObject implements IListener {
    @FXML
    private Text txtQuestion;

    @FXML
    private CheckBox cbOne;

    @FXML
    private CheckBox cbTwo;

    @FXML
    private CheckBox cbThree;

    @FXML
    private Label txtOptionOneProcent;

    @FXML
    private Label txtOptionThreeProcent;

    @FXML
    private Label txtOptionTwoProcent;

    private Session session;

    private Poll poll;
    private List<Option> options;

    public PollController() throws RemoteException {
    }

    public void setup(Session session, Poll poll) throws SQLException, IOException, ClassNotFoundException {
        this.session = session;
        this.poll = poll;

        try {
            session.getGraphManagerServer().addListener(this);
        } catch (RemoteException e) {
            System.out.println("Could not register listener");
        }
        txtQuestion.setText(poll.getQuestion());
        this.loadOptions();
    }

    @FXML
    public void btnHome() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Dashboard/Dashboard.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        DashboardController dashboardController = fxmlLoader.<DashboardController>getController();
        dashboardController.setup(session);
        Scene registerScreen = new Scene(root);
        Stage stage;
        stage = (Stage) txtOptionThreeProcent.getScene().getWindow();
        stage.setScene(registerScreen);
        stage.show();
    }

    public void loadOptions() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("load options");
        this.options = session.getPollServer().getOptions(poll);

        Option optionOne = options.get(0);
        Option optionTwo = options.get(1);
        Option optionThree = options.get(2);
        System.out.println(String.valueOf(loadVotes(optionOne)));
        int totalVotes = this.loadTotalVotes(poll);

        txtOptionOneProcent.accessibleHelpProperty().setValue(String.valueOf(optionOne.getId()));
        cbOne.setText(optionOne.getName());

        Platform.runLater(new Runnable() {
            @Override public void run() {
                txtOptionOneProcent.setText("0%");
            }
        });

        System.out.println("votes: " + loadVotes(optionOne));
        if(loadVotes(optionOne) != 0) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    try {
                        txtOptionOneProcent.setText(String.valueOf(loadVotes(optionOne) * 100 / totalVotes) + "%");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        txtOptionTwoProcent.accessibleHelpProperty().setValue(String.valueOf(optionTwo.getId()));
        cbTwo.setText(optionTwo.getName());


        Platform.runLater(new Runnable() {
            @Override public void run() {
                txtOptionTwoProcent.setText("0%");
            }
        });

        if((loadVotes(optionTwo) != 0)) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    try {
                        txtOptionTwoProcent.setText(String.valueOf(loadVotes(optionTwo) * 100 / totalVotes) + "%");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

        cbThree.setText(optionThree.getName());
        txtOptionThreeProcent.accessibleHelpProperty().setValue(String.valueOf(optionThree.getId()));


        Platform.runLater(new Runnable() {
            @Override public void run() {
                txtOptionThreeProcent.setText("0%");
            }
        });

        if((loadVotes(optionThree) != 0)) {
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    try {
                        txtOptionThreeProcent.setText(String.valueOf(loadVotes(optionThree) * 100 / totalVotes) + "%");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private int loadVotes(Option option) throws SQLException, IOException, ClassNotFoundException {
        return session.getPollServer().getVotes(option);
    }

    private int loadTotalVotes(Poll poll) throws SQLException, IOException, ClassNotFoundException {
        return session.getPollServer().getTotalVotes(poll);
    }

    public void btnVote() throws SQLException, IOException, ClassNotFoundException {

        if(cbOne.selectedProperty().get()) {
            System.out.println("Vote one");
            session.getPollServer().vote(Integer.parseInt(txtOptionOneProcent.getAccessibleHelp()), session.getUser().getId());
            session.getGraphManagerServer().broadCastVoteChanged();
        }

        if(cbTwo.selectedProperty().get()) {
            System.out.println("Vote two");
            session.getPollServer().vote(Integer.parseInt(txtOptionTwoProcent.getAccessibleHelp()), session.getUser().getId());
            session.getGraphManagerServer().broadCastVoteChanged();
        }

        if(cbThree.selectedProperty().get()) {
            System.out.println("Vote three");
            session.getPollServer().vote(Integer.parseInt(txtOptionThreeProcent.getAccessibleHelp()), session.getUser().getId());
            session.getGraphManagerServer().broadCastVoteChanged();
        }
    }
}
