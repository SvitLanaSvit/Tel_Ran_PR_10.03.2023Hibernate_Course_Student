package org.example.main;

import org.example.util.BeanConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(BeanConfiguration.class)
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
