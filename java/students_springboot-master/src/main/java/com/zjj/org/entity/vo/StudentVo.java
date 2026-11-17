package com.zjj.org.entity.vo;

import com.zjj.org.base.BasePage;
import lombok.Data;

/**
 * 学生列表查询分页条件
 * @author zjj
 * @date 2022-05-11
 */
@Data
public class StudentVo extends BasePage {
    private String stuName;
    private String stuNo;
}
