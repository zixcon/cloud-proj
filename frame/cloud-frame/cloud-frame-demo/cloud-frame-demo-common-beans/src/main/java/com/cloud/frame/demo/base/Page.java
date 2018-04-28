package com.cloud.frame.demo.base;

import lombok.Data;

/**
 * Created by wd on 2018/4/27.
 */
@Data
public class Page {

    private Integer pageNo;

    private Integer pageSize;

    private Integer pageStart;

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageStart() {
        this.pageStart = this.pageSize * (this.pageNo - 1) + 1;
        return pageStart;
    }
}
