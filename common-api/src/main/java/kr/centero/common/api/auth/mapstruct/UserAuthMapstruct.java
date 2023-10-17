package kr.centero.common.api.auth.mapstruct;

import kr.centero.common.api.auth.domain.dto.UserAuthDto;
import kr.centero.common.api.common.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructMapperConfig.class)
public interface UserAuthMapstruct {
    UserAuthMapstruct INSTANCE = Mappers.getMapper(UserAuthMapstruct.class);

    User toUserModel(UserAuthDto.SignupRequest signupRequest);
}
