package Controller;


import jdk.nashorn.internal.objects.annotations.Setter;

import javax.ws.rs.*;

@Path("/Raavare")
public class RaavareController {
    @GET
    public String getRaavare()
    {
        return "get Raavare";
    }

    @POST
    public String setRaavare(String a)
    {

        return "set Raavare";
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
