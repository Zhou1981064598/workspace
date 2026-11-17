package com.zhou.springbootrbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.springbootrbac.eneity.StudentEntity;
import com.zhou.springbootrbac.pojo.Paging;

public interface StudentService extends IService<StudentEntity> {
    public Paging<StudentEntity> page(Paging<StudentEntity> paging);
}
