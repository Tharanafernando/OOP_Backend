package OOPbackend.example.OOP_CWbackend.Classes;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;
import org.springframework.beans.factory.annotation.Value;


@Setter   // use instead of setter method
@Getter  // use instead of getter method
@Document
@Component



public class Configuration {

    @Id
    private String ID;

    private int totalTickets;
    private int maxTickets;
    private int releaseRate;
    private int customerRate;
    private int noOfVendor;
    private int noOfConsumer;

    public Configuration() {}



    public Configuration(int totalTickets, int maxTickets, int releaseRate, int customerRate,int noOfVendor,int noOfConsumer) {
        this.totalTickets = totalTickets;
        this.maxTickets = maxTickets;
        this.releaseRate = releaseRate;
        this.customerRate = customerRate;
        this.noOfVendor = noOfVendor;
        this.noOfConsumer = noOfConsumer;
    }


}
