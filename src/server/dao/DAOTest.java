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

        //testaddGame();
        testcreateticket();
    }

    private static void testaddGame() {
        Game game = new Game();
        game.setFirstTeam("aas");
        game.setSecondTeam("asxs");
        game.setCoef1(1.2);
        game.setCoef2(3.2);
        game.setCoefx(2.0);
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
        tg1.setCurrentkush(2.0);
        ticketGames.add(tg1);
        TicketGame tg2 = new TicketGame();
        tg2.setID(2);
        tg2.setVizedado(2);
        tg2.setCurrentkush(2.0);
        ticketGames.add(tg2);
        TicketGame tg3 = new TicketGame();
        tg3.setID(3);
        tg3.setVizedado(2);
        tg3.setCurrentkush(2.0);
        ticketGames.add(tg3);
        ticket.setKush(ticketGames.get(1).getCurrentkush()*ticketGames.get(2).getCurrentkush()*ticketGames.get(0).getCurrentkush());
        ticket.setGames(ticketGames);
        try {
            ticketDAO.createTicket(ticket);
        } catch (Exception ex){
            System.out.println("cant add");
        }
    }


}
