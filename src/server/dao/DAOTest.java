package server.dao;

import model.Game;

public class DAOTest {
    private static final GamesDAO gameDAO = new GamesDAOimpl();
    public static void main(String[] args) {
        testaddGame();
        }
        private static void testaddGame(){
        Game game =new Game();
        game.setID(1);
        game.setFirstTeam("FCB");
        game.setSecondTeam("PSG");
        game.setCoef1(1.5);
        game.setCoef2(2.4);
        game.setCoefx(3.0);
        try{
            gameDAO.addGames(game);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        }



}
