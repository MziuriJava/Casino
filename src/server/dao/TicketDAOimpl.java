package server.dao;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.xml.internal.bind.v2.TODO;
import model.Ticket;
import model.TicketGame;
import server.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TicketDAOimpl implements TicketDAO {
    @Override
    public void createTicket(Ticket ticket) throws Exception {
        Connection con =null;
        PreparedStatement pstmt1 =null;
        PreparedStatement pstmt2 =null;
        try{
            con= DatabaseConnector.getConnection();
            pstmt1 =con.prepareStatement("INSERT INTO TICKET ( GAMES, BET, KUSH , WIN) VALUES (?,?,?,?) RETURNING ID");
            pstmt1.setInt(1, ticket.getGamesnumber());
            pstmt1.setDouble(2,ticket.getBet());
            pstmt1.setDouble(3,ticket.getKush());
            pstmt1.setDouble(4,ticket.getWin());
            ResultSet rs = pstmt1.executeQuery();
            rs.next();
            ticket.setID(rs.getInt(1));
            List<TicketGame> ticketgame= ticket.getGames();
            for(int i=0;i<ticketgame.size();i++){
                pstmt2=con.prepareStatement("INSERT INTO GAMETCK (TCKID, GAMEID,VIZEDADO,KUSH) VALUES (?,?,?,?)");
                pstmt2.setInt(1,ticket.getID());
                pstmt2.setInt(2,ticketgame.get(i).getID());
                pstmt2.setInt(3,ticketgame.get(i).getVizedado());
                pstmt2.setDouble(4,ticketgame.get(i).getCurrentkush());
                pstmt2.executeUpdate();

            }
            pstmt1.close();
            pstmt2.close();
            con.close();
        } catch(Exception ex){
            System.out.println("createticket is not possible");
            ex.printStackTrace();
        }


    }

    @Override
    public void checkTicket(int ID) throws Exception {
    Connection con = DatabaseConnector.getConnection();
    PreparedStatement pstmt = con.prepareStatement("SELECT * FROM GAMETCK where ID=?");
    pstmt.setInt(1,ID);
    ResultSet rs1= pstmt.executeQuery();
    PreparedStatement pstmt2 =null;
        while(rs1.next()){
            pstmt2=con.prepareStatement("SELECT * FROM GAME where ID=?");
            pstmt2.setInt(1,rs1.getInt("RESULT"));
            ResultSet rs2 = pstmt2.executeQuery();
            rs2.next();
            //TODO checkticket

        }
    }
}
