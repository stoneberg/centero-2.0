package kr.centero.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * centero user token info entity in redis
 * preserve user token in one hour
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisToken {
    private Long id;

    private String accessToken;

    private String username;

    private String refreshToken;

    private String roles;

    private LocalDateTime issuedAt;
}

