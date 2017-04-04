package com.ccc.base.entity;

import java.util.List;

/**
 * Created by ccc on 2016/12/11.
 */
public class ResultInfo<T> {
    //总记录数
    private long total;

    //行数据
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
