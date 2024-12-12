package OOPbackend.example.OOP_CWbackend.Threads;



import OOPbackend.example.OOP_CWbackend.Classes.TicketPool;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Setter
@Component
public class Consumer implements Runnable {


    private int customerRate;
    private TicketPool ticketPool;
    private int totalTickets;
    private boolean stop = false;

    public void stopThread(){
        stop = true;
    }

    public Consumer(){
    }

    public Consumer(TicketPool ticketPool,int customerRate,int totalTickets) {
        this.customerRate = customerRate;
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !stop) {
            for (int i = 0; i < totalTickets; i++){
                if (Thread.currentThread().isInterrupted() || stop){
                    return;
                }
                ticketPool.buyTickets();
            }


            try {
                Thread.sleep(customerRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;

            }
        }


    }
}
