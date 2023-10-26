package kr.centero.common.client.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("centero_user_token")
public class CenteroUserTokenEntity implements Serializable {
    @Id
    private Long id;

    @Indexed
    private String accessToken;

    @Indexed
    private String username;

    @Indexed
    private String refreshToken;

    private String roles;

    private LocalDateTime issuedAt;
}

