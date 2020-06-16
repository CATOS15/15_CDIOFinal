package Controller;

import Model.DAO.IProduktBatchDAO;
import Model.DAO.IUserProduktBatch;
import Model.DAO.ProduktBatchDAO;
import Model.DAO.UserProduktBatchDAO;
import Model.DTO.ProduktBatch;
import Model.DTO.UserProduktBatch;
import Security.Authenticated;
import Security.RolleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/afvejning")
public class UserProduktBatchController {
    private IUserProduktBatch iUserProduktBatchDAO;
    private ObjectMapper mapper;

    public UserProduktBatchController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iUserProduktBatchDAO = new UserProduktBatchDAO();
    }

    @GET
    @Authenticated(RolleEnum.LABORANT)
    public Response getUserProduktBatches() {
        try{
            List<UserProduktBatch> userProduktBatches = iUserProduktBatchDAO.getUserProduktBatches();
            iUserProduktBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(userProduktBatches)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Authenticated(RolleEnum.LABORANT)
    public Response createUserProduktBatch(String JSON_userproduktbatch) {
        try{
            UserProduktBatch userProduktBatch = mapper.readValue(JSON_userproduktbatch, UserProduktBatch.class);
            iUserProduktBatchDAO.createUserProduktBatch(userProduktBatch);
            iUserProduktBatchDAO.end();
            return Response.ok("Afvejning oprettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
