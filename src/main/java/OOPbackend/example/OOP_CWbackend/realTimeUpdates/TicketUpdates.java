package OOPbackend.example.OOP_CWbackend.realTimeUpdates;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdates {
    private String ticketID;
    private String EventName;
    private double TicketPrice;
    private String status;

    public TicketUpdates(String ticketID, String EventName, double TicketPrice, String status) {
        this.ticketID = ticketID;
        this.EventName = EventName;
        this.TicketPrice = TicketPrice;

    }


}
