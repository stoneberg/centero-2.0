package kr.centero.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CenteroCommonApp {
    public static void main(String[] args) {
        System.out.println("currrr: " + System.getProperty("user.dir"));
        SpringApplication.run(CenteroCommonApp.class, args);
    }
}
