package kr.centero.netzero.client.user.domain.model;

import kr.centero.core.common.pagination.PageInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

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
    private String domain;
    private Boolean active;
    private String roleName;

    public User() {
        // SuperBuilder를 사용하면 기본 생성자가 필요하다.
    }
}
