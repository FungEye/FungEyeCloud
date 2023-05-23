package fungeye.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class FungEyeCloudApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Copenhagen"));
        SpringApplication.run(FungEyeCloudApplication.class, args);
    }
}
