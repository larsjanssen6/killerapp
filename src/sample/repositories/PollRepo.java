package sample.repositories;

import sample.domain.Option;
import sample.domain.Poll;
import sample.interfaces.IConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PollRepo {
    public List<Poll> getAll() throws SQLException, IOException, ClassNotFoundException {

         /*
            Connect to database.
         */

        IConnection connection = new ConnectionManager();
        Connection conn = connection.getConnection();

        List<Poll> items = new ArrayList<>();
        String getitems = "select id, question from poll";

        PreparedStatement preparedStmt = conn.prepareStatement(getitems, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = preparedStmt.executeQuery();

        while (rs.next()) {
            Poll poll = new Poll();
            poll.setQuestion(rs.getString("question"));
            poll.setId(rs.getInt("id"));
            items.add(poll);
        }

        conn.close();
        return items;
    }

    public List<Option> findOptions(int id) throws SQLException, IOException, ClassNotFoundException {

         /*
            Connect to database.
         */

        List<Option> options = new ArrayList<>();

        IConnection connection = new ConnectionManager();
        Connection conn = connection.getConnection();

        String getitems = "SELECT * FROM `option` where poll_id = ?";

        PreparedStatement preparedStmt = conn.prepareStatement(getitems);
        preparedStmt.setInt(1, id);
        ResultSet r = preparedStmt.executeQuery();

        while (r.next()) {
            Option option = new Option();
            option.setId(r.getInt("id"));
            option.setPollId(r.getInt("poll_id"));
            option.setName(r.getString("name"));
            options.add(option);
        }

        conn.close();
        return options;
    }

    public boolean store(Poll poll, Option optionOne, Option optionTwo, Option optionThree)
    {
        try {
             /*
                Connect to database.
             */

            IConnection connection = new ConnectionManager();
            Connection conn = connection.getConnection();

            PreparedStatement statementOne = conn.prepareStatement("INSERT INTO option (poll_id, name) VALUES (?, ?);");
            Statement statementTwo = conn.createStatement();
//            Statement statementThree = conn.createStatement();

            String[] returnId = { "id" };

            PreparedStatement statement = conn.prepareStatement("INSERT into poll (question, name) VALUES ('" + poll.getQuestion() + "', '" + poll.getName() + "')", returnId);
            int affectedRows = statement.executeUpdate();
//
            statementOne.setInt(1, 16);
            statementOne.setString(2, "test");
            statementOne.execute();


            statementTwo.execute("INSERT into `option` (poll_id, name) VALUES (22, '" + "test123" + "')");
//            statementThree.execute("INSERT into option (poll_id, name) VALUES ('" + 6 + "', '" + optionThree + "')");

            conn.close();

            return true;
        }
        catch (Exception ex) {
            System.out.println("Something went wrong, please contact the administrator.");
            return false;
        }

    }
}
