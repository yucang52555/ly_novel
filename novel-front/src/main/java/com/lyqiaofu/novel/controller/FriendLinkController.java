package com.lyqiaofu.novel.controller;

import com.lyqiaofu.novel.core.bean.ResultBean;
import com.lyqiaofu.novel.service.FriendLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 11797
 */
@RequestMapping("friendLink")
@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendLinkController {

    private final FriendLinkService friendLinkService;

    /**
     * 查询首页友情链接
     * */
    @PostMapping("listIndexLink")
    public ResultBean listIndexLink(){
        return ResultBean.ok(friendLinkService.listIndexLink());
    }




}
