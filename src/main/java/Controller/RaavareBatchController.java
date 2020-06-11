package Controller;

import Model.DAO.IUserDAO;
import Model.DAO.UserDAO;
import Model.DTO.RaavareBatch;
import Model.DTO.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class RaavareBatchController {
    private IUserDAO iUserDAO;
    private ObjectMapper mapper;

    public RaavareBatchController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iUserDAO = new UserDAO();
    }

    @GET
    public Response getRaavareBatches() {
        try{
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{rbId}")
    public Response getRaavareBatch(@PathParam("rbId") String rbId) {
        try{
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createRaavareBatch(String JSON_user) {
        try{
            return null;
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


}
