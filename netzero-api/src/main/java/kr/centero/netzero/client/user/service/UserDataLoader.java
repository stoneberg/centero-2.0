package kr.centero.netzero.client.user.service;

import kr.centero.netzero.client.auth.domain.enums.ERole;
import kr.centero.netzero.client.auth.domain.model.Role;
import kr.centero.netzero.client.auth.mapper.RoleMapper;
import kr.centero.netzero.client.auth.mapper.UserRoleMapper;
import kr.centero.netzero.client.user.domain.model.User;
import kr.centero.netzero.client.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"default", "h2", "local"})
public class UserDataLoader implements CommandLineRunner {
    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // ROLE("ADMIN", "USER") 모두 삭제
        userRoleMapper.deleteAll();
        roleMapper.deleteAll();
        userMapper.deleteAll();

        // ROLE("ADMIN", "USER") 생성
        Role adminRole = new Role(ERole.ADMIN);
        roleMapper.save(adminRole);

        Role userRole = new Role(ERole.USER);
        roleMapper.save(userRole);

        // USER 생성
        Faker faker = new Faker();
        for (int i = 1; i <= 21; i++) {
            User user = new User();
            user.setUsername(faker.basketball().players());
            user.setPassword(passwordEncoder.encode("pwd1"));
            user.setEmail(faker.internet().emailAddress());
            userMapper.save(user);

            // USER ROLE 부여
            // 3의 배수는 ADMIN, USER ROLE 부여, 그 외는 USER ROLE 부여
            if (i % 3 == 0) {
                userRoleMapper.save(user.getUserId(), adminRole.getRoleId());
                userRoleMapper.save(user.getUserId(), userRole.getRoleId());
            } else {
                userRoleMapper.save(user.getUserId(), userRole.getRoleId());
            }
        }

        this.createSpecificNameMasterRole("Lee", faker, adminRole, userRole);
        this.createSpecificNameAdminRole("Hong", faker, adminRole);
        this.createSpecificNameUserRole("Park", faker, userRole);
    }

    public void createSpecificNameMasterRole(String specificName, Faker faker, Role adminRole, Role userRole) {
        User user = new User();
        user.setUsername(specificName);
        user.setPassword(passwordEncoder.encode("pwd1"));
        user.setEmail(faker.internet().emailAddress());
        userMapper.save(user);
        userRoleMapper.save(user.getUserId(), adminRole.getRoleId());
        userRoleMapper.save(user.getUserId(), userRole.getRoleId());
    }

    public void createSpecificNameAdminRole(String specificName, Faker faker, Role adminRole) {
        User user = new User();
        user.setUsername(specificName);
        user.setPassword(passwordEncoder.encode("pwd1"));
        user.setEmail(faker.internet().emailAddress());
        userMapper.save(user);
        userRoleMapper.save(user.getUserId(), adminRole.getRoleId());
    }

    public void createSpecificNameUserRole(String specificName, Faker faker, Role userRole) {
        User user = new User();
        user.setUsername(specificName);
        user.setPassword(passwordEncoder.encode("pwd1"));
        user.setEmail(faker.internet().emailAddress());
        userMapper.save(user);
        userRoleMapper.save(user.getUserId(), userRole.getRoleId());
    }

}
