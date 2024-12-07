package OOPbackend.example.OOP_CWbackend.Threads;


import OOPbackend.example.OOP_CWbackend.ApiController.TicketController;
import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import OOPbackend.example.OOP_CWbackend.Classes.TicketPool;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Setter
@Component
public class Vendor implements Runnable{
    private int totalTickets;
    private int releaseRate;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private TicketPool ticketPool;
    private Ticket ticket;
    private String eventName;
    private double ticketPrice;
    private TicketController ticketController;

    public Vendor (){}

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public Vendor(int totalTickets, int releaseRate) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            System.out.println(Thread.currentThread().getName() + ": Thread started");
            logger.info(Thread.currentThread().getName() + ": Thread started");
//            String ticketId = String.valueOf(i);
//            ticket = new Ticket(ticketId,eventName,ticketPrice);
//            ticketPool.addTickets(ticket);
//            ticketController.createTicket(ticket);



            try{
                Thread.sleep(releaseRate*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
