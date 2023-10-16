package kr.centero.common.api.user.service;

import kr.centero.common.api.user.domain.dto.BoardDto;
import kr.centero.common.api.user.domain.model.Board;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {

    private static final Map<String, Board> boards = new HashMap<>();

    static {
        // 게시글 데이터 생성
        boards.put("1", new Board("1", "제목1", "내용1", "작성자1", "2023-01-01", "2023-01-02"));
        boards.put("2", new Board("2", "제목2", "내용2", "작성자2", "2023-01-03", "2023-01-04"));
        boards.put("3", new Board("3", "제목3", "내용3", "작성자3", "2023-01-05", "2023-01-06"));
        boards.put("4", new Board("4", "제목4", "내용4", "작성자4", "2023-01-07", "2023-01-08"));
        boards.put("5", new Board("5", "제목5", "내용5", "작성자5", "2023-01-09", "2023-01-10"));
    }

    public BoardDto.BoardContentResponse findBoardContentById(String id) {
        Board board = Optional.ofNullable(boards.get(id)).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return BoardDto.BoardContentResponse.from(board);
    }

}
