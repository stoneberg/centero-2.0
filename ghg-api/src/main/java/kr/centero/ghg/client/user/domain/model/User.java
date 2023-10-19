package kr.centero.ghg.client.user.domain.model;

import kr.centero.ghg.common.mybatis.pagination.PageInfo;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Data
@Builder
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
