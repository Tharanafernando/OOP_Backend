package OOPbackend.example.OOP_CWbackend.Classes;

import OOPbackend.example.OOP_CWbackend.ApiController.MessageService;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class TicketPool {
    private Queue<Ticket> tickets;
    private int maxTickets;
    private final Configuration configuration;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private MessageService messageService;

    public TicketPool(Configuration configuration) {
        this.tickets = new LinkedList<>();
        this.configuration = configuration;
    }

    public void addTickets(Ticket ticket) {
        lock.lock();
        try{
            while(tickets.size() >= configuration.getMaxTickets()){
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            tickets.add(ticket);
            System.out.println(Thread.currentThread().getName()+" Ticket added to the pool. Ticket pool size is: "+tickets.size());
//            messageService.SendMessage(
//                    configuration.getID(),
//                    configuration.getTotalTickets(),
//                    configuration.getMaxTickets(),
//                    configuration.getReleaseRate(),
//                    configuration.getCustomerRate()
//            );
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
            notEmpty.signal();
        }finally {
            lock.unlock();
        }

    }
}
