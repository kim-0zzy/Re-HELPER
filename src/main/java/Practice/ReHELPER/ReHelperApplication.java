package Practice.ReHELPER;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReHelperApplication.class, args);
	}

}
