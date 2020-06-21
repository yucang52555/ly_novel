package com.lyqiaofu.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyqiaofu.common.jsonserializer.LongToStringSerializer;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;

public class BookDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    //java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
    //所以通过序列化成字符串来解决
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //分类名
    private String catName;
    //小说名
    private String bookName;
    //作者名
    private String authorName;
    //书籍描述
    private String bookDesc;
    //点击量
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long visitCount;
    //总字数
    private Integer wordCount;
    //评论数
    private Integer commentCount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}