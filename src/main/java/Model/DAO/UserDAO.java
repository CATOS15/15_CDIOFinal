package Model.DAO;

import Model.DTO.Rolle;
import Model.DTO.User;
import Model.Exception.DALException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Security.Security.crypt;

public class UserDAO extends Database implements IUserDAO {
    private IRolleDAO iRolleDAO;

    public UserDAO() throws ClassNotFoundException, SQLException {
        super();
        iRolleDAO = new RolleDAO();
    }

    @Override
    public List<User> getUsers() throws DALException {
        try{
            List<User> users = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT userId, userName, userIni, CPRnummer FROM users");
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserIni(rs.getString(3));
                user.setCPRnummer(rs.getString(4));

                List<Rolle> roller = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT roleId FROM rolesusers WHERE userId = %d;", user.getUserId()));
                while(rs2.next()) {
                    Rolle rolle = iRolleDAO.getRolle(String.valueOf(rs2.getInt(1)));
                    roller.add(rolle);
                }
                user.setRoller(roller);
                users.add(user);
            }
            return users;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af bruger");
        }
    }

    @Override
    public User getUserByName(String username) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT userId, userName, userIni, CPRnummer FROM Users WHERE userName = '%s';", username));
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
    @Override
    public User getUser(String userId) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT userId, userName, userIni, CPRnummer FROM Users WHERE userId = %s;",userId));
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

    public User createUser(User user) throws DALException {
        try{
            this.executeUpdate(String.format("INSERT INTO Users VALUES (%d,'%s','%s','%s','%s',true);",user.getUserId(),user.getUserName(),user.getUserIni(),user.getCPRnummer(),crypt(user.getPassword())));
            for (Rolle rolle : user.getRoller()) {
                this.executeUpdate(String.format("INSERT INTO rolesusers VALUES(%d, %d);", rolle.getRoleId(), user.getUserId()));
            }
            return user;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af bruger");
        }
    }

    @Override
    public User updateUser(User user) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT userId FROM Users WHERE userId = %d;", user.getUserId()));
            if(rs.next()) {
                executeUpdate(String.format("UPDATE Users SET userName='%s',password='%s',userIni='%s',CPRnummer='%s' WHERE userId=%s;",user.getUserName(),crypt(user.getPassword()),user.getUserIni(),user.getCPRnummer(),user.getUserId()));
                this.executeUpdate(String.format("DELETE FROM rolesusers WHERE userId = %d", user.getUserId()));
                for (Rolle rolle : user.getRoller()) {
                    this.executeUpdate(String.format("INSERT INTO rolesusers VALUES(%d, %d);", rolle.getRoleId(), user.getUserId()));
                }
                return user;
            }else{
                throw new DALException("Brugeren eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved opdatering af bruger");
        }
    }

    @Override
    public boolean deleteUser(String userId) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT userId FROM Users WHERE userId = %s;", userId));
            if(rs.next()) {
                //TODO Brugeren skal slettet(DEAKTIVERES!) her
                return true;
            }else{
                throw new DALException("Brugeren eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved deaktivering af bruger");
        }
    }

    public User login(User user) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT userId, userName, userIni, CPRnummer, password FROM Users WHERE userName = '%s' AND password = '%s';",user.getUserName(), crypt(user.getPassword())));
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
