package kr.centero.core.common.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PageInfo {
    @JsonIgnore
    @Builder.Default
    private Integer pageNo = 1;
    @JsonIgnore
    @Builder.Default
    private Integer pageSize = 10;
    @JsonIgnore
    @Builder.Default
    private String orderBy = null; // "user_id DESC, age ASC"

    public PageInfo() {
        // 상속받는 클래스에서 pageSize, pageNo, orderBy를 사용하기 위해 기본 생성자를 만들어준다.
        // SuperBuilder를 사용하면 기본 생성자가 필요하다.
    }
}
