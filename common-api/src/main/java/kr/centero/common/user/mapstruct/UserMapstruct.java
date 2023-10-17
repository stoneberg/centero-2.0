package kr.centero.common.user.mapstruct;

import kr.centero.common.common.config.MapStructMapperConfig;
import kr.centero.common.user.domain.dto.UserDto;
import kr.centero.common.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface UserMapstruct {
    UserMapstruct INSTANCE = Mappers.getMapper(UserMapstruct.class);
    // roleName -> role
    @Mapping(source = "roleName", target = "role")
    UserDto.UserResponse toUserDto(User user);

    List<UserDto.UserResponse> toUserDto(List<User> users);
}
