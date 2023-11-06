package kr.centero.netzero.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Optional;

@Configuration
public class OpenDocConfig {
    // http://localhost:8082/swagger-ui/index.html
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/**")
                .build();
    }


    /**
     * 공통 파라미터 HTTP HEADER TimeZone 추가
     * @return
     */
    @Bean
    public OperationCustomizer customGlobalHeaders() {
        String TIMEZONE_HEADER = "TimeZone";

        return (Operation operation, HandlerMethod handlerMethod) -> {
            Optional<List<Parameter>> isParameterPresent = Optional.ofNullable(operation.getParameters());
            Boolean isTestHeaderPresent = Boolean.FALSE;
            if (isParameterPresent.isPresent()) {
                isTestHeaderPresent = isParameterPresent.get().stream()
                        .anyMatch(param -> param.getName().equalsIgnoreCase(TIMEZONE_HEADER));
            }
            if (Boolean.FALSE.equals(isTestHeaderPresent)) {
                Parameter remoteUser = new Parameter()
                        .in(ParameterIn.HEADER.toString())
                        .schema(new StringSchema()._default("Asia/Seoul") )
                        .name(TIMEZONE_HEADER)
                        .description("고객 TimeZone")
                        .example("Asia/Seoul")
                        .required(true);
                operation.addParametersItem(remoteUser);
            }
            return operation;
        };
    }


    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("Centero-Netzero 1.0")
                .description("Centero-Netzero 1.0 API");

        // SecuritySecheme명
        String jwtSchemeName = "jwtAuth";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("Bearer")
                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}
