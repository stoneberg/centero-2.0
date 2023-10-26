package kr.centero.common.client.auth.service;


import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import kr.centero.common.client.auth.repository.UserTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * user token redis service
 * handle token crud process in redis
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenRedisService {
    private final UserTokenRedisRepository userTokenRepository;

    /**
     * save user token
     * delete current token and save new token if exist
     *
     * @param userTokenEntity user token entity
     */
    public void save(CenteroUserTokenEntity userTokenEntity) {
        userTokenRepository.findByAccessToken(userTokenEntity.getAccessToken()).ifPresentOrElse(
                existUserToken -> {
                    userTokenRepository.delete(existUserToken);
                    userTokenRepository.save(userTokenEntity);
                },
                () -> userTokenRepository.save(userTokenEntity));
    }

    /**
     * update user token
     * delete current token and save new token if exist
     *
     * @param accessToken     access token (current access token, not new access token)
     * @param userTokenEntity user token entity
     */
    public void update(String accessToken, CenteroUserTokenEntity userTokenEntity) {
        userTokenRepository.findByAccessToken(accessToken).ifPresentOrElse(
                existUserToken -> {
                    userTokenRepository.delete(existUserToken);
                    userTokenRepository.save(userTokenEntity);
                },
                () -> userTokenRepository.save(userTokenEntity));
    }

    /**
     * find user token by access token
     *
     * @param accessToken access token
     * @return user token entity
     */
    public CenteroUserTokenEntity findByAccessToken(String accessToken) {
        return userTokenRepository.findByAccessToken(accessToken).orElse(null);
    }

    /**
     * find user token by username
     *
     * @param username username
     * @return user token entity
     */
    public CenteroUserTokenEntity findByUsername(String username) {
        return userTokenRepository.findByUsername(username).orElse(null);
    }

    /**
     * delete user token by access token
     *
     * @param accessToken access token
     */
    public void deleteByAccessToken(String accessToken) {
        userTokenRepository.findByAccessToken(accessToken).ifPresent(userTokenRepository::delete);
    }

    /**
     * delete user token by username
     *
     * @param username usernameo
     */
    public void deleteByUsername(String username) {
        userTokenRepository.findByUsername(username).ifPresent(userTokenRepository::delete);
    }
}
