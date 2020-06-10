package Model.DAO;

import Model.DTO.User;
import Model.Exception.DALException;

public interface IUserDAO {
    public User CreateUser(User user) throws DALException;
    public User UpdateUser(User user) throws DALException;
    public User Login(User user) throws DALException;

    public void end() throws DALException;
}
