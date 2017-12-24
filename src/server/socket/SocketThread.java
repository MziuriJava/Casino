package server.socket;

import model.*;
import server.dao.GamesDAO;
import server.dao.GamesDAOimpl;
import server.dao.TicketDAO;
import server.dao.TicketDAOimpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketThread implements Runnable {

    private static final GamesDAO gameDAO = new GamesDAOimpl();

    private Socket socket;

    private static final TicketDAO ticketDAO = new TicketDAOimpl();

    public SocketThread(Socket socket){
        this.socket = socket;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run() {
        try{
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Command command = (Command) in.readObject();
                switch (command) {
                    case get_games:
                        try {
                            List<Game> games = gameDAO.getGames();
                            out.writeObject(CommandResult.SUCCESSFUL);
                            out.writeObject(games);
                        } catch (Exception ex) {
                            out.writeObject(CommandResult.FAILURE);
                            ex.printStackTrace();
                        }
                        break;
                    case create_ticket:
                        try {
                            Ticket ticket = (Ticket) in.readObject();
                            double srulikush = 1;
                            List<TicketGame> ticketGame = ticket.getGames();
                            for (int i = 0; i < ticketGame.size(); i++) {
                                double kush = 0;
                                Game game = gameDAO.checkGame(ticketGame.get(i).getID());
                                if (ticketGame.get(i).getVizedado() == 1) kush = game.getCoef1();
                                if (ticketGame.get(i).getVizedado() == 2) kush = game.getCoef2();
                                if (ticketGame.get(i).getVizedado() == 3) kush = game.getCoefx();
                                ticket.getGames().get(i).setCurrentkush(kush);
                                srulikush *= kush;
                            }
                            ticket.setKush(srulikush);
                            ticketDAO.createTicket(ticket);
                            out.writeObject(CommandResult.SUCCESSFUL);

                        } catch (Exception ex) {
                            out.writeObject(CommandResult.FAILURE);
                            ex.printStackTrace();
                        }

                        break;


                    case check_result:
                        int ID = (int) in.readObject();
                        try {
                            List<TicketGame> ticketGames = ticketDAO.checkTicket(ID);
                            int ans = 1;
                            for (int i = 0; i < ticketGames.size(); i++) {
                                Game game = gameDAO.checkGame(ticketGames.get(i).getID());
                                System.out.println(game.getResult() + " " + ticketGames.get(i).getVizedado());
                                if (game.getResult() != ticketGames.get(i).getVizedado()) {
                                    ans = 0;
                                    break;
                                }
                            }
                            out.writeObject(CommandResult.SUCCESSFUL);
                            out.writeObject(ans);

                        } catch (Exception ex) {
                            out.writeObject(CommandResult.FAILURE);
                            ex.printStackTrace();

                        }
                        break;
                    case Exit:
                        in.close();
                        out.close();
                        socket.close();
                        return;
                }
            }
        } catch(Exception ex ){
            ex.printStackTrace();
        }
    }
}
