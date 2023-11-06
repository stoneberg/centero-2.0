package kr.centero.common.client.user.service;

import kr.centero.common.client.auth.domain.model.Role;
import kr.centero.common.client.auth.mapper.RoleMapper;
import kr.centero.common.client.auth.mapper.UserRoleMapper;
import kr.centero.common.client.user.domain.model.User;
import kr.centero.common.client.user.mapper.UserMapper;
import kr.centero.core.common.enums.roles.ERole;
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
@Profile({"default", "h2"})
public class UserDataLoader implements CommandLineRunner {
    private static final String CENTERO = "CENTERO";
    private static final String NETZERO = "NETZERO";
    private static final String PASSWORD = "pwd1";
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
            user.setPassword(passwordEncoder.encode(PASSWORD));
            user.setEmail(faker.internet().emailAddress());

            if (i % 2 == 0) {
                user.setDomain(CENTERO);
            } else {
                user.setDomain(NETZERO);
            }

            userMapper.save(user);

            // ROLE 부여
            if (i % 2 == 0) {
                userRoleMapper.save(user.getUserId(), ctrUserRole.getRoleId());
                if (i % 4 == 0) {
                    userRoleMapper.save(user.getUserId(), ctrAdminRole.getRoleId());
                }
            }  else {
                userRoleMapper.save(user.getUserId(), nzrUserRole.getRoleId());
                if (i % 7 == 0) {
                    userRoleMapper.save(user.getUserId(), nzrAdminRole.getRoleId());
                }
            }
        }

        this.createSpecificNameMasterRole("Lee", faker, roleMap);
        this.createSpecificNameAdminRole("Hong", faker, roleMap);
        this.createSpecificNameUserRole("Park", faker, roleMap);
        this.createSpecificDomainUserRole("Kim", faker, CENTERO, roleMap);
        this.createSpecificDomainUserRole("Choi", faker, NETZERO, roleMap);
    }

    public void createSpecificNameMasterRole(String specificName, Faker faker, Map<String, Role> roleMap) {
        String email = faker.internet().emailAddress();

        User master1 = new User();
        master1.setUsername(specificName);
        master1.setPassword(passwordEncoder.encode(PASSWORD));
        master1.setEmail(email);
        master1.setDomain(CENTERO);
        userMapper.save(master1);

        userRoleMapper.save(master1.getUserId(), roleMap.get(ERole.CENTERO_ADMIN.name()).getRoleId());
        userRoleMapper.save(master1.getUserId(), roleMap.get(ERole.CENTERO_USER.name()).getRoleId());

        User master2 = new User();
        master2.setUsername(specificName);
        master2.setPassword(passwordEncoder.encode(PASSWORD));
        master2.setEmail(email);
        master2.setDomain(NETZERO);
        userMapper.save(master2);

        userRoleMapper.save(master2.getUserId(), roleMap.get(ERole.NETZERO_ADMIN.name()).getRoleId());
        userRoleMapper.save(master2.getUserId(), roleMap.get(ERole.NETZERO_USER.name()).getRoleId());

//        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.ADMIN.name()).getRoleId());
//        userRoleMapper.save(master.getUserId(), roleMap.get(ERole.USER.name()).getRoleId());
    }

    public void createSpecificNameAdminRole(String specificName, Faker faker, Map<String, Role> roleMap) {
        String email = faker.internet().emailAddress();

        User admin1 = new User();
        admin1.setUsername(specificName);
        admin1.setPassword(passwordEncoder.encode(PASSWORD));
        admin1.setEmail(email);
        admin1.setDomain(CENTERO);
        userMapper.save(admin1);

        userRoleMapper.save(admin1.getUserId(), roleMap.get(ERole.CENTERO_ADMIN.name()).getRoleId());

        User admin2 = new User();
        admin2.setUsername(specificName);
        admin2.setPassword(passwordEncoder.encode(PASSWORD));
        admin2.setEmail(email);
        admin2.setDomain(NETZERO);
        userMapper.save(admin2);

        userRoleMapper.save(admin2.getUserId(), roleMap.get(ERole.NETZERO_ADMIN.name()).getRoleId());

//        userRoleMapper.save(admin.getUserId(), roleMap.get(ERole.ADMIN.name()).getRoleId());
    }

    public void createSpecificNameUserRole(String specificName, Faker faker, Map<String, Role> roleMap) {
        User user1 = new User();
        user1.setUsername(specificName);
        user1.setPassword(passwordEncoder.encode(PASSWORD));
        user1.setEmail(faker.internet().emailAddress());
        user1.setDomain(CENTERO);
        userMapper.save(user1);

        userRoleMapper.save(user1.getUserId(), roleMap.get(ERole.CENTERO_USER.name()).getRoleId());

        User user2 = new User();
        user2.setUsername(specificName);
        user2.setPassword(passwordEncoder.encode(PASSWORD));
        user2.setEmail(faker.internet().emailAddress());
        user2.setDomain(NETZERO);
        userMapper.save(user2);


        userRoleMapper.save(user2.getUserId(), roleMap.get(ERole.NETZERO_USER.name()).getRoleId());
//        userRoleMapper.save(user.getUserId(), roleMap.get(ERole.USER.name()).getRoleId());
    }

    public void createSpecificDomainUserRole(String specificName, Faker faker, String domain, Map<String, Role> roleMap) {
        User user = new User();
        user.setUsername(specificName);
        user.setPassword(passwordEncoder.encode(PASSWORD));
        user.setEmail(faker.internet().emailAddress());

        if (CENTERO.equals(domain)) {
            user.setDomain(CENTERO);
        } else if (NETZERO.equals(domain)) {
            user.setDomain(NETZERO);
        }

        userMapper.save(user);

        if (CENTERO.equals(domain)) {
            userRoleMapper.save(user.getUserId(), roleMap.get(ERole.CENTERO_USER.name()).getRoleId());
        } else if (NETZERO.equals(domain)) {
            userRoleMapper.save(user.getUserId(), roleMap.get(ERole.NETZERO_USER.name()).getRoleId());
        }

//        userRoleMapper.save(user.getUserId(), roleMap.get(ERole.USER.name()).getRoleId());
    }

}
