package kr.centero.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.TimeZone;

@EnableFeignClients(basePackages = {"kr.centero.core", "kr.centero.common"})
@SpringBootApplication(scanBasePackages = {"kr.centero.core", "kr.centero.common"})
public class CenteroCommonApp {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));  // set default timezone to UTC
        SpringApplication.run(CenteroCommonApp.class, args);
    }
}
