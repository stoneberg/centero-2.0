package kr.centero.netzero.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

// @MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@MapperConfig(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class MapStructMapperConfig {

}
