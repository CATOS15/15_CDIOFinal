package Model.DAO;

import Model.DTO.Raavare;
import Model.DTO.RaavareBatch;
import Model.Exception.DALException;

public interface IRaavareBatchDAO {
        public Raavare CreateRaavareBatch(RaavareBatch raavareBatch) throws DALException;
        public Raavare SelectRaavareBatch(RaavareBatch raavareBatch) throws DALException;
        public void end() throws DALException;
}
