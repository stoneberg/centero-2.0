package kr.centero.ghg.client.auth.mapper;

import kr.centero.ghg.client.auth.domain.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    Long findByRoleName(@Param("roleName") String roleName);

    void save(Role role);

    void deleteAll();
}
