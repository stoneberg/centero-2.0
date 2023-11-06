package kr.centero.core.common.model;

import kr.centero.core.common.enums.roles.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("CenteroUserRole")
@NoArgsConstructor
@AllArgsConstructor
public class CenteroUserRole {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String domain;
    private Long roleId;
    private ERole roleName;
}

