package Controller;

import Security.Authenticated;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/produktionsbatch")
public class ProduktionsbatchController {
    @GET
    @Authenticated
    public String getProduktionsbatch()
    {
        return "get Produktionsbatch";
    }
    @POST
    @Authenticated
    public String setProduktionsbatch()
    {
        return "set Produktionsbatch";
    }
    @DELETE
    @Authenticated
    public String deleteProduktionsbatch()
    {

        return "delete Produktionsbatch";
    }
}
