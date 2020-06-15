package Model.DAO;

import Model.DTO.Raavare;
import Model.Exception.DALException;

import java.util.ArrayList;
import java.util.List;

public interface IRaavareDAO {
    public Raavare createRaavare(Raavare raavare) throws DALException;
    public Raavare updateRaavare(Raavare raavare) throws DALException;
    public Raavare getRaavare(String raavareId) throws DALException;
    public List<Raavare> getRaavarer() throws DALException;
    public void end() throws DALException;
}
