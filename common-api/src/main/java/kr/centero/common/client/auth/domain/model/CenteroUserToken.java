package kr.centero.common.client.auth.domain.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Alias("CenteroUserToken")
@NoArgsConstructor
@AllArgsConstructor
public class CenteroUserToken {
    private Long id;
    private String username;
    private String roles;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime issuedAt;
}
