package Model.DAO;

import Model.DTO.Raavare;
import Model.DTO.RaavareBatch;
import Model.Exception.DALException;

import java.util.List;

public interface IRaavareBatchDAO {
    public RaavareBatch createRavareBatch(RaavareBatch raavareBatch) throws DALException;
    public RaavareBatch getRaavareBatch(String rbId) throws DALException;
    public List<RaavareBatch> getRaavareBatches() throws DALException;
    public void end() throws DALException;

}
