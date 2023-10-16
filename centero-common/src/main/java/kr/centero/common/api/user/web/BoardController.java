package kr.centero.common.api.user.web;

import kr.centero.common.api.user.domain.dto.BoardDto;
import kr.centero.common.api.user.service.BoardService;
import kr.centero.core.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findBoardContentById(@PathVariable("id") String id) {
        return ApiResponse.ok(boardService.findBoardContentById(id));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findBoardContents() {
        return ApiResponse.ok(boardService.findBoardContents());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addBoardContent(@RequestBody BoardDto.BoardContentRequest boardCreateRequest) {
        return ApiResponse.ok(boardService.addBoardContent(boardCreateRequest));
    }

}
