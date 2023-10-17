package kr.centero.ghg.api.user.service;

import kr.centero.ghg.api.user.domain.dto.UserDto;
import kr.centero.ghg.api.user.domain.model.User;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    public UserDto.UserCreateResponse createUser(UserDto.UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(userCreateRequest.getName())
                .email(userCreateRequest.getEmail())
                .build();
        return UserDto.UserCreateResponse.from(user);
    }

    public List<UserDto.UserResponse> findUsers() {
        List<User> userList = new ArrayList<>();
        Faker faker = new Faker();

        // make a user 10
        for (int i = 0; i < 10; i++) {
            // make a user
            User user = User.builder()
                    .id(UUID.randomUUID().toString())
                    .name(faker.name().firstName() + " " + faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .build();

            userList.add(user);
        }

        // User to UserDto.UserResponse
        return userList.stream().map(UserDto.UserResponse::new).toList();
    }

}
