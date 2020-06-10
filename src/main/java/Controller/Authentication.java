package Controller;

import Model.DAO.IUserDAO;
import Model.DAO.UserDAO;
import Model.DTO.User;
import Security.Security;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/authentication")
public class Authentication {
    private IUserDAO iUserDAO;

    public Authentication() throws SQLException, ClassNotFoundException {
        iUserDAO = new UserDAO();
    }

    @GET
    @Path("/test/{token}")
    public Response test(@PathParam("token") String token) {
        try{
            boolean isok = Security.verifyToken(token);
            return Response.ok().build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(String JSON_user) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(JSON_user, User.class);
            user = iUserDAO.Login(user);
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(Security.generateToken())).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/createuser")
    public Response createUser(String JSON_user) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(JSON_user, User.class);
            user = iUserDAO.CreateUser(user);
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(user)).build();
        }
        catch (Exception e){
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
