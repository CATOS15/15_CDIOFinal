package Model.DAO;

import Model.DTO.User;
import Model.Exception.DALException;

import java.util.List;

public interface IUserDAO {
    public List<User> GetUsers() throws DALException;
    public User GetUserByName(String usernname) throws DALException;
    public User GetUser(String userId) throws DALException;
    public User CreateUser(User user) throws DALException;
    public User UpdateUser(User user) throws DALException;
    public boolean DeleteUser(String userId) throws DALException;
    public User Login(User user) throws DALException;

    public void end() throws DALException;
}
