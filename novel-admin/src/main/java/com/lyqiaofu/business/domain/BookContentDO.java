package com.lyqiaofu.business.domain;

import javax.annotation.Generated;
import java.io.Serializable;

public class BookContentDO implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long indexId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String content;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getIndexId() {
        return indexId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getContent() {
        return content;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}