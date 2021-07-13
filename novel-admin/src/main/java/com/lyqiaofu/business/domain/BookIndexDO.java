package com.lyqiaofu.business.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyqiaofu.common.jsonserializer.LongToStringSerializer;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;

public class BookIndexDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = LongToStringSerializer.class)
    private Long bookId;

    private Integer indexNum;

    private String indexName;

    private Integer wordCount;

    private Byte isVip;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Byte getIsVip() {
        return isVip;
    }

    public void setIsVip(Byte isVip) {
        this.isVip = isVip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}