package com.example.demo.vo;


public class PageVO<Object> {
    private Integer recordCount;
    private Integer totalCount;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPage;
    private Object data;

    public PageVO(Object data) {
        this.data = data;
    }

    public PageVO() {
        this.recordCount = 0;
        this.totalCount = 0;
        this.totalPage = 0;
        this.pageNo = 0;
        this.pageSize = 0;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
