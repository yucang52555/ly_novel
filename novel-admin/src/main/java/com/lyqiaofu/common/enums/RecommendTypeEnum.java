package com.lyqiaofu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 推荐类型：0：轮播图，1：顶部小说栏设置，2：本周强推，3：热门推荐，4：精品推荐
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RecommendTypeEnum {

    ROTATION(0, "轮播图"),
    TOP(1, "顶部小说栏设置"),
    WEEK(2, "本周强推"),
    HOT(3, "热门推荐"),
    FINE(4,"精品推荐");

    private int code;
    private String msg;

    RecommendTypeEnum(Integer code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
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

    /**
     * 枚举项的集合
     */
    private static final Map<Integer, String > items = new HashMap<Integer,String>();
    static {
        for(RecommendTypeEnum item : values()) {
            items.put(item.getCode(), item.getMsg());
        }
    }

    /**
     * 根据自定义编号得到名称
     * @param code
     * @return
     */
    public static String getNameByCode(Integer code) {
        return items.get(code);
    }

}
