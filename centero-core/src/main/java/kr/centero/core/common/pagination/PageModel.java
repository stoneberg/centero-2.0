package kr.centero.core.common.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * Page Helper Class For Model
 */
@Getter
@Setter
public abstract class PageModel {
    @JsonIgnore
    private Integer pageNo = 1;
    @JsonIgnore
    private Integer pageSize = 10;
    @JsonIgnore
    private String orderBy = null; // "user_id DESC, age ASC"
}
