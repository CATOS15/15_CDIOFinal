package Controller;

import Model.DAO.IReceptDAO;
import Model.DAO.ReceptDAO;
import Model.DTO.RaavareBatch;
import Model.DTO.Recept;
import Model.Exception.DALException;
import Security.Authenticated;
import Security.RolleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Path;

@Path("/recept")
public class ReceptController {
    private IReceptDAO iReceptDAO;
    private ObjectMapper mapper;

    public ReceptController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iReceptDAO = new ReceptDAO();
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    public Response getRecepter() throws DALException {
        try{
            List<Recept> recepter = iReceptDAO.getRecepter();
            iReceptDAO.end();
            return Response.ok(mapper.writeValueAsString(recepter)).build();
        }
        catch (Exception e){
            iReceptDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Authenticated(RolleEnum.FARMACEUT)
    public Response createRecept(String JSON_recepter) throws DALException {
        try{
            Recept recept = mapper.readValue(JSON_recepter, Recept.class);
            iReceptDAO.createRecept(recept);
            iReceptDAO.end();
            return Response.ok("Recept oprettet").build();
        }
        catch (Exception e){
            iReceptDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}
