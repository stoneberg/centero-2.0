package kr.centero.ghg.client.board.mapper;

import kr.centero.core.common.pagination.MybatisPageResponse;
import kr.centero.core.common.pagination.PageMapper;
import kr.centero.core.common.pagination.PageResponse;
import kr.centero.ghg.client.board.domain.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper extends PageMapper {

    Board selectBoard(Board board);
    List<Board> selectBoardList(Board board);

    default PageResponse<Board> findPagesByCond(Board board) {
        prepare(board.getPageNo(), board.getPageSize());
        return new MybatisPageResponse<>(selectBoardList(board));
    }

    void insertBoard(Board board);
    void updateBoard(Board board);
    void updateBoardToDelete(Board board);

}
