package com.lyqiaofu.business.domain;

import javax.annotation.Generated;
import java.io.Serializable;

public class BookCategoryDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer workDirection;

    private String workDirectionDesc;

    private String name;

    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkDirection() {
        return workDirection;
    }

    public void setWorkDirection(Integer workDirection) {
        this.workDirection = workDirection;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getWorkDirectionDesc() {
        return workDirectionDesc;
    }

    public void setWorkDirectionDesc(String workDirectionDesc) {
        this.workDirectionDesc = workDirectionDesc;
    }
}