package Controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/recepter")
public class Recepter {

    @GET
    public String getRecepter()
    {

        return "get recepter";
    }
    @POST
    public String setRecepter()
    {

        return "set recepter";
    }

}
