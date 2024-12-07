package OOPbackend.example.OOP_CWbackend.Service;


import OOPbackend.example.OOP_CWbackend.Classes.Configuration;
import OOPbackend.example.OOP_CWbackend.Repository.ConfigRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class configService {
    @Autowired
    private ConfigRepo configRepo;
    public Configuration createConfiguration(Configuration configuration){
        return configRepo.save(configuration);

    }

}
