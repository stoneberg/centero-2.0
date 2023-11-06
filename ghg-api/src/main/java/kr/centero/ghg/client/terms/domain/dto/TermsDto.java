package kr.centero.ghg.client.terms.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TermsDto {

    /**
     * 약관 조회 객체
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsRequest {
        @Schema(title = "약관 ID", example = "1")
        private int termsId;

        @Schema(title = "서비스 타입", example = "GHG")
        public String serviceType;

        @Schema(title = "차수", example = "1")
        private int version;

        @Schema(title = "약관 유형", example = "PS")
        public String termsType;

        @Schema(title = "타이틀", example = "센테로 웹 사이트 이용 약관")
        public String title;

        @Schema(title = "약관 내용")
        public String content;

        @Schema(title = "사용 여부", example = "Y")
        public String isUse;

        @Schema(title = "필수 동의 약관 여부", example = "Y")
        public String isRequired;
    }

    /**
     * 약관 저장 객체
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsSaveRequest{
        @Schema(title = "서비스 타입", example = "GHG")
        @NotBlank
        public String serviceType;

        @Schema(title = "약관 유형", example = "PS")
        @NotBlank
        public String termsType;

        @Schema(title = "타이틀", example = "센테로 웹 사이트 이용 약관")
        @NotBlank
        public String title;

        @Schema(title = "약관 내용")
        @NotBlank
        public String content;

        @Schema(title = "사용 여부", example = "Y", defaultValue = "N")
        public String isUse;

        @Schema(title = "필수 동의 약관 여부", example = "Y", defaultValue = "N")
        public String isRequired;

        @Schema(title = "노출 순서", example = "1")
        public int displayOrder;

        @Schema(title = "약관 적용 일자", example = "2023-11-01", type = "string")
        public String openedAt;

        @Schema(title = "생성자ID", example = "admin_centero")
        public String createId;

        @Schema(title = "수정자ID", example = "admin_centero")
        public String modifyId;
    }


    /**
     * 약관 저장 객체
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsModifyRequest{
        @Schema(title = "약관 ID", example = "1")
        @NotBlank
        private int termsId;

        @Schema(title = "서비스 타입", example = "GHG")
        @NotBlank
        public String serviceType;

        @Schema(title = "타이틀", example = "센테로 웹 사이트 이용 약관")
        @NotBlank
        public String title;

        @Schema(title = "약관 내용")
        public String content;

        @Schema(title = "사용 여부", example = "Y", defaultValue = "N")
        public String isUse;

        @Schema(title = "노출 순서", example = "1")
        public int displayOrder;

        @Schema(title = "약관 적용 일자", example = "2023-11-01", type = "string")
        public String openedAt;

        @Schema(title = "생성자ID", example = "admin_centero")
        public String createId;

        @Schema(title = "수정자ID", example = "admin_centero")
        public String modifyId;
    }

    /**
     * 약관 개정 객체
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsRevisionRequest{
        @Schema(title = "약관 ID", example = "1")
        @NotBlank
        private int termsId;

        @Schema(title = "타이틀", example = "센테로 웹 사이트 이용 약관")
        @NotBlank
        public String title;

        @Schema(title = "약관 내용")
        @NotBlank
        public String content;

        @Schema(title = "사용 여부", example = "Y", defaultValue = "N")
        public String isUse;

        @Schema(title = "필수 동의 약관 여부", example = "Y", defaultValue = "N")
        public String isRequired;

        @Schema(title = "약관 적용 일자", example = "2023-11-01", type = "string")
        public String openedAt;

        @Schema(title = "생성자ID", example = "admin_centero")
        public String createId;

        @Schema(title = "수정자ID", example = "admin_centero")
        public String modifyId;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsResponse{
        @Schema(title = "사용자ID")
        private String accountId;

        @Schema(title = "약관")
        private List<TermsDto.Terms> terms = new ArrayList<>();
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Terms
    {
        private int termsId;
        private String serviceType;
        private int version;
        private String termsType;
        private String title;
        private String content;
        private String openedAt;
        private String isUse;
        private String isRequired;
        private int displayOrder;
        private Date createDt;
        private String createId;
        private Date modifyDt;
        private String modifyId;
        private String versionList;
        private List<Integer> list;

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }
}
