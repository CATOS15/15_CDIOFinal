package Model.DTO;

import java.util.List;

public class User {
    private int userId;
    private String userName;
    private String userIni;
    private String CPRnummer;
    private List<Rolle> roller;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIni() {
        return userIni;
    }

    public void setUserIni(String userIni) {
        this.userIni = userIni;
    }

    public String getCPRnummer() {
        return CPRnummer;
    }

    public void setCPRnummer(String CPRnummer) {
        this.CPRnummer = CPRnummer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public List<Rolle> getRoller() {
        return roller;
    }

    public void setRoller(List<Rolle> roller) {
        this.roller = roller;
    }
}