package kr.centero.common.client.lang.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lang {
    /**
     * 언어 코드
     */
    private String langCd;
    /**
     * 언어 코드의 텍스트
     */
    private String dspText;
}
