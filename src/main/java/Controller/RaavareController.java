package Controller;


import Model.DAO.IRaavareDAO;
import Model.DAO.RaavareDAO;
import Model.DTO.Raavare;
import Model.Exception.DALException;
import Security.Authenticated;
import Security.RolleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/raavare")
public class RaavareController {

    private IRaavareDAO iRaavareDAO;
    private ObjectMapper mapper;

    public RaavareController() throws SQLException, ClassNotFoundException {
        iRaavareDAO = new RaavareDAO();
        mapper = new ObjectMapper();
    }


    @GET
    @Authenticated(RolleEnum.NULL)
    public Response getRaavarer() throws DALException {
        try{
            List<Raavare> raavarer = iRaavareDAO.getRaavarer();
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavarer)).build();
        }
        catch (Exception e){
            iRaavareDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    @Path("{raavareId}")
    public Response getRaavare(@PathParam("raavareId") String raavareId) throws DALException {
        try{
            Raavare raavare = iRaavareDAO.getRaavare(raavareId);
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavare)).build();
        }
        catch (Exception e){
            iRaavareDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Authenticated(RolleEnum.FARMACEUT)
    public Response createRaavare(String JSON_raavare) throws DALException {
        try{
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            iRaavareDAO.createRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok("Råvare oprettet").build();
        }
        catch (Exception e){
            iRaavareDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Authenticated(RolleEnum.FARMACEUT)
    public Response updateRaavare(String JSON_raavare) throws DALException {
        try{
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            iRaavareDAO.updateRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok("Råvare opdateret").build();
        }
        catch (Exception e){
            iRaavareDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
