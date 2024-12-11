package OOPbackend.example.OOP_CWbackend.Classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("TicketDetails")
@Getter
@Setter
public class Ticket {
    @Id
    private String id;

    private String EventName;
    private double TicketPrice;



    public Ticket(String id, String EventName, double TicketPrice) {
        this.id = id;
        this.EventName = EventName;
        this.TicketPrice = TicketPrice;
    }

}
