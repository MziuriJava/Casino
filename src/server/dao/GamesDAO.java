package server.dao;

import model.Game;

public interface GamesDAO {

    void addGames(Game game) throws Exception;

    void updateGame(int ID, double coef1, double coef2, double coef3) throws Exception;

    void deleteGame(int ID) throws Exception;

    void getGames () throws Exception;

    void checkResult (int ID);

    void setResult(int ID, int result);
}
