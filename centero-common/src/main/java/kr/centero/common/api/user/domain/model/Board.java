package kr.centero.common.api.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private String id;
    private String title;
    private String content;
    private String writer;
    private String regDate;
    private String modDate;
}
