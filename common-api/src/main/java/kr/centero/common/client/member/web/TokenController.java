package kr.centero.common.client.member.web;

import kr.centero.common.client.auth.domain.entity.CenteroUserTokenEntity;
import kr.centero.common.client.auth.service.UserTokenRedisService;
import kr.centero.common.client.member.domain.entity.Member;
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
     * redis test
     */


    // save
    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody CenteroUserTokenEntity tokenEntity) {
        userTokenRedisService.save(tokenEntity);
        return ApiResponse.ok();
    }

    // update
    @PutMapping("/{accessToken}")
    public ResponseEntity<ApiResponse> update(@PathVariable String accessToken, @RequestBody CenteroUserTokenEntity tokenEntity) {
        userTokenRedisService.update(accessToken, tokenEntity);
        return ApiResponse.ok();
    }

    // find by accessToken
    @GetMapping("/{accessToken}")
    public ResponseEntity<ApiResponse> findByAccessToken(@PathVariable String accessToken) {
        CenteroUserTokenEntity userToken = userTokenRedisService.findByAccessToken(accessToken);
        log.info("[ZET]find userToken: {}", userToken);
        return ApiResponse.ok(userToken);
    }

    // find by username
    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse> findByUsername(@PathVariable String username) {
        CenteroUserTokenEntity userToken = userTokenRedisService.findByUsername(username);
        log.info("[ZET]find userToken: {}", userToken);
        return ApiResponse.ok(userToken);
    }

    // delete by accessToken
    @DeleteMapping("/{accessToken}")
    public ResponseEntity<ApiResponse> deleteByAccessToken(@PathVariable String accessToken) {
        userTokenRedisService.deleteByAccessToken(accessToken);
        return ApiResponse.ok();
    }

    // delete by username
    @DeleteMapping("/username/{username}")
    public ResponseEntity<ApiResponse> deleteByUsername(@PathVariable String username) {
        userTokenRedisService.deleteByUsername(username);
        return ApiResponse.ok();
    }


}
