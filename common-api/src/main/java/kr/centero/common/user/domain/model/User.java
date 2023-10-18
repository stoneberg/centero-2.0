package kr.centero.common.user.domain.model;

import kr.centero.common.common.mybatis.pagination.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("User")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends PageInfo {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String roleName;
}
