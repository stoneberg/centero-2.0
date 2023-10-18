package kr.centero.ghg.auth.mapstruct;

import kr.centero.ghg.auth.domain.dto.UserAuthDto;
import kr.centero.ghg.auth.domain.model.SignupUser;
import kr.centero.ghg.common.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructMapperConfig.class)
public interface UserAuthMapstruct {
    UserAuthMapstruct INSTANCE = Mappers.getMapper(UserAuthMapstruct.class);

    SignupUser toUserModel(UserAuthDto.SignupRequest signupRequest);
}
