package ru.clevertec.house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.clevertec.cachestarter.aspect.CacheAspect;

@SpringBootApplication
public class HousingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HousingApplication.class, args);
//        CacheAspect bean = run.getBean(CacheAspect.class);

    }
}
