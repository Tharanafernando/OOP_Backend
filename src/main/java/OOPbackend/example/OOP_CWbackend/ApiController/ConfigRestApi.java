package OOPbackend.example.OOP_CWbackend.ApiController;


import OOPbackend.example.OOP_CWbackend.Classes.Configuration;
import OOPbackend.example.OOP_CWbackend.Repository.ConfigRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConfigRestApi {

    private final ConfigRepo configRepo;


    public ConfigRestApi(ConfigRepo configRepo) {
        this.configRepo = configRepo;

    }

    @GetMapping("/getConfig")
    public ResponseEntity<List<Configuration>>getConfig() {
        return ResponseEntity.ok(this.configRepo.findAll());


    }

    @PostMapping("/createConfig")
    public ResponseEntity<Configuration> createConfig(@RequestBody Configuration config) {


        return ResponseEntity.status(201).body(this.configRepo.save(config));


    }
}
