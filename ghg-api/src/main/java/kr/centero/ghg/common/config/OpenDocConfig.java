package kr.centero.ghg.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenDocConfig {
        // http://localhost:8080/swagger-ui/index.html
        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder()
                                .group("v1")
                                .pathsToMatch("/api/**")
                                .build();
        }

        @Bean
        public OpenAPI openAPI() {

                Info info = new Info()
                                .version("v2.0.0")
                                .title("Centero-Common 2.0")
                                .description("Centero-Common 2.0 API");

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
