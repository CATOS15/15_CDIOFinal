package Model.DTO;

import java.util.List;

public class ProduktVisning {
    public static class ProduktVisningItem{
        private int raavareId;
        private String raavareNavn;
        private double maengde;
        private double tolerance;
        private double tara;
        private double netto;
        private int rbId;
        private String userIni;
        private int terminal;

        public int getRaavareId() {
            return raavareId;
        }

        public void setRaavareId(int raavareId) {
            this.raavareId = raavareId;
        }

        public String getRaavareNavn() {
            return raavareNavn;
        }

        public void setRaavareNavn(String raavareNavn) {
            this.raavareNavn = raavareNavn;
        }

        public double getMaengde() {
            return maengde;
        }

        public void setMaengde(double maengde) {
            this.maengde = maengde;
        }

        public double getTolerance() {
            return tolerance;
        }

        public void setTolerance(double tolerance) {
            this.tolerance = tolerance;
        }

        public double getTara() {
            return tara;
        }

        public void setTara(double tara) {
            this.tara = tara;
        }

        public double getNetto() {
            return netto;
        }

        public void setNetto(double netto) {
            this.netto = netto;
        }

        public int getRbId() {
            return rbId;
        }

        public void setRbId(int rbId) {
            this.rbId = rbId;
        }

        public String getUserIni() {
            return userIni;
        }

        public void setUserIni(String userIni) {
            this.userIni = userIni;
        }

        public int getTerminal() {
            return terminal;
        }

        public void setTerminal(int terminal) {
            this.terminal = terminal;
        }
    }

    private int pbId;
    private int receptId;
    private double totalTara;
    private double totalNetto;
    private String status;
    private String opretDato;
    private String slutDato;
    private List<ProduktVisningItem> produktVisningItems;

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

    public double getTotalTara() {
        return totalTara;
    }

    public void setTotalTara(double totalTara) {
        this.totalTara = totalTara;
    }

    public double getTotalNetto() {
        return totalNetto;
    }

    public void setTotalNetto(double totalNetto) {
        this.totalNetto = totalNetto;
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

    public List<ProduktVisningItem> getProduktVisningItems() {
        return produktVisningItems;
    }

    public void setProduktVisningItems(List<ProduktVisningItem> produktVisningItems) {
        this.produktVisningItems = produktVisningItems;
    }
}
