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
            this.executeUpdate(String.format("INSERT INTO Recept VALUES (%d, '%s');", recept.getReceptId(), recept.getReceptNavn()));
            for (ReceptRaavare receptRaavare : recept.getReceptRaavarer()) {
                this.executeUpdate(String.format("INSERT INTO ReceptRaavare VALUES (%d, %d, %d, %d);", receptRaavare.getReceptId(), receptRaavare.getRaavareId(), receptRaavare.getNonNetto(), receptRaavare.getTolerance()));
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
                    receptRaavare.setNonNetto(rs2.getInt(3));
                    receptRaavare.setTolerance(rs2.getInt(4));
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
}
