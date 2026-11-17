package com.zhou.service;

import com.zhou.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SubjectService {

    List<Subject> selectAll();
}
