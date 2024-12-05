package OOPbackend.example.OOP_CWbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OopCWbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopCWbackendApplication.class, args);
	}



}
