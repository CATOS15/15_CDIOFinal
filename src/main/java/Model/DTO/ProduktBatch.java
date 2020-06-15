package Model.DTO;

import java.util.List;

public class ProduktBatch {
    private int pbId;
    private int receptId;
    private String status;
    private String opretDato;
    private String slutDato;

    private List<UserProduktBatch> userProduktBatches;

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpretDato() {
        return opretDato;
    }

    public void setOpretDato(String opretDato) {
        this.opretDato = opretDato;
    }

    public String getSlutDato() {
        return slutDato;
    }

    public void setSlutDato(String slutDato) {
        this.slutDato = slutDato;
    }

    public List<UserProduktBatch> getUserProduktBatches() {
        return userProduktBatches;
    }

    public void setUserProduktBatches(List<UserProduktBatch> userProduktBatches) {
        this.userProduktBatches = userProduktBatches;
    }
}