package Model.DAO;

import Model.DTO.Raavare;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaavareDAO extends Database implements IRaavareDAO {


    @Override
    public Raavare CreateRaavare(Raavare raavare) throws DALException {
        try{
            this.executeUpdate("INSERT INTO Ravare VALUES ("+raavare.getRaavareId()+", \""+raavare.getRaavareNavn()+");");
            return raavare;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af raavare");
        }
    }

    @Override
    public Raavare UpdateRaavare(Raavare raavare) throws DALException {
        try{
            ResultSet rs = this.executeSelect("SELECT * FROM Raavare WHERE raavareId = " + raavare.getRaavareId());
            if(rs.next()) {
                executeUpdate("UPDATE raavare SET raavareId=\""+raavare.getRaavareId()+"\", raavareNavn=\""+raavare.getRaavareNavn()+"\";");
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
    public Raavare DeleteRaavare(Raavare raavare) throws DALException {

        try{
            this.executeUpdate("DELETE Raavare WHERE raavareId = " + raavare.getRaavareId());

            return raavare;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved delete af råvare");
        }
    }

    @Override
    public Raavare SelectRaavare(Raavare raavare) throws DALException {
        try{
            ResultSet rs = this.executeSelect("SELECT * FROM Raavare WHERE raavareId = " + raavare.getRaavareId());
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
            throw new DALException("Fejl ved delete af råvare");
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
