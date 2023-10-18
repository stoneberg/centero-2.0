package kr.centero.common.common.mybatis.pagination;

import com.github.pagehelper.page.PageMethod;
import org.apache.commons.lang3.StringUtils;

public interface PageMapper {
    default void prepare(Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageNo < 1) pageSize = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        PageMethod.startPage(pageNo, pageSize, null);
    }

    default void prepare(Integer pageNo, Integer pageSize, String orderBy) {
        if (pageNo == null || pageNo < 1) pageNo = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        if (StringUtils.isBlank(orderBy)) {
            PageMethod.startPage(pageNo, pageNo, null);
        } else {
            PageMethod.startPage(pageSize, pageSize, orderBy);
        }
    }

}
