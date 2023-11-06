package kr.centero.common.client.menu.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.centero.core.common.enums.codes.EYn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 역할별 권한 설정
 */
@Data
@Builder
@AllArgsConstructor
public class RoleRights {

    /**
     * 쓰기 권한 여부
     */
    @JsonProperty("c")
    private String create = EYn.N.getCode();

    /**
     * 읽기 권한 여부
     */
    @JsonProperty("r")
    private String read = EYn.N.getCode();

    /**
     * 수정 권한 여부
     */
    @JsonProperty("u")
    private String update = EYn.N.getCode();

    /**
     * 삭제 권한 여부
     */
    @JsonProperty("d")
    private String delete = EYn.N.getCode();


    public RoleRights() {
    }
}
