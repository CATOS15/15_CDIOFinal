package Model.DAO;

import Model.DTO.Rolle;
import Model.Exception.DALException;

import java.util.List;

public interface IRolleDAO {
    public Rolle createRolle(Rolle rolle) throws DALException;
    public Rolle updateRolle(Rolle rolle) throws DALException;
    public Rolle getRolle(String rolleId) throws DALException;
    public boolean deleteRolle(String rolleId) throws DALException;


    public List<Rolle> getRoller() throws DALException;
    public void end() throws DALException;
}
