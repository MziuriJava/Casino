package server.socket;

import model.Command;
import model.CommandResult;
import model.Game;
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
    public void run() {
        try{
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Command command = (Command)in.readObject();
            switch (command){
                case get_games:
                    try {
                        List<Game> games = new ArrayList<>();
                        games = gameDAO.getGames();
                        out.writeObject(CommandResult.SUCCESSFUL);
                        out.writeObject(games);
                    } catch (Exception ex){
                        out.writeObject(CommandResult.FAILURE);
                        ex.printStackTrace();
                    }
                    break;

                case create_ticket:
                    break;

                case check_result:
                    break;
                case Exit:
                    in.close();
                    out.close();
                    socket.close();
                    return ;
            }
        } catch(Exception ex ){
            ex.printStackTrace();
        }

    }
}
