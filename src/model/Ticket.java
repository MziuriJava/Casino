package model;

import java.util.List;

public class Ticket {
    private int ID ;

    private int gamesnumber; //tamashebis raodenoba

    private double bet; //dadebuli tanxa

    private double kush;

    private double win; //mogeba

    private List<TicketGame> games; //tamashebi romlebic shedis



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getGamesnumber() {
        return gamesnumber;
    }

    public void setGamesnumber(int gamesnumber) {
        this.gamesnumber = gamesnumber;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public double getKush() {
        return kush;
    }

    public void setKush(double kush) {
        this.kush = kush;
    }

    public double getWin() {return kush*bet; }

    public List<TicketGame> getGames() {
        return games;
    }

    public void setGames(List<TicketGame> games) {
        this.games = games;
    }
}
