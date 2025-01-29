package edu.kirkwood.shared;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Connect {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver not found");
        }

        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.load();
        } catch(DotenvException e) {
            throw new SQLException("Could not find .env file");
        }
        String connectionString = dotenv.get("AZURE_MYSQL_CONNECTIONSTRING");
        if(connectionString == null) {
            throw new SQLException("Connection string not found");
        }

        try {
            Connection connection = DriverManager.getConnection(connectionString);
            if (connection.isValid(2)) {
                return connection;
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            if(getConnection() != null) {
                System.out.println("Connection successful");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}