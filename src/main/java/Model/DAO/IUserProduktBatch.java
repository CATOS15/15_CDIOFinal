package Model.DAO;

import Model.DTO.UserProduktBatch;
import Model.Exception.DALException;

import java.sql.SQLException;
import java.util.List;

public interface IUserProduktBatch {
    public UserProduktBatch createUserProduktBatch(UserProduktBatch userProduktBatch) throws DALException;
    public List<UserProduktBatch> getUserProduktBatches() throws DALException;
    public UserProduktBatch getUserProduktBatch(String pbId) throws DALException;
    public void end() throws DALException, SQLException;
}
