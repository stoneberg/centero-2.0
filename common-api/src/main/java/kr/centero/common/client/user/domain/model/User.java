package kr.centero.common.client.user.domain.model;

import kr.centero.common.common.mybatis.pagination.PageInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends PageInfo {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String roleName;

    public User() {
        // SuperBuilder를 사용하면 기본 생성자가 필요하다.
    }
}
