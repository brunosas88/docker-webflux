package br.com.letscode.comprasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ComprasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComprasApiApplication.class, args);
    }

}
