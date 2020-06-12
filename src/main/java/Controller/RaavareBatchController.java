package Controller;

import Model.DAO.IRaavareBatchDAO;
import Model.DAO.IUserDAO;
import Model.DAO.RaavareBatchDAO;
import Model.DAO.UserDAO;
import Model.DTO.RaavareBatch;
import Model.DTO.User;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Authenticated
@Path("/raavarebatch")
public class RaavareBatchController {
    private IRaavareBatchDAO iRaavareBatchDAO;
    private ObjectMapper mapper;

    public RaavareBatchController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iRaavareBatchDAO = new RaavareBatchDAO();
    }

    @GET
    public Response getRaavareBatches() {
        try{
            List<RaavareBatch> raavareBatches = iRaavareBatchDAO.getRaavareBatches();
            iRaavareBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(raavareBatches)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{rbId}")
    public Response getRaavareBatch(@PathParam("rbId") String rbId) {
        try{
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createRaavareBatch(String JSON_raavareBatch) {
        try{
            RaavareBatch raavareBatch = mapper.readValue(JSON_raavareBatch, RaavareBatch.class);
            iRaavareBatchDAO.createRavareBatch(raavareBatch);
            iRaavareBatchDAO.end();
            return Response.ok("Råvarebatch oprettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


}
