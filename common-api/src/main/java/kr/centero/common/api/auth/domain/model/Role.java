package kr.centero.common.api.auth.domain.model;

import kr.centero.common.api.auth.domain.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Role")
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long roleId;
    private ERole roleName; // ADMIN, USER

    public Role(ERole roleName) {
        this.roleName = roleName;
    }
}
