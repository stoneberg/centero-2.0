package kr.centero.ghg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@EnableFeignClients(basePackages = {"kr.centero.core", "kr.centero.ghg"})
@SpringBootApplication(scanBasePackages = {"kr.centero.core", "kr.centero.ghg"})
public class CenteroGhgApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));  // set default timezone to UTC
        SpringApplication.run(CenteroGhgApplication.class, args);
    }
}
