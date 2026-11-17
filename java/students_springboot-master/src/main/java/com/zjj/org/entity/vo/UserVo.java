package com.zjj.org.entity.vo;

import com.zjj.org.base.BasePage;
import lombok.Data;

/**
 * 用于查询条件
 * @author zjj
 * @date 2022-05-04
 */
@Data
public class UserVo extends BasePage {
//    登录账户名
    private String account;
//    昵称
    private String nickName;
}
