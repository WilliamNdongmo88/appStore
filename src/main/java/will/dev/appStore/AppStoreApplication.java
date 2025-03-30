package will.dev.appStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AppStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppStoreApplication.class, args);
	}

}
