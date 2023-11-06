package kr.centero.ghg.client.board.service;

import kr.centero.core.common.pagination.PageResponse;
import kr.centero.ghg.client.board.domain.dto.BoardDto;
import kr.centero.ghg.client.board.domain.model.Board;
import kr.centero.ghg.client.board.mapper.BoardMapper;
import kr.centero.ghg.client.board.mapstruct.BoardMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * @param boardRequest board request
     * @return BoardResponse
     */
    public BoardDto.BoardResponse selectBoard(BoardDto.BoardRequest boardRequest) {
        Board board = BoardMapstruct.INSTANCE.toBoard(boardRequest);
        return BoardMapstruct.INSTANCE.toBoardDto(boardMapper.selectBoard(board));
    }
    public List<BoardDto.BoardResponse> selectBoardList(BoardDto.BoardPageRequest boardPageRequest) {
        Board board = BoardMapstruct.INSTANCE.toBoard(boardPageRequest);
        return boardMapper.selectBoardList(board).stream()
                .map(BoardMapstruct.INSTANCE::toBoardDto).toList();
    }

    /**
     * find board pages by condition
     *
     * @param boardPageRequest board page request
     * @return BoardPageDtoResponse
     */
    public BoardDto.BoardPageDtoResponse findPagesByCond(BoardDto.BoardPageRequest boardPageRequest) {
        log.info("PAG#2===============>{}", boardPageRequest);
        Board board = BoardMapstruct.INSTANCE.toBoard(boardPageRequest);
        PageResponse<Board> pageResponse = boardMapper.findPagesByCond(board);
        log.info("[PAG]pageResponse=======>{}", pageResponse);

        // detail page info example
        List<Board> list = pageResponse.getList();
        log.info("[PAG]list===============>{}", list);
        long total = pageResponse.getTotal();
        log.info("[PAG]total==============>{}", total);
        int pageNo = pageResponse.getPageNo();
        log.info("[PAG]pageNo=============>{}", pageNo);
        int pageSize = pageResponse.getPageSize();
        log.info("[PAG]pageSize===========>{}", pageSize);
        boolean first = pageResponse.isFirst();
        log.info("[PAG]first==============>{}", first);
        boolean last = pageResponse.isLast();
        log.info("[PAG]last===============>{}", last);
        boolean next = pageResponse.hasNext();
        log.info("[PAG]next===============>{}", next);

        // convert pageResponse to BoardPageDtoResponse
        return BoardDto.BoardPageDtoResponse.fromPageResponse(pageResponse);
    }

    /**
     * insert board
     *
     * @param boardInsertRequest board insert request
     */
    @Transactional
    public void insertBoard(BoardDto.BoardInsertRequest boardInsertRequest) {
        /* 수정 필요 - 작성자ID(접속자ID) loginUserId 값이 없는 경우 값 입력 */
        String loginUserId = boardInsertRequest.getLoginUserId();
        if(loginUserId == null || loginUserId.isEmpty()){
            boardInsertRequest.setLoginUserId("Create_ID");
        }

        Board board = BoardMapstruct.INSTANCE.toBoard(boardInsertRequest);
        boardMapper.insertBoard(board);
    }

    /**
     * update board
     *
     * @param boardUpdateRequest board update request
     */
    @Transactional
    public void updateBoard(BoardDto.BoardUpdateRequest boardUpdateRequest) {
        /* 수정 필요 - 수정자ID(접속자ID) loginUserId 값이 없는 경우 값 입력 */
        String loginUserId = boardUpdateRequest.getLoginUserId();
        if(loginUserId == null || loginUserId.isEmpty()){
            boardUpdateRequest.setLoginUserId("Update_ID");
        }

        Board board = BoardMapstruct.INSTANCE.toBoard(boardUpdateRequest);
        boardMapper.updateBoard(board);
    }
    @Transactional
    public void updateBoardToDelete(BoardDto.BoardDeleteRequest boardDeleteRequest) {
        /* 수정 필요 - 수정자ID(접속자ID) loginUserId 값이 없는 경우 값 입력 */
        String loginUserId = boardDeleteRequest.getLoginUserId();
        if(loginUserId == null || loginUserId.isEmpty()){
            boardDeleteRequest.setLoginUserId("Update_ID");
        }

        Board board = BoardMapstruct.INSTANCE.toBoard(boardDeleteRequest);
        boardMapper.updateBoardToDelete(board);
    }

}
