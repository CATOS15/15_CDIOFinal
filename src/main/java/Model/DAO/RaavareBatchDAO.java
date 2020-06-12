package Model.DAO;

import Model.DTO.Raavare;
import Model.DTO.RaavareBatch;
import Model.DTO.User;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Security.Security.crypt;

public class RaavareBatchDAO extends Database implements IRaavareBatchDAO {
    public RaavareBatchDAO() throws SQLException, ClassNotFoundException {
        this.connect();
    }


    @Override
    public RaavareBatch createRavareBatch(RaavareBatch raavareBatch) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT * FROM Raavare WHERE raavareId = %s;",raavareBatch.getRaavareId()));
            if(rs.next()) {
                try{
                    this.executeUpdate(String.format("INSERT INTO RaavareBatch VALUES (%d,%d,%d,'%s');",raavareBatch.getRbId(),raavareBatch.getRaavareId(),raavareBatch.getMaengde(),raavareBatch.getLeverandoer()));
                    return raavareBatch;
                }
                catch(SQLException sqlEx){
                    throw new DALException("Fejl ved oprettelse af råvarebatch");
                }
            }else{
                throw new DALException("Råvarer eksisterer ikke");
            }
        }catch (SQLException sqlEx){
            throw new DALException("Fejl ved hent af råvarebatch");
        }
    }

    @Override
    public RaavareBatch getRaavareBatch(String rbId) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT * FROM RaavareBatch WHERE rbId = %s;",rbId));
            if(rs.next()) {
                RaavareBatch raavareBatch = new RaavareBatch();
                raavareBatch.setRbId(rs.getInt(1));
                raavareBatch.setRaavareId(rs.getInt(2));
                raavareBatch.setMaengde(rs.getInt(3));
                raavareBatch.setLeverandoer(rs.getString(4));
                return raavareBatch;
            }else{
                throw new DALException("Råvarebatch eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af råvarebatch");
        }
    }

    @Override
    public List<RaavareBatch> getRaavareBatches() throws DALException {
        try{
            List<RaavareBatch> raavareBatches = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT * FROM RaavareBatch;");
            while(rs.next()) {
                RaavareBatch raavareBatch = new RaavareBatch();
                raavareBatch.setRbId(rs.getInt(1));
                raavareBatch.setRaavareId(rs.getInt(2));
                raavareBatch.setMaengde(rs.getInt(3));
                raavareBatch.setLeverandoer(rs.getString(4));
                raavareBatches.add(raavareBatch);
            }
            return raavareBatches;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af råvarerbatch");
        }
    }

    @Override
    public void end() throws DALException {

    }
}
