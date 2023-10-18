package kr.centero.ghg.common.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

// @MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class MapStructMapperConfig {

}
