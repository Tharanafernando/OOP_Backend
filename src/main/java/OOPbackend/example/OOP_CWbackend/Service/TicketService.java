package OOPbackend.example.OOP_CWbackend.Service;

import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import OOPbackend.example.OOP_CWbackend.Repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepo ticketRepo;

    public Ticket ticketService(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

}
