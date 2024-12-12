package OOPbackend.example.OOP_CWbackend.Classes;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Component
//@MessageMapping("/sendLog")    //this annotation is misplaced. it should map a specific method
public class TicketPool {
    private Queue<Ticket> tickets = new LinkedList<>();

    @Setter
    private int maxTickets;
    @Setter
    private int totalTickets;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private int limit = 0;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;




    public void addTickets(Ticket ticket) {
        lock.lock();
        try{
            while(tickets.size() >= maxTickets){
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                limit++;
                if (limit == totalTickets){
                    sendMessage("Total number of tickets reached");
                    return;
                }
            }
            tickets.add(ticket);
            System.out.println(Thread.currentThread().getName()+" Ticket added to the pool. Ticket pool size is: "+tickets.size());
            sendMessage(Thread.currentThread().getName()+" Ticket added to the pool. Ticket pool size is: "+tickets.size()+"\n");

            notEmpty.signal();



        }
        finally {
            lock.unlock();
        }

    }


    public void buyTickets() {
        lock.lock();
        try{
            while (tickets.isEmpty()){
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            } Ticket ticket = tickets.poll();
            System.out.println(Thread.currentThread().getName()+"bought ticket. Ticket pool size: "+tickets.size());
            sendMessage(Thread.currentThread().getName()+"bought ticket. Ticket pool size: "+tickets.size()+"\n");


            notFull.signal();
        }finally {
            lock.unlock();
        }

    }

    public int currentSize() {
        lock.lock();
        try {
            return tickets.size();
        }finally{
            lock.unlock();
        }

    }



    public void sendMessage(String message) {
        messagingTemplate.convertAndSend("/topic/tickets", message);
    }
}
