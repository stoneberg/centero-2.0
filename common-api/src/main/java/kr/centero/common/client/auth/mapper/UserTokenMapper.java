package kr.centero.common.client.auth.mapper;

import kr.centero.core.common.model.CenteroUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTokenMapper {
    void save(CenteroUserToken centeroUserToken);

    void update(CenteroUserToken centeroUserToken);

    CenteroUserToken findByUsername(@Param("username") String username);

    CenteroUserToken findByAccessToken(@Param("accessToken") String accessToken);

    void deleteByUsername(@Param("username") String username);
}
