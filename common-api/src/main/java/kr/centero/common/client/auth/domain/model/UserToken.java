package kr.centero.common.client.auth.domain.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Alias("UserToken")
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {
    private Long id;
    private String token;
    private String username;
    private String domain;
    private String roles;
    private LocalDateTime issuedAt;
}
