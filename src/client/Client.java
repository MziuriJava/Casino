package client;

import model.Command;
import model.CommandResult;
import model.Game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
                    case check_result:{
                        break;
                    }
                    case create_ticket:{
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
