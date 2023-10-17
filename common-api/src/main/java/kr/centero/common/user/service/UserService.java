package kr.centero.common.user.service;

import kr.centero.common.common.util.UserUtils;
import kr.centero.common.user.domain.dto.UserDto;
import kr.centero.common.user.domain.model.User;
import kr.centero.common.user.mapper.UserMapper;
import kr.centero.common.user.mapstruct.UserMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public List<UserDto.UserResponse> findAll() {
        String authenticatedUserName = UserUtils.getAuthenticatedUserName();
        log.info("authenticatedUserName: {}", authenticatedUserName);
        List<User> users = userMapper.findAll();
        return UserMapstruct.INSTANCE.toUserDto(users);
    }

}
