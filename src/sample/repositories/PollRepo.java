package sample.repositories;

import sample.domain.Option;
import sample.domain.Poll;
import sample.domain.User;
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

    public int findVotes(Option option) throws SQLException, IOException, ClassNotFoundException {

         /*
            Connect to database.
         */


        IConnection connection = new ConnectionManager();
        Connection conn = connection.getConnection();

        String getitems = "select count(*) as total from vote where option_id = ?";

        PreparedStatement preparedStmt = conn.prepareStatement(getitems);
        preparedStmt.setInt(1, option.getId());
        ResultSet r = preparedStmt.executeQuery();

        int total = 0;

        while (r.next()) {
            total = r.getInt("total");
        }

        conn.close();
        return total;
    }

    public int findTotalVotes(Poll poll) throws SQLException, IOException, ClassNotFoundException {

         /*
            Connect to database.
         */


        IConnection connection = new ConnectionManager();
        Connection conn = connection.getConnection();

        String getitems = "select count(*) as total from `vote` as v inner join `option` as o on v.option_id = o.id inner join `poll` as p on p.id = o.poll_id where p.id = ?";

        PreparedStatement preparedStmt = conn.prepareStatement(getitems);
        preparedStmt.setInt(1, poll.getId());
        ResultSet r = preparedStmt.executeQuery();

        int total = 0;

        while (r.next()) {
            total = r.getInt("total");
        }

        conn.close();
        return total;
    }

    public boolean store(Poll poll, Option optionOne, Option optionTwo, Option optionThree)
    {
        try {
             /*
                Connect to database.
             */
            System.out.println(" test");
            IConnection connection = new ConnectionManager();
            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT into poll (question, name) VALUES ('" + poll.getQuestion() + "', '" + poll.getName() + "')", Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            String sqlOption = "INSERT INTO `option` (poll_id, `name`) VALUES (?, ?);";
            PreparedStatement statementOne = conn.prepareStatement(sqlOption);
            PreparedStatement statementTwo = conn.prepareStatement(sqlOption);
            PreparedStatement statementThree = conn.prepareStatement(sqlOption);

            statementOne.setInt(1, generatedKey);
            statementOne.setString(2, optionOne.getName());
            statementOne.execute();

            statementTwo.setInt(1, generatedKey);
            statementTwo.setString(2, optionTwo.getName());
            statementTwo.execute();

            statementThree.setInt(1, generatedKey);
            statementThree.setString(2, optionThree.getName());
            statementThree.execute();

            conn.close();

            return true;
        }
        catch (Exception ex) {
            System.out.println("Something went wrong, please contact the administrator.");
            return false;
        }

    }

    public boolean vote(int voteId, int userId)
    {
        try {
             /*
                Connect to database.
             */

            IConnection connection = new ConnectionManager();
            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT into `vote` (option_id, user_id) VALUES (?, ?)");

            statement.setInt(1, voteId);
            statement.setInt(2, userId);
            statement.execute();

            conn.close();

            return true;
        }
        catch (Exception ex) {
            System.out.println("Something went wrong, please contact the administrator.");
        }

        return false;
    }
}
