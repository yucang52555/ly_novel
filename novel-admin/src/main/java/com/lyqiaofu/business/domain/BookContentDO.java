package com.lyqiaofu.business.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyqiaofu.common.jsonserializer.LongToStringSerializer;

import javax.annotation.Generated;
import java.io.Serializable;

public class BookContentDO implements Serializable {
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long indexId;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}