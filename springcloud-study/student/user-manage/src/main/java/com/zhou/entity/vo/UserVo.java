package com.zhou.entity.vo;

import com.zhou.base.BasePage;
import lombok.Data;


@Data
public class UserVo extends BasePage {
//    登录账户名
    private String account;
//    昵称
    private String nickName;
}
