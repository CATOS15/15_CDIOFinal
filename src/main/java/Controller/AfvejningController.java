package Controller;

import Model.DTO.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/afvejning")
public class AfvejningController {
    @GET
    public String getAfvejning()
    {

        return "get Afvejning";
    }
    @POST
    public String setAfvejning(String JSON_afvejning)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
//            User user = mapper.readValue(JSON_afvejning, Afvejning.class);
//            return mapper.writeValueAsString(user);
        }
        catch (Exception e){
//            return "Fejl i backend: " + e.toString();
        }
        return "test";
    }

    @DELETE
    public String deleteAfvejning()
    {

        return "delete Afvejning";
    }
}
