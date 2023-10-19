package kr.centero.common.client.user.mapstruct;

import kr.centero.common.client.user.domain.dto.UserDto;
import kr.centero.common.client.user.domain.model.User;
import kr.centero.common.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface UserMapstruct {
    UserMapstruct INSTANCE = Mappers.getMapper(UserMapstruct.class);

    // role -> roleName (different field name mapping)
    @Mapping(source = "role", target = "roleName")
    User toUser(UserDto.UserRequest userRequest);

    @Mapping(source = "role", target = "roleName")
    User toUser(UserDto.UserPageRequest userRequest);

    User toUser(UserDto.UserUpdateRequest userUpdateRequest);

    // roleName -> role (different field name mapping)
    @Mapping(source = "roleName", target = "role")
    UserDto.UserResponse toUserDto(User user);

    List<UserDto.UserResponse> toUserDto(List<User> users);
}
