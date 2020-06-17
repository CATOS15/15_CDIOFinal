package Model.DAO;

import Model.DTO.Recept;
import Model.DTO.ReceptRaavare;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAO extends Database implements IReceptDAO {
    public ReceptDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public Recept createRecept(Recept recept) throws DALException {
        try{
            validateRecept(recept);
            ResultSet receptIdExist =  this.executeSelect(String.format("SELECT * FROM Recept WHERE receptId = %d;", recept.getReceptId()));
            if(receptIdExist.next()){
                throw new DALException("En bruger med det ID findes allerede");
            }
            this.executeUpdate(String.format("INSERT INTO Recept VALUES (%d, '%s');", recept.getReceptId(), recept.getReceptNavn()));
            for (ReceptRaavare receptRaavare : recept.getReceptRaavarer()) {
                this.executeUpdate(String.format("INSERT INTO ReceptRaavare VALUES (%d, %d, %s, %s);", receptRaavare.getReceptId(), receptRaavare.getRaavareId(), receptRaavare.getNonNetto(), receptRaavare.getTolerance()));
            }
            return recept;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af recept");
        }
    }

    @Override
    public Recept getRecept(String receptId) throws DALException {
        return null;
    }

    @Override
    public List<Recept> getRecepter() throws DALException {
        try{
            List<Recept> recepter = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT receptId, receptName FROM Recept");
            while(rs.next()) {
                Recept recept = new Recept();
                recept.setReceptId(rs.getInt(1));
                recept.setReceptNavn(rs.getString(2));

                List<ReceptRaavare> receptRaavarer = new ArrayList<>();
                ResultSet rs2 = this.executeSelect(String.format("SELECT receptId, raavareId, nonNetto, tolerance FROM ReceptRaavare WHERE receptId = %d;", recept.getReceptId()));
                while(rs2.next()){
                    ReceptRaavare receptRaavare = new ReceptRaavare();
                    receptRaavare.setReceptId(rs2.getInt(1));
                    receptRaavare.setRaavareId(rs2.getInt(2));
                    receptRaavare.setNonNetto(rs2.getDouble(3));
                    receptRaavare.setTolerance(rs2.getDouble(4));
                    receptRaavarer.add(receptRaavare);
                }
                recept.setReceptRaavarer(receptRaavarer);
                recepter.add(recept);
            }
            return recepter;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hent af recepter");
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


    private void validateRecept(Recept recept) throws DALException {
        if(recept.getReceptId() < 1 || recept.getReceptId() >= 100000000)
            throw new DALException("Recept ID skal bestå af et tal og være på mindste værdien 1, og være højst på 8 cifre");
        if(recept.getReceptNavn() == null || recept.getReceptNavn() .length() < 3 || recept.getReceptNavn() .length() > 20)
            throw new DALException("Receptet navnet skal være mellem 3 og 20 karakterer");

        List<ReceptRaavare> receptRaavarer = recept.getReceptRaavarer();
        if(receptRaavarer.size() < 1){
            throw new DALException("Ingen råvare tilført");
        }
        for(ReceptRaavare receptRaavare : receptRaavarer){
            if(receptRaavare.getRaavareId() < 1){
                throw new DALException("Udfyld venligst alle Råvarer");
            }
            if(receptRaavare.getNonNetto() < 1){
                throw new DALException("Udfyld venligst alle NonNettoer");
            }
            if(receptRaavare.getTolerance() < 1){
                throw new DALException("Udfyld venligst alle Tolerancer");
            }
        }

        try{
            ResultSet receptExist = this.executeSelect(String.format("SELECT receptId FROM Recept WHERE receptName = '%s' AND receptId != %d", recept.getReceptNavn(), recept.getReceptId()));
            if(receptExist.next()){
                throw new DALException("En recept med det navn findes allerede");
            }
        }
        catch (SQLException sqlException){
            throw new DALException("Fejl ved validering af bruger");
        }
    }
}
