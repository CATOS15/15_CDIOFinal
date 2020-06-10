package Model.DTO;

public class Raavare {
    private int raavareId;
    private String raavareNavn;

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public void setRaavareNavn(String raavareNavn) {this.raavareNavn = raavareNavn; }

    public int getRaavareId() {return raavareId;}

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

}
