package com.demo.dao;

import com.demo.entity.Clazz;
import com.demo.entity.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class SectionDaoTest {

    Section section = new Section();
    Clazz clazz = new Clazz();

//    @Autowired
//    SectionDao sectionDao;
//
    
// 注入Dao实现类依赖
    @Resource
    SectionDao sectionDao;

    @Test
    public void create() {
//        各个表之间没有关联
        clazz.setId(55);
        section.setClazz(clazz);
        section.setId(20);
        section.setType("夏季");
        section.setYear(2022);
        section.setRemark("这是测试section");
        sectionDao.create(section);
    }

    @Test
    public void queryByStudent() {
        Map<String, Object> paramMap = new HashMap<>();
        
        System.out.println(sectionDao.queryByStudent(paramMap));

    }

    @Test
    public void queryByTeacher() {


    }
}