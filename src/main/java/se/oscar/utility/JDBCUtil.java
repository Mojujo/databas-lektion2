package se.oscar.utility;

import java.sql.*;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        Driver hsqlDriver = new org.hsqldb.jdbcDriver();
        DriverManager.registerDriver(hsqlDriver);
        String dbURL = "jdbc:hsqldb:hsql://localhost/jdbclab";
        String dbUser = "sa";
        String dbPassword = "";
        Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        conn.setAutoCommit(false);
        return conn;
    }

    public static void commit(Connection conn) {
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            loggerUtil.logError("Error committing", e);
        }
    }

    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            loggerUtil.logError("Error rolling back", e);
        }
    }
}
