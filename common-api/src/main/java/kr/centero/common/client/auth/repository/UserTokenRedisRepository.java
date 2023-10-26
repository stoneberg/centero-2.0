package kr.centero.common.client.auth.repository;

import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * user token redis repository
 */
public interface UserTokenRedisRepository extends CrudRepository<CenteroUserTokenEntity, Long> {
    Optional<CenteroUserTokenEntity> findByUsername(String username);

    Optional<CenteroUserTokenEntity> findByAccessToken(String accessToken);
}
