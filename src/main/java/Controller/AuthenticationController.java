package Controller;

import Model.DAO.IUserDAO;
import Model.DAO.UserDAO;
import Model.DTO.User;
import Model.Exception.DALException;
import Security.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/authentication")
public class AuthenticationController {
    private IUserDAO iUserDAO;
    private ObjectMapper mapper;

    public AuthenticationController() throws SQLException, ClassNotFoundException {
        mapper = new ObjectMapper();
        iUserDAO = new UserDAO();
    }

    @GET
    @Authenticated(RolleEnum.NULL)
    @Path("/getLogin")
    public Response getLogin(@HeaderParam(HttpHeaders.AUTHORIZATION) String token) throws DALException {
        try{
            token = token.substring("Bearer".length()).trim();
            String username = Security.verifyToken(token, null);
            User user = iUserDAO.getUserByName(username);
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(user)).build();
        }
        catch (Exception e){
            iUserDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(String JSON_user) throws DALException {
        try{
            User user = mapper.readValue(JSON_user, User.class);
            iUserDAO.login(user);
            iUserDAO.end();
            String token = Security.generateToken(user.getUserName());
            return Response.ok(mapper.writeValueAsString(token)).build();
        }
        catch (Exception e){
            iUserDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/createuser")
    @Authenticated(RolleEnum.ADMINISTRATOR)
    public Response createUser(String JSON_user) throws DALException {
        try{
            User user = mapper.readValue(JSON_user, User.class);
            user = iUserDAO.createUser(user);
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(user)).build();
        }
        catch (Exception e){
            iUserDAO.end();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
