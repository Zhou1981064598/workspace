package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SpringCloudStudy.microservice.goods.eneity.TemplateEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.TemplateRepository;
import com.example.SpringCloudStudy.microservice.goods.service.TemplateService;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl extends ServiceImpl<TemplateRepository, TemplateEntity>implements TemplateService {
}
