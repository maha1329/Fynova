package tn.fynova.spring;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class FynovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FynovaApplication.class, args);
	}
 

}
