package kr.centero.netzero.common.mybatis.pagination;

import com.github.pagehelper.Page;

import java.util.List;

public class MybatisPageResponse<T> implements PageResponse<T> {
    private final long total;
    private final List<T> list;
    private final int pageNo;
    private final int pageSize;
    private final boolean isFirst;
    private final boolean hasNext;
    private final boolean isLast;

    public MybatisPageResponse(List<T> list) {
        if (list instanceof Page<T> page) {
            this.total = page.getTotal();
            this.list = page;
            this.pageNo = page.getPageNum();
            this.pageSize = page.getPageSize();
            int totalElements = page.size();
            int lastElementIndex = (pageNo - 1) * pageSize + totalElements;
            this.hasNext = lastElementIndex < this.total;
            this.isFirst = pageNo == 1;
            this.isLast = !hasNext;
        } else {
            this.total = list.size();
            this.list = list;
            this.pageNo = 1;
            this.pageSize = list.size();
            this.hasNext = false;
            this.isFirst = true;
            this.isLast = true;
        }
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public int getPageNo() {
        return this.pageNo;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public List<T> getList() {
        return this.list;
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public boolean isFirst() {
        return this.isFirst;
    }

    @Override
    public boolean isLast() {
        return this.isLast;
    }
}
