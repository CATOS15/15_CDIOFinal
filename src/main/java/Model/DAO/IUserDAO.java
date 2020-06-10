package Model.DAO;

import Model.DTO.User;
import Model.Exception.DALException;

public interface IUserDAO {
    public User GetUser(String usernname) throws DALException;
    public User CreateUser(User user) throws DALException;
    public User UpdateUser(User user) throws DALException;
    public User Login(User user) throws DALException;

    public void end() throws DALException;
}
