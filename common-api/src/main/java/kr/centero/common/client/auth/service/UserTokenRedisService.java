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

    public void save(CenteroUserTokenEntity userToken) {
        userTokenRepository.save(userToken);
    }

    // in redis 'update' is 'save'
    public void update(CenteroUserTokenEntity userToken) {
        userTokenRepository.save(userToken);
    }

    public CenteroUserTokenEntity findByUsername(String username) {
        return userTokenRepository.findByUsername(username);
    }

    public CenteroUserTokenEntity findByAccessToken(String accessToken) {
        log.info("[ZET]findByAccessToken===================>{}", accessToken);
        CenteroUserTokenEntity userToken = userTokenRepository.findByAccessToken(accessToken);
        log.info("[ZET]findByAccessToken===================>{}", userToken);
        return userToken;
    }

    public void deleteByUsername(String username) {
        CenteroUserTokenEntity token = userTokenRepository.findByUsername(username);
        if (token != null) {
            userTokenRepository.deleteById(token.getAccessToken());
        }
    }
}
