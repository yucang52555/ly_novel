package com.lyqiaofu.novel.service;


import com.lyqiaofu.novel.entity.FriendLink;

import java.util.List;

/**
 * @author 11797
 */
public interface FriendLinkService {

    /**
     * 查询首页友情链接
     * @return 集合
     * */
    List<FriendLink> listIndexLink();
}
