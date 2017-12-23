package model;

import java.io.Serializable;

public class TicketGame implements Serializable{

    private int ID; //tamashis

    private int vizedado; //vizedado

    private double currentkush; //misi kushi

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
