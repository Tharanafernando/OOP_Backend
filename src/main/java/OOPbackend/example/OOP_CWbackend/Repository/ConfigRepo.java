package OOPbackend.example.OOP_CWbackend.Repository;

import OOPbackend.example.OOP_CWbackend.Classes.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends MongoRepository<Configuration, String> {
}
