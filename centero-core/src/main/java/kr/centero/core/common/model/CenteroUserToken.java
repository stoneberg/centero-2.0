package kr.centero.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CenteroUserToken {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private String username;
    private String roles;
    private LocalDateTime issuedAt;
}
