package OOPbackend.example.OOP_CWbackend.Classes;

import OOPbackend.example.OOP_CWbackend.ApiController.MessageService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    private Configuration configuration;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

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
            }
            tickets.add(ticket);
            System.out.println(Thread.currentThread().getName()+" Ticket added to the pool. Ticket pool size is: "+tickets.size());
            sendMessage(Thread.currentThread().getName()+" Ticket added to the pool. Ticket pool size is: "+tickets.size());

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
            System.out.println(Thread.currentThread().getName()+" {"+ticket+"} bought. Ticket pool size: "+tickets.size());
            sendMessage(Thread.currentThread().getName()+" {"+ticket+"} bought. Ticket pool size: "+tickets.size());


            notFull.signal();
        }finally {
            lock.unlock();
        }

    }



    public void sendMessage(String message) {
        messagingTemplate.convertAndSend("/topic/tickets", message);
    }
}
