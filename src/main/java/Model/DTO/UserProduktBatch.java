package Model.DTO;

import java.util.List;

public class UserProduktBatch {
    private int pbId;
    private int userId;
    private int rbId;
    private int tara;
    private int netto;
    private int terminal;

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRbId() {
        return rbId;
    }

    public void setRbId(int rbId) {
        this.rbId = rbId;
    }

    public int getTara() {
        return tara;
    }

    public void setTara(int tara) {
        this.tara = tara;
    }

    public int getNetto() {
        return netto;
    }

    public void setNetto(int netto) {
        this.netto = netto;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }
}