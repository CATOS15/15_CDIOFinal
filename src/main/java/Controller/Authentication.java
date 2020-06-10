package Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/login")
public class Authentication {
    @POST
    public String login(String JSON_data) {
        try{
            System.out.println("");
            return "";
        }
        catch (Exception e){
            return "Fejl i backend: " + e.toString();
        }
    }
}
