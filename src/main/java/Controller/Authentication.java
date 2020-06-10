package Controller;

import Model.DTO.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/login")
public class Authentication {
    @POST
    public String login(String JSON_user) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(JSON_user, User.class);
            return mapper.writeValueAsString(user);
        }
        catch (Exception e){
            return "Fejl i backend: " + e.toString();
        }
    }
}
