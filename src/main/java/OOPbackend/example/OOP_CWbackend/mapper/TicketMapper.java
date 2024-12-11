package OOPbackend.example.OOP_CWbackend.mapper;

import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import OOPbackend.example.OOP_CWbackend.realTimeUpdates.TicketUpdates;

public class TicketMapper {

    public TicketUpdates updateTicket(Ticket ticket,String status) {
        return new TicketUpdates(
                ticket.getId(),
                ticket.getEventName(),
                ticket.getTicketPrice(),
                status
        );

    }

    public Ticket toTicket(TicketUpdates updates) {
        return new Ticket(
                updates.getTicketID(),
                updates.getEventName(),
                updates.getTicketPrice()
        );
    }
}
