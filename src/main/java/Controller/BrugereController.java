package Controller;

import Model.DAO.Database;
import Security.Authenticated;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/brugere")
public class BrugereController {
    @GET
    @Authenticated
    public String getBrugere() {
        return "blah";
    }
}
