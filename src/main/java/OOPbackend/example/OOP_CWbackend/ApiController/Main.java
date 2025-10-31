package OOPbackend.example.OOP_CWbackend.ApiController;


import OOPbackend.example.OOP_CWbackend.Classes.Configuration;
import OOPbackend.example.OOP_CWbackend.Repository.ConfigRepo;
import OOPbackend.example.OOP_CWbackend.Service.configService;
import OOPbackend.example.OOP_CWbackend.Threads.Consumer;
import OOPbackend.example.OOP_CWbackend.Threads.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Main {


    private Configuration configuration;
    @Autowired
    private configService configurationService;


    @PostMapping("/createConfig")
    public ResponseEntity<Configuration> createConfig(@RequestBody Configuration config) {
        return ResponseEntity.status(201).body(configurationService.createConfiguration(config));


    }



    @PostMapping("/runThreads")
    public String runThreads(@RequestBody Configuration config) {

        Vendor[] vendors = new Vendor[10];
        Consumer[] consumers = new Consumer[5];
        createConfig(config);
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(config.getTotalTickets(), config.getReleaseRate());
            Thread venThread = new Thread("Vendor "+vendors[i]+"added tickets");
            venThread.start();
        }

        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(config.getTotalTickets(), config.getReleaseRate()); // this line should be changed 12/7/2024 at 10.23
            Thread consumerThread = new Thread("Customer"+consumers[i]+"bought tickets");
            consumerThread.start();
        }

        return "Threads started";
//        return config.getTotalTickets() + " tickets"+"\n"+
//                config.getMaxTickets() + "Max capacity"+"\n"+
//                config.getReleaseRate()+ "Release rate"+"\n"+
//                config.getCustomerRate() + "Customer rate";



    }


}
