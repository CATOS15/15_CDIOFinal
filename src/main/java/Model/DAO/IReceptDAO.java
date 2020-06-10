package Model.DAO;

import Model.DTO.Recept;
import Model.Exception.DALException;

public interface IReceptDAO {
    public Recept CreateRecept(Recept recept) throws DALException;
    public Recept UpdateRecept(Recept recept) throws DALException;
    public Recept DeleteRecept(Recept recept) throws DALException;
    public Recept SelectRecept(Recept recept) throws DALException;
    public void end() throws DALException;
}
