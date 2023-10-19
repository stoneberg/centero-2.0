package kr.centero.ghg.client.auth.mapper;

import kr.centero.ghg.client.auth.domain.model.SignupUser;
import kr.centero.ghg.client.auth.domain.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAuthMapper {
    List<UserRole> findUserByUsername(@Param("username") String username);

    Boolean existsUser(SignupUser user);

    void save(SignupUser user);
}
