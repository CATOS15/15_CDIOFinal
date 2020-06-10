package Controller;


import Model.DTO.Raavare;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.ws.rs.*;

@Path("/Raavare")
public class RaavareController {
    @GET
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
    public String editRaavare(String a)
    {

        return "Ændret";
    }

    @DELETE
    public String deleteRaavare()
    {

        return "delete Raavare";
    }

}
