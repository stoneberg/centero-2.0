package kr.centero.ghg.client.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {
    void save(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void deleteAll();
}
