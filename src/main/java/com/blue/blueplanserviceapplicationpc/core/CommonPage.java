package com.blue.blueplanserviceapplicationpc.core;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "分页数据封装类")
public class CommonPage<T> {
    private int code = 0;
    private String msg;
    @ApiModelProperty(value = "总条数")
    private Long count;
    @ApiModelProperty(value = "当前页")
    private Integer pageNum;
    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize;
    @ApiModelProperty(value = "页数")
    private Integer totalPage;
    @ApiModelProperty(value = "数据列表")
    private List<T> data;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(PageInfo<T> pageInfo ,int code) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        result.setCode(code);
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
