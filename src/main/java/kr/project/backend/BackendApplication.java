package kr.project.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BackendApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "optional:classpath:application-local.yml"
            + ", optional:/app/project/config/application.yml";
    public static void main(String[] args) {
        new SpringApplicationBuilder(BackendApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
