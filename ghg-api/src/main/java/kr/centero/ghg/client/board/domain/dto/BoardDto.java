package kr.centero.ghg.client.board.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import kr.centero.core.common.pagination.PageResponse;
import kr.centero.core.common.payload.BaseRequest;
import kr.centero.core.common.payload.PageDtoResponse;
import kr.centero.ghg.client.board.domain.model.Board;
import kr.centero.ghg.client.board.mapstruct.BoardMapstruct;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {

    /**
     * 게시글 검색 조건 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "게시글 검색 조건", description = "게시글 검색 조건 조회 요청 DTO")
    public static class BoardRequest {
        @Schema(title = "게시글 번호", example = "1")
        @NotBlank(message = "{dto.brdSeq.blank}")
        private Long brdSeq;
    }

    /**
     * 게시글 검색 조건 페이징 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @Schema(title = "게시글 검색 조건 페이징", description = "게시글 검색 조건 페이징 조회 요청 DTO")
    public static class BoardPageRequest extends BaseRequest {
        @Schema(title = "검색조건", allowableValues = {"null", "0", "1", "2"})
        private String searchType;

        @Schema(title = "검색어", example = "String")
        private String searchText;
    }

    /**
     * 게시글 등록 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "게시글 등록 요청", description = "게시글 등록 요청 DTO")
    public static class BoardInsertRequest {
        @Schema(title = "부모 게시글 번호", type = "Long")
        private Long brdParentSeq;

        @Schema(title = "게시글 카테고리", type = "String", example = "KCS_NEWS")
        @NotEmpty(message = "{dto.brdCategory.blank}")
        private String brdCategory;

        @Schema(title = "게시글 제목", example = "title")
        @NotEmpty(message = "{dto.brdTitle.blank}")
        private String brdTitle;

        @Schema(title = "게시글 내용", example = "contents")
        @NotEmpty(message = "{dto.brdContents.blank}")
        private String brdContents;

        @Schema(title = "게시글 상단 노출 기간", type = "Storing")
        private String topEndDt;

        @Schema(title = "접속자 ID", description = "작성자/수정자", example = "ID")
        private String loginUserId;
    }

    /**
     * 게시글 수정 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "게시글 수정 요청", description = "게시글 수정 요청 DTO")
    public static class BoardUpdateRequest {
        @Schema(title = "게시글 번호", type = "Long")
        @NotBlank(message = "{dto.brdSeq.blank}")
        private Long brdSeq;

        @Schema(title = "게시글 카테고리", type = "String", example = "KCS_NEWS")
        private String brdCategory;

        @Schema(title = "게시글 제목", type = "String", example = "Title")
        private String brdTitle;

        @Schema(title = "게시글 내용", type = "String", example = "Contents")
        private String brdContents;

        @Schema(title = "게시글 상단 노출 여부", type = "Storing", allowableValues = {"Y", "N"})
        private String topYn;

        @Schema(title = "게시글 상단 노출 기간", type = "Storing")
        private String topEndDt;

        @Schema(title = "접속자 ID", description = "작성자/수정자", example = "ID")
        private String loginUserId;
    }

    /**
     * 게시글 삭제 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "게시글 삭제 요청", description = "게시글 삭제 요청 DTO")
    public static class BoardDeleteRequest {
        @Schema(title = "게시글 번호", type = "Long")
        @NotBlank(message = "{dto.brdSeq.blank}")
        private Long brdSeq;

        @Schema(title = "접속자 ID", description = "작성자/수정자", example = "ID")
        private String loginUserId;
    }

    /**
     * 게시글 조회 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "게시글 조회 응답", description = "게시글 조회 응답 DTO")
    public static class BoardResponse {
        @Schema(title = "게시글 번호", type = "Long")
        private Long brdSeq;
        @Schema(title = "부모 게시글 번호", type = "Long")
        private Long brdParentSeq;
        @Schema(title = "게시글 카테고리", type = "String", example = "KCS_NEWS")
        private String brdCategory;
        @Schema(title = "게시글 제목", type = "String", example = "Title")
        private String brdTitle;
        @Schema(title = "게시글 내용", type = "String", example = "Contents")
        private String brdContents;
        @Schema(title = "게시글 상단 노출 여부", type = "Storing", allowableValues = {"Y", "N"})
        private String topYn;
        @Schema(title = "게시글 상단 노출 기간", type = "Storing")
        private String topEndDt;
        @Schema(title = "조회수", type = "Long")
        private Long readCnt;
        @Schema(title = "삭제 여부", type = "Storing", allowableValues = {"Y", "N"})
        private String deletedYn;
        @Schema(title = "첨부파일 갯수", type = "Long")
        private Long attachfileCnt;
        @Schema(title = "댓글 갯수", type = "Long")
        private Long commentCnt;
        @Schema(title = "최신글 여부", type = "Storing", allowableValues = {"Y", "N"})
        private String newYn;
        @Schema(title = "생성자 ID", example = "ID")
        private String createId;
        @Schema(title = "생성자 명 (조직명)", example = "Name")
        private String createNm;
        @Schema(title = "생성일시", type = "DateTime", format = "YYYY-MM-DD hh:mm:ss")
        private LocalDateTime createDt;
        @Schema(title = "수정자 ID", example = "ID")
        private String updateId;
        @Schema(title = "수정자 명 (조직명)", example = "Name")
        private String updateNm;
        @Schema(title = "수정일시", type = "DateTime", format = "YYYY-MM-DD hh:mm:ss")
        private LocalDateTime updateDt;
        private Long rowNum;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BoardPageDtoResponse extends PageDtoResponse {
        private List<BoardResponse> boards;
        private long total;
        private int pageNo;
        private int pageSize;
        private boolean isFirst;
        private boolean hasNext;
        private boolean isLast;

        // Model Page -> DTO Page
        public static BoardPageDtoResponse fromPageResponse(PageResponse<Board> pageResponse) {
            return convertPageDto(pageResponse, page -> BoardPageDtoResponse.builder()
                    .boards(BoardMapstruct.INSTANCE.toBoardDto(page.getList()))
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

