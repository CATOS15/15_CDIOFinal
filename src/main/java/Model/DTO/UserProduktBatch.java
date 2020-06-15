package Model.DTO;

import java.util.List;

public class UserProduktBatch {
    private int pbId;
    private List<Afvejning> afvejninger;

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public List<Afvejning> getAfvejninger() {
        return afvejninger;
    }

    public void setAfvejninger(List<Afvejning> afvejninger) {
        this.afvejninger = afvejninger;
    }
}