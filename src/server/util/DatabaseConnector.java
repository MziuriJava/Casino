package server.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public static Connection getConnection() throws Exception{
        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(DatabaseUtil.url, DatabaseUtil.username, DatabaseUtil.password);
    }
}
