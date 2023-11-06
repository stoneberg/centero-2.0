package kr.centero.ghg.client.board.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.centero.core.common.pagination.PageModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Getter
@Setter
@Alias("Board")
@EqualsAndHashCode(callSuper = true)
@Schema(title = "게시글", description = "게시글 DTO")
public class Board extends PageModel {
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

    @Schema(title = "검색조건", allowableValues = {"null", "0", "1", "2"})
    private String searchType;

    @Schema(title = "검색어", example = "String")
    private String searchText;

    @Schema(title = "접속자 ID", description = "작성자/수정자", example = "ID")
    private String loginUserId;

    private Long rowNum;
}
