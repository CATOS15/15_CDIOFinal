package Controller;

import Model.DAO.Database;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/brugere")
public class BrugereController {
    @GET
    public String getBrugere() {
        try{
            Database db = new Database();
            db.connect();
            db.disconnect();
            return "Der er hul igennem til databasen";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "Der er IKKE hul igennem til databasen";
    }
}
