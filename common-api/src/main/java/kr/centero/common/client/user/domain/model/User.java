package kr.centero.common.client.user.domain.model;

import kr.centero.core.common.pagination.PageModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@Alias("User")
@EqualsAndHashCode(callSuper = true)
public class User extends PageModel {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String roleName;
    private Boolean active;
    private String domain;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
