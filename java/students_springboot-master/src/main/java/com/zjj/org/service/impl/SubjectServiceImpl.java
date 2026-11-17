package com.zjj.org.service.impl;

import com.zjj.org.entity.Subject;
import com.zjj.org.mapper.SubjectMapper;
import com.zjj.org.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjj
 * @date 2022-05-25
 */
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
