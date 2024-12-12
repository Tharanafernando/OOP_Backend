package OOPbackend.example.OOP_CWbackend.ApiController;


import OOPbackend.example.OOP_CWbackend.Classes.Configuration;
import OOPbackend.example.OOP_CWbackend.Classes.TicketPool;
import OOPbackend.example.OOP_CWbackend.Service.configService;
import OOPbackend.example.OOP_CWbackend.Threads.Consumer;
import OOPbackend.example.OOP_CWbackend.Threads.Vendor;
import com.mongodb.annotations.Beta;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class Main {

    private List<Thread>threads = new LinkedList<>();
    private List<Vendor>vendors = new LinkedList<>();
    private List<Consumer>consumers = new LinkedList<>();

    @Autowired
    private configService configurationService;
    @Autowired
    private TicketPool ticketPool;





    @PostMapping("/createConfig")
    public ResponseEntity<Configuration> createConfig(@RequestBody Configuration config) {
        return ResponseEntity.status(201).body(configurationService.createConfiguration(config));


    }






    @Bean
    @PostMapping("/runThreads")
    @CrossOrigin (origins = "http://localhost:4200")
    public ResponseEntity<Map<String,String>> runThreads(@RequestBody Configuration config) {
        ticketPool.setMaxTickets(config.getMaxTickets());
        ticketPool.setTotalTickets(config.getTotalTickets());
       // List<Thread>threads = new LinkedList<>();

//        Vendor[] vendors = new Vendor[config.getNoOfVendor()];
//        Consumer[] consumers = new Consumer[config.getNoOfConsumer()];
        createConfig(config);

        for (int i = 0; i < config.getNoOfVendor(); i++) {
            Vendor vendor = new Vendor(ticketPool,config.getTotalTickets(), config.getReleaseRate());
            vendors.add(vendor);
           // vendors[i] = new Vendor(ticketPool,config.getTotalTickets(), config.getReleaseRate());
            Thread venThread = new Thread(vendor,"Vendor"+i);
            venThread.start();
        }

        for (int i = 0; i < config.getNoOfConsumer(); i++) {
            Consumer consumer = new Consumer(ticketPool, config.getReleaseRate(),config.getTotalTickets());
            consumers.add(consumer);
            Thread conThread = new Thread(consumer,"Customer"+i);
            conThread.start();

        }




        Map<String,String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);





    }

    @PostMapping("/stopThreads")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String,String>>stopThreads() {
        for (Vendor vendor:vendors) {
            vendor.stopThread();
        }

        for (Consumer consumer:consumers) {
            consumer.stopThread();
        }

        for (Thread thread:threads) {
            thread.interrupt();
        }

        threads.clear();
        vendors.clear();
        consumers.clear();


        Map<String,String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }





}
