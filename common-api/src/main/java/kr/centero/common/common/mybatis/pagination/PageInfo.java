package kr.centero.common.common.mybatis.pagination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    @JsonIgnore
    private Integer pageNo = 1;
    @JsonIgnore
    private Integer pageSize = 10;
    @JsonIgnore
    private String orderBy = null; // "user_id DESC, age ASC"
}
