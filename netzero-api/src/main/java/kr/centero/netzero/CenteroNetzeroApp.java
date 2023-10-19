package kr.centero.netzero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"kr.centero.core", "kr.centero.netzero"})
public class CenteroNetzeroApp {
    public static void main(String[] args) {
        SpringApplication.run(CenteroNetzeroApp.class, args);
    }
}
