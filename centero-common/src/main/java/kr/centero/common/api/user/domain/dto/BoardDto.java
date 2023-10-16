package kr.centero.common.api.user.domain.dto;

import kr.centero.common.api.user.domain.model.Board;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardContentRequest {
        private String title;
        private String content;
        private String writer;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardContentResponse {
        private String id;
        private String title;
        private String content;
        private String writer;
        private String regDate;
        private String modDate;

        public static BoardContentResponse from(Board board) {
            return BoardContentResponse.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .regDate(board.getRegDate())
                    .modDate(board.getModDate())
                    .build();
        }
    }
}
