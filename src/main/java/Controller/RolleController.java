package Controller;

import Model.DAO.IRolleDAO;
import Model.DAO.RolleDAO;
import Model.DTO.Rolle;
import Model.Exception.DALException;
import Security.Authenticated;
import Security.RolleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/rolle")
public class RolleController {

    private IRolleDAO iRolleDAO;
    private ObjectMapper mapper;

    public RolleController() throws SQLException, ClassNotFoundException {
        iRolleDAO = new RolleDAO();
        mapper = new ObjectMapper();
    }


    @GET
    @Authenticated(RolleEnum.NULL)
    public Response getRoller() throws DALException {
        try{
            List<Rolle> roller = iRolleDAO.getRoller();
            iRolleDAO.end();
            return Response.ok(mapper.writeValueAsString(roller)).build();
        }
        catch (Exception e){
            iRolleDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    @Path("{rolleId}")
    public Response getRolle(@PathParam("rolleId") String rolleId) throws DALException {
        try{
            Rolle rolle = iRolleDAO.getRolle(rolleId);
            iRolleDAO.end();
            return Response.ok(mapper.writeValueAsString(rolle)).build();
        }
        catch (Exception e){
            iRolleDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Authenticated(RolleEnum.ADMINISTRATOR)
    public Response createRolle(String JSON_raavare) throws DALException {
        try{
            Rolle rolle = mapper.readValue(JSON_raavare, Rolle.class);
            iRolleDAO.createRolle(rolle);
            iRolleDAO.end();
            return Response.ok("Rolle oprettet").build();
        }
        catch (Exception e){
            iRolleDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Authenticated(RolleEnum.ADMINISTRATOR)
    public Response updateRolle(String JSON_rolle) throws DALException {
        try{
            Rolle rolle = mapper.readValue(JSON_rolle, Rolle.class);
            iRolleDAO.updateRolle(rolle);
            iRolleDAO.end();
            return Response.ok("Rolle opdateret").build();
        }
        catch (Exception e){
            iRolleDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Authenticated(RolleEnum.ADMINISTRATOR)
    @Path("{rolleId}")
    public Response deleteRaavare(@PathParam("rolleId") String rolleId) throws DALException {
        try{
            iRolleDAO.deleteRolle(rolleId);
            iRolleDAO.end();
            return Response.ok("Rolle slettet").build();
        }
        catch (Exception e){
            iRolleDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
