package Model.DAO;

import java.sql.*;

abstract public class Database {
    private Connection con;

    protected void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CDIOFinal?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "kagemand123");
    }

    ResultSet executeSelect(String statement) throws SQLException{
        Statement stmt = con.createStatement();
        return stmt.executeQuery(statement);
    }

    void executeUpdate(String statement) throws SQLException{
        Statement stmt = con.createStatement();
        stmt.executeUpdate(statement);
    }

    public void disconnect() throws SQLException {
        con.close();
    }
}
