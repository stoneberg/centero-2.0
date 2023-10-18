package kr.centero.common.auth.mapper;

import kr.centero.common.auth.domain.model.SignupUser;
import kr.centero.common.auth.domain.model.UserRole;
import kr.centero.common.user.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAuthMapper {
    List<UserRole> findUserByUsername(@Param("username") String username);

    Boolean existsUser(SignupUser user);

    void save(SignupUser user);
}
