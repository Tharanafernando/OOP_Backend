package OOPbackend.example.OOP_CWbackend.Threads;



import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import OOPbackend.example.OOP_CWbackend.Classes.TicketPool;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;


@Component
public class Vendor implements Runnable{
    private int totalTickets;
    private int releaseRate;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private TicketPool ticketPool;
    private Ticket ticket;
    private String eventName = "Musical";
    private double ticketPrice = 50;
    private int ticketCount = 0;
    public volatile boolean stop = false; // stop part

    public void stopThread(){  // stop part
        stop = true;
    }


    public Vendor (){}




    public Vendor(TicketPool ticketPool,int totalTickets, int releaseRate) {
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
        this.ticketPool = ticketPool;
    }

//    public Vendor(TicketPool ticketPool, int totalTickets, int releaseRate) {
//    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && ticketCount < totalTickets){
            for (int i = 0; i < totalTickets; i++) {
                if (Thread.currentThread().isInterrupted() || stop){
                    return;
                }
                String ticketId = String.valueOf(i);
                ticket = new Ticket(ticketId,eventName,ticketPrice);
                ticketPool.addTickets(ticket);
            }ticketCount++;


            try{
                Thread.sleep(releaseRate*1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }



    }
}
