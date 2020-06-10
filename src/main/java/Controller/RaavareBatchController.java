package Controller;

import javax.ws.rs.*;

@Path("/raavareBatch")
public class RaavareBatchController {
    @GET
    public String getRaavareBatch()
    {
        return "get RaavareBatch";
    }

    @POST
    public String setRaavareBatch(String a)
    {

        return "set RaavareBatch";
    }

    @PUT
    public String editRaavareBatch(String a)
    {

        return "edit RaavareBatch";
    }

    @DELETE
    public String deleteRaavareBatch()
    {

        return "delete RaavareBatch";
    }
}
