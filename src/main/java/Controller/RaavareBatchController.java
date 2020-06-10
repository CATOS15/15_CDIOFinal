package Controller;

import Security.Authenticated;

import javax.ws.rs.*;

@Path("/raavareBatch")
public class RaavareBatchController {
    @GET
    @Authenticated
    public String getRaavareBatch()
    {
        return "get RaavareBatch";
    }

    @POST
    @Authenticated
    public String setRaavareBatch(String a)
    {

        return "set RaavareBatch";
    }

    @PUT
    @Authenticated
    public String editRaavareBatch(String a)
    {

        return "edit RaavareBatch";
    }

    @DELETE
    @Authenticated
    public String deleteRaavareBatch()
    {

        return "delete RaavareBatch";
    }
}
