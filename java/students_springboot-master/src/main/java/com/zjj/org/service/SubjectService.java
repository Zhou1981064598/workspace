package com.zjj.org.service;

import com.zjj.org.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjj
 * @date 2022-05-25
 */
@Service
public interface SubjectService {

    List<Subject> selectAll();
}
