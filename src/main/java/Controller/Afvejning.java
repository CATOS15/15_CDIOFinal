package Controller;

import Model.Database;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/afvejning")
public class Afvejning {
    @GET
    public String getAfvejning()
    {

        return "get Afvejning";
    }
    @POST
    public String setAfvejning()
    {

        return "set Afvejning";
    }
}
