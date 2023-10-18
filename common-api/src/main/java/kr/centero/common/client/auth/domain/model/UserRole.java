package kr.centero.common.client.auth.domain.model;

import kr.centero.common.client.auth.domain.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserRole")
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Long roleId;
    private ERole roleName;
}

