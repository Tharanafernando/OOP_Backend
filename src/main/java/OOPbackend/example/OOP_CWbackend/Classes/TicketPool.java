package OOPbackend.example.OOP_CWbackend.Classes;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private Queue<Ticket> tickets;
    private int maxTickets;
    private Configuration configuration;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public TicketPool(Configuration configuration,Queue<Ticket> tickets) {
        this.configuration = configuration;
        this.tickets = new LinkedList<>(tickets);
    }

    public void addTickets(Ticket ticket) {
        lock.lock();
        try{
            while(tickets.size()>= configuration.getMaxTickets()){
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            tickets.add(ticket);
            System.out.println(Thread.currentThread().getName()+" Ticket added to the pool. Ticket pool size is: "+tickets.size());
            notEmpty.signal();

        }finally {
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
            System.out.println(Thread.currentThread().getName()+" {"+ticket+"} bought. Ticket pool size: "+tickets.size()); // should be a massage when implement websockets
            notEmpty.signal();
        }finally {
            lock.unlock();
        }

    }
}
