package Controller;

import Model.DAO.IReceptDAO;
import Model.DAO.ReceptDAO;
import Model.DTO.RaavareBatch;
import Model.DTO.Recept;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Path;

@Authenticated
@Path("/recept")
public class ReceptController {
    private IReceptDAO iReceptDAO;
    private ObjectMapper mapper;

    public ReceptController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iReceptDAO = new ReceptDAO();
    }

    @GET
    public Response getRecepter(){
        try{
            List<Recept> recepter = iReceptDAO.getRecepter();
            iReceptDAO.end();
            return Response.ok(mapper.writeValueAsString(recepter)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
