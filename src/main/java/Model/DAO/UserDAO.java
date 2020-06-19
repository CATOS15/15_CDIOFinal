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

    public UserDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public List<User> getUsers() throws DALException {
        try{
            IRolleDAO iRolleDAO = new RolleDAO();
            List<User> users = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT userId, userName, userIni, CPRnummer, tilstand FROM users");
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserIni(rs.getString(3));
                user.setCPRnummer(rs.getString(4));
                user.setTilstand(rs.getBoolean(5));

                List<Rolle> roller = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT roleId FROM rolesusers WHERE userId = %d;", user.getUserId()));
                while(rs2.next()) {
                    Rolle rolle = iRolleDAO.getRolle(String.valueOf(rs2.getInt(1)));
                    iRolleDAO.end();
                    roller.add(rolle);
                }
                user.setRoller(roller);
                users.add(user);
            }
            return users;
        }
        catch(SQLException | ClassNotFoundException sqlEx){
            throw new DALException("Fejl ved hent af brugere");
        }
    }

    @Override
    public User getUserByName(String username) throws DALException {
        try{
            IRolleDAO iRolleDAO = new RolleDAO();
            ResultSet rs = this.executeSelect(String.format("SELECT userId, userName, userIni, CPRnummer, tilstand FROM Users WHERE userName = '%s';", username));
            if(rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserIni(rs.getString(3));
                user.setCPRnummer(rs.getString(4));
                user.setTilstand(rs.getBoolean(5));

                List<Rolle> roller = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT roleId FROM rolesusers WHERE userId = %d;", user.getUserId()));
                while(rs2.next()) {
                    Rolle rolle = iRolleDAO.getRolle(String.valueOf(rs2.getInt(1)));
                    iRolleDAO.end();
                    roller.add(rolle);
                }
                user.setRoller(roller);

                return user;
            }else{
                throw new DALException("Brugeren eksisterer ikke");
            }
        }
        catch(SQLException | ClassNotFoundException sqlEx){
            throw new DALException("Fejl ved hent af brugeren " + username);
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
            throw new DALException("Fejl ved hent af brugeren på id'et " + userId);
        }
    }

    public User createUser(User user) throws DALException {
        try{
            validateUser(user);
            ResultSet useridExist = this.executeSelect(String.format("SELECT userId FROM Users WHERE userId = '%s';", user.getUserId()));
            if(useridExist.next()){
                throw new DALException("En bruger med det ID findes allerede");
            }
            this.executeUpdate(String.format("INSERT INTO Users VALUES (%d,'%s','%s','%s','%s',true);",user.getUserId(),user.getUserName(),user.getUserIni(),user.getCPRnummer(),crypt(user.getPassword())));
            for (Rolle rolle : user.getRoller()) {
                this.executeUpdate(String.format("INSERT INTO rolesusers VALUES(%d, %d);", rolle.getRoleId(), user.getUserId()));
            }
            return user;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af bruger " + user.getUserName());
        }
    }

    @Override
    public User updateUser(User user) throws DALException {
        try{
            validateUser(user);
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
                executeUpdate(String.format("UPDATE Users SET tilstand=0 WHERE userId=%s;", userId));
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
            ResultSet rs = this.executeSelect(String.format("SELECT userId, userName, userIni, CPRnummer, password FROM Users WHERE userName = '%s' AND password = '%s' AND tilstand = 1;",user.getUserName(), crypt(user.getPassword())));
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

    private void validateUser(User user) throws DALException {
        if(user.getUserId() < 1 || user.getUserId()  >= 100000000)
            throw new DALException("Brugerens ID skal bestå af et tal og være på mindste værdien 1, og være højst på 8 cifre");
        if(user.getUserName() == null || user.getUserName().length() < 3 || user.getUserName().length() > 16)
            throw new DALException("Bruger navnet skal være mellem 3 og 15 karakterer");
        if(user.getPassword() == null || user.getPassword().length() < 3 || user.getPassword().length() > 41)
            throw new DALException("Passwordet skal være mellem 3 og 40 karakterer");
        if(user.getUserIni() == null || user.getUserIni().length() < 2 || user.getUserIni().length() > 6)
            throw new DALException("Bruger initialerne skal være mellem 2 og 5 karakterer");
        if(user.getCPRnummer() == null || user.getCPRnummer().length() != 10)
            throw new DALException("CPR nummeret skal være 10 karakterer langt");
        if(user.getRoller() == null || user.getRoller().size() < 1)
            throw new DALException("Brugeren skal være knyttet til mindst en rolle");

        try{
            ResultSet usernameExist = this.executeSelect(String.format("SELECT userId FROM Users WHERE userName = '%s' AND userId != %d", user.getUserName(), user.getUserId()));
            if(usernameExist.next()){
                throw new DALException("En bruger med det navn findes allerede");
            }
        }
        catch (SQLException sqlException){
            throw new DALException("Fejl ved validering af bruger");
        }
    }
}
