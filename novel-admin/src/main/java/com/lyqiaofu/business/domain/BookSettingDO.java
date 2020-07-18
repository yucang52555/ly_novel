package com.lyqiaofu.business.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyqiaofu.common.jsonserializer.LongToStringSerializer;

import java.io.Serializable;

/**
 * 书籍设置dao
 */
public class BookSettingDO implements Serializable {

    //主键
    //java中的long能表示的范围比js中number大,也就意味着部分数值在js中存不下(变成不准确的值)
    //所以通过序列化成字符串来解决
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //小说ID
    private Long bookId;
//    //小说名
//    private String bookName;
//    //作者名
//    private String catName;
    //排序号
    private String sort;
    //类型，0：轮播图，1：顶部小说栏设置，2：本周强推，3：热门推荐，4：精品推荐
    private Integer type;
    //类型描述
    private Integer typeName;

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

//    public String getBookName() {
//        return bookName;
//    }
//
//    public void setBookName(String bookName) {
//        this.bookName = bookName;
//    }
//
//    public String getCatName() {
//        return catName;
//    }
//
//    public void setCatName(String catName) {
//        this.catName = catName;
//    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeName() {
        return typeName;
    }

    public void setTypeName(Integer typeName) {
        this.typeName = typeName;
    }
}
