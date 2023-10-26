package kr.centero.common.client.auth.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * centero user token info entity in redis
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("CenteroUserToken")
public class CenteroUserTokenEntity implements Serializable {
    @Id
    private String accessToken;

    @Indexed
    private String username;

    @Indexed
    private String refreshToken;

    private String roles;

    private LocalDateTime issuedAt;
}

