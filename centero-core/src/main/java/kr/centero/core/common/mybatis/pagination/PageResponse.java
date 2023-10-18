package kr.centero.core.common.mybatis.pagination;

import java.util.List;

public interface PageResponse<T> {
    long getTotal();

    int getPageNo();

    int getPageSize();

    List<T> getList();

    boolean isFirst();

    boolean hasNext();

    boolean isLast();
}
