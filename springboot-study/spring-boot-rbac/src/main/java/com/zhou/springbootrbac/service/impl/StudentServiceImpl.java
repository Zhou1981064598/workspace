package com.zhou.springbootrbac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.springbootrbac.eneity.StudentEntity;
import com.zhou.springbootrbac.pojo.Paging;
import com.zhou.springbootrbac.repository.StudentRepository;
import com.zhou.springbootrbac.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentRepository,StudentEntity> implements StudentService {
  @Resource(name = "studentRepository")
    StudentRepository studentRepository;
  @Override
  public Paging<StudentEntity> page(Paging<StudentEntity> paging){
    Page<StudentEntity> page= studentRepository.selectPage(new Page<>(paging.getCurrentPage(),paging.getPageSize()),new QueryWrapper<StudentEntity>().eq("is_deleted",paging.getIsRecovery()));
    return Paging.build(page.getTotal(),paging.getCurrentPage(),paging.getPageSize(),page.getRecords());
  }
}
