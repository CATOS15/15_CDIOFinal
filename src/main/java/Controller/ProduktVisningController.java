package Controller;

import Model.DAO.*;
import Model.DTO.*;
import Model.Exception.DALException;
import Security.Authenticated;
import Security.RolleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/produktvisning")
public class ProduktVisningController {
    private ObjectMapper mapper;
    private IProduktBatchDAO iProduktBatchDAO;
    private IReceptDAO iReceptDAO;
    private IRaavareDAO iRaavareDAO;
    private IUserProduktBatch iUserProduktBatch;
    private IRaavareBatchDAO iRaavareBatchDAO;
    private IUserDAO iUserDAO;

    public ProduktVisningController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iProduktBatchDAO = new ProduktBatchDAO();
        iReceptDAO = new ReceptDAO();
        iRaavareDAO = new RaavareDAO();
        iUserProduktBatch = new UserProduktBatchDAO();
        iRaavareBatchDAO = new RaavareBatchDAO();
        iUserDAO = new UserDAO();
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    @Path("{pbId}")
    public Response getProduktVisning(@PathParam("pbId") String pbId) throws SQLException, DALException {
        try{
            ProduktBatch produktBatch = iProduktBatchDAO.getProduktBatch(pbId);
            UserProduktBatch userProduktBatch = iUserProduktBatch.getUserProduktBatch(pbId);

            ProduktVisning produktVisning = new ProduktVisning();
            produktVisning.setPbId(Integer.parseInt(pbId));
            produktVisning.setOpretDato(produktBatch.getOpretDato());
            produktVisning.setSlutDato(produktBatch.getSlutDato());
            produktVisning.setStatus(produktBatch.getStatus());
            produktVisning.setReceptId(produktBatch.getReceptId());

            Recept recept = iReceptDAO.getRecept(String.valueOf(produktBatch.getReceptId()));
            double totaleNetto = 0;
            double totaleTara = 0;
            List<ProduktVisning.ProduktVisningItem> produktVisningItems = new ArrayList<>();
            for (Afvejning afvejning : userProduktBatch.getAfvejninger()) {
                RaavareBatch raavareBatch = iRaavareBatchDAO.getRaavareBatch(String.valueOf(afvejning.getRbId()));
                Raavare raavare = iRaavareDAO.getRaavare(String.valueOf(raavareBatch.getRaavareId()));
                ProduktVisning.ProduktVisningItem produktVisningItem = new ProduktVisning.ProduktVisningItem();
                produktVisningItem.setNetto(afvejning.getNetto());
                produktVisningItem.setRaavareId(raavare.getRaavareId());
                produktVisningItem.setRaavareNavn(raavare.getRaavareNavn());
                produktVisningItem.setRbId(raavareBatch.getRbId());
                produktVisningItem.setTara(afvejning.getTara());
                produktVisningItem.setTerminal(afvejning.getTerminal());
                produktVisningItem.setUserIni(iUserDAO.getUser(String.valueOf(afvejning.getUserId())).getUserIni());
                for (ReceptRaavare receptRaavare: recept.getReceptRaavarer()) {
                    if(receptRaavare.getRaavareId() == raavare.getRaavareId()){
                        produktVisningItem.setMaengde(receptRaavare.getNonNetto());
                        produktVisningItem.setTolerance(receptRaavare.getTolerance());
                        break;
                    }
                }
                totaleNetto += afvejning.getNetto();
                totaleTara += afvejning.getTara();
                produktVisningItems.add(produktVisningItem);
            }
            produktVisning.setProduktVisningItems(produktVisningItems);
            produktVisning.setTotalNetto(totaleNetto);
            produktVisning.setTotalTara(totaleTara);
            iProduktBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(produktVisning)).build();
        }
        catch (Exception e){
            iProduktBatchDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
