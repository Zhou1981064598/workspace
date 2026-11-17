package com.zhou.service.impl;

import com.zhou.entity.Subject;
import com.zhou.mapper.SubjectMapper;
import com.zhou.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 查询所有科目
     * @return
     */
    @Override
    public List<Subject> selectAll() {
        return subjectMapper.selectList(null);
    }
}
