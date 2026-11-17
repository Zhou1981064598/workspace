package com.zjj.org.base;

import lombok.Data;

/**
 * 分页
 * @author zjj
 * @date 2022-05-04
 */
@Data
public class BasePage {
    private int page;//第几页
    private int limit;//每页显示的条数
}
