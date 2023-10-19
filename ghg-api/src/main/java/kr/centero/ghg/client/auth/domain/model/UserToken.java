package kr.centero.ghg.client.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Builder
@Alias("UserToken")
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {
    private Long id;
    private String token;
    private String username;
    private LocalDateTime issuedAt;
}
