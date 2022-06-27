package demo.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
@EnableFeignClients({"demo.openapi", "demo.openapi.drink"})
public class DemoApplication {

    public static void main(final String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(demo.openapi.DemoApplication.class, args);
        context.start();
    }

}
