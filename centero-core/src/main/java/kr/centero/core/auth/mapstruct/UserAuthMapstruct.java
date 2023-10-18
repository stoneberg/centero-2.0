package kr.centero.core.auth.mapstruct;

import kr.centero.core.auth.domain.dto.UserAuthDto;
import kr.centero.core.auth.domain.model.SignupUser;
import kr.centero.core.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructMapperConfig.class)
public interface UserAuthMapstruct {
    UserAuthMapstruct INSTANCE = Mappers.getMapper(UserAuthMapstruct.class);

    SignupUser toUserModel(UserAuthDto.SignupRequest signupRequest);
}
