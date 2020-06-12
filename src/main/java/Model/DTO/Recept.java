package Model.DTO;

public class Recept {

    private int receptId;
    private String receptNavn;
    private int raavareId;
    private double nonNetto;
    private double tolerance;

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

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public double getNonNetto() {
        return nonNetto;
    }

    public void setNonNetto(double nonNetto) {
        this.nonNetto = nonNetto;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }



}
