package Controller;

import Model.DAO.IRolleDAO;
import Model.DAO.RolleDAO;
import Model.DTO.Rolle;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Authenticated
@Path("/rolle")
public class RolleController {

    private IRolleDAO iRolleDAO;
    private ObjectMapper mapper;

    public RolleController() throws SQLException, ClassNotFoundException {
        iRolleDAO = new RolleDAO();
        mapper = new ObjectMapper();
    }


    @GET
    public Response getRoller()
    {
        try{
            List<Rolle> roller = iRolleDAO.getRoller();
            iRolleDAO.end();
            return Response.ok(mapper.writeValueAsString(roller)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{rolleId}")
    public Response getRolle(@PathParam("rolleId") String rolleId)
    {
        try{
            Rolle rolle = iRolleDAO.getRolle(rolleId);
            iRolleDAO.end();
            return Response.ok(mapper.writeValueAsString(rolle)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createRolle(String JSON_raavare)
    {
        try{
            Rolle rolle = mapper.readValue(JSON_raavare, Rolle.class);
            iRolleDAO.createRolle(rolle);
            iRolleDAO.end();
            return Response.ok("Rolle oprettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response updateRolle(String JSON_rolle)
    {
        try{
            Rolle rolle = mapper.readValue(JSON_rolle, Rolle.class);
            iRolleDAO.updateRolle(rolle);
            iRolleDAO.end();
            return Response.ok("Rolle opdateret").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{rolleId}")
    public Response deleteRaavare(@PathParam("rolleId") String rolleId)
    {
        try{
            iRolleDAO.deleteRolle(rolleId);
            iRolleDAO.end();
            return Response.ok("Rolle slettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
