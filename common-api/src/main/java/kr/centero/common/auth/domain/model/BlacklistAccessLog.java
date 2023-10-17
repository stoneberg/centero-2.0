package kr.centero.common.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Builder
@Alias("BlacklistAccessLog")
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistAccessLog {
    private Long id;
    private String ipAddress;
    private java.sql.Timestamp accessTime;
    private String userAgent;
    private String uri;
}
