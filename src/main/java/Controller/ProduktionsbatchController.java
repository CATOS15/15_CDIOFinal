package Controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/produktionsbatch")
public class ProduktionsbatchController {
    @GET
    public String getProduktionsbatch()
    {
        return "get Produktionsbatch";
    }
    @POST
    public String setProduktionsbatch()
    {
        return "set Produktionsbatch";
    }
    @DELETE
    public String deleteProduktionsbatch()
    {

        return "delete Produktionsbatch";
    }
}
