package Model.DAO;

import Model.DTO.Afvejning;
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
            for (Afvejning afvejning: userProduktBatch.getAfvejninger()) {
                ResultSet rs = this.executeSelect(String.format("SELECT pbId, rbId FROM UserProduktBatch WHERE pbId = %d AND rbId = %d", userProduktBatch.getPbId(), afvejning.getRbId()));
                if(rs.next()) {
                    continue;
                }
                this.executeUpdate(String.format("INSERT INTO UserProduktBatch VALUES (%d, %d, %d, %d, %d, %d);", userProduktBatch.getPbId(), afvejning.getUserId(), afvejning.getRbId(), afvejning.getTara(), afvejning.getNetto(), afvejning.getTerminal()));
            }
            String test = String.format("SELECT DISTINCT COUNT(*) FROM produktbatch as pb NATURAL JOIN receptraavare as r WHERE pb.pbId = %d;", userProduktBatch.getPbId());
            ResultSet rs = this.executeSelect(test);
            if(rs.next()){
                int totaleRaavarePaakraevet = rs.getInt(1);
                if(totaleRaavarePaakraevet == userProduktBatch.getAfvejninger().size()){
                    this.executeUpdate(String.format("UPDATE produktbatch as pb SET pb.status = 'Afsluttet' WHERE pb.pbId = %d;", userProduktBatch.getPbId()));
                }
            }else{
                throw new DALException("Fejl ved hent af råvare på produktbatch!");
            }

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
            ResultSet rs = this.executeSelect("SELECT DISTINCT pbId FROM ProduktBatch");
            while(rs.next()){
                UserProduktBatch userProduktBatch = new UserProduktBatch();
                userProduktBatch.setPbId(rs.getInt(1));

                List<Afvejning> afvejninger = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT pbId, userId, rbId, tara, netto, terminal FROM UserProduktBatch WHERE pbId = %d", userProduktBatch.getPbId()));
                while (rs2.next()){
                    Afvejning afvejning = new Afvejning();
                    afvejning.setUserId(rs2.getInt(2));
                    afvejning.setRbId(rs2.getInt(3));
                    afvejning.setTara(rs2.getInt(4));
                    afvejning.setNetto(rs2.getInt(5));
                    afvejning.setTerminal(rs2.getInt(6));
                    afvejninger.add(afvejning);
                }
                userProduktBatch.setAfvejninger(afvejninger);
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
