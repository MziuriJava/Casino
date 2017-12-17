package server.dao;

import model.Game;
import server.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GamesDAOimpl implements GamesDAO{


    @Override
    public void addGames(Game game) throws Exception {
        Connection con =null;
        PreparedStatement pstmt =null;
        try {
            con = DatabaseConnector.getConnection();
            pstmt =con.prepareStatement("INSERT INTO GAMES (TEAM1,TEAM2,COEFFICIENT1,COEFFICIENT2,COEFFICIENTX) VALUES (?,?,?,?,?)");
            pstmt.setString(1,game.getFirstTeam());
            pstmt.setString(2,game.getSecondTeam());
            pstmt.setDouble(3,game.getCoef1());
            pstmt.setDouble(4,game.getCoef2());
            pstmt.setDouble(5,game.getCoefx());
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
        Connection con =null;
        PreparedStatement pstmt =null;
        try {
            con = DatabaseConnector.getConnection();
            pstmt =con.prepareStatement("UPDATE GAMES SET COEFFICIENT1=?,COEFFICIENT2=?,COEFFICIENTX=?  WHERE ID=?");
            pstmt.setDouble(1,coef1);
            pstmt.setDouble(2,coef2);
            pstmt.setDouble(3,coef3);
            pstmt.setInt(4,ID);
            pstmt.executeUpdate();

        }catch (Exception ex){
            System.out.println("cant update game");
        }
    }

    @Override
    public void deleteGame(int ID) throws Exception {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM GAMES WHERE ID=?");
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            throw new Exception("Couldn't delete game " + ex);
        }

    }

    @Override
    public List<Game> getGames() throws Exception {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM GAMES" );
            ResultSet rs = pstmt.executeQuery();
            List<Game> games = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String FirstTeam = rs.getString("TEAM1");
                String SecondTeam = rs.getString("TEAM2");
                Double Coef1=rs.getDouble("COEFFICIENT1");
                Double Coef2=rs.getDouble("COEFFICIENT2");
                Double Coef3=rs.getDouble("COEFFICIENTX");

                Game game = new Game();
                game.setID(id);
                game.setFirstTeam(FirstTeam);
                game.setSecondTeam(SecondTeam);
                game.setCoef1(Coef1);
                game.setCoef2(Coef2);
                game.setCoefx(Coef3);
                games.add(game);
                ////bolos 3 sveti
            }
            pstmt.close();
            con.close();
            return games;
        } catch (Exception ex) {
            throw new Exception("Can't get games " + ex);
        }

    }

    @Override
    public Game checkResult(int ID) throws Exception {
    try {
        Connection con = DatabaseConnector.getConnection();
        PreparedStatement pstmt =con.prepareStatement("SELECT * FROM GAMES Where ID=?");
        pstmt.setInt(1,ID);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int id = rs.getInt("ID");
        String FirstTeam = rs.getString("TEAM1");
        String SecondTeam = rs.getString("TEAM2");
        double result=rs.getDouble("RESULT");
        Game game = new Game();
        game.setID(id);
        game.setFirstTeam(FirstTeam);
        game.setSecondTeam(SecondTeam);
        game.setResult(result);
        con.close();
        pstmt.close();
        return game;

    }catch (Exception ex){
        ex.printStackTrace();
        throw new Exception("can't check result "+ ex);
    }
    }

    @Override
    public void setResult(int ID, int result) {
        try {
            Connection con = DatabaseConnector.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE GAMES SET RESULT=? WHERE ID=?");
            pstmt.setInt(1,result );
            pstmt.setInt(2,ID);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (Exception ex) {
            try {
                throw new Exception("Can't set result" + ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

