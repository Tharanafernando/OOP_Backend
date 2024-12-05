package OOPbackend.example.OOP_CWbackend.ApiController;


import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import OOPbackend.example.OOP_CWbackend.Repository.TicketRepo;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {
    private final TicketRepo ticketRepo;

    public TicketController(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @GetMapping("/getTicket")

    public ResponseEntity<List<Ticket>> getTicket() {
        return ResponseEntity.ok(this.ticketRepo.findAll());
    }


    @PostMapping("/createTicket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {

        return ResponseEntity.status(200).body(ticketRepo.save(ticket));
    }
}
