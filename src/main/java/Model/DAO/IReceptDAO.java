package Model.DAO;

import Model.DTO.Recept;
import Model.Exception.DALException;

import java.util.List;

public interface IReceptDAO {
    public Recept createRecept(Recept recept) throws DALException;
    public Recept getRecept(String receptId) throws DALException;
    public List<Recept> getRecepter() throws DALException;
    public void end() throws DALException;
}
