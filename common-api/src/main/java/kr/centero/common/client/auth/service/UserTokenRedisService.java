package kr.centero.common.client.auth.service;


import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import kr.centero.common.client.auth.repository.UserTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenRedisService {
    private final UserTokenRedisRepository userTokenRepository;

    public void save(CenteroUserTokenEntity userTokenEntity) {
        userTokenRepository.findByAccessToken(userTokenEntity.getAccessToken()).ifPresentOrElse(
                existUserToken -> {
                    log.info("[ZET]existUserToken: {}", existUserToken);
                    userTokenRepository.delete(existUserToken);
                    userTokenRepository.save(userTokenEntity);
                },
                () -> {
                    log.info("[ZET]userTokenEntity: {}", userTokenEntity);
                    userTokenRepository.save(userTokenEntity);
                });
    }

    public void update(String accessToken, CenteroUserTokenEntity userTokenEntity) {
        userTokenRepository.findByAccessToken(accessToken).ifPresentOrElse(
                existUserToken -> {
                    log.info("[ZET]existUserToken: {}", existUserToken);
                    userTokenRepository.delete(existUserToken);
                    userTokenRepository.save(userTokenEntity);
                },
                () -> {
                    log.info("[ZET]userTokenEntity: {}", userTokenEntity);
                    userTokenRepository.save(userTokenEntity);
                });
    }

    public CenteroUserTokenEntity findByUsername(String username) {
        return userTokenRepository.findByUsername(username).orElse(null);
    }

    public CenteroUserTokenEntity findByAccessToken(String accessToken) {
        log.info("[ZET]findByAccessToken===================>{}", accessToken);
        CenteroUserTokenEntity userToken = userTokenRepository.findByAccessToken(accessToken).orElse(null);
        log.info("[ZET]findByAccessToken===================>{}", userToken);
        return userToken;
    }

    public void deleteByAccessToken(String accessToken) {
        userTokenRepository.findByAccessToken(accessToken).ifPresent(existUserToken -> {
            log.info("[ZET]existUserToken: {}", existUserToken);
            userTokenRepository.delete(existUserToken);
        });
    }

    public void deleteByUsername(String username) {
        userTokenRepository.findByUsername(username).ifPresent(existUserToken -> {
            log.info("[ZET]existUserToken: {}", existUserToken);
            userTokenRepository.delete(existUserToken);
        });
    }
}
