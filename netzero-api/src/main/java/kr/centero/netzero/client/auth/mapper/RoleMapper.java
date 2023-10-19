package kr.centero.netzero.client.auth.mapper;

import kr.centero.netzero.client.auth.domain.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMapper {
    Long findByRoleName(@Param("roleName") String roleName);

    void save(Role role);

    void deleteAll();
}
