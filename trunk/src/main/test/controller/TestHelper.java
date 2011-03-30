package controller;


import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connects to Database to execute/delete TableData via JDBC
 */
public class TestHelper {

    private Connection conn;

    /**
     * Establish Database Connection
     */
    public void connect() {

        try {

            PropertiesConfiguration config = new PropertiesConfiguration("config.properties");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://" + config.getString("database.hostname") + ":3306/" + config.getString("database.schema"), config.getString("database.username"), config.getString("database.password"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * MySQL Statement to execute
     *
     * @param query SQL Statement
     */
    public void execute(String query) {

        try {

            Statement statement = conn.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Disconnects from Database
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
