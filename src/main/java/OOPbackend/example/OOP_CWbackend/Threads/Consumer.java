package OOPbackend.example.OOP_CWbackend.Threads;



import OOPbackend.example.OOP_CWbackend.Classes.TicketPool;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Setter
@Component
public class Consumer implements Runnable {


    private int customerRate;
    private TicketPool ticketPool;

    public Consumer(){
    }

    public Consumer(TicketPool ticketPool,int customerRate) {
        this.customerRate = customerRate;
        this.ticketPool = ticketPool;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ticketPool.buyTickets();

            try {
                Thread.sleep(customerRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }


    }
}
