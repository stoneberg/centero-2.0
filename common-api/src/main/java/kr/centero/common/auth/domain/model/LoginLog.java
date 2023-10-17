package kr.centero.common.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Builder
@Alias("LoginLog")
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog {
    private Long id;
    private String username;
    private Boolean loginSuccess;
    private String ipAddress;
    private Integer failureCount;
    private LocalDateTime loginTime;
}
