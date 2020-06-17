package Model.DAO;

import Model.DTO.Raavare;
import Model.DTO.RaavareBatch;
import Model.DTO.User;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static Security.Security.crypt;

public class RaavareBatchDAO extends Database implements IRaavareBatchDAO {
    public RaavareBatchDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    Locale locale = Locale.ENGLISH;
    NumberFormat nf = NumberFormat.getNumberInstance(locale);


    private static DecimalFormat decimalFormat = new DecimalFormat("#.00");

    @Override
    public RaavareBatch createRavareBatch(RaavareBatch raavareBatch) throws DALException {
        try{
            validateRaavareBatch(raavareBatch);
            ResultSet rbIdExist = this.executeSelect(String.format("SELECT rbId FROM RaavareBatch WHERE rbId = %d;", raavareBatch.getRbId()));
            if(rbIdExist.next()){
                throw new DALException("En bruger med det ID findes allerede");
            }

            ResultSet rs = this.executeSelect(String.format("SELECT * FROM Raavare WHERE raavareId = %s;",raavareBatch.getRaavareId()));
            if(rs.next()) {
                try{
                    this.executeUpdate(String.format("INSERT INTO RaavareBatch VALUES (%d,%d,%s,'%s');",raavareBatch.getRbId(),raavareBatch.getRaavareId(),raavareBatch.getMaengde(),raavareBatch.getLeverandoer()));
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
                raavareBatch.setMaengde(rs.getDouble(3));
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
                raavareBatch.setMaengde(rs.getDouble(3));
                raavareBatch.setLeverandoer(rs.getString(4));
                raavareBatches.add(raavareBatch);
            }
            return raavareBatches;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af råvarerbatch");
        }
    }

    private void validateRaavareBatch(RaavareBatch raavareBatch) throws DALException {
        if(raavareBatch.getRbId() < 1 || raavareBatch.getRbId() >= 100000000){
            throw new DALException("Råvarebatchet ID skal bestå af et tal og være på mindste værdien 1, og være højst på 8 cifre");
        }
        if(raavareBatch.getRaavareId() < 1){
            throw new DALException("En råvare mangler at blive valgt");
        }
        if(raavareBatch.getMaengde() <= 0){
            throw new DALException("Mængden skal angives og være større end 0");
        }
        if(raavareBatch.getLeverandoer().length() < 3  || raavareBatch.getLeverandoer().length() > 20){
            throw new DALException("Leverandør navnet skal være mellem 3 og 20 karakterer");
        }

        try{
            ResultSet raavareBatchIdExist = this.executeSelect(String.format("SELECT rbId FROM RaavareBatch WHERE rbId = %d", raavareBatch.getRbId()));
            if(raavareBatchIdExist.next()){
                throw new DALException("Et Raavarebatch med dette ID findes allerede");
            }
        }
        catch (SQLException sqlException){
            throw new DALException("Fejl ved validering af Raavarebatch");
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
