package kr.centero.netzero.client.user.service;

import kr.centero.core.common.pagination.PageResponse;
import kr.centero.netzero.client.user.domain.dto.UserDto;
import kr.centero.netzero.client.user.domain.model.User;
import kr.centero.netzero.client.user.mapper.UserMapper;
import kr.centero.netzero.client.user.mapstruct.UserMapstruct;
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

    /**
     * find users by condition : return list of users
     *
     * @return list of users
     */
    public List<UserDto.UserResponse> findUsers() {
        return userMapper.findUserByCond(new User()).stream().map(UserMapstruct.INSTANCE::toUserDto).toList();
    }

    /**
     * find user by userId, username, email, role
     *
     * @param userRequest user request
     * @return UserResponse
     */
    public List<UserDto.UserResponse> findUserByCond(UserDto.UserRequest userRequest) {
        User user = UserMapstruct.INSTANCE.toUser(userRequest);
        return userMapper.findUserByCond(user).stream()
                .map(UserMapstruct.INSTANCE::toUserDto).toList();
    }

    /**
     * find user pages by condition
     *
     * @param userPageRequest user page request
     * @return UserPageDtoResponse
     */
    public UserDto.UserPageDtoResponse findPagesByCond(UserDto.UserPageRequest userPageRequest) {
        User user = UserMapstruct.INSTANCE.toUser(userPageRequest);
        PageResponse<User> pageResponse = userMapper.findUserPageByCond(user);
        log.info("[PAG]pageResponse=======>{}", pageResponse);

        // detail page info example
        List<User> list = pageResponse.getList();
        log.info("[PAG]list===============>{}", list);
        long total = pageResponse.getTotal();
        log.info("[PAG]total==============>{}", total);
        int pageNo = pageResponse.getPageNo();
        log.info("[PAG]pageNo=============>{}", pageNo);
        int pageSize = pageResponse.getPageSize();
        log.info("[PAG]pageSize===========>{}", pageSize);
        boolean first = pageResponse.isFirst();
        log.info("[PAG]first==============>{}", first);
        boolean last = pageResponse.isLast();
        log.info("[PAG]last===============>{}", last);
        boolean next = pageResponse.hasNext();
        log.info("[PAG]next===============>{}", next);

        // convert pageResponse to UserPageDtoResponse
        return UserDto.UserPageDtoResponse.fromPageResponse(pageResponse);
    }

    /**
     * update user
     *
     * @param userUpdateRequest user update request
     */
    @Transactional
    public void updateUser(UserDto.UserUpdateRequest userUpdateRequest) {
        User user = UserMapstruct.INSTANCE.toUser(userUpdateRequest);
        userMapper.update(user);
    }


}
