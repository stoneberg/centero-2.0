package kr.centero.common.client.user.domain.model;

import kr.centero.core.common.pagination.PageInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@Alias("User")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends PageInfo {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String roleName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User() {
        // SuperBuilder를 사용하면 기본 생성자가 필요하다.
    }
}
