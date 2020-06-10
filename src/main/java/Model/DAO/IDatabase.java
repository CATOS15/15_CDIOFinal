package Model.DAO;

import java.sql.SQLException;

public interface IDatabase {
    void connect() throws ClassNotFoundException, SQLException;
    void disconnect() throws SQLException;
}
