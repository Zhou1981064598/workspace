package com.zhou.springbootrbac.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> implements Serializable {
    // 总条数
    @JsonView(PagingView.class)
    private Long total;
    // 当前页
    @JsonView(PagingView.class)
    private Long currentPage;
    // 每页条数
    @JsonView(PagingView.class)
    private Long pageSize;
    // 数据
    @JsonView(PagingView.class)
    private List<T> list;
    // 查询类型：true-回收站数据；false-未删除数据
    @JsonView(PagingView.class)
    private Boolean isRecovery;
    // 数据
    @JsonView(PagingView.class)
    private Long pid;

    // 构建对象
    public static <D> Paging<D> build(long total, long currentPage, long pageSize, List<D> list) {
        return new Paging<D>(total, currentPage, pageSize, list, null, null);
    }

    // 分页视图
    public interface PagingView extends Result.ResultView {}
}
