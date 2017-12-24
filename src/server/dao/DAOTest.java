package server.dao;

import model.Game;
import model.Ticket;
import model.TicketGame;

import java.util.ArrayList;
import java.util.List;

public class DAOTest {
    private static final GamesDAO gameDAO = new GamesDAOimpl();

    private static final TicketDAO ticketDAO = new TicketDAOimpl();

    public static void main(String[] args) {

    //    testaddGame();
     //   testcreateticket();
//        testupdategame();
//        testgetgames();
//        testdeletegame();
//        testsetresult();
        checktickresult();

    }

    private static void testaddGame() {
        Game game = new Game();
        game.setFirstTeam("UNITED");
        game.setSecondTeam("CITY");
        game.setCoef1(1.5);
        game.setCoef2(2.7);
        game.setCoefx(5.0);
        try {
            gameDAO.addGames(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void testcreateticket() {
        Ticket ticket= new Ticket();
        ticket.setGamesnumber(3);
        ticket.setBet(100);
        List <TicketGame> ticketGames = new ArrayList<>();
        TicketGame tg1 = new TicketGame();
        tg1.setID(1);
        tg1.setVizedado(2);
        tg1.setCurrentkush(2.7);
        ticketGames.add(tg1);
        TicketGame tg2 = new TicketGame();
        tg2.setID(2);
        tg2.setVizedado(2);
        tg2.setCurrentkush(2.7);
        ticketGames.add(tg2);
        TicketGame tg3 = new TicketGame();
        tg3.setID(3);
        tg3.setVizedado(1);
        tg3.setCurrentkush(1.5);
        ticketGames.add(tg3);
        ticket.setKush(ticketGames.get(1).getCurrentkush()*ticketGames.get(0).getCurrentkush()*ticketGames.get(2).getCurrentkush());
        ticket.setGames(ticketGames);
        try {
            ticketDAO.createTicket(ticket);
        } catch (Exception ex){
            System.out.println("cant add");
        }
    }

    private static void testupdategame(){
        int ID=1;
        double coef1=2.3;
        double coef2=3.3;
        double coef3=4.3;

        try {
            gameDAO.updateGame(ID,coef1,coef2,coef3);
        }catch (Exception ex){
            System.out.println("cant update");
        }


    }

    private static void testgetgames(){
        try {
            List <Game> games=gameDAO.getGames();
            for (int i=0;i<games.size();i++){
                System.out.println(games.get(i).toString());
            }
        }catch (Exception ex){
            System.out.println("cant get games");
            System.out.println(ex.getMessage());
        }

    }

    private static void testdeletegame(){
        int ID=2;
        try {
            gameDAO.deleteGame(ID);
        }catch (Exception ex){
            System.out.println("cant delete game");
            System.out.println(ex.getMessage());
        }

    }

    private static void testsetresult(){
        int ID=3;
        int result=2;

        try {
            gameDAO.setResult(ID,result);
        }catch (Exception ex){
            System.out.println("cant set result"+ex);
        }

    }

    @SuppressWarnings("Duplicates")
    private static void checktickresult(){
        try {
            List<TicketGame> ticketGames = new ArrayList<>();
            ticketGames =ticketDAO.checkTicket(1);
            double kush = 1;
            int ans= 1;
            for(int i=0;i<ticketGames.size();i++){
                Game game =gameDAO.checkGame(ticketGames.get(i).getID());
                System.out.println(game.getResult()+" "+ticketGames.get(i).getVizedado());
                if(game.getResult()!=ticketGames.get(i).getVizedado()){
                    ans=0;
                    break;
                }

            }
            if(ans==0) {
                System.out.println("ar dajda");
            } else System.out.println("dajda******");

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("testdao cant check ticket result");
        }

    }

}

