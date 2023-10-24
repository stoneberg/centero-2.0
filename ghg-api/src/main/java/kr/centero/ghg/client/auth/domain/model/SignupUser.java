package kr.centero.ghg.client.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SignupUser")
@NoArgsConstructor
@AllArgsConstructor
public class SignupUser {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Boolean centero;
    private Boolean netzero;
    private String roleName;
}
