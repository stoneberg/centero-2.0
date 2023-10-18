package kr.centero.ghg.admin.mdm.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import kr.centero.ghg.admin.mdm.domain.model.Code;
import kr.centero.ghg.admin.mdm.mapstruct.CodeMapstruct;
import kr.centero.ghg.common.mybatis.pagination.PageResponse;
import kr.centero.ghg.common.payload.PageDtoResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodeRequest {
        private String searchText;
        private String[] pCodeCd;
        private String[] period;
        private String useYn;
    }

    /**
     * 페이징 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodePageRequest {
        private String searchText;
        private String[] pCodeCd;
        private String[] period;
        private String useYn;
        private Integer pageNo;
        private Integer pageSize;
    }

    /**
     * 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodeResponse {
        private String codeCd;
        private String codeNm;
        private String codeLvl;
        private String pCodeCd;
        private String dspOrder;
        private String useYn;
        private String expFrDt;
        private String expToDt;
        private String attr1Json;
        private String attr1Val;
        private String attr2Json;
        private String attr2Val;
        private String attr3Json;
        private String attr3Val;
        private String attr4Json;
        private String attr4Val;
        private String attr5Json;
        private String attr5Val;
        private String attr6Json;
        private String attr6Val;
        private String attr7Json;
        private String attr7Val;
        private String attr8Json;
        private String attr8Val;
        private String attr9Json;
        private String attr9Val;
        private String attr10Json;
        private String attr10Val;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CodePageDtoResponse extends PageDtoResponse {
        private List<CodeResponse> codes;
        private long total;
        private int pageNo;
        private int pageSize;
        private boolean isFirst;
        private boolean hasNext;
        private boolean isLast;

        // Model Page -> DTO Page
        public static CodePageDtoResponse fromPageResponse(PageResponse<Code> pageResponse) {
            return convertPageDto(pageResponse, page -> CodePageDtoResponse.builder()
                    .codes(CodeMapstruct.INSTANCE.toCodeDto(page.getList()))
                    .total(page.getTotal())
                    .pageNo(page.getPageNo())
                    .pageSize(page.getPageSize())
                    .isFirst(page.isFirst())
                    .isLast(page.isLast())
                    .hasNext(page.hasNext())
                    .build());
        }
    }

}
