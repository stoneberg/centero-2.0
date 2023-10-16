package kr.centero.common.api.user.web;

import kr.centero.common.api.user.service.BoardService;
import kr.centero.core.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBoard(@PathVariable("id") String id) {
        return ApiResponse.ok(boardService.findBoardContentById(id));
    }

}
