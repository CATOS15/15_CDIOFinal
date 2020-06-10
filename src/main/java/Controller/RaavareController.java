package Controller;


import Model.DTO.Raavare;
import Security.Authenticated;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;

@Path("/Raavare")
public class RaavareController {
    @GET
    @Authenticated
    public String getRaavare(String JSON_raavare)
    {

        try{
            ObjectMapper mapper = new ObjectMapper();
            Raavare user = mapper.readValue(JSON_raavare, Raavare.class);
            return mapper.writeValueAsString(user);
        }
        catch (Exception e){
            return "Fejl i backend: " + e.toString();
        }
    }

    @POST
    @Authenticated
    public String setRaavare(String JSON_raavare)
    {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Raavare user = mapper.readValue(JSON_raavare, Raavare.class);
            return mapper.writeValueAsString(user);
        }
        catch (Exception e){
            return "Fejl i backend: " + e.toString();
        }
    }

    @PUT
    @Authenticated
    public String editRaavare(String a)
    {

        return "Ændret";
    }

    @DELETE
    @Authenticated
    public String deleteRaavare()
    {

        return "delete Raavare";
    }

}
