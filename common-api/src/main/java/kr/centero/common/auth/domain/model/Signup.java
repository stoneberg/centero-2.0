package kr.centero.common.auth.domain.model;

import kr.centero.common.auth.domain.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Signup")
@NoArgsConstructor
@AllArgsConstructor
public class Signup {
    private String username;
    private String password;
    private String email;
    private ERole role;
}
