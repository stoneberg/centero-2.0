package kr.centero.common.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testNewPasswordEncode() {
        /* !!! 먼저 새롭게 생성된 PasswordEncoder 의 주석을 해제하고 테스트 하세요.  */
        // 평문:dbsldhs2030!
        // 암호문:7f4580988ebcc2ca4f269b16fb6a5835eac21e4f3fe6e24e13ba31136a1e3092f079e6f79c05832a6fc0428bf248da8960e2e03d085481bd314f7c4ff8cd978b

        String rawPassword = "dbsldhs2030";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        log.info("encodedPassword: {}", encodedPassword);

        // 암호화된 비밀번호가 일치하는지 검사
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
}
