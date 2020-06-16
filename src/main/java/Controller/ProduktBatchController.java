package Controller;

import Model.DAO.IProduktBatchDAO;
import Model.DAO.ProduktBatchDAO;
import Model.DTO.ProduktBatch;
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

@Path("/produktbatch")
public class ProduktBatchController {
    private IProduktBatchDAO iProduktBatchDAO;
    private ObjectMapper mapper;

    public ProduktBatchController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iProduktBatchDAO = new ProduktBatchDAO();
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    public Response getProduktBatches() {
        try{
            List<ProduktBatch> produktBatches = iProduktBatchDAO.getProduktBatches();
            iProduktBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(produktBatches)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    @Path("{pbId}")
    public Response getProduktBatch(@PathParam("pbId") String pbId) {
        try{
            ProduktBatch produktBatch = iProduktBatchDAO.getProduktBatch(pbId);
            iProduktBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(produktBatch)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Authenticated(RolleEnum.PRODUKTIONSLEDER)
    public Response createProduktBatch(String JSON_produktbatch) {
        try{
            ProduktBatch produktBatch = mapper.readValue(JSON_produktbatch, ProduktBatch.class);
            ProduktBatch newProduktBatch = iProduktBatchDAO.createProduktBatch(produktBatch);
            iProduktBatchDAO.end();
            return Response.ok(mapper.writeValueAsString(newProduktBatch)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
