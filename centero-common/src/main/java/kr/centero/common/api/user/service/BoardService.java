package kr.centero.common.api.user.service;

import kr.centero.common.api.user.domain.dto.BoardDto;
import kr.centero.common.api.user.domain.model.Board;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardService {

    private static final Map<String, Board> boards = new HashMap<>();
    static {
        // 게시글 데이터 생성
        boards.put("1", new Board(UUID.randomUUID().toString(), "제목1", "내용1", "작성자1", "2023-01-01", "2023-01-02"));
        boards.put("2", new Board(UUID.randomUUID().toString(), "제목2", "내용2", "작성자2", "2023-01-03", "2023-01-04"));
        boards.put("3", new Board(UUID.randomUUID().toString(), "제목3", "내용3", "작성자3", "2023-01-05", "2023-01-06"));
        boards.put("4", new Board(UUID.randomUUID().toString(), "제목4", "내용4", "작성자4", "2023-01-07", "2023-01-08"));
        boards.put("5", new Board(UUID.randomUUID().toString(), "제목5", "내용5", "작성자5", "2023-01-09", "2023-01-10"));
        boards.put("6", new Board(UUID.randomUUID().toString(), "제목6", "내용6", "작성자6", "2023-01-11", "2023-01-12"));
        boards.put("7", new Board(UUID.randomUUID().toString(), "제목7", "내용7", "작성자7", "2023-01-13", "2023-01-14"));
        boards.put("8", new Board(UUID.randomUUID().toString(), "제목8", "내용8", "작성자8", "2023-01-15", "2023-01-16"));
        boards.put("9", new Board(UUID.randomUUID().toString(), "제목9", "내용9", "작성자9", "2023-01-17", "2023-01-18"));
        boards.put("10", new Board(UUID.randomUUID().toString(), "제목10", "내용10", "작성자10", "2023-01-19", "2023-01-20"));
        boards.put("11", new Board(UUID.randomUUID().toString(), "제목11", "내용11", "작성자11", "2023-01-21", "2023-01-22"));
    }

    public BoardDto.BoardContentResponse findBoardContentById(String id) {
        Board board = Optional.ofNullable(boards.get(id)).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return BoardDto.BoardContentResponse.from(board);
    }

    public List<BoardDto.BoardContentResponse> findBoardContents() {
        return boards.values().stream().map(BoardDto.BoardContentResponse::from).toList();
    }

    public BoardDto.BoardContentResponse addBoardContent(BoardDto.BoardContentRequest boardContentRequest) {
        Board board = Board.builder()
                .id(UUID.randomUUID().toString())
                .title(boardContentRequest.getTitle())
                .content(boardContentRequest.getContent())
                .writer(boardContentRequest.getWriter())
                .regDate(new Date().toString())
                .modDate(new Date().toString())
                .build();

        boards.put(board.getId(), board);

        return BoardDto.BoardContentResponse.from(board);
    }
}
