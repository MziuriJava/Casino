package model;

public class TicketGame {

    private int ID;

    private String FirstTeam;

    private String SecondTeam;

    private int vizedado;

    private double currentkush;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstTeam() {
        return FirstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        FirstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return SecondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        SecondTeam = secondTeam;
    }

    public int getVizedado() {
        return vizedado;
    }

    public void setVizedado(int vizedado) {
        this.vizedado = vizedado;
    }

    public double getCurrentkush() {
        return currentkush;
    }

    public void setCurrentkush(double currentkush) {
        this.currentkush = currentkush;
    }
}
