package com.zhou.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Mapper 继承了该接口之后，无需编写mapper.xml文件，即可获得CRUD功能
 * 支持泛型
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T> {
    /**
     * 插入一条数据
     * @param entity 实体对象
     * @return
     */
    int insert(T entity);

    /**
     * 根据id进行删除
     * @param id 主键
     * @return
     */
    int deleteById(Serializable id);

    /**
     * 根据columnMap条件，进行删除
     * @param columnMap map对象
     * @return
     */
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据entity条件，删除记录
     * @param queryWrapper 实体对象封装的操作类，可以为空null
     * @return
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据id批量删除
     * @param idList 主键id列表，不能为null或者empty
     * @return
     */
    int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

    /**
     * 根据id修改
     * @param entity 实体对象
     * @return
     */
    int updateById(@Param(Constants.ENTITY) T entity);

    /**
     * 根据whereEntity条件修改记录
     * @param entity 实体对象   set条件值，可以为null
     * @param updateWrapper 实体对象封装的操作类，可以为null，里面的泛型（entity）用于生成where语句
     * @return
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    /**
     * 根据id进行查询
     * @param id 主键id
     * @return
     */
    T selectById(Serializable id);

    /**
     * 查询（根据id批量查询）
     * @param idList 主键id列表，不能为null或者empty
     * @return
     */
    List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 查询
     * @param columnMap map对象
     * @return
     */
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 查询一条数据
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 查询所有记录的条数
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据entity条件，查询全部记录
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据wrapper条件，查询全部记录
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据wrapper条件，查询全部记录
     * 注意：只返回第一个字段的值
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据entity条件，查询全部记录
     * @param page 分页查询条件 可以为RowBounds.DEFAULT
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据wrapper条件，查询全部记录
     * @param page 分页查询条件
     * @param queryWrapper 实体对象封装操作类，可以为null
     * @return
     */
    IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);



}
