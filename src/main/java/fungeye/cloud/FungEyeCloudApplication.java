package fungeye.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FungEyeCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FungEyeCloudApplication.class, args);
    }
}
