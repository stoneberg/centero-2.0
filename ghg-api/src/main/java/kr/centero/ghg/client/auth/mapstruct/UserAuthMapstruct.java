package kr.centero.ghg.client.auth.mapstruct;

import kr.centero.ghg.client.auth.domain.dto.UserAuthDto;
import kr.centero.ghg.client.auth.domain.model.SignupUser;
import kr.centero.ghg.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapStructMapperConfig.class)
public interface UserAuthMapstruct {
    UserAuthMapstruct INSTANCE = Mappers.getMapper(UserAuthMapstruct.class);

    SignupUser toUserModel(UserAuthDto.SignupRequest signupRequest);
}
