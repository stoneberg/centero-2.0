package kr.centero.common.client.auth.repository;

import kr.centero.common.client.auth.domain.entity.RedisTokenEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * user token redis repository
 */
public interface UserTokenRedisRepository extends CrudRepository<RedisTokenEntity, Long> {
    Optional<RedisTokenEntity> findByUsername(String username);

    Optional<RedisTokenEntity> findByAccessToken(String accessToken);
}
