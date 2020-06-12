package Model.DTO;

import java.util.List;

public class Recept {

    private int receptId;
    private String receptNavn;
    private List<ReceptRaavare> receptRaavarer;

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public String getReceptNavn() {
        return receptNavn;
    }

    public void setReceptNavn(String receptNavn) {
        this.receptNavn = receptNavn;
    }

    public List<ReceptRaavare> getReceptRaavarer() {
        return receptRaavarer;
    }

    public void setReceptRaavarer(List<ReceptRaavare> receptRaavarer) {
        this.receptRaavarer = receptRaavarer;
    }
}
