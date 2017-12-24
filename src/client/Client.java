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
            while (true) {
                System.out.println("Choose command");
                Command commands[] =Command.values();
                for(int i=0;i< commands.length;i++){
                    System.out.println(i+1+". "+commands[i].name());
                }
                int num = scanner.nextInt();
                Command command = commands[num-1];
                switch (command) {
                    case GET_GAMES: {
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


                        Ticket ticket= new Ticket();
                        System.out.println("tamashebis raodenoba?");
                        ticket.setGamesnumber(scanner.nextInt());

                        List <TicketGame> ticketGames = new ArrayList<>();
                        for (int i=0;i<ticket.getGamesnumber();i++){
                            TicketGame ticketGame = new TicketGame();
                            System.out.println("Tamashis ID?");
                            ticketGame.setID(scanner.nextInt());
                            System.out.println("Vize deb? pirveli gundi-1 : fre-3 : meore gundi 2");
                            ticketGame.setVizedado(scanner.nextInt());
                            ticketGames.add(ticketGame);
                        }
                        System.out.println("Ramdens deb?");
                        ticket.setBet(scanner.nextDouble());
                        ticket.setGames(ticketGames);

                        out.writeObject(command);
                        out.writeObject(ticket);
                        CommandResult commandResult =(CommandResult)in.readObject();
                        System.out.println(commandResult.name());

                        break;
                    }
                    case check_result:{
                        System.out.println("Ticket ID:");
                        int ID=scanner.nextInt();
                        out.writeObject(command);
                        out.writeObject(ID);
                        CommandResult commandResult =(CommandResult)in.readObject();
                        System.out.println(commandResult);
                        int ans=(int)in.readObject();
                        if(ans==0) {
                            System.out.println("Ar dajda :(((");
                        } else System.out.println("Dajda :******");
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
        } catch (Exception ex ){
            ex.printStackTrace();
            System.out.println("can't connect to server");
        }
    }
}
