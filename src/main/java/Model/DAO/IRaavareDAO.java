package Model.DAO;

import Model.DTO.Raavare;
import Model.Exception.DALException;

public interface IRaavareDAO {
    public Raavare CreateRaavare(Raavare raavare) throws DALException;
    public Raavare UpdateRaavare(Raavare raavare) throws DALException;
    public Raavare DeleteRaavare(Raavare raavare) throws DALException;
    public Raavare SelectRaavare(Raavare raavare) throws DALException;
    public void end() throws DALException;
}
