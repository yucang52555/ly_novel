package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookSettingDO;
import com.lyqiaofu.business.service.BookSettingService;
import com.lyqiaofu.common.utils.PageBean;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.common.utils.R;
import com.lyqiaofu.test.domain.OrderDO;
import com.lyqiaofu.test.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Desc:书籍页面配置
 * @Author ktc
 * @Date  2020/6/18 14:19
 **/
@Controller
@RequestMapping("/business/book/setting")
public class BookSettingController {
    @Autowired
    private BookSettingService bookSettingService;

    @GetMapping()
    @RequiresPermissions("business:book:setting")
    String Order() {
        return "business/book/setting";
    }

    @ApiOperation(value = "获取书籍-设置列表", notes = "获取书籍-设置列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("business:book:setting")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BookSettingDO> bookSettingList = bookSettingService.list(params);
        int total = bookSettingService.count(query);
        PageBean pageBean = new PageBean(bookSettingList, total);
        return R.ok().put("data", pageBean);
    }

}
