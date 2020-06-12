package Model.DAO;

import Model.DTO.Rolle;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolleDAO extends Database implements IRolleDAO {

    public RolleDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public Rolle createRolle(Rolle rolle) throws DALException {
        try{
            this.executeUpdate(String.format("INSERT INTO Roles VALUES (%d, '%s');", rolle.getRoleId(), rolle.getRolename()));
            return rolle;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af Rolle");
        }
    }

    @Override
    public Rolle updateRolle(Rolle rolle) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT * FROM Roles WHERE roleId = %d;", rolle.getRoleId()));
            if(rs.next()) {
                executeUpdate(String.format("UPDATE Roles SET rolename='%s' WHERE roleId=%d" , rolle.getRolename(),rolle.getRoleId()));
                return rolle;
            }else{
                throw new DALException("Rollen eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved opdatering af rolle");
        }
    }
    @Override
    public boolean deleteRolle(String rolleId) throws DALException {
        try{
            this.executeUpdate("DELETE Roles WHERE roleId = " + rolleId);
            return true;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved delete af rolle");
        }
    }
    @Override
    public Rolle getRolle(String rolleId) throws DALException {
        try{
            Rolle rolle = new Rolle();
            ResultSet rs = this.executeSelect(String.format("SELECT * FROM Roles WHERE roleId = %s", rolleId));
            if(rs.next())
            {
                rolle.setRoleId(rs.getInt(1));
                rolle.setRolename(rs.getString(2));
            }
            else
            {
                throw new DALException("Forkert rolle id");
            }


            return rolle;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved henting af rolle");
        }
    }

    @Override
    public List<Rolle> getRoller() throws DALException {
        try{
            List<Rolle> roller = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT * FROM Roles");

            while (rs.next()){
                Rolle rv = new Rolle();
                rv.setRoleId(rs.getInt(1));
                rv.setRolename(rs.getString(2));
                roller.add(rv);
            }
            return roller;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hentning af roller");
        }
    }

    @Override
    public void end() throws DALException {
        try {
            this.disconnect();
        } catch (SQLException e) {
            throw new DALException("Forbindelsen til databasen kunne ikke lukkes");
        }
    }
}
