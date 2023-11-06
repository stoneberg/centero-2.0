package kr.centero.common.client.member.web;

import kr.centero.common.client.auth.domain.entity.RedisTokenEntity;
import kr.centero.common.client.auth.service.UserTokenRedisService;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/tokens")
public class TokenController {

    private final UserTokenRedisService userTokenRedisService;

    /**
     * This is Test Controller !!
     * redis test
     */


    // save
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> save(@RequestBody RedisTokenEntity tokenEntity) {
        userTokenRedisService.save(tokenEntity);
        return ApiResponse.ok();
    }

    // update
    @PutMapping("/{accessToken}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable String accessToken, @RequestBody RedisTokenEntity tokenEntity) {
        userTokenRedisService.update(accessToken, tokenEntity);
        return ApiResponse.ok();
    }

    // find by accessToken
    @GetMapping("/{accessToken}")
    public ResponseEntity<ApiResponse<RedisTokenEntity>> findByAccessToken(@PathVariable String accessToken) {
        RedisTokenEntity userToken = userTokenRedisService.findByAccessToken(accessToken);
        log.info("[ZET]find userToken: {}", userToken);
        return ApiResponse.of(userToken);
    }

    // find by username
    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<RedisTokenEntity>> findByUsername(@PathVariable String username) {
        RedisTokenEntity userToken = userTokenRedisService.findByUsername(username);
        log.info("[ZET]find userToken: {}", userToken);
        return ApiResponse.of(userToken);
    }

    // delete by accessToken
    @DeleteMapping("/{accessToken}")
    public ResponseEntity<ApiResponse<Void>> deleteByAccessToken(@PathVariable String accessToken) {
        userTokenRedisService.deleteByAccessToken(accessToken);
        return ApiResponse.ok();
    }

    // delete by username
    @DeleteMapping("/username/{username}")
    public ResponseEntity<ApiResponse<Object>> deleteByUsername(@PathVariable String username) {
        userTokenRedisService.deleteByUsername(username);
        return ApiResponse.ok();
    }


}
