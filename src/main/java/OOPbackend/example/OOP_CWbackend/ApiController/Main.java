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
import java.util.Map;


@RestController
@RequestMapping("/api")
public class Main {

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

        Vendor[] vendors = new Vendor[10];
        Consumer[] consumers = new Consumer[5];
        createConfig(config);

        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(ticketPool,config.getTotalTickets(), config.getReleaseRate());
            Thread venThread = new Thread(vendors[i],"Vendor"+i);
            venThread.start();
        }

        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Consumer(ticketPool, config.getReleaseRate());
            Thread consumerThread = new Thread(consumers[i]," Customer"+i);
            consumerThread.start();
        }



        Map<String,String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);





    }





}
