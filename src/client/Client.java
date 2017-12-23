package client;

import model.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public void start(){
        try {
            Socket socket = new Socket("localhost", 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("Choose command");
                Command commands[] =Command.values();
                for(int i=0;i< commands.length;i++){
                    System.out.println(i+1+". "+commands[i].name());
                }
                int num = scanner.nextInt();
                Command command = commands[num-1];
                switch (command) {
                    case get_games:{
                        out.writeObject(command);
                        CommandResult commandResult =(CommandResult)in.readObject();
                        System.out.println(commandResult.name());
                        if(commandResult==CommandResult.FAILURE){
                            break;
                        }
                        List<Game> games=(List<Game>)in.readObject();
                        for (int i=0;i<games.size();i++){
                            System.out.println(games.get(i).toString());
                        }
                        break;
                    }

                    case create_ticket:{
                        out.writeObject(command);
                        CommandResult commandResult =(CommandResult)in.readObject();
                        System.out.println(commandResult.name());
                        if(commandResult==CommandResult.FAILURE){
                            break;
                        }
                        double kushi= 1;
                        Ticket ticket= new Ticket();
                        System.out.println("tamashebis raodenoba?");
                        ticket.setGamesnumber(scanner.nextInt());
                        System.out.println("beti?");
                        ticket.setBet(scanner.nextDouble());
                        List <TicketGame> ticketGames = new ArrayList<>();
                        for (int i=0;i<ticket.getGamesnumber();i++){
                            TicketGame ticketGame = new TicketGame();
                            System.out.println("game id?");
                            ticketGame.setID(scanner.nextInt());
                            System.out.println("visze deb?");
                            ticketGame.setVizedado(scanner.nextInt());
                            System.out.println("kushi?");
                            ticketGame.setCurrentkush(scanner.nextDouble());
                            ticketGames.add(ticketGame);
                            kushi=kushi*ticketGame.getCurrentkush();
                        }
                        ticket.setKush(kushi);
                        ticket.setGames(ticketGames);
                        out.writeObject(ticket);

                        break;
                    }
                    case check_result:{
                        break;
                    }
                    case Exit:
                        out.writeObject(command);
                        out.close();
                        in.close();
                        socket.close();
                        return ;
                }
            }
        }catch (Exception ex ){
            System.out.println("can't connect to server");
        }
    }
}
