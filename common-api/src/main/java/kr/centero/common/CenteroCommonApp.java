package kr.centero.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"kr.centero.core", "kr.centero.common"})
public class CenteroCommonApp {
    public static void main(String[] args) {
        SpringApplication.run(CenteroCommonApp.class, args);
    }
}
