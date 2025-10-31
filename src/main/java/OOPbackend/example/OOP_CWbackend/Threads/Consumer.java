package OOPbackend.example.OOP_CWbackend.Threads;


import OOPbackend.example.OOP_CWbackend.Classes.Ticket;
import OOPbackend.example.OOP_CWbackend.Classes.TicketPool;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Setter
@Component
public class Consumer implements Runnable {


    private int customerRate;
    private int quantitiy;
    private Logger logger = Logger.getLogger(Consumer.class.getName());
    private TicketPool ticketPool;

    public Consumer(){
    }

    public Consumer(int customerRate, int quantitiy) {
        this.ticketPool = ticketPool;
        this.customerRate = customerRate;
        this.quantitiy = quantitiy;
    }


    @Override
    public void run() {
        for (int i = 0; i < quantitiy; i++) {
            System.out.println(Thread.currentThread().getName() + ": Consumer started");
           // ticketPool.buyTickets();

            try {
                Thread.sleep(customerRate*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
