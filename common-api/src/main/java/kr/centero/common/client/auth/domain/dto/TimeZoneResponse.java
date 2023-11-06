package kr.centero.common.client.auth.domain.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class TimeZoneResponse {
    private String zoneId;
    private LocalDateTime localDateTime;
}
