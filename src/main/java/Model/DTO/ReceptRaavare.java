package Model.DTO;

public class ReceptRaavare {
    private int receptId;
    private int raavareId;
    private int nonNetto;
    private int tolerance;

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public int getNonNetto() {
        return nonNetto;
    }

    public void setNonNetto(int nonNetto) {
        this.nonNetto = nonNetto;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }
}