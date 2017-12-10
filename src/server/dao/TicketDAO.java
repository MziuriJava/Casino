package server.dao;

import model.Ticket;

public interface TicketDAO {

    void createTicket (Ticket ticket) throws Exception;

    void checkTicket (int ID) throws Exception;

}
