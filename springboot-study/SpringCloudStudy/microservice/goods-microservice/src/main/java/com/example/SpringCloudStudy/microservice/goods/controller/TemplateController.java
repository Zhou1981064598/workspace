package com.example.SpringCloudStudy.microservice.goods.controller;

import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.TemplateEntity;
import com.example.SpringCloudStudy.microservice.goods.service.TemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/template")
public class TemplateController extends BaseController<TemplateEntity> {
    protected TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        super(templateService);
        this.templateService= templateService;
    }
}
