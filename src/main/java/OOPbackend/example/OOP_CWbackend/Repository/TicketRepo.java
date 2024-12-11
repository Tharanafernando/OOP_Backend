package OOPbackend.example.OOP_CWbackend.Repository;


import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends MongoRepository<Ticket, String> {
}
