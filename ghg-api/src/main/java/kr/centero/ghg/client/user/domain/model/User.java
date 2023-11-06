package kr.centero.ghg.client.user.domain.model;

import kr.centero.core.common.pagination.PageModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

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
}
