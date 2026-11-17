package com.example.SpringCloudStudy.common.microservice.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.SpringCloudStudy.common.microservice.pojo.Paging;
import com.example.SpringCloudStudy.common.microservice.pojo.Result;
import com.example.SpringCloudStudy.common.microservice.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public class BaseController<Entity extends BaseEntity> {
    protected IService<Entity> iService;

    @Autowired
    protected RequestUtil requestUtil;

    public BaseController(IService<Entity> iService) {
        this.iService = iService;
    }

    /**
     * 分页获取数据
     *
     * @param paging /
     * @return /
     */
    @GetMapping("/page")
    public Result<Paging<Entity>> page(Paging<Entity> paging) {
        Page<Entity> page = iService.page(
                new Page<>(paging.getCurrentPage(), paging.getPageSize()),
                new QueryWrapper<Entity>().eq("is_deleted", paging.getIsRecovery()));
        return Result.success("获取分页成功",
                Paging.build(page.getTotal(), paging.getCurrentPage(), paging.getPageSize(), page.getRecords()));
    }

    /**
     * 根据 id 删除一个
     *
     * @param entitys /
     * @return /
     */
    @DeleteMapping("/del")
    public Result<String> del(@RequestBody List<Entity> entitys) {
        iService.updateBatchById(entitys.stream().peek(entity -> entity.setIsDeleted(true)).collect(Collectors.toList()));
        return Result.success("删除成功");
    }

    /**
     * 根据id恢复哪些数据
     *
     * @param entitys /
     * @return /
     */
    @PutMapping("/recovery")
    public Result<Entity> recovery(@RequestBody List<Entity> entitys) {
        iService.updateBatchById(entitys.stream().peek(entity -> entity.setIsDeleted(false)).collect(Collectors.toList()));
        return Result.success("恢复成功");
    }

    /**
     * 修改一条数据
     *
     * @param entity /
     * @return /
     */
    @PutMapping("/edit")
    public Result<Entity> edit(@RequestBody Entity entity) {
        entity.setUpdateBy(requestUtil.getHeaderUsername());
        iService.updateById(entity);
        return Result.success("修改成功");
    }

    /**
     * 添加一条数据
     *
     * @param entity /
     * @return /
     */
    @PostMapping("/plus")
    public Result<Entity> plus(@RequestBody Entity entity) {
        entity.setCreateBy(requestUtil.getHeaderUsername());
        iService.save(entity);
        return Result.success("添加成功");
    }
}
