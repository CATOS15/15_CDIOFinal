package Model.DAO;

import Model.DTO.ProduktBatch;
import Model.Exception.DALException;

import java.sql.SQLException;
import java.util.List;

public interface IProduktBatchDAO {
    public ProduktBatch createProduktBatch(ProduktBatch produktBatch) throws DALException;
    public ProduktBatch getProduktBatch(String pbId) throws DALException;
    public List<ProduktBatch> getProduktBatches() throws DALException;
    public void end() throws DALException, SQLException;
}