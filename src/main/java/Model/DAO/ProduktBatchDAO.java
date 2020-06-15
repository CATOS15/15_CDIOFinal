package Model.DAO;

import Model.DTO.ProduktBatch;
import Model.DTO.UserProduktBatch;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchDAO extends Database implements IProduktBatchDAO {
    public ProduktBatchDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public ProduktBatch createProduktBatch(ProduktBatch produktBatch) throws DALException {
        try{
            this.executeUpdate(String.format("INSERT INTO ProduktBatch VALUES (%d, %d,'Oprettet', NOW(), null);", produktBatch.getPbId(), produktBatch.getReceptId()));
            return produktBatch;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af produkt batch");
        }
    }

    @Override
    public ProduktBatch getProduktBatch(String pbId) throws DALException {
        try{
            ResultSet rs = this.executeSelect(String.format("SELECT pbId, receptId, status, opretDato, slutDato FROM ProduktBatch WHERE pbId = %s;",pbId));
            if(rs.next()) {
                ProduktBatch produktBatch = new ProduktBatch();
                produktBatch.setPbId(rs.getInt(1));
                produktBatch.setReceptId(rs.getInt(2));
                produktBatch.setStatus(rs.getString(3));
                produktBatch.setOpretDato(rs.getString(4));
                produktBatch.setSlutDato(rs.getString(5));

                List<UserProduktBatch> userProduktBatches = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT pbId, userId, rbId, tara, netto, terminal FROM UserProduktBatch WHERE pbId = %s;",pbId));
                while(rs2.next()){
                    UserProduktBatch userProduktBatch = new UserProduktBatch();
                    userProduktBatch.setPbId(rs2.getInt(1));
                    userProduktBatch.setUserId(rs2.getInt(2));
                    userProduktBatch.setRbId(rs2.getInt(3));
                    userProduktBatch.setTara(rs2.getInt(4));
                    userProduktBatch.setNetto(rs2.getInt(5));
                    userProduktBatch.setTerminal(rs2.getInt(6));
                    userProduktBatches.add(userProduktBatch);
                }
                produktBatch.setUserProduktBatches(userProduktBatches);
                return produktBatch;
            }else{
                throw new DALException("Produkt batch eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af produkt batch");
        }
    }

    @Override
    public List<ProduktBatch> getProduktBatches() throws DALException {
        try{
            List<ProduktBatch> produktBatches = new ArrayList<>();

            ResultSet rs = this.executeSelect("SELECT pbId, receptId, status, opretDato, slutDato FROM ProduktBatch");
            while(rs.next()) {
                ProduktBatch produktBatch = new ProduktBatch();
                produktBatch.setPbId(rs.getInt(1));
                produktBatch.setReceptId(rs.getInt(2));
                produktBatch.setStatus(rs.getString(3));
                produktBatch.setOpretDato(rs.getString(4));
                produktBatch.setSlutDato(rs.getString(5));

                List<UserProduktBatch> userProduktBatches = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT pbId, userId, rbId, tara, netto, terminal FROM UserProduktBatch WHERE pbId = %s;", produktBatch.getPbId()));
                while (rs2.next()) {
                    UserProduktBatch userProduktBatch = new UserProduktBatch();
                    userProduktBatch.setPbId(rs2.getInt(1));
                    userProduktBatch.setUserId(rs2.getInt(2));
                    userProduktBatch.setRbId(rs2.getInt(3));
                    userProduktBatch.setTara(rs2.getInt(4));
                    userProduktBatch.setNetto(rs2.getInt(5));
                    userProduktBatch.setTerminal(rs2.getInt(6));
                    userProduktBatches.add(userProduktBatch);
                }
                produktBatch.setUserProduktBatches(userProduktBatches);
                produktBatches.add(produktBatch);
            }
            return produktBatches;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af produkt batches");
        }
    }

    @Override
    public void end() throws DALException {
        try{
            this.disconnect();
        }catch (SQLException se){
            throw new DALException("Forbindelsen kunne ikke lukkes");
        }
    }
}
