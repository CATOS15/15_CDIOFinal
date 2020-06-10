package Model.DAO;

import Model.DTO.Recept;
import Model.Exception.DALException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceptDAO extends Database implements IReceptDAO {

    public ReceptDAO() throws SQLException, ClassNotFoundException {
        this.connect();
    }

    @Override
    public Recept CreateRecept(Recept recept) throws DALException {
        try{
            this.executeUpdate("INSERT INTO Recept VALUES ("+recept.getReceptId()+", \""+recept.getReceptNavn()+"\", \""+recept.getRaavareId()+"\", \""+recept.getNonNetto()+"\", \""+recept.getTolerance()+"\");");

            return recept;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved oprettelse af recept");
        }
    }

    @Override
    public Recept UpdateRecept(Recept recept) throws DALException {

        try{
            ResultSet rs = this.executeSelect("SELECT * FROM Recept WHERE ReceptID = " + recept.getReceptId());
            if(rs.next()) {
                executeUpdate("UPDATE Recept SET receptId=\""+recept.getReceptId()+"\", receptNavn=\""+recept.getReceptNavn()+"\", raavareId=\""+recept.getReceptId()+"\", nonNetto=\""+recept.getNonNetto()+ "\", tolerance=\""+recept.getTolerance()+"\" WHERE receptId=\""+recept.getReceptId()+"\";");
                return recept;
            }else{
                throw new DALException("Brugeren eksisterer ikke");
            }
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved opdatering af recept");
        }
    }

    @Override
    public Recept DeleteRecept(Recept recept) throws DALException {
        try{
            this.executeUpdate("DELETE Recept WHERE ReceptID = " + recept.getReceptId());

            return recept;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved delete af recept");
        }
    }

    @Override
    public Recept SelectRecept(Recept recept) throws DALException {
        try{
            this.executeSelect("SELECT * FROM Recept WHERE ReceptID = " + recept.getReceptId());

            return recept;
        }
        catch(SQLException sqlEx){
            throw new DALException("Fejl ved delete af recept");
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
