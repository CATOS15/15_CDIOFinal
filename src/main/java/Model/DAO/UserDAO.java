package Model.DAO;

import Model.DTO.User;
import Model.Exception.DALException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Security.Security.crypt;

public class UserDAO extends Database implements IUserDAO {
    public UserDAO() throws SQLException, ClassNotFoundException {
        this.connect();
    }

    @Override
    public User GetUser(String username) throws DALException {
        try{
            ResultSet rs = this.executeSelect("SELECT userId, userName, userIni, CPRnummer FROM Users WHERE userName = \"" + username + "\"");
            if(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserIni(rs.getString(3));
                user.setCPRnummer(rs.getString(4));
                return user;
            }else{
                throw new DALException("Brugeren eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af bruger");
        }
    }

    public User CreateUser(User user) throws DALException {
        try{
            this.executeUpdate("INSERT INTO Users VALUES ("+user.getUserId()+", \""+user.getUserName()+"\", \""+user.getUserIni()+"\", \""+user.getCPRnummer()+"\", \""+crypt(user.getPassword())+"\");");
            return user;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af bruger");
        }
    }

    @Override
    public User UpdateUser(User user) throws DALException {
        try{
            ResultSet rs = this.executeSelect("SELECT userId FROM Users WHERE userId = " + user.getUserId());
            if(rs.next()) {
                executeUpdate("UPDATE users SET userName=\""+user.getUserName()+"\", password=\""+crypt(user.getPassword())+"\", userIni=\""+user.getUserIni()+"\", CPRnummer=\""+user.getCPRnummer()+"\" WHERE id=\""+user.getUserId()+"\";");
                return user;
            }else{
                throw new DALException("Brugeren eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved opdatering af bruger");
        }
    }

    public User Login(User user) throws DALException {
        try{
            ResultSet rs = this.executeSelect("SELECT userId, userName, userIni, CPRnummer, password FROM Users WHERE userName = \"" + user.getUserName() + "\" AND password = \"" + crypt(user.getPassword()) + "\"");
            if(rs.next()) {
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserIni(rs.getString(3));
                user.setCPRnummer(rs.getString(4));
                user.setPassword(rs.getString(5));
                return user;
            }else{
                throw new DALException("Forkert brugernavn eller password");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved login af bruger");
        }
    }

    public void end() throws DALException {
        try {
            this.disconnect();
        } catch (SQLException e) {
            throw new DALException("Forbindelsen til databasen kunne ikke lukkes");
        }
    }
}
