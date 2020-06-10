package Controller;

import Model.DAO.IUserDAO;
import Model.DAO.UserDAO;
import Model.DTO.User;
import Model.Exception.DALException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/authentication")
public class Authentication {
    private IUserDAO iUserDAO;

    public Authentication() throws SQLException, ClassNotFoundException {
        iUserDAO = new UserDAO();
    }

    @POST
    @Path("/login")
    public Response login(String JSON_user) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(JSON_user, User.class);
            user = iUserDAO.Login(user);
            iUserDAO.end();
            return Response.ok(mapper.writeValueAsString(user)).build();
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
