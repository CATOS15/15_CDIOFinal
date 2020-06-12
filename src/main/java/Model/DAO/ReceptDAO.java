package Model.DAO;

import Model.DTO.Recept;
import Model.Exception.DALException;

import java.sql.SQLException;
import java.util.List;

public class ReceptDAO extends Database implements IReceptDAO {
    public ReceptDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public Recept createRecept(Recept recept) throws DALException {
        return null;
    }

    @Override
    public Recept getRecept(String receptId) throws DALException {
        return null;
    }

    @Override
    public List<Recept> getRecepter() throws DALException {
        return null;
    }

    @Override
    public void end() throws DALException {

    }
}
