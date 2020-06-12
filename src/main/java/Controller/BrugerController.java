package Controller;

import Model.DAO.IUserDAO;
import Model.DAO.UserDAO;
import Model.DTO.User;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Authenticated
@Path("/bruger")
public class BrugerController {
    private IUserDAO iUserDAO;
    private ObjectMapper mapper;

    public BrugerController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iUserDAO = new UserDAO();
    }

    @GET
    public Response getBrugerer() {
        try{
            List<User> users = iUserDAO.getUsers();
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(users)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{brugerId}")
    public Response getBruger(@PathParam("brugerId") String brugerId) {
        try{
            User user = iUserDAO.getUser(brugerId);
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(user)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createBruger(String JSON_user) {
        try{
            User user = mapper.readValue(JSON_user, User.class);
            iUserDAO.createUser(user);
            iUserDAO.end();
            return Response.ok("Bruger oprettet").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response updateBruger(String JSON_user) {
        try{
            User user = mapper.readValue(JSON_user, User.class);
            iUserDAO.updateUser(user);
            iUserDAO.end();
            return Response.ok("Bruger opdateret").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{brugerId}")
    public Response deleteBruger(@PathParam("brugerId") String brugerId) {
        try{
            iUserDAO.deleteUser(brugerId);
            iUserDAO.end();
            return Response.ok("Bruger deaktiveret").build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
