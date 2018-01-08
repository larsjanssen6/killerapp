package sample.repositories;

import sample.domain.User;
import sample.interfaces.IConnection;
import java.io.IOException;
import java.sql.*;

public class AuthenticationRepo {

    public AuthenticationRepo() throws SQLException, IOException, ClassNotFoundException { }

    public User login(String username, String password) throws SQLException, IOException, ClassNotFoundException {

        /*
            Connect to database.
         */

        IConnection connection = new ConnectionManager();
        Connection conn = connection.getConnection();

        /*
            Sql query.
         */

        String query = "SELECT * FROM user WHERE username = ? AND password= ?;";
        User user = new User();

        /*
            Execute query.
         */

        PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1,username.toLowerCase());
        preparedStmt.setString(2, password);
        ResultSet rs = preparedStmt.executeQuery();

        /*
            Read data.
         */

        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
        }

        conn.close();

        return user;
    }

    public boolean store(User user)
    {
        try {
         /*
            Connect to database.
         */

            IConnection connection = new ConnectionManager();
            Connection conn = connection.getConnection();

            Statement statement = conn.createStatement();

            statement.execute("INSERT  into user (firstname, lastname, username, password) VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "','" + user.getUsername() + "','" + user.getPassword() + "')");
            return true;
        }
        catch (Exception ex) {
            System.out.println("Something went wrong, please contact the administrator.");
            return false;
        }
    }
}
