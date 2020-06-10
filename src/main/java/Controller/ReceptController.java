package Controller;

import Model.DAO.IReceptDAO;
import Model.DAO.ReceptDAO;
import Model.DTO.Recept;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.sql.SQLException;

@Path("/recept")
public class ReceptController {

    private IReceptDAO iReceptDAO;

    public ReceptController() throws SQLException, ClassNotFoundException {
        iReceptDAO = new ReceptDAO();
    }
    @GET
    public Response getRecept(String JSON_recept)
    {

        try{
            ObjectMapper mapper = new ObjectMapper();
            Recept recept = mapper.readValue(JSON_recept, Recept.class);
            recept = iReceptDAO.SelectRecept(recept);
            iReceptDAO.end();
            return Response.ok(mapper.writeValueAsString(recept)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @POST
    public Response setRecept(String JSON_recept)
    {

        try{
            ObjectMapper mapper = new ObjectMapper();
            Recept recept = mapper.readValue(JSON_recept, Recept.class);
            recept = iReceptDAO.CreateRecept(recept);
            iReceptDAO.end();
            return Response.ok(mapper.writeValueAsString(recept)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
    @DELETE
    public Response deleteRecept(String JSON_recept)
    {

        try{
            ObjectMapper mapper = new ObjectMapper();
            Recept recept = mapper.readValue(JSON_recept, Recept.class);
            recept = iReceptDAO.DeleteRecept(recept);
            iReceptDAO.end();
            return Response.ok(mapper.writeValueAsString(recept)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
