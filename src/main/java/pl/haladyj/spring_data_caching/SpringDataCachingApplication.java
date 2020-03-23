package pl.haladyj.spring_data_caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDataCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataCachingApplication.class, args);
	}

}
