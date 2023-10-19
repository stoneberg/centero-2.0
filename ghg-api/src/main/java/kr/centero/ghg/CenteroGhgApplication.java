package kr.centero.ghg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"kr.centero.core", "kr.centero.ghg"})
public class CenteroGhgApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenteroGhgApplication.class, args);
    }
}
