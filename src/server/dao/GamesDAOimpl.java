package server.dao;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Game;
import server.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GamesDAOimpl implements GamesDAO{


    @Override
    public void addGames(Game game) throws Exception {
        Connection con =null;
        PreparedStatement pstmt =null;
        try {
            con = DatabaseConnector.getConnection();
            pstmt =con.prepareStatement("INSERT INTO GAMES (ID,TEAM1,TEAM2,COEFFICIENT1,COEFFICIENT2,COEFFICIENTX) VALUES (?,?,?,?,?,?)");
            pstmt.setInt(1,game.getID());
            pstmt.setString(2,game.getFirstTeam());
            pstmt.setString(3,game.getSecondTeam());
            pstmt.setDouble(4,game.getCoef1());
            pstmt.setDouble(5,game.getCoef2());
            pstmt.setDouble(6,game.getCoefx());
            pstmt.executeUpdate();
        } catch(Exception ex) {
            System.out.println("can't add game");
            ex.printStackTrace();
        } finally {
            try{
            pstmt.close();
            con.close();
        } catch (Exception ignored) {}
    }}

    @Override
    public void updateGame(int ID, double coef1, double coef2, double coef3) throws Exception {

    }

    @Override
    public void deleteGame(int ID) throws Exception {

    }

    @Override
    public void getGames() throws Exception {

    }

    @Override
    public void checkResult(int ID) {

    }

    @Override
    public void setResult(int ID, int result) {

    }
}
