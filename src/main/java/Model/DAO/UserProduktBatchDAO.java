package Model.DAO;

import Model.DTO.UserProduktBatch;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserProduktBatchDAO extends Database implements IUserProduktBatch {
    public UserProduktBatchDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public UserProduktBatch createUserProduktBatch(UserProduktBatch userProduktBatch) throws DALException {
        try{
            this.executeUpdate(String.format("INSERT INTO UserProduktBatch VALUES (%d, %d, %d, %d, %d, %d);", userProduktBatch.getPbId(), userProduktBatch.getUserId(), userProduktBatch.getRbId(), userProduktBatch.getTara(), userProduktBatch.getNetto(), userProduktBatch.getTerminal()));
            return userProduktBatch;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af afvejning");
        }
    }

    @Override
    public List<UserProduktBatch> getUserProduktBatches() throws DALException {
        try{
            List<UserProduktBatch> userProduktBatches = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT pbId, userId, rbId, tara, netto, terminal FROM UserProduktBatch");
            while(rs.next()){
                UserProduktBatch userProduktBatch = new UserProduktBatch();
                userProduktBatch.setPbId(rs.getInt(1));
                userProduktBatch.setUserId(rs.getInt(2));
                userProduktBatch.setRbId(rs.getInt(3));
                userProduktBatch.setTara(rs.getInt(4));
                userProduktBatch.setNetto(rs.getInt(5));
                userProduktBatch.setTerminal(rs.getInt(6));
                userProduktBatches.add(userProduktBatch);
            }
            return userProduktBatches;
        }
        catch (SQLException sqlException){
            throw new DALException("Fejl ved hent af afvejninger");
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
