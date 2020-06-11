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
@Path("/Raavare")
public class RaavareController {

    private IRaavareDAO iRaavareDAO;

    public RaavareController() throws SQLException, ClassNotFoundException {
        iRaavareDAO = new RaavareDAO();
    }
    @GET
    public Response getRaavare(String JSON_raavare)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            raavare = iRaavareDAO.SelectRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavare)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response setRaavare(String JSON_raavare)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            raavare = iRaavareDAO.CreateRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavare)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response editRaavare(String JSON_raavare)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            raavare = iRaavareDAO.UpdateRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavare)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    public Response deleteRaavare(String JSON_raavare)
    {

        try{
            ObjectMapper mapper = new ObjectMapper();
            Raavare raavare = mapper.readValue(JSON_raavare, Raavare.class);
            raavare = iRaavareDAO.DeleteRaavare(raavare);
            iRaavareDAO.end();
            return Response.ok(mapper.writeValueAsString(raavare)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
