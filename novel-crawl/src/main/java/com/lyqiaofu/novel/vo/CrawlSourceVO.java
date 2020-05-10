package com.lyqiaofu.novel.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyqiaofu.novel.entity.CrawlSource;
import lombok.Data;

import javax.annotation.Generated;
import java.util.Date;

/**
 * @author Administrator
 */
@Data
public class CrawlSourceVO extends CrawlSource{

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;



    @Override
    public String toString() {
        return super.toString();
    }
}
