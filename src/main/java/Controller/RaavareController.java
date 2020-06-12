package Controller;


import Model.DAO.IRaavareDAO;
import Model.DAO.RaavareDAO;
import Model.DTO.Raavare;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Authenticated
@Path("/raavare")
public class RaavareController {

    private IRaavareDAO iRaavareDAO;
    private ObjectMapper mapper;

    public RaavareController() throws SQLException, ClassNotFoundException {
        iRaavareDAO = new RaavareDAO();
        mapper = new ObjectMapper();
    }


    @GET
    public Response getRaavarer()
    {
        try{
            List<Raavare> raavarer = iRaavareDAO.getRaavarer();
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavarer)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{raavareId}")
    public Response getRaavare(@PathParam("raavareId") String raavareId)
    {
        try{
            Raavare raavare = iRaavareDAO.getRaavare(raavareId);
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavare)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createRaavare(String JSON_raavare)
    {
        try{
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            iRaavareDAO.createRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok("Råvare oprettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response updateRaavare(String JSON_raavare)
    {
        try{
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            iRaavareDAO.updateRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok("Råvare opdateret").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{raavareId}")
    public Response deleteRaavare(@PathParam("raavareId") String raavareId)
    {
        try{
            iRaavareDAO.deleteRaavare(raavareId);
            iRaavareDAO.end();
            return Response.ok("Råvare slettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
