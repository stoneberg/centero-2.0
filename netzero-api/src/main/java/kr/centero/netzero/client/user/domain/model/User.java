package kr.centero.netzero.client.user.domain.model;

import kr.centero.core.common.pagination.PageModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@Alias("User")
@EqualsAndHashCode(callSuper = true)
public class User extends PageModel {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String domain;
    private Boolean active;
    private String roleName;
    private LocalDateTime updatedAt;
}
