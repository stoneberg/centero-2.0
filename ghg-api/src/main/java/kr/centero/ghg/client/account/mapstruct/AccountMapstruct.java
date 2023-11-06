package kr.centero.ghg.client.account.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.centero.ghg.config.MapStructMapperConfig;

@Mapper(config = MapStructMapperConfig.class)
public interface AccountMapstruct {
    AccountMapstruct INSTANCE = Mappers.getMapper(AccountMapstruct.class);

}
