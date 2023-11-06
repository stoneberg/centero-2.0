package kr.centero.ghg.client.board.mapstruct;

import kr.centero.ghg.client.board.domain.dto.BoardDto;
import kr.centero.ghg.client.board.domain.model.Board;
import kr.centero.ghg.config.MapStructMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = MapStructMapperConfig.class)
public interface BoardMapstruct {
    BoardMapstruct INSTANCE = Mappers.getMapper(BoardMapstruct.class);

    // BRD_SEQ -> brd_seq (different field name mapping)
    Board toBoard(BoardDto.BoardRequest boadRequest);

//    @Mapping(source = "seq", target = "BRD_SEQ")
    Board toBoard(BoardDto.BoardPageRequest boardRequest);

    Board toBoard(BoardDto.BoardInsertRequest boardInsertRequest);

    Board toBoard(BoardDto.BoardUpdateRequest boardUpdateRequest);

    Board toBoard(BoardDto.BoardDeleteRequest boardDeleteRequest);

    // BRD_SEQ -> seq (different field name mapping)
//    @Mapping(source = "brdSeq", target = "BRD_SEQ")
    BoardDto.BoardResponse toBoardDto(Board board);

    List<BoardDto.BoardResponse> toBoardDto(List<Board> boards);
}
