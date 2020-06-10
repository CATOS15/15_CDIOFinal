package Model.DAO;

import Model.DTO.User;
import Model.Exception.DALException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends Database implements IUserDAO {
    public UserDAO() throws SQLException, ClassNotFoundException {
        this.connect();
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
            ResultSet rs = this.executeSelect("SELECT * FROM Users WHERE userName = " + user.getUserName() + " AND password = " + crypt(user.getPassword()));
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
            ResultSet rs = this.executeSelect("SELECT * FROM Users WHERE userName = \"" + user.getUserName() + "\" AND password = \"" + crypt(user.getPassword()) + "\"");
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

    private String crypt(String password){
        try {
            password = "missetand" + password;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
