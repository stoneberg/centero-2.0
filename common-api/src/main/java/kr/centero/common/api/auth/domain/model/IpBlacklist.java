package kr.centero.common.api.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Builder
@Alias("IpBlacklist")
@NoArgsConstructor
@AllArgsConstructor
public class IpBlacklist {
    private Long id;
    private String ipAddress;
    private String reason;
    private LocalDateTime createdAt;
}
