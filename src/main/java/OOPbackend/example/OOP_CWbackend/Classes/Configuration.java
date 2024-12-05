package OOPbackend.example.OOP_CWbackend.Classes;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;
import org.springframework.beans.factory.annotation.Value;


@Setter   // use instead of setter method
@Getter  // use instead of getter method
@Document

public class Configuration {

    @Id
    private String ID;

    private int totalTickets;
    private int maxTickets;
    private int releaseRate;
    private int customerRate;



    public Configuration(int totalTickets, int maxTickets, int releaseRate, int customerRate) {
        this.totalTickets = totalTickets;
        this.maxTickets = maxTickets;
        this.releaseRate = releaseRate;
        this.customerRate = customerRate;
    }




}
