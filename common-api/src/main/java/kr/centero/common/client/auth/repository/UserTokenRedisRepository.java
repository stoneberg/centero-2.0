package kr.centero.common.client.auth.repository;

import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * user token redis repository
 */
public interface UserTokenRedisRepository extends CrudRepository<CenteroUserTokenEntity, String> {
    CenteroUserTokenEntity findByUsername(String username);

    CenteroUserTokenEntity findByAccessToken(String accessToken);

    void deleteByUsername(String username);
}
