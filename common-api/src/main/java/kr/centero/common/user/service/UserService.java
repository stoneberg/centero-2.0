package kr.centero.common.user.service;

import kr.centero.common.common.exception.BusinessErrorCode;
import kr.centero.common.common.exception.BusinessException;
import kr.centero.common.common.mybatis.pagination.PageResponse;
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

    /**
     * find users by condition : return list of users
     *
     * @return list of users
     */
    public List<UserDto.UserResponse> findUsers() {
        return userMapper.findAllByCond(new User()).stream().map(UserMapstruct.INSTANCE::toUserDto).toList();
    }

    /**
     * find user by id : return a user
     *
     * @param userId user id (primary key)
     * @return user
     */
    public UserDto.UserResponse findUser(Long userId) {
        return userMapper.findUserByCond(userId, null)
                .map(UserMapstruct.INSTANCE::toUserDto)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.USER_NOT_FOUND));
    }

    /**
     * find user by username, email, role
     *
     * @param userId user id (primary key)
     * @param userDetailRequest user detail request
     * @return user
     */
    public UserDto.UserResponse findUserByCond(Long userId, UserDto.UserDetailRequest userDetailRequest) {
        User user = UserMapstruct.INSTANCE.toUser(userDetailRequest);
        return userMapper.findUserByCond(userId, user)
                .map(UserMapstruct.INSTANCE::toUserDto)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.USER_NOT_FOUND));
    }


    /**
     * find user pages by condition
     *
     * @param userDetailRequest user detail request
     * @return user page dto response
     */
    public UserDto.UserPageDtoResponse findPagesByCond(Long userId, UserDto.UserDetailRequest userDetailRequest) {
        User user = UserMapstruct.INSTANCE.toUser(userDetailRequest);
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
     * @param userId user id (primary key)
     * @param userDetailRequest user detail request
     */
    public void updateUser(Long userId, UserDto.UserDetailRequest userDetailRequest) {
        User user = UserMapstruct.INSTANCE.toUser(userDetailRequest);
        userMapper.update(userId, user);
    }
}
