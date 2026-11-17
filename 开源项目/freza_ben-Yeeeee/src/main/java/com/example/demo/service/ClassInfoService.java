package com.example.demo.service;

import com.example.demo.bo.AddDModifyClassBO;
import com.example.demo.bo.GetClassBO;
import com.example.demo.entity.ClassInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.vo.ClassVO;
import com.example.demo.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-23
 */
public interface ClassInfoService extends IService<ClassInfo> {
    Boolean addClass(AddDModifyClassBO addDModifyClassBO, Integer operatorId) throws ValidationFailException;

    PageVO<List<ClassVO>> listGetClassInfo(GetClassBO classBO);

    Boolean modifyClass(AddDModifyClassBO classBO, Integer id, Integer operatorId) throws ValidationFailException;
}
