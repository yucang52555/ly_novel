package com.lyqiaofu.business.controller;

import com.lyqiaofu.business.domain.BookDO;
import com.lyqiaofu.business.domain.BookSettingDO;
import com.lyqiaofu.business.service.BookSettingService;
import com.lyqiaofu.common.annotation.Log;
import com.lyqiaofu.common.utils.PageBean;
import com.lyqiaofu.common.utils.Query;
import com.lyqiaofu.common.utils.R;
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
@RequestMapping("/business/setting")
public class BookSettingController {
    @Autowired
    private BookSettingService bookSettingService;

    @GetMapping()
    @RequiresPermissions("business:setting:setting")
    String bookSetting() {
        return "business/setting/setting";
    }

    @ApiOperation(value = "获取书籍-设置列表", notes = "获取书籍-设置列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("business:setting:list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BookSettingDO> bookSettingList = bookSettingService.list(params);
        int total = bookSettingService.count(query);
        PageBean pageBean = new PageBean(bookSettingList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "修改书籍-设置列表页面", notes = "修改书籍-设置列表页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("business:setting:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        BookSettingDO bookSetting = bookSettingService.get(id);
        model.addAttribute("bookSetting", bookSetting);
        return "business/setting/edit";
    }

    @RequiresPermissions("business:setting:edit")
    @Log("更新推荐书籍")
    @PostMapping("/update")
    @ResponseBody
    R update(BookSettingDO bookSetting) {
        if (bookSettingService.update(bookSetting) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation(value = "新增书籍-设置列表页面", notes = "新增书籍-设置列表页面")
    @GetMapping("/add")
    @RequiresPermissions("business:setting:add")
    String add() {
        return "business/setting/add";
    }

    /**
     * 删除
     */
    @ApiOperation(value = "更新推荐列表", notes = "更新推荐列表")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("business:setting:remove")
    public R remove( Long id) {
        if (bookSettingService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

}
