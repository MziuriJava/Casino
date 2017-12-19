package server.dao;

import model.Ticket;
import model.TicketGame;

import java.util.List;

public interface TicketDAO {

    void createTicket (Ticket ticket) throws Exception;

    List<TicketGame> checkTicket (int ID) throws Exception;

    void getTicket (int ID) throws Exception;

}
