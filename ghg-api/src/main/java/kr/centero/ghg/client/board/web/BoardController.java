package kr.centero.ghg.client.board.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.centero.core.common.payload.ApiResponse;
import kr.centero.ghg.client.board.domain.dto.BoardDto;
import kr.centero.ghg.client.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "GHG Board API", description = "GHG Board CRUD API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ghg/v1/boards")
public class BoardController {

    private final BoardService boardService;

    // 게시글 조회
    @Operation(summary = "GHG Board 조회", description = "전체 GHG Board 정보를 조회한다.")
    @GetMapping("/select")
    public ResponseEntity<ApiResponse<BoardDto.BoardResponse>> selectBoard(BoardDto.BoardRequest boardRequest) {
        log.info("select#===============>{}", boardRequest);
        return ApiResponse.of(boardService.selectBoard(boardRequest));
    }

    // 사용자 상세 조회 리스트 (request param: boardname, email, role) :  Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "GHG Board 조회", description = "GHG Board page 를 조회한다.")
    @GetMapping("/selectList")
    public ResponseEntity<ApiResponse<List<BoardDto.BoardResponse>>> selectBoardList(BoardDto.BoardPageRequest boardRequest) {
        log.info("PAG#1===============>{}", boardRequest);
        return ApiResponse.of(boardService.selectBoardList(boardRequest));
    }

    // 사용자 상세 조회 리스트 (request param: boardname, email, role) :  Mapping Query String Parameters to ModelAttributes
    @Operation(summary = "GHG Board 조회", description = "GHG Board page를 조회한다.")
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse<BoardDto.BoardPageDtoResponse>> findPagesByCond(BoardDto.BoardPageRequest boardPageRequest) {
        log.info("PAG#1===============>{}", boardPageRequest);
        return ApiResponse.of(boardService.findPagesByCond(boardPageRequest));
    }

    // 사용자 수정
    @Operation(summary = "GHG Board 등록", description = "GHG Board 정보를 등록한다.")
    @PostMapping("/insert")
    public ResponseEntity<ApiResponse<List<BoardDto.BoardResponse>>> insertBoard(@RequestBody BoardDto.BoardInsertRequest boardInsertRequest) {
        boardService.insertBoard(boardInsertRequest);
        return ApiResponse.ok();
    }

    // 사용자 수정
    @Operation(summary = "GHG Board 수정", description = "GHG Board 정보를 수정한다.")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<List<BoardDto.BoardResponse>>> updateBoard(@RequestBody BoardDto.BoardUpdateRequest boardUpdateRequest) {
        // if use path variable as query condition
        // boardDetailRequest.setBRD_SEQ(BRD_SEQ);
        boardService.updateBoard(boardUpdateRequest);
        return ApiResponse.ok();
    }

    // 사용자 수정
    @Operation(summary = "GHG Board 수정", description = "GHG Board 정보를 수정한다.")
    @PutMapping("/delete")
    public ResponseEntity<ApiResponse<List<BoardDto.BoardResponse>>> updateBoardToDelete(@RequestBody BoardDto.BoardDeleteRequest boardDeleteRequest) {
        boardService.updateBoardToDelete(boardDeleteRequest);
        return ApiResponse.ok();
    }

}
