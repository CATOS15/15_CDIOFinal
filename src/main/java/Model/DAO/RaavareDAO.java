package Model.DAO;

import Model.DTO.Raavare;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAO extends Database implements IRaavareDAO {

    public RaavareDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public Raavare createRaavare(Raavare raavare) throws DALException {
        try{
            validateRaavare(raavare);
            ResultSet raavareIdExist = this.executeSelect(String.format("SELECT raavareId FROM raavare WHERE raavareId = '%s';", raavare.getRaavareId()));
            if(raavareIdExist.next()){
                throw new DALException("En råvare med det ID findes allerede");
            }
            this.executeUpdate(String.format("INSERT INTO Raavare VALUES (%d, '%s');", raavare.getRaavareId(), raavare.getRaavareNavn()));
            return raavare;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af raavare");
        }
    }

    @Override
    public Raavare updateRaavare(Raavare raavare) throws DALException {
        try{
            validateRaavare(raavare);
            ResultSet rs = this.executeSelect(String.format("SELECT * FROM Raavare WHERE raavareId = %d;", raavare.getRaavareId()));
            if(rs.next()) {
                executeUpdate(String.format("UPDATE Raavare SET raavareName='%s' WHERE raavareId=%d" , raavare.getRaavareNavn(),raavare.getRaavareId()));
                return raavare;
            }else{
                throw new DALException("Råvaren eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved opdatering af råvare");
        }
    }

    @Override
    public Raavare getRaavare(String raavareId) throws DALException {
        try{
            Raavare raavare = new Raavare();
            ResultSet rs = this.executeSelect(String.format("SELECT * FROM Raavare WHERE raavareId = %s", raavareId));
            if(rs.next())
            {
                raavare.setRaavareId(rs.getInt(1));
                raavare.setRaavareNavn(rs.getString(2));
            }
            else
            {
                throw new DALException("Forkert raavare id");
            }


            return raavare;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hentning af råvare");
        }
    }

    @Override
    public List<Raavare> getRaavarer() throws DALException {
        try{
            List<Raavare> raavarer = new ArrayList<>();
            ResultSet rs = this.executeSelect("SELECT * FROM Raavare");

            while (rs.next()){
                Raavare rv = new Raavare();
                rv.setRaavareId(rs.getInt(1));
                rv.setRaavareNavn(rs.getString(2));
                raavarer.add(rv);
            }
            return raavarer;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved hentning af råvarerer");
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


    private void validateRaavare(Raavare raavare) throws DALException {
        if(raavare.getRaavareId() < 1)
            throw new DALException("Råvarens ID skal bestå af et tal og være på mindste værdien 1");
        if(raavare.getRaavareNavn().length() < 3)
            throw new DALException("Råvaren skal have et navn på mindst 3 karakterer");

        try{
            ResultSet raavareNameExist = this.executeSelect(String.format("SELECT raavareId FROM raavare WHERE raavareName = '%s' AND raavareId != %d", raavare.getRaavareNavn(), raavare.getRaavareId()));
            if(raavareNameExist.next()){
                throw new DALException("En råvare med det navn findes allerede");
            }
        }
        catch (SQLException sqlException){
            throw new DALException("Fejl ved validering af råvare");
        }
    }
}
