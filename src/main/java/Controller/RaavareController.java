package Controller;


import Model.DAO.IRaavareDAO;
import Model.DAO.RaavareDAO;
import Model.DTO.Raavare;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

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
            Raavare[] raavarer = new Raavare[2];
            return Response.ok(raavarer).build();
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
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createRaavare(String JSON_raavare)
    {
        try{
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response updateRaavare(String JSON_raavare)
    {
        try{
            return null;
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
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
