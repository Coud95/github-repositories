package pl.allegro.recruitment.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubRepositoriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubRepositoriesApplication.class, args);
    }

}