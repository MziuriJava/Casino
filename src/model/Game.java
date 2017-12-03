package model;

import java.io.Serializable;

public class Game implements Serializable {
    private int ID;

    private String FirstTeam;

    private String SecondTeam;

    private double coef1;

    private double coef2;

    private double coefx;

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

    public double getCoef1() {
        return coef1;
    }

    public void setCoef1(double coef1) {
        this.coef1 = coef1;
    }

    public double getCoef2() {
        return coef2;
    }

    public void setCoef2(double coef2) {
        this.coef2 = coef2;
    }

    public double getCoefx() {
        return coefx;
    }

    public void setCoefx(double coefx) {
        this.coefx = coefx;
    }

    @Override
    public String toString() {
        return "Game{" +
                "ID=" + ID +
                ", FirstTeam='" + FirstTeam + '\'' +
                ", SecondTeam='" + SecondTeam + '\'' +
                ", coef1=" + coef1 +
                ", coef2=" + coef2 +
                ", coefx=" + coefx +
                '}';
    }
}
