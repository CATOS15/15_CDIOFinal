package Controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/recept")
public class ReceptController {

    @GET
    public String getRecept()
    {
        return "get recept";
    }
    @POST
    public String setRecept()
    {

        return "set recept";
    }
    @DELETE
    public String deleteRecept()
    {

        return "delete Recept";
    }

}
