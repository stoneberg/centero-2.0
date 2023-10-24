package kr.centero.common.client.auth.mapper;

import kr.centero.common.client.auth.domain.model.CenteroUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTokenMapper {
    void save(CenteroUserToken centeroUserToken);

    CenteroUserToken findByUsername(@Param("username") String username);

    void deleteByUsername(@Param("username") String username);
}
