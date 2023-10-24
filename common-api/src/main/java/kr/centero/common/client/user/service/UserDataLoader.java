package kr.centero.common.client.user.service;

import kr.centero.common.client.auth.domain.enums.ERole;
import kr.centero.common.client.auth.domain.model.Role;
import kr.centero.common.client.auth.mapper.RoleMapper;
import kr.centero.common.client.auth.mapper.UserRoleMapper;
import kr.centero.common.client.user.domain.model.User;
import kr.centero.common.client.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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

        Map<String, Role> roleMap = new HashMap<>();

        // ROLE("ADMIN", "USER") 생성
//        Role adminRole = new Role(ERole.ADMIN);
//        roleMapper.save(adminRole);
//        roleMap.put(adminRole.getRoleName().name(), adminRole);

        Role ctrAdminRole = new Role(ERole.CENTERO_ADMIN);
        roleMapper.save(ctrAdminRole);
        roleMap.put(ctrAdminRole.getRoleName().name(), ctrAdminRole);

        Role nzrAdminRole = new Role(ERole.NETZERO_ADMIN);
        roleMapper.save(nzrAdminRole);
        roleMap.put(nzrAdminRole.getRoleName().name(), nzrAdminRole);

//        Role userRole = new Role(ERole.USER);
//        roleMapper.save(userRole);
//        roleMap.put(userRole.getRoleName().name(), userRole);

        Role ctrUserRole = new Role(ERole.CENTERO_USER);
        roleMapper.save(ctrUserRole);
        roleMap.put(ctrUserRole.getRoleName().name(), ctrUserRole);

        Role nzrUserRole = new Role(ERole.NETZERO_USER);
        roleMapper.save(nzrUserRole);
        roleMap.put(nzrUserRole.getRoleName().name(), nzrUserRole);

        // USER 생성
        Faker faker = new Faker();
        for (int i = 1; i <= 21; i++) {
            User user = new User();
            user.setUsername(faker.name().firstName() + " " + faker.name().lastName());
            user.setPassword(passwordEncoder.encode("pwd1"));
            user.setEmail(faker.internet().emailAddress());
            userMapper.save(user);

            // USER ROLE 부여
//            if (i % 3 == 0) {
//                userRoleMapper.save(user.getUserId(), adminRole.getRoleId());
//                userRoleMapper.save(user.getUserId(), userRole.getRoleId());
//            } else

            if (i % 5 == 0) {
                userRoleMapper.save(user.getUserId(), ctrAdminRole.getRoleId());
                userRoleMapper.save(user.getUserId(), ctrUserRole.getRoleId());
            } else if (i % 7 == 0) {
                userRoleMapper.save(user.getUserId(), nzrAdminRole.getRoleId());
                userRoleMapper.save(user.getUserId(), nzrUserRole.getRoleId());
            } else {
                userRoleMapper.save(user.getUserId(), ctrUserRole.getRoleId());
                userRoleMapper.save(user.getUserId(), nzrUserRole.getRoleId());
            }
        }

        this.createSpecificNameMasterRole("Lee", faker, roleMap);
        this.createSpecificNameAdminRole("Hong", faker, roleMap);
        this.createSpecificNameUserRole("Park", faker, roleMap);
        this.createSpecificDomainUserRole("Kim", faker, "CENTERO", roleMap);
        this.createSpecificDomainUserRole("Choi", faker, "NETZERO", roleMap);
    }

    public void createSpecificNameMasterRole(String specificName, Faker faker, Map<String, Role> roleMap) {
        User master = new User();
        master.setUsername(specificName);
        master.setPassword(passwordEncoder.encode("pwd1"));
        master.setEmail(faker.internet().emailAddress());
        userMapper.save(master);

//        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.ADMIN.name()).getRoleId());
        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.CENTERO_ADMIN.name()).getRoleId());
        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.NETZERO_ADMIN.name()).getRoleId());
//        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.USER.name()).getRoleId());
        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.CENTERO_USER.name()).getRoleId());
        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.NETZERO_USER.name()).getRoleId());
    }

    public void createSpecificNameAdminRole(String specificName, Faker faker, Map<String, Role> roleMap) {
        User admin = new User();
        admin.setUsername(specificName);
        admin.setPassword(passwordEncoder.encode("pwd1"));
        admin.setEmail(faker.internet().emailAddress());
        userMapper.save(admin);
//        userRoleMapper.save(admin.getUserId(), roleMap.get(ERole.ADMIN.name()).getRoleId());
        userRoleMapper.save(admin.getUserId(), roleMap.get(ERole.CENTERO_ADMIN.name()).getRoleId());
        userRoleMapper.save(admin.getUserId(), roleMap.get(ERole.NETZERO_ADMIN.name()).getRoleId());
    }

    public void createSpecificNameUserRole(String specificName, Faker faker, Map<String, Role> roleMap) {
        User user = new User();
        user.setUsername(specificName);
        user.setPassword(passwordEncoder.encode("pwd1"));
        user.setEmail(faker.internet().emailAddress());
        userMapper.save(user);
//        userRoleMapper.save(user.getUserId(), roleMap.get(ERole.USER.name()).getRoleId());
        userRoleMapper.save(user.getUserId(), roleMap.get(ERole.CENTERO_USER.name()).getRoleId());
        userRoleMapper.save(user.getUserId(), roleMap.get(ERole.NETZERO_USER.name()).getRoleId());
    }

    public void createSpecificDomainUserRole(String specificName, Faker faker, String domain, Map<String, Role> roleMap) {
        User user = new User();
        user.setUsername(specificName);
        user.setPassword(passwordEncoder.encode("pwd1"));
        user.setEmail(faker.internet().emailAddress());
        userMapper.save(user);

        if ("CENTERO".equals(domain)) {
            userRoleMapper.save(user.getUserId(), roleMap.get(ERole.CENTERO_USER.name()).getRoleId());
        } else if ("NETZERO".equals(domain)) {
            userRoleMapper.save(user.getUserId(), roleMap.get(ERole.NETZERO_USER.name()).getRoleId());
        }

//        userRoleMapper.save(user.getUserId(), roleMap.get(ERole.USER.name()).getRoleId());
    }

}
