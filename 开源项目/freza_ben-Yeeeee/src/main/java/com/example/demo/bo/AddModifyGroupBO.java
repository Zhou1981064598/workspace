package com.example.demo.bo;

public class AddModifyGroupBO {
    private Integer groupId;
    private String groupName;
    private Boolean groupGraduate;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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
