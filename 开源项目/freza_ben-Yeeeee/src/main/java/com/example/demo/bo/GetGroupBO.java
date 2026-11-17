package com.example.demo.bo;

public class GetGroupBO {


    private String groupName;
    private Boolean groupGraduate;

    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer pageOfSet = 0;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page != 0) {
            this.page = page;
            setPageOfSet(page, this.pageSize);
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (page != 0) {
            this.pageSize = pageSize;
            setPageOfSet(this.page, pageSize);
        }
    }

    public Integer getPageOfSet() {
        return pageOfSet;
    }

    private void setPageOfSet(Integer page, Integer pageSize) {
        this.pageOfSet = (page - 1) * pageSize;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        if (!groupName.equals(""))
            this.groupName = groupName;
    }

    public Boolean getGroupGraduate() {
        return groupGraduate;
    }

    public void setGroupGraduate(Boolean groupGraduate) {
        this.groupGraduate = groupGraduate;
    }
}
