package com.davina.entity;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description TODO
 * @Author Davina Chan
 * @Date 2020/3/3 19:44
 * @Version 1.0
 */
public class PageResult<T> {

    private Long total;//总记录数
    private List<T> rows;//每页数据

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResult() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
