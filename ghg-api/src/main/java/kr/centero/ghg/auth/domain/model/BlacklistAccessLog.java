package kr.centero.ghg.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Builder
@Alias("BlacklistAccessLog")
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistAccessLog {
    private Long id;
    private String ipAddress;
    private LocalDateTime accessTime;
    private String userAgent;
    private String uri;
}
