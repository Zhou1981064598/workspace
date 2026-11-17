package com.example.demo.bo;

public class GetClassBO {
    private Integer majorId;
    private Integer departmentId;
    private Integer gradeId;
    private Integer classId;
    private Integer teacherId;
    private Boolean graduate;
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

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Boolean getGraduate() {
        return graduate;
    }

    public void setGraduate(Boolean graduate) {
        this.graduate = graduate;
    }
}

