package kr.centero.ghg.client.menu.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.centero.core.common.enums.codes.EYn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 역할별 권한 설정
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRights {
    /**
     * 쓰기 권한 여부
     */
    @JsonProperty("c")
    @Builder.Default
    private String create = EYn.N.getCode();

    /**
     * 읽기 권한 여부
     */
    @JsonProperty("r")
    @Builder.Default
    private String read = EYn.N.getCode();

    /**
     * 수정 권한 여부
     */
    @JsonProperty("u")
    @Builder.Default
    private String update = EYn.N.getCode();

    /**
     * 삭제 권한 여부
     */
    @JsonProperty("d")
    @Builder.Default
    private String delete = EYn.N.getCode();
}
