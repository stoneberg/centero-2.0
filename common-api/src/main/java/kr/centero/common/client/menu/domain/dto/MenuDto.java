package kr.centero.common.client.menu.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.centero.common.client.menu.domain.model.RoleRights;
import kr.centero.core.common.enums.codes.ELocale;
import kr.centero.core.common.enums.codes.ERole;
import kr.centero.core.common.payload.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuDto {

    /**
     * 역할별 메뉴 목록 요청
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuRequest {
        /**
         * 서비스 코드
         */
        @Schema(description = "서비스 코드", defaultValue = "", allowableValues = {"ghg", "netzero"})
        private String svcCd;
        
        /**
         * 로케일 코드 ERole
         */
        @Schema(description = "로케일 코드", defaultValue = "ko_KR", allowableValues = {"ko_KR", "en-US"})
        private String locale = ELocale.KO_KR.getCode();

        /**
         * 사용자 권한
         */
        @Schema(description = "사용자 권한", defaultValue = "AnonymousRole", allowableValues = {"AnonymousRole"})
        public String role = ERole.Anonymous.getCode();
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuResponse{
        /**
         * 로케일 코드 ERole
         */
        @Schema(description = "로케일 코드", defaultValue = "ko_KR", allowableValues = {"ko_KR", "en-US"})
        private String locale;

        /**
         * 사용자 권한
         */
        @Schema(description = "사용자 권한")
        private String role;


        /**
         * 사용자 권한(role)의 메뉴 목록 ( Read 권한이 있는 목록 반환 )
         */
        @Schema(description = "권한별 메뉴 목록")
        private List<Menu> menus = new ArrayList<>();
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Menu
    {
        @Schema(description = "메뉴 레벨")
        private int treeLvl;
        @Schema(description = "메뉴 정렬")
        private String treeOrder;
        @Schema(description = "메뉴 경로")
        private String treePath;
        @Schema(description = "언어 코드")
        private String langCd;
        @Builder.Default
        private RoleRights rights = new RoleRights();
    }
}
