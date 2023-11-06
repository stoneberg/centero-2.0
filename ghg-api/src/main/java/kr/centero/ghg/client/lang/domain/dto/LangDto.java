package kr.centero.ghg.client.lang.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class LangDto {

    /**
     * 역할별 메뉴 목록 요청
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LangRequest {
        /**
         * 로케일 코드 ERole
         */
        @Schema(description = "로케일 코드", defaultValue = "ko_KR", allowableValues = {"ko_KR", "en-US"})
        private String locale;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LangResponse {
        /**
         * 마지막 수정일
         */
        @Schema(description = "마지막 수정일")
        private String lastDt;

        /**
         * Locale
         */
        @Schema(description = "로케일 코드", defaultValue = "ko_KR", allowableValues = {"ko_KR", "en-US"})
        private String locale;

        /**
         * Locale의 언어팩 리소스
         */
        @Builder.Default
        private List<Lang> resources = new ArrayList<>();
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Lang {
        /**
         * 언어 코드
         */
        @Schema(description = "언어 코드")
        private String langCd;
        /**
         * 언어 코드의 텍스트
         */
        @Schema(description = "언어 코드의 텍스트")
        private String dspText;
    }
}
