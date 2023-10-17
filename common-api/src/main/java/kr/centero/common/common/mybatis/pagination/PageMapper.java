package kr.centero.common.common.mybatis.pagination;

import com.github.pagehelper.page.PageMethod;
import org.apache.commons.lang3.StringUtils;

public interface PageMapper {
    default void prepare(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null || pageNumber < 1) pageNumber = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        PageMethod.startPage(pageNumber, pageSize, null);
    }

    default void prepare(Integer pageNumber, Integer pageSize, String orderBy) {
        if (pageNumber == null || pageNumber < 1) pageNumber = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        if (StringUtils.isBlank(orderBy)) {
            PageMethod.startPage(pageNumber, pageSize, null);
        } else {
            PageMethod.startPage(pageNumber, pageSize, orderBy);
        }
    }

}
