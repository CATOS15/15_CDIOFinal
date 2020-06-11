package Model.DAO;

import Model.DTO.User;
import Model.Exception.DALException;

import java.util.List;

public interface IUserDAO {
    public List<User> getUsers() throws DALException;
    public User getUserByName(String usernname) throws DALException;
    public User getUser(String userId) throws DALException;
    public User createUser(User user) throws DALException;
    public User updateUser(User user) throws DALException;
    public boolean deleteUser(String userId) throws DALException;
    public User login(User user) throws DALException;

    public void end() throws DALException;
}
