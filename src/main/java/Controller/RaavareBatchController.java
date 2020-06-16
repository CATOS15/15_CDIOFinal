package Controller;

import Model.DAO.IRaavareBatchDAO;
import Model.DAO.IUserDAO;
import Model.DAO.RaavareBatchDAO;
import Model.DAO.UserDAO;
import Model.DTO.RaavareBatch;
import Model.DTO.User;
import Model.Exception.DALException;
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

@Path("/raavarebatch")
public class RaavareBatchController {
    private IRaavareBatchDAO iRaavareBatchDAO;
    private ObjectMapper mapper;

    public RaavareBatchController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iRaavareBatchDAO = new RaavareBatchDAO();
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    public Response getRaavareBatches() throws DALException {
        try{
            List<RaavareBatch> raavareBatches = iRaavareBatchDAO.getRaavareBatches();
            iRaavareBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(raavareBatches)).build();
        }
        catch (Exception e){
            iRaavareBatchDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    @Path("{rbId}")
    public Response getRaavareBatch(@PathParam("rbId") String rbId) throws DALException {
        try{
            iRaavareBatchDAO.end();
            return null;
        }
        catch (Exception e){
            iRaavareBatchDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Authenticated(RolleEnum.PRODUKTIONSLEDER)
    public Response createRaavareBatch(String JSON_raavareBatch) throws DALException {
        try{
            RaavareBatch raavareBatch = mapper.readValue(JSON_raavareBatch, RaavareBatch.class);
            iRaavareBatchDAO.createRavareBatch(raavareBatch);
            iRaavareBatchDAO.end();
            return Response.ok("Råvarebatch oprettet").build();
        }
        catch (Exception e){
            iRaavareBatchDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


}
