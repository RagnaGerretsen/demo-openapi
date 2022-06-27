package demo.openapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DemoApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        log.info("Beans created: {}", Arrays.toString(applicationContext.getBeanDefinitionNames()));
    }
}
